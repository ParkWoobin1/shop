pipeline {
    agent any
    triggers {
            githubPush() //트리거값추가
        }
    stages {
        stage('Prepare') {
            steps {
                git credentialsId: 'github_shop',
                    branch: 'master',
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