
def call(Map args) {
    def repoUrl         = args.repoUrl
    def branch          = args.branch ?: 'master'
    def awsRegion       = args.awsRegion ?: 'us-east-1'
    def ecrRepo         = args.ecrRepo
    def awsCredentialsId = args.awsCredentialsId
    def imageTag        = args.imageTag
    
    if (!imageTag) {
        echo "No imageTag provided. Fetching latest short SHA from repo..."
        imageTag = utils.getLatestCommitShortSha(repoUrl, branch)
    }

    echo "Starting build for ${repoUrl} (${branch}) → Tag: ${imageTag}"

    // Checkout source
    git branch: branch, url: repoUrl

    // // Build Docker image We can use this command with docker if we usr dind or docker package installation in agen template
    // def dockerImage = docker.build("${ecrRepo}:${imageTag}")

    // Push to ECR
    withAWS(region: awsRegion, credentials: awsCredentialsId) {
        sh """
            mkdir -p /kaniko/.docker
            // aws ecr get-login-password --region ${awsRegion} \
            // | docker login --username AWS --password-stdin ${ecrRepo.split('/')[0]}
        """
        // dockerImage.push()
        // dockerImage.push('latest')
    
        container('kaniko') {
            sh """
            /kaniko/executor \
                --context ${WORKSPACE} \
                --dockerfile ${WORKSPACE}/Dockerfile \
                --destination ${ecrRepo}:${imageTag} \
                --cleanup
            """
        }


    echo "✅ Image pushed: ${ecrRepo}:${imageTag}"
}
