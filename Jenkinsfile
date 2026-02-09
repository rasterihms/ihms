node('master') {

    stage("Checkout sourcecode") {
        checkout scm
    }

    stage("build") {
        try {
            sh "./gradlew clean build"
        } catch(err) {
            step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/test/TEST-*.xml'])
            throw err
        }

    }
  
     stage("Build Angular ...") {
        try {
            sh './gradlew -x test clean :client:buildClientCode'
        } catch(err) {
            step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/test/TEST-*.xml'])
            throw err
        }

    }
  
    stage("Sonar Analysis ...") {
        try {
           sh './gradlew -x test sonarqube'
        } catch(err) {
            step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/test/TEST-*.xml'])
            throw err
        }
    }
 
    stage("stop") {
        sh "docker-compose down"
    }

    stage("start") {
        sh "docker-compose up -d"
    }
  
    
}