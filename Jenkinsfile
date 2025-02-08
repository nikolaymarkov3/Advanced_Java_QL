import jenkins.model.*
import hudson.tools.*
import hudson.tasks.Maven

// Создаем новый экземпляр Maven
def mavenHome = "/usr/share/maven" // Укажите путь к установленному Maven
def mavenName = "Maven_3.8.6" // Укажите имя для Maven

// Получаем дескриптор для Maven
def descriptor1 = Jenkins.instance.getDescriptorByType(Maven.DescriptorImpl.class)

// Проверяем, существует ли уже Maven с таким именем
def existingMaven = descriptor1.getInstallations().find { it.name == mavenName }

if (!existingMaven) {
    // Создаем новый экземпляр Maven
    def maven = new Maven(mavenName, mavenHome)

    // Добавляем Maven в Jenkins
    descriptor1.setInstallations(maven)
    descriptor1.save()
    println "Maven ${mavenName} добавлен."
} else {
    println "Maven ${mavenName} уже существует."
}

// Создаем новый экземпляр JDK
def jdk = new JDK("JDK_17", "/usr/lib/jvm/java-17-openjdk-amd64")

// Получаем дескриптор для JDK
def descriptor = Jenkins.instance.getDescriptorByType(JDK.DescriptorImpl.class)

// Добавляем JDK в Jenkins
descriptor.addJDK(jdk)

pipeline {
    agent any
    stages {
            stage('Install Maven') {
                steps {
                    script {
                        // Убедитесь, что Maven установлен через плагин
                        def mavenHome = tool name: 'Maven_3.8.6', type: 'maven'
                        env.PATH = "${mavenHome}/bin:${env.PATH}"
                    }
                }
            }

//     tools {
//     def jdkInstaller = new InstallSourceProperty([new InstallSourceProperty.Sources.JDKInstaller()])
//
//         def jdk = new JDK("JDK_17", "/usr/lib/jvm/java-17-openjdk-amd64", [jdkInstaller])
//         def descriptor = Jenkins.instance.getDescriptorByType(JDK.DescriptorImpl.class)
//     descriptor.addJDK(jdk)
//         jdk 'jdk17' // Убедитесь, что это имя совпадает с настройками
//         maven 'maven3' // Убедитесь, что это имя совпадает с настройками
//     }
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