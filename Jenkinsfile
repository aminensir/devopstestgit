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
                git branch: 'master', 
                url: 'https://github.com/aminensir/devopstestgit.git', 
                 credentialsId:  '2e52c0ba-82e3-48de-8b4f-b603e27b386c' 
               
            } 
        } 
        stage('Code Build') { 
            steps { 
                 sh 'mvn install -Dmaven.test.skip=true' 
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
