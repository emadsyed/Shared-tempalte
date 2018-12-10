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
   sh 'npm install'
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

    echo 'publishing'
       sh 'docker push adilforms/the-example-app.nodejs:latest'
   
 
  }
}}
}

}

