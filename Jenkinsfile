#!/usr/bin/env groovy

pipeline {
  
  agent any
  tools {
    maven 'maven-3.8'
  }
  stages {
     stage("incriment version") {
      
      steps {
        script {
              echo 'incrementing version'
              sh 'mvn build-helper:parse-version versions:set\
                 -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion}\
                 versions:commit'
              def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
              def version = matcher[0][1]
              env.IMAGE_NAME = "$version-$BUILD_NUMBER"
        }
          
      }
    }
    stage("build jar") {
      
      steps {
        script {
              echo "building the application"
              sh 'mvn clean package'
        }
          
      }
    }
    stage("build image") {
      
      steps {
          script {
              echo "building the docker image..."
              withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                sh "docker build -t drewberen/my-repo:${IMAGE_NAME} ."
                sh 'echo $PASS| docker login -u $USER --password-stdin'
                sh "docker push drewberen/my-repo:${IMAGE_NAME}"
            }

        }
      }
    } 
     
     stage("deploy") {
      
      steps {   
           script {
             echo 'deploying the application to EC2'
           }
      }
    }   
    
    stage("commit version update") {
      
      steps {
        script {
          withCredentials([usernamePassword(credentialsId: 'github-credentials-token', passwordVariable: 'PASS', usernameVariable: 'USER')]){
          sh 'git config user.email "jenkins@example.com"'
          sh 'git config user.name "jenkins"'
          sh 'git status'
          sh 'git branch'
          sh "git remote set-url origin https://${USER}:${PASS}@github.com/drewberen/simple-java-maven-app.git"
          sh 'git add .'
          sh 'git commit -m "ci: version bump"'
          sh 'git push origin HEAD:java-maven-pipeline-increment-version'
            }
        }
      }
    }
  }
}
