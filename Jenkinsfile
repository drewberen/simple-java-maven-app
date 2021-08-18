
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
              sh 'mvn build-helper:parse-version versions:set \ 
                  -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \ 
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
                sh "docker build -t drewberen/my-repo:$IMAGE_NAME ."
                sh 'echo $PASS| docker login -u $USER --password-stdin'
                sh "docker push drewberen/my-repo:$IMAGE_NAME"
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
  }
}
