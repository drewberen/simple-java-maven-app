/*  library identifier: 'jenkins-shared-library@jenkins-shared-library-classes', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/drewberen/jenkins-shared-library.git',
   credentialsID: 'github-userpass'
  ]
) */

/* SHARED LIBRARY + EC2 Docker deployment */
  
@Library('jenkins-shared-library@jenkins-shared-library-complete-pipeline-ec2') _

pipeline {
  
  agent any
  tools {
    maven 'maven-3.8'
  }
  environment {
    IMAGE_NAME = 'drewberen/my-repo:jma-3.0' 
  }
  stages {
    stage("build jar") {
      steps {
        script {
           echo 'building application jar.'
           buildJar()
        }
          
      }
    }
    stage("build image") {
      steps {
          script {
            echo "building docker image from ${BRANCH_NAME}."
           buildImage(env.IMAGE_NAME)
        }
      }
    }
     stage("deploy") {
      steps {   
           script {
              echo 'deploying the application...'
             def dockerCMD = "docker run -p 8080:8080 -d ${IMAGE_NAME}"
           sshagent(['ec2-credentials']) {
             sh "ssh -o StrictHostKeyChecking=no ec2-user@184.73.115.188 ${dockerCMD}"
          }
           }
      }
    }   
  }
}
