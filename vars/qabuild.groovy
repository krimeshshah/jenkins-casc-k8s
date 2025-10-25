def buildAndPushImage(Map args) {
    def repoUrl         = args.repoUrl
    def branch          = args.branch ?: 'main'
    def awsRegion       = args.awsRegion ?: 'us-east-1'
    def ecrRepo         = args.ecrRepo
    def awsCredentialsId = args.awsCredentialsId
    def imageTag        = args.imageTag ?: "latest"

    echo "Starting build for ${repoUrl} (${branch})"

    // Checkout source
    git branch: branch, url: repoUrl

    // Build Docker image
    def dockerImage = docker.build("${ecrRepo}:${imageTag}")

    // Push to ECR
    withAWS(region: awsRegion, credentials: awsCredentialsId) {
        sh """
            aws ecr get-login-password --region ${awsRegion} \
            | docker login --username AWS --password-stdin ${ecrRepo.split('/')[0]}
        """
        dockerImage.push()
        dockerImage.push('latest')
    }

    echo "Image pushed: ${ecrRepo}:${imageTag}"
}
