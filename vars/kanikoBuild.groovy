def call(Map config = [:]) {

    def image      = config.image
    def tag        = config.tag ?: env.BUILD_NUMBER
    def dockerfile = config.dockerfile ?: 'Dockerfile'
    def context    = config.context ?: env.WORKSPACE

    container('kaniko') {
        sh """
          /kaniko/executor \
            --context ${context} \
            --dockerfile ${dockerfile} \
            --destination ${image}:${tag} \
            --cache=true \
            --cache-repo registry.kube-system/python-flaskapp-cache \
            --insecure \
            --skip-tls-verify \
            --insecure-registry registry.kube-system
            --snapshot-mode=redo
            --use-new-run
        """
    }
}