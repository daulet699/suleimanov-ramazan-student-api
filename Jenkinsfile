pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    environment {
        PATH = "/Users/nurasylzapsarbek/.docker/bin:${env.PATH}"
        IMAGE = 'suleimanov-ramazan-student-api:jenkins'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'docker --version'
                sh 'docker build -t "$IMAGE" .'
            }
        }

        stage('Test') {
            steps {
                sh '''
                    docker run --rm \
                      -v "$PWD:/workspace" \
                      -w /workspace \
                      gradle:8.14.3-jdk21 \
                      gradle test --no-daemon
                '''
            }
        }

        stage('Docker Verify') {
            steps {
                sh 'docker image inspect "$IMAGE"'
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: Suleimanov Ramazan CI Pipeline'
        }
        failure {
            echo 'FAILED: Check Jenkins console output'
        }
        always {
            echo 'Student: Suleimanov Ramazan'
            echo 'Group: IT2-2308'
            echo 'Student ID: 37408'
        }
    }
}
