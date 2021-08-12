pipeline {
  
  agent any
  tools {
    maven 'Maven'
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
             withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')])
               sh 'docker build -t drewberen/my-repo:jma-2.0 .'
               sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
               sh 'docker push drewberen/my-repo:jma-2.0'
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
