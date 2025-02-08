import jenkins.model.*
import hudson.tools.*

// ������� ����� ��������� JDK
def jdk = new JDK("JDK_17", "/usr/lib/jvm/java-17-openjdk-amd64")

// �������� ���������� ��� JDK
def descriptor = Jenkins.instance.getDescriptorByType(JDK.DescriptorImpl.class)

// ��������� JDK � Jenkins
descriptor.addJDK(jdk)



pipeline {
    agent any

    tools {
//     def jdkInstaller = new InstallSourceProperty([new InstallSourceProperty.Sources.JDKInstaller()])
//
//         def jdk = new JDK("JDK_17", "/usr/lib/jvm/java-17-openjdk-amd64", [jdkInstaller])
//         def descriptor = Jenkins.instance.getDescriptorByType(JDK.DescriptorImpl.class)
//     descriptor.addJDK(jdk)
        jdk 'jdk17' // ���������, ��� ��� ��� ��������� � �����������
        maven 'maven3' // ���������, ��� ��� ��� ��������� � �����������
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