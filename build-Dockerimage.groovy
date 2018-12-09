def call(Map config) {
 sh '''#!/bin/bash -el
    echo 'publishing'
   
    docker build -t adilforms/the-example-app.nodejs .
         docker login --username adilforms --password Rimsha@548
    docker push adilforms/the-example-app.nodejs   
    '''
}
