import jenkins.model.*
import hudson.tools.*
import hudson.tasks.Maven

// ������� ����� ��������� Maven
def mavenHome = "/usr/share/maven" // ������� ���� � �������������� Maven
def mavenName = "Maven_3.8.6" // ������� ��� ��� Maven

// �������� ���������� ��� Maven
def descriptor1 = Jenkins.instance.getDescriptorByType(Maven.DescriptorImpl.class)

// ���������, ���������� �� ��� Maven � ����� ������
def existingMaven = descriptor1.getInstallations().find { it.name == mavenName }

if (!existingMaven) {
    // ������� ����� ��������� Maven
    def maven = new Maven(mavenName, mavenHome)

    // ��������� Maven � Jenkins
    descriptor1.setInstallations(maven)
    descriptor1.save()
    println "Maven ${mavenName} ��������."
} else {
    println "Maven ${mavenName} ��� ����������."
}

// ������� ����� ��������� JDK
def jdk = new JDK("JDK_17", "/usr/lib/jvm/java-17-openjdk-amd64")

// �������� ���������� ��� JDK
def descriptor = Jenkins.instance.getDescriptorByType(JDK.DescriptorImpl.class)

// ��������� JDK � Jenkins
descriptor.addJDK(jdk)

pipeline {
    agent any

    tools {
        jdk 'JDK_17' // ���������, ��� ��� ��� ��������� � �����������
        maven 'Maven_3.8.6' // ���������, ��� ��� ��� ��������� � �����������
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