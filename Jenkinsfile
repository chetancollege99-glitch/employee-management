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
    }

    post {
        success {
            echo 'CI pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed. Docker deployment will not run.'
        }
    }
}