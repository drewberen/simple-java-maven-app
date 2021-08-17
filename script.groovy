
def buildJar () {
  echo "building the application ${env.BRANCH_NAME}"
  sh 'mvn package'
}

def buildImage () {
  echo "building the docker image..."
  withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
     sh 'docker build -t drewberen/my-repo:jma-2.0 .'
     sh 'echo $PASS| docker login -u $USER --password-stdin'
     sh 'docker push drewberen/my-repo:jma-2.0'
     }
}

def deployApp() {
  echo 'deploying the application...'
}

return this
