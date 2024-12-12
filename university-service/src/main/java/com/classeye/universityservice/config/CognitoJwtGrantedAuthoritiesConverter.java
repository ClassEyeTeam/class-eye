package com.classeye.universityservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.classeye.studentservice.util.Constants.AUTHORITY_PREFIX;

/**
 * @author sejja
 **/
public class CognitoJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String COGNITO_GROUPS_CLAIM = "cognito:groups";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<String> groups = jwt.getClaimAsStringList(COGNITO_GROUPS_CLAIM);
        if (groups == null) {
            return List.of();
        }

        return groups.stream()
                .map(group -> new SimpleGrantedAuthority(AUTHORITY_PREFIX + group))
                .collect(Collectors.toList());
    }
}

