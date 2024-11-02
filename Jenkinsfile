pipeline { 
    agent any 
    tools { 
        maven 'maven-3.9' 
    } 
   
    environment { 
        APP_ENV  = "DEV" 
    } 
    stages { 
        stage('Code Checkout') { 
            steps { 
                git branch: 'Rawef-Messaoudi', 
                url: 'https://github.com/aminensir/devopstestgit.git', 
                 credentialsId:  'Github Credentials' 
               
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
                 withCredentials([usernamePassword(credentialsId:'DockerHubCredentials',passwordVariable:'PASS',usernameVariable:'USER')]) {}
                 sh 'docker build -t rawef/rawefmessaoudi:jar-1.0 . '
                 sh " echo $PASS | docker login -u $USER --password-stdin"
                 sh 'docker push rawef/rawefmessaoudi:jar-1.0'
            } 
        } 
    } 
      
    }
