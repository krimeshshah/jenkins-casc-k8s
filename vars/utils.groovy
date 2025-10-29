def getLatestCommitShortSha(String repoUrl = 'https://github.com/krimeshshah/python-flaskapp.git', String branch = 'develop') {
    echo "Fetching latest commit SHA from ${repoUrl} (${branch})"

    // Clone repo into temp dir
    dir("${env.WORKSPACE}/temp-repo") {
        sh "rm -rf * .git"
        git branch: branch, url: repoUrl

        // Get short SHA (first 7 characters)
        def shortSha = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
        echo "Latest short SHA: ${shortSha}"
        return shortSha
    }
}
