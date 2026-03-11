pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
        }
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Obteniendo código fuente...'
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Compilando y ejecutando pruebas...'
                sh 'java -version'
                sh 'mvn -version'
                sh 'mvn clean package'
            }
        }

        stage('Deploy Container') {
            steps {
                echo 'Desplegando app...'
                sh '''
                docker stop mi-app || true
                docker rm mi-app || true
                docker run -d -p 9090:9090 --name mi-app mi-app:latest
                '''
            }
        }
    }

    post {

        success {
            echo 'Pipeline completado con éxito'
        }

        failure {
            echo 'El pipeline ha fallado. Revisa los logs.'
        }

        always {
            echo 'Archivando artefactos...'
            archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
        }
    }
}