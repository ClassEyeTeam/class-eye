package com.classeye.authservice.service.impl;

import com.classeye.authservice.service.impl.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@Service
@Slf4j
public class CognitoService {

    private final CognitoIdentityProviderClient cognitoClient;
    private final MailService mailService;

    @Value("${aws.cognito.user-pool-id}")
    private String userPoolId;

    @Autowired
    public CognitoService(MailService mailService,
                          @Value("${aws.access-key}") String accessKey,
                          @Value("${aws.secret-key}") String secretKey,
                          @Value("${aws.region}") String region) {
        this.mailService = mailService;
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.of(region))
                .build();
    }

    public void createUser(String email, String groupName) {
        try {
            String username = email.split("@")[0] + System.currentTimeMillis();
            String tempPassword = "TempPassword123!";

            AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .userAttributes(
                            AttributeType.builder().name("email").value(email).build(),
                            AttributeType.builder().name("email_verified").value("true").build()
                    )
                    .temporaryPassword(tempPassword)
                    .desiredDeliveryMediums(DeliveryMediumType.EMAIL)
                    .build();

            AdminCreateUserResponse createUserResponse = cognitoClient.adminCreateUser(createUserRequest);
            String userId = createUserResponse.user().username();
            System.out.println("User created: " + userId);

            mailService.sendAccountCreationEmail(email, username, tempPassword);

            AdminAddUserToGroupRequest addToGroupRequest = AdminAddUserToGroupRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .groupName(groupName)
                    .build();

            cognitoClient.adminAddUserToGroup(addToGroupRequest);
            System.out.println("User added to group: " + groupName);

        } catch (UsernameExistsException e) {
            System.out.println("User already exists. Resetting password.");
            resetPassword(email);
        } catch (CognitoIdentityProviderException e) {
            System.err.println("Error creating user: " + e.awsErrorDetails().errorMessage());
            throw e;
        }
    }

    public void resetPassword(String email) {
        try {
            String newPassword = "NewTempPassword123!";
            AdminSetUserPasswordRequest setPasswordRequest = AdminSetUserPasswordRequest.builder()
                    .userPoolId(userPoolId)
                    .username(email)
                    .password(newPassword)
                    .permanent(true)
                    .build();

            cognitoClient.adminSetUserPassword(setPasswordRequest);
            log.info("Password reset for user: " + email);
            mailService.sendPasswordResetEmail(email, newPassword);
        } catch (CognitoIdentityProviderException e) {
            log.error("Error resetting password: " + e.awsErrorDetails().errorMessage());
            throw e;
        }
    }
}
