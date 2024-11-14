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
        stage('Commit version update ') { 
                  steps { 
                      script{
                            withCredentials([usernamePassword(credentialsId:'DockerHubCredentials',passwordVariable:'PASS',usernameVariable:'USER')]) {
                                sh 'git config --global user.email "jenkins@exemple.com" '
                                sh 'git config --global user.name "jenkins" '
                                sh "git remove set-url origin https://${USER}:${PASS}@github.com/aminensir/devopstestgit.git"
                                sh 'git add .'
                                sh 'git commit -m "version update"'
                                sh'git push origin HEAD:Rawef-Messaoudi-5se1-G3'
                                
                                
                            }

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
