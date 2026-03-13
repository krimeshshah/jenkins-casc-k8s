@Library('my-shared-library@feature/ks/flaskapp-build') _

// pipeline {
//     agent {label "k8s-agent"}
//     stages {
//         stage("hellowrld-test") {
//             steps {
//                 helloWorld()
//             }
//         }
//     }
// }

pipeline {
    agent {
        kubernetes {
            label 'kaniko'
        }
    }

    stages {
        stage('Build') {
            steps {
                kanikoBuild(
                    image: 'myrepo/flask',
                    tag: env.BUILD_NUMBER
                )
            }
        }
    }
}


// pipeline {
//     agent { label 'k8s-agent' }

//     parameters {
//         string(name: 'IMAGE_TAG', defaultValue: '', description: 'Optional: Provide semantic version (e.g., 1.0.0). Leave empty for auto short SHA tag.')
//         string(name: 'BRANCH', defaultValue: '', description: 'Optional: Provide Source Code Branch to be built')
//     }

//     environment {
//         REPO_URL    = 'https://github.com/krimeshshah/python-flaskapp.git'
//         AWS_REGION  = 'us-east-1'
//         ECR_REPO    = '806153319059.dkr.ecr.us-east-1.amazonaws.com/pyproject/flaskapp'
//         AWS_CRED_ID = 'aws-ecr-jenkins-creds'
//      }

//     stages {
//         stage('Build and Push to ECR') {
//             steps {
//                 // Directly call shared library step
//                 qaBuild(
//                     repoUrl: REPO_URL,
//                     branch: params.BRANCH,
//                     awsRegion: AWS_REGION,
//                     ecrRepo: ECR_REPO,
//                     awsCredentialsId: AWS_CRED_ID,
//                     imageTag: params.IMAGE_TAG
//                 )
//             }
//         }
//     }

//     post {
//         success {
//             echo "✅ QA build completed successfully for ${ECR_REPO}:${IMAGE_TAG}"
//         }
//         failure {
//             echo "❌ QA build failed!"
//         }
//     }
// }
