#!/usr/bin/env groovy
@Library('jenkins-shared-library')_
pipeline { 
    agent any 
    tools { 
        maven 'maven' 
    } 
   
   
    stages { 
          stage('SonarQube') { 
              steps { 
                 withSonarQubeEnv(installationName:'sonarQube'){
                      sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.host.url=http://sonarqube:9000 -Dsonar.java.binaries=target'
                }
               
             } 
          } 
         stage('Code Build') { 
            steps { 
                script{
                     buildJar() 
                }
 
            } 
        } 
     
        
         stage('Build image') { 
            steps { 
                 script{
                     buildImage()
                 }
            } 
        } 
        stage('Run Docker Compose') {
            steps {
                echo "Starting application and MySQL using Docker Compose"
                sh 'docker-compose up -d --build'

            }
        }
              stage('Push Artifact to Nexus') { 
                  steps { 
                      script{
                          nexusPush()
                      }
                  } 
              }
      
    } 
      post {
        success {
            mail to: 'rawef31@gmail.com',
                 subject: "Jenkins Pipeline Success: ${currentBuild.fullDisplayName}",
                 body: "The Jenkins pipeline for project co&co has completed successfully."
        }
        failure {
            mail to: 'rawef31@gmail.com',
                 subject: "Jenkins Pipeline Failure: ${currentBuild.fullDisplayName}",
                 body: "The Jenkins pipeline for project co&co has failed. Please check the logs for details."
        }
    }
      
    }
