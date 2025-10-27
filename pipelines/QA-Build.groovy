@Library('my-shared-library@feature/ks/shared-library') _

pipline{
    agent(label "k8s-agent")
    stages {
        stage("hellowrld-test") {
            step {
                helloWorld()
            }
        }
    }
}

// pipeline {
//     agent any

//     environment {
//         REPO_URL         = 'https://github.com/krimeshshah/python-flaskapp.git'
//         BRANCH           = 'develop'
//         AWS_REGION       = 'us-east-1'
//         ECR_REPO         = '806153319059.dkr.ecr.us-east-1.amazonaws.com/flaskapp'
//         AWS_CRED_ID      = 'aws-ecr-jenkins-creds'
//         IMAGE_TAG        = "build-${env.BUILD_NUMBER}"
//     }

//     stages {
//         stage('Build and Push to ECR') {
//             steps {
//                 script {
//                     qabuild.buildAndPushImage([
//                         repoUrl: REPO_URL,
//                         branch: BRANCH,
//                         awsRegion: AWS_REGION,
//                         ecrRepo: ECR_REPO,
//                         awsCredentialsId: AWS_CRED_ID,
//                         imageTag: IMAGE_TAG
//                     ])
//                 }
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
