// noinspection GroovyAssignabilityCheck

pipeline {
    agent any
    stages {
        stage('Pull browser') {
            steps {
                sh('docker pull selenoid/vnc_chrome:105.0 && docker ps')
            }
        }

        stage('Run selenoid') {
            steps {
                catchError {
                    script {
                        sh('docker run -d --rm --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${PWD}/docker:/etc/selenoid:ro aerokube/selenoid:latest-release')
                    }
                }
            }
        }

        stage('Run tests') {
            steps {
                script {
                    sh('./mvnw -Dgroups="${testGroup}"')
                }
            }
        }

    }
    post {
        always {
            sh('docker ps && docker stop selenoid')
            sh('./mvnw allure:report')
            archiveArtifacts artifacts: 'target/site/allure-maven-plugin/', followSymlinks: false

            script {
                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}