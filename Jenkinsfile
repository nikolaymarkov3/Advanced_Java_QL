pipeline {
    agent any
    tools {
        jdk 'jdk17' // Убедитесь, что это имя совпадает с настройками
        maven 'maven3' // Убедитесь, что это имя совпадает с настройками
    }
    stages {
        stage('Git Checkout') {
            steps {
                git 'https://github.com/nikolaymarkov3/Advanced_Java_QL.git'
            }
        }
        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('Package') {
            steps {
                sh "mvn package"
            }
        }
        stage('Install') {
            steps {
                sh "mvn install"
            }
        }
        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}