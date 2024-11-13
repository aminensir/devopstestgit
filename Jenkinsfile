pipeline { 
    agent any 
    tools { 
        maven 'M2_HOME' 
    } 
    
    environment { 
        APP_ENV  = "DEV" 
    } 
    stages { 
         
         
        stage('Code Build') { 
            steps { 
                 sh 'mvn package' 
            } 
        } 
        stage('SonarQube') { 
              steps { 
                 withSonarQubeEnv(installationName:'sonarQube'){
                     sh'chmod +x mvnw'
                      sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.host.url=http://localhost:9001 -Dsonar.java.binaries=target'
                }
               
             } 
          } 
    } 
       post { 
          always {                                                              
                 echo "======always======"  
          } 
          success { 
                 echo "=====pipeline executed successfully =====" 
          } 
          failure { 
                 echo "======pipeline execution failed======" 
          } 
       } 
    }
