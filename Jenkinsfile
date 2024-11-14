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
/* stage('SonarQube') { 
              steps { 
                 withSonarQubeEnv(installationName:'sonarQube'){
                     sh'chmod +x mvnw'
                      sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.java.binaries=target'
                }
               
             } 
          } */

       stage('Build image') { 
            steps { 
                 echo "Building the docker  image"
                 withCredentials([usernamePassword(credentialsId:'DockerHubCredentials',passwordVariable:'PASS',usernameVariable:'USER')]) {

                 sh 'docker build  -t hoossem7/houssemkhedhri-5se1-g3:jar-2.0 . '
                 //  sh " echo $PASS | docker login -u $USER --password-stdin"
                  // sh 'docker push hoossem7/houssemkhedhri-5se1-g3:jar-2.0'
                 }
            } 
        } 
   /*     stage('Push Artifact to Nexus') { 
                  steps { 
                      echo "Pushing artifact to Nexus"
                      withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'NEXUS_PASS', usernameVariable: 'NEXUS_USER')]) {
                           sh '''
                                  mvn deploy:deploy-file \
                                      -DgroupId=com.example \
                                      -DartifactId=testEDITIONs \
                                       -Dversion=0.0.1 \
                                      -Dpackaging=jar \
                                      -Dfile=target/testEDITIONs-0.0.1-SNAPSHOT.jar \
                                      -DrepositoryId=maven-releases \
                                      -Durl=http://192.168.13.128:8081/repository/maven-releases/ \
                                      -Dusername=$NEXUS_USER \
                                      -Dpassword=$NEXUS_PASS
                              '''
                      }
                  } 
              } */
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
