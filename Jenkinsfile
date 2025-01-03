pipeline {
    agent any
    stages {
        stage('Prepare') {
            steps {
                git credentialsId: 'github_shop',
                    branch: 'main', // 올바른 브랜치 이름 사용
                    url: 'https://github.com/ParkWoobin1/shop.git' // 올바른 저장소 URL 사용
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