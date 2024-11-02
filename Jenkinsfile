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
        stage('Code Checkout') { 
            steps { 
                git branch: 'Rawef-Messaoudi', 
                url: 'https://github.com/aminensir/devopstestgit.git', 
                 credentialsId:  'Github Credentials' 
               
            } 
        } 
        stage('Code Build') { 
            steps { 
                 echo "eveything works just fine"  
            } 
        } 
    } 
      
    }
