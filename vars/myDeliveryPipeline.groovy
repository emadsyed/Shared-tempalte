def call(Map config) {
    pipeline {
        agent {
            docker {
                image 'node:7'
            }
        }
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
      sh '''#!/bin/bash -el
   WORKDIR /app
COPY package.json /app
RUN npm install
copy . /app
CMD node myapp.js
EXPOSE 3009
      '''
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

