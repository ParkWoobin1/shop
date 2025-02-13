pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        DEPLOY_SERVER = "ec2-user@15.168.76.246"  // 배포할 원격 서버
        DEPLOY_DIR = "/home/user/app"  // JARr 파일이 배포될 디렉터리
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'github', url: 'https://github.com/ParkWoobin1/shop.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'  // Maven 빌드 수행
                script {
                    env.APP_NAME = sh(script: "ls -t target/*.jar | head -n 1", returnStdout: true).trim()
                    echo "✔ Detected JAR File: ${env.APP_NAME}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo " Deploying ${env.APP_NAME} to ${DEPLOY_SERVER}:${DEPLOY_DIR}"
                    sh """
                    scp ${env.APP_NAME} ${DEPLOY_SERVER}:${DEPLOY_DIR}/
                    ssh ${DEPLOY_SERVER} "nohup java -jar ${DEPLOY_DIR}/$(basename ${env.APP_NAME}) > /dev/null 2>&1 &"
                    """
                }
            }
        }
    }

    post {
        success {
            echo " Deployment successful!"
        }
        failure {
            echo " Deployment failed!"
        }
    }
}