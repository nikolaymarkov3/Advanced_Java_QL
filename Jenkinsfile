image: maven:latest # Образ, который будет использоваться для запуска тестов

variables:
  CI_RUN: "true"
  # MAVEN_OPTS: "-Dmaven.repo.local=./.m2/repository"
  MAVEN_OPTS: "-Dhttps.protocols=TLSv1.2 -Dmaven.repo.local=.m2/repository
              -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=WARN
              -Dorg.slf4j.simpleLogger.showDateTime=true -Djava.awt.headless=true"

  MAVEN_SETTINGS: "pom.xml"
  CHROME_DEB_PATH: "./google-chrome-stable_current_amd64.deb"


stages:
  - build
  - test
  - package
  - deploy
  - notify
build:
  stage: build
  # only:
  #   - dev
  #   - merge_requests
  #   - /^release\/.*$/
  # except:
  #   - tags
  script:
    - 'mvn --settings $MAVEN_SETTINGS compile' #передает GitLab CI файл c настройками
  cache:
    paths:
      - ./target
      - ./.m2


test:
  stage: test
  before_script:
    - apt update
    - if [ ! -f "$CHROME_DEB_PATH" ]; then wget -O "$CHROME_DEB_PATH" https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb; fi
    - dpkg -l | grep google-chrome-stable || { apt install -y "$CHROME_DEB_PATH"; apt-get install -f; }
    # - curl -fsSL https://github.com/allure-framework/allurectl/releases/latest/download/allurectl_linux_amd64 -o allurectl

  # only:
  #   - dev
  #   - merge_requests
  #   - /^release\/.*$/
  # except:
  #   - tags
  services:
    - name: selenium/standalone-chrome  # Образ для запуска тестов с Selenium
      alias: selenium

  script:
    - 'mvn --settings $MAVEN_SETTINGS test'
  cache:
    paths:
      - ./target
      - ./.m2




// pipeline {
//     agent {
//         docker {
//             image 'maven:3.9.0'
//             args '-v /root/.m2:/root/.m2'
//         }
//     }
//     stages {
//         stage('Build') {
//             steps {
//                 sh 'mvn -B -DskipTests clean package'
//             }
//         }
//     }
// }




// import jenkins.model.*
// import hudson.tools.*
// import hudson.tasks.Maven
//
// // Создаем новый экземпляр Maven
// def mavenHome = "/usr/share/maven" // Укажите путь к установленному Maven
// def mavenName = "Maven_3.8.6" // Укажите имя для Maven
//
// // Получаем дескриптор для Maven
// def descriptor1 = Jenkins.instance.getDescriptorByType(Maven.DescriptorImpl.class)
//
// // Проверяем, существует ли уже Maven с таким именем
// def existingMaven = descriptor1.getInstallations().find { it.name == mavenName }
//
// if (!existingMaven) {
//     // Создаем новый экземпляр Maven
//     def maven = new Maven(mavenName, mavenHome)
//
//     // Добавляем Maven в Jenkins
//     descriptor1.setInstallations(maven)
//     descriptor1.save()
//     println "Maven ${mavenName} добавлен."
// } else {
//     println "Maven ${mavenName} уже существует."
// }
//
// // Создаем новый экземпляр JDK
// def jdk = new JDK("JDK_17", "/usr/lib/jvm/java-17-openjdk-amd64")
//
// // Получаем дескриптор для JDK
// def descriptor = Jenkins.instance.getDescriptorByType(JDK.DescriptorImpl.class)
//
// // Добавляем JDK в Jenkins
// descriptor.addJDK(jdk)

// pipeline {
//     agent any
//
//     tools {
//         jdk 'JDK_17' // Убедитесь, что это имя совпадает с настройками
//         maven 'Maven_3.8.6' // Убедитесь, что это имя совпадает с настройками
//     }
//
//     stages {
//         stage('Git Checkout') {
//             steps {
//                 git 'https://github.com/nikolaymarkov3/Advanced_Java_QL.git'
//             }
//         }
//         stage('Compile') {
//             steps {
//                 sh "mvn compile"
//             }
//         }
//         stage('Test') {
//             steps {
//                 sh "mvn test"
//             }
//         }
//         stage('Package') {
//             steps {
//                 sh "mvn package"
//             }
//         }
//         stage('Install') {
//             steps {
//                 sh "mvn install"
//             }
//         }
//         stage('Archive Artifacts') {
//             steps {
//                 archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
//             }
//         }
//     }
// }