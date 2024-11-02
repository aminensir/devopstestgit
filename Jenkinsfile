pipeline { 
    agent any 
    tools { 
        maven 'maven' 
    } 
   
   
    stages { 
        stage('SonarQube') { 
            steps { 
               withSonarQubeEnv(installationName:'sonarQube'){
                sh './mvnw clean sonar:sonar'
               }
               
            } 
        } 
        stage('Code Build') { 
            steps { 
                 echo "Building the application "
                 sh 'mvn package '  
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
