def getShortCommitSha() {
    return sh(
        script: "git rev-parse --short HEAD",
        returnStdout: true
    ).trim()
}