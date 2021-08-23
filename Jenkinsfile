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
           def dockerCMD = 'docker run -dit --name my-apache-app -p 8080:80 -v "$PWD":/usr/local/apache2/htdocs/ httpd:2.4'
           sshagent(['ec2-credentials']) {
             sh "ssh -o StrictHostKeyChecking=no ec2-user@34.207.163.164 ${dockerCMD}"
          }
        }
      }
    }   
  }
}
