def call(Map config) {
    pipeline {
  agent any
stages {
stage('Checkout'){
  steps{
/*
      checkout([$class: 'GitSCM',
            branches: [[name: env.BRANCH_NAME]],
           extensions: [[$class: 'CleanBeforeCheckout']],
                         userRemoteConfigs: [[url: env.REPO_NAME]] 
                        ])
  
      checkout([$class:'GitSCM', branches: "**"])
      */
      checkout scm
  }
}
stage('Build'){
  steps {
    echo 'building'
    sh 'npm install'
      sh 'docker build -t adilforms/the-example-app.nodejs .'
  }
}
stage('Test'){ steps {
    echo 'Testing'
  
  }
  
}
stage('Publish') {
  steps {

    echo 'publishing'
       sh 'docker push adilforms/the-example-app.nodejs:latest'
   
 
  }
}}
}

}

