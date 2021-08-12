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
