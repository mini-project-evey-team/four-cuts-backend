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
            echo 'Clonning Repository'
            git url: 'git@github.com:mini-project-evey-team/four-cuts-backend.git',
              branch: 'main',
              credentialsId: 'github'
            }
            post {
             success { 
              echo 'Successfully Cloned Repository'
             }
          	 failure {
              error 'This pipeline stops here...'
             }
          }
        }

        stage('Bulid Gradle') {
          steps {
            echo 'Bulid Gradle'
            dir('.'){
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
            script {
                dockerImage = docker.build registry
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
            script {
                docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
                    dockerImage.push()
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
                sshagent (credentials: ['ssh']) {
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@3.35.216.160  'docker pull ironprayer1208/four-cut'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@3.35.216.160 'docker ps -q --filter 'name=four-cut' | grep -q . && docker stop four-cut && docker rm -fv four-cut || [ \$? = 1 ]'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@3.35.216.160 'docker run -d --name four-cut -p 8080:8080 ironprayer1208/four-cut'"
                }
            }
        }
    }
}
