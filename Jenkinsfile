// CI-конвеєр для проєкту ai-slop (система керування студентським розкладом)
pipeline {
    agent any

    // Запуск збірки за подією push з GitHub (webhook)
    triggers {
        githubPush()
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {
        stage('Prepare') {
            steps {
                // У git файл gradlew збережено без прав на виконання
                sh 'chmod +x gradlew'
                sh './gradlew --version'
                sh 'git log -1 --pretty=format:"%h %an: %s"'
            }
        }

        stage('Lint') {
            steps {
                // Checkstyle: перевірка стилю основного коду і тестів
                sh './gradlew checkstyleMain checkstyleTest --no-daemon'
            }
        }

        stage('Unit tests') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    // Публікація результатів JUnit: графік і список тестів у Jenkins
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Build') {
            steps {
                sh './gradlew jar -x test --no-daemon'
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            // HTML-звіти лінтера та тестів доступні в артефактах збірки
            archiveArtifacts artifacts: 'build/reports/**', allowEmptyArchive: true
        }
        success {
            echo 'Збірка успішна: лінтер і всі тести пройдено'
        }
        failure {
            echo 'Збірка впала: дивіться стадію з помилкою'
        }
    }
}
