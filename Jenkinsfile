library identifier: 'jenkins-shared-library@jenkins-shared-library-classes', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/drewberen/jenkins-shared-library.git',
   credentialsID: 'github-userpass'
  ]
)
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
           buildJar()
        }
          
      }
    }
    stage("build image") {
      
      steps {
          script {
           buildImage 'drewberen/my-repo:jma-3.0'

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
