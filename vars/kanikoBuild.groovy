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
            --cache-dir=/kaniko/cache
        """
    }
}