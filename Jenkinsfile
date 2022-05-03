pipeline {
    agent { docker { image 'ovsukmaksim/docker-java:latest' } }
    stages {
        stage('check java') {
            steps {
                sh 'java -version'
            }
        }
        stage('check docker') {
                    steps {
                        sh 'docker-compose --version'
                    }
                }

        stage('check docker-compose config') {
                    steps {
                        sh 'cd docker && IPADDRESS=$(ip -o route get to 8.8.8.8 | sed -n 's/.*src \([0-9.]\+\).*/\1/p') && echo "IPADDRESS=$IPADDRESS" > .env && cat .env && docker-compose config'
                    }
        }
    }
}