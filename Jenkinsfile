pipeline {
  
  agent any
  
  stages {
    
    stage("build") {
      
      steps {    
           echo 'building the application...'
      }
    }
      
    stage("test") {
      
      steps {
           echo 'testing the application...'
      }
    }
      
     stage("deploy") {
      
      steps {    
           echo 'deploying the application...'
      }
    }   
  }
}

--------------------------------------

With parameters 

pipeline {
  
  agent any
  parameters {
      choice(name: ‘VERSION’, choices: [‘one’, ‘two’, ‘three’], description: ‘’)
      booleanParam(name: ‘executeTests’, defaultValue: ‘true’, description: ‘’)
  }
  
  stages {
    
    stage("build") {
      
      steps {    
           echo 'building the application...'
      }
    }
      
    stage("test") {
      when {
          expression     {
              params.executeTests 
      }

      }
      steps {
           echo 'testing the application...'
      }
    }
      
     stage("deploy") {
      
      steps {    
           echo 'deploying the application...'
           echo "deploying version ${params.VERSION}"
      }
      }
    }   
}



-------------------------

with external groovy script

  
def gv
pipeline {
  
  agent any
  parameters {
      choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
      booleanParam(name: 'executeTests', defaultValue: 'true', description: '')
  }
  
  stages {
    
   stage("init") {
      
      steps {
          script {
            gv = load "script.groovy"
        }  
      }
    }
    
    stage("build") {
      
      steps {    
           script {
              gv.buildApp()
           }
      }
    }
      
    stage("test") {
      when {
          expression     {
              params.executeTests 
      }

      }
      steps {
           script {
              gv.testApp()
           }
      }
    }
      
     stage("deploy") {
      
      steps {    
           script {
              gv.deployApp()
           }
      }
      }
    }   
}




script.groovy


def buildApp () {
  echo 'building the application...'
}

def testApp () {
  echo 'testing the application...'
  
}

def deployApp () {
   echo 'deploying the application...'
   echo "deploying version ${params.VERSION}"
}


return this

-------------


Input parameters in Jenkins
-input parameter for user input


def gv
pipeline {
  
  agent any
  parameters {
      choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
      booleanParam(name: 'executeTests', defaultValue: 'true', description: '')
  }
  
  stages {
    
   stage("init") {
      
      steps {
          script {
            gv = load "script.groovy"
        }  
      }
    }
    
    stage("build") {
      
      steps {    
           script {
              gv.buildApp()
           }
      }
    }
      
    stage("test") {
      when {
          expression     {
              params.executeTests 
      }

      }
      steps {
           script {
              gv.testApp()
           }
      }
    }
      
     stage("deploy") {
       input {
         message "Select the environment to deploy to"
         ok "Done"
         parameters {
           choice(name: 'ENV', choices: ['dev', 'staging', 'prod'], description: '')
         }
         
       }
       
       steps {    
           script {
              gv.deployApp()
             echo "Deploying to ${ENV}"
           }
      }
      }
    }   
}



script.groovy


def buildApp () {
  echo 'building the application...'
}

def testApp () {
  echo 'testing the application...'
  
}

def deployApp () {
   echo 'deploying the application...'
   echo "deploying version ${params.VERSION}"
}

-------

Input parameters in Jenkins (Assign to environmental variable)
-input parameter for user input

def gv
pipeline {
  
  agent any
  parameters {
      choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
      booleanParam(name: 'executeTests', defaultValue: 'true', description: '')
  }
  
  stages {
    
   stage("init") {
      
      steps {
          script {
            gv = load "script.groovy"
        }  
      }
    }
    
    stage("build") {
      
      steps {    
           script {
              gv.buildApp()
           }
      }
    }
      
    stage("test") {
      when {
          expression     {
              params.executeTests 
      }

      }
      steps {
           script {
              gv.testApp()
           }
      }
    }
      
     stage("deploy") {
       steps {    
           script {
              env.ENV = input message: "Select the environment to deploy to", ok: "Done", parameters: [choice(name: 'environment', choices: ['dev', 'staging', 'prod'], description: '')]
              gv.deployApp()
              echo "Deploying to ${ENV}"
           }
      }
      }
    }   
}

script.groovy


def buildApp () {
  echo 'building the application...'
}

def testApp () {
  echo 'testing the application...'
  
}

def deployApp () {
   echo 'deploying the application...'
   echo "deploying version ${params.VERSION}"
}


https://stackoverflow.com/questions/47080683/read-interactive-input-in-jenkins-pipeline-to-a-variable
https://stackoverflow.com/questions/42501553/jenkins-declarative-pipeline-how-to-read-choice-from-input-step

----------------

Build/Build docker Image pipeline w/out groovy


pipeline {
  
  agent any
  tools {
    maven 'maven-3.8'
  }
  stages {
    stage("build jar") {
      
      steps {
        script {
             echo "building the application..."
             sh 'mvn package'
        }
          
      }
    }
    stage("build image") {
      
      steps {
          script {
             echo "building the docker image..."
             withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
               sh 'docker build -t drewberen/my-repo:jma-2.0 .'
               sh 'echo $PASS| docker login -u $USER --password-stdin'
               sh 'docker push drewberen/my-repo:jma-2.0'
             }
        }
      }
    }
      
     stage("deploy") {
      
      steps {    
           echo 'deploying the application...'
      }
    }   
  }
}

Build/Build docker Image pipeline w/ groovy

def gv
pipeline {
  
  agent any
  tools {
    maven 'maven-3.8'
  }
  stages {
     stage("initialize") {
      
      steps {
        script {
           gv = load "script.groovy"
        }
          
      }
    }
    stage("build jar") {
      
      steps {
        script {
            gv.buildJar()
        }
          
      }
    }
    stage("build image") {
      
      steps {
          script {
            gv.buildImage()

        }
      }
    }
      
     stage("deploy") {
      
      steps {   
           script {
            gv.deployApp()
           }
      }
    }   
  }
}


