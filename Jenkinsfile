pipeline {
    agent any
    stages {
        stage('Prepare') {
            steps {
                git credentialsId: 'github_shop',
                    branch: 'main',
                    url: 'https://github.com/ParkWoobin1/shop.git'
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