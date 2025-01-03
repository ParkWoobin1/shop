pipeline {
    agent any
    stages {
        stage('Prepare'){
            steps {
                git credentialsId : '{github_shop}',
                    branch : '{https://aea1-121-134-77-227.ngrok-free.app/github-webhook}',
                    url : 'https://github.com/ParkWoobin1/shop.git'
            }
        }
        stage('test') {
            steps {
                echo 'test stage'
            }
        }
        stage('build') {
            steps {
                echo 'build stage'
            }
        }

    }
}