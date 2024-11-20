pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/MohammedSadok/ClassEye'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build and Test succeeded!'
        }
        failure {
            echo 'Build or Test failed!'
        }
    }
}
