pipeline {
  agent any

  options { timestamps(); ansiColor('xterm'); buildDiscarder(logRotator(numToKeepStr: '30')) }

  parameters {
    string(name: 'TAGS',        defaultValue: 'SMOKE', description: 'JUnit5 tags, comma-separated (e.g. SMOKE,WEB or Test_Case_5)')
    string(name: 'GRADLE_ARGS', defaultValue: '',      description: 'Optional Gradle args (e.g. --info --stacktrace)')
  }

  environment { ALLURE_RESULTS = 'build/allure-results' }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Prepare') {
      steps {
        sh 'chmod +x ./gradlew || true'
        echo "TAGS=${params.TAGS}"
      }
    }

    stage('Test') {
      steps {
        sh """
          ./gradlew clean test \\
            -Dtags="${params.TAGS}" \\
            ${params.GRADLE_ARGS}
        """
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: '**/test-results/test/*.xml, **/surefire-reports/*.xml'
          archiveArtifacts artifacts: "${env.ALLURE_RESULTS}/**", fingerprint: true, onlyIfSuccessful: false
        }
      }
    }

    stage('Allure') {
      steps {
        allure includeProperties: false, jdk: '', results: [[path: "${env.ALLURE_RESULTS}", reportBuildPolicy: 'ALWAYS']]
      }
    }
  }

  post {
    success { echo "✅ Tags: ${params.TAGS}" }
    failure { echo "❌ Tags: ${params.TAGS}" }
  }
}
