#!/usr/bin/env groovy

pipeline {
  
  agent any
  
  stages {
    
      stage("test") {
      
      steps {
           echo 'testing the application...'
           echo "executing pipeline from $BRANCH_NAME"
      }
    }
    
    stage("build") {
      steps {
        script {
           echo 'building the application...'
        }
      }
    }
      
    stage("deploy") {
      steps {
        script {
           echo 'deploying the application...'
           def dockerCMD = 'docker run -it -p 8080:8080 --rm tomcat:9.0'
           sshagent(['ec2-credentials']) {
             sh "ssh -o StrictHostKeyChecking=no ec2-user@34.207.163.164 ${dockerCMD}"
          }
        }
      }
    }   
  }
}
