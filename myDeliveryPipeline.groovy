
#!/usr/bin/env groovy
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
  */
      checkout([$class:'GitSCM', branches: "**"])
  }
}
stage('Build'){
  steps {
    echo 'building'
    sh 'npm install'
       docker build -t adilforms/the-example-app.nodejs .
  }
}
stage('Test'){ steps {
    echo 'Testing'
    sh "git rev-parse HEAD > .git/commit-id"
        def commit_id = readFile('.git/commit-id').trim()
        println commit_id
  
  }
  
}
stage('Publish') {
  steps {
    
    sh '''#!/bin/bash -el
    echo 'publishing'
   
    docker build -t adilforms/the-example-app.nodejs .
         docker login --username adilforms --password Rimsha@548
    docker push adilforms/the-example-app.nodejs   
    '''
  }
}}
}

}

