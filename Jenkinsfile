// 1) bootstrap: сразу объявим параметры на уровне job'a
properties([
  parameters([
    string(name: 'TAGS', defaultValue: 'SMOKE', description: 'JUnit5 tags, comma-separated'),
    string(name: 'GRADLE_ARGS', defaultValue: '', description: 'Optional Gradle args (e.g. --info)')
  ])
])

pipeline {
  agent any

  options {
    timestamps()
    ansiColor('xterm')
    buildDiscarder(logRotator(numToKeepStr: '30'))
  }

  parameters {
    string(name: 'TAGS', defaultValue: 'SMOKE', description: 'JUnit5 tags, comma-separated')
    string(name: 'GRADLE_ARGS', defaultValue: '', description: 'Optional Gradle args (e.g. --info)')
  }

  stages {
    stage('Echo only') {
      steps {
        echo "TAGS=${params.TAGS}"
      }
    }
  }
}
