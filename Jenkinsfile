pipeline {
    agent none

    stages {
        stage('Build and Jib') {
            agent {
                label 'java11' // Use an agent with the "java11" label
            }
            steps {
                // Build project including tests & analytics
                sh './gradlew build'

                // Nexus auth for jib, build.gradle is pointed explicitly to the Nexus env vars to find them
                withCredentials([usernamePassword(credentialsId: 'nexus-cred', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD' )]){
                    // Build & publish image with a valid credential set
                    sh './gradlew --console=plain jib' // Jib outputs some gibberish progress logging we skip with "plain"
                }

                // GCR alternative to create and publish a Docker image using Jib with GCR auth present (magic env var)
                //withCredentials([file(credentialsId: 'gcr-service-user-proto-client-ttf', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                //    sh './gradlew --console=plain jib' // Jib outputs some gibberish progress logging we skip with "plain"
                //}

                // Customize Kubernetes manifests for deployment
                sh './gradlew prepareCD'

                // Stash the modified 'k8s' directory so we can use it in the next stage
                stash includes: 'k8s/**', name: 'k8s-stash'
            }
        }

        
        stage('SonarQube analysis') {
            agent {
                label 'java11' // Use an agent with the "java11" label
            }
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh './gradlew sonarqube -Dsonar.token=$SONAR_TOKEN'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            agent {
                label 'kubectl' // Use an agent with the "kubectl" label
            }
            steps {
                // On the second agent, unstash the 'k8s' directory
                unstash 'k8s-stash'
                sh 'ls -la k8s'

                // Deploy to Kubernetes
                withKubeConfig(clusterName: 'ttf-cluster', contextName: 'jenkins-k8s', credentialsId: '1c00907c-98ab-4c55-bd44-7afc075d4ac8', namespace: '', restrictKubeConfigAccess: false, serverUrl: 'https://kubernetes.default') {
                    sh 'kubectl get ns'
                    //sh 'kubectl create ns jibby' // Add rerun safe logic / dynamic namespace pick
                    sh 'kubectl apply -f k8s/ -n jibby'
                }
            }
        }
    }
}
