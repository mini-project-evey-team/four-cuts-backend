pipeline {
    agent any

    environment {
        registry = "ironprayer1208/four-cut"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }

    stages {
        stage('Prepare') {
            steps {
                deleteDir()
                withCredentials([GitUsernamePassword(credentialsId: 'github', gitToolName: 'Default')]) {
                    sh '''
                    git clone --recursive https://github.com/mini-project-evey-team/four-cuts-backend.git
                    '''
                }
            }
        }

        stage('Bulid Gradle') {
          steps {
            echo 'Bulid Gradle'
            dir('./four-cuts-backend'){
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }
        
        stage('Bulid Docker') {
          steps {
            echo 'Bulid Docker'
            dir('./four-cuts-backend'){
            script {
                dockerImage = docker.build registry
            }
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

        stage('Push Docker') {
          steps {
            echo 'Push Docker'
            dir('./four-cuts-backend'){
            script {
                docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
                    dockerImage.push()
                }
            }
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }
        
        stage('Docker Run') {
            steps {
                echo 'Pull Docker Image & Docker Image Run'
                dir('./four-cuts-backend'){
                sshagent (credentials: ['ssh']) {
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@3.35.216.160  'docker pull ironprayer1208/four-cut'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@3.35.216.160 'docker ps -q --filter 'name=four-cut' | grep -q . && docker stop four-cut && docker rm -fv four-cut || [ \$? = 1 ]'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@3.35.216.160 'docker run -d --name four-cut -p 8080:8080 ironprayer1208/four-cut'"
                }
                }
            }
        }
    }
}
