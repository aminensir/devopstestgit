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
