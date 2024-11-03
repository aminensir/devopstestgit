pipeline { 
    agent any 
    tools { 
        maven 'maven' 
    } 
   
   
    stages { 
         stage('Code Build') { 
            steps { 
                 echo "Building the application "
                 sh 'mvn package ' 
                 sh 'ls -R target || echo "No target directory found"' 
 
            } 
        } 
        
        stage('SonarQube') { 
            steps { 
               withSonarQubeEnv(installationName:'sonarQube'){
                    sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.host.url=http://sonarqube:9000 -Dsonar.java.binaries=target'
               }
               
            } 
        } 
         stage('Build image') { 
            steps { 
                 echo "Building the docker  image"
                 withCredentials([usernamePassword(credentialsId:'DockerHubCredentials',passwordVariable:'PASS',usernameVariable:'USER')]) {
                 sh 'docker build -t rawef/rawefmessaoudi:jar-1.0 . '
                 sh " echo $PASS | docker login -u $USER --password-stdin"
                 sh 'docker push rawef/rawefmessaoudi:jar-1.0'
                 }
            } 
        } 
    } 
      
    }
