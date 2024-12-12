pipeline {
    agent any

    environment {
        SERVICES = 'admin-server discovery-service gateway-service config-service university-service'
    }

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'https://github.com/ClassEyeTeam/class-eye',
                    branch: 'main'
                )
            }
        }
        stage('Build Services') {
            steps {
                script {
                    def services = SERVICES.split(' ')
                    for (service in services) {
                        dir("${service}") {
                            echo "Building ${service}..."
                            sh 'mvn clean install'
                        }
                    }
                }
            }
        }
        stage('Test Services') {
            steps {
                script {
                    def services = SERVICES.split(' ')
                    for (service in services) {
                        dir("${service}") {
                            echo "Testing ${service}..."
                            sh 'mvn test'
                        }
                    }
                }
            }
        }
        stage('Package Services') {
            steps {
                script {
                    def services = SERVICES.split(' ')
                    for (service in services) {
                        dir("${service}") {
                            echo "Packaging ${service}..."
                            sh 'mvn package'
                        }
                    }
                }
            }
        }
        stage('Docker Build and Deploy') {
            steps {
                echo "Building and deploying all services using Docker Compose..."
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
