def call(Map args) {

    def repoUrl   = args.repoUrl
    def branch    = args.branch ?: 'master'
    def imageRepo = args.imageRepo

    stage('Checkout') {
        git branch: branch, url: repoUrl
    }

    stage('Get Image Tag') {
        def tag = utils.getShortCommitSha()
        env.IMAGE_TAG = tag
    }

    stage('Build Image') {
        kanikoBuild(
            image: imageRepo,
            tag: env.IMAGE_TAG
        )
    }

}