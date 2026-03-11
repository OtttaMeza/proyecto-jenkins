pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Obteniendo código fuente...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilando el proyecto...'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias y de integración...'
                sh 'mvn test'
            }
        }

        stage('Deploy Simulation') {
            steps {
                echo 'Simulando despliegue de la aplicación...'
                echo 'Copiando archivo JAR a carpeta de distribución...'
                sh 'mkdir -p dist'
                sh 'cp target/*.jar dist/'
                echo 'Despliegue simulado con éxito.'
            }
        }
    }

    post {
        always {
            echo 'Limpiando el espacio de trabajo...'
        }
        success {
            echo '¡Pipeline completado con éxito!'
        }
        failure {
            echo 'El pipeline ha fallado. Revisa los logs.'
        }
    }
}