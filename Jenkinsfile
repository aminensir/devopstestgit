pipeline { 
    agent any 
    tools { 
        maven 'maven' 
    } 
   
     
   
    stages { 
        // stage('SonarQube') { 
        //     steps { 
        //        withSonarQubeEnv(installationName:'sonarQube'){
        //             sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.host.url=http://sonarqube:9000 -Dsonar.java.binaries=target'
        //        }
               
        //     } 
        // } 
         stage('Code Build') { 
            steps { 
                 echo "Building the application "
                 sh 'mvn clean package ' 
 
            } 
        } 
     
        
         stage('Build image') { 
            steps { 
                 echo "Building the docker  image"
                 withCredentials([usernamePassword(credentialsId:'DockerHubCredentials',passwordVariable:'PASS',usernameVariable:'USER')]) {

                 sh 'docker build  -t rawef/rawefmessaoudi:jar-2.0 . '
                 sh " echo $PASS | docker login -u $USER --password-stdin"
                 sh 'docker push rawef/rawefmessaoudi:jar-1.0'
                 }
            } 
        } 
        stage('Run Docker Compose') {
            steps {
                echo "Starting application and MySQL using Docker Compose"
                sh 'docker-compose down --remove-orphans'
                sh 'docker-compose up -d --build'

            }
        }
            // stage('Push Artifact to Nexus') { 
            //     steps { 
            //         echo "Pushing artifact to Nexus"
            //         withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'NEXUS_PASS', usernameVariable: 'NEXUS_USER')]) {
            //              sh '''
            //                     mvn deploy:deploy-file \
            //                         -DgroupId=com.example \
            //                         -DartifactId=testEDITIONs \
            //                          -Dversion=0.0.1 \
            //                         -Dpackaging=jar \
            //                         -Dfile=target/testEDITIONs-0.0.1-SNAPSHOT.jar \
            //                         -DrepositoryId=maven-releases \
            //                         -Durl=http://nexus:8081/repository/maven-releases/ \
            //                         -Dusername=$NEXUS_USER \
            //                         -Dpassword=$NEXUS_PASS
            //                 '''
            //         }
            //     } 
            // }
      
    } 
      
    }
