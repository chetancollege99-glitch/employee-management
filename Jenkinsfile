pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Test') {
            steps {
                sh './mvnw clean test'
            }
        }

        stage('Maven Package') {
            steps {
                sh './mvnw package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t employee-management:latest .'
            }
        }

        stage('Stop Previous Container') {
            steps {
                sh 'docker stop employee-management || true'
                sh 'docker rm employee-management || true'
            }
        }

        stage('Deploy Docker Container') {
            steps {
                sh 'docker run -d --name employee-management -p 8081:8080 employee-management:latest'
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed. Docker deployment will not run.'
        }
    }
}