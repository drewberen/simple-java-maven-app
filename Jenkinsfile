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
           echo "test"
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
