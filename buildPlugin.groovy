def call(Map config) {
    node {
         checkout([$class:'GitSCM', branches: "**"])
        sh 'npm install'
        
    }
}
