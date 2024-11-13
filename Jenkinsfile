pipeline { 
    agent any 
    tools { 
        maven 'M2_HOME' 
    } 
    options { 

        timeout(time: 1, unit: 'SECONDS') 
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
                      sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.host.url=http://sonarqube:9000 -Dsonar.java.binaries=target'
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
