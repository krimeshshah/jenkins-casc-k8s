pipelineJob('DEV_Build') {
  definition {
    cpsScm {
      scm {
        git {
          remote { url('https://github.com/krimeshshah/jenkins-casc-k8s.git') }
          branch('*/main')
        }
      }
      scriptPath('pipelines/Dev-Build.groovy')   // tells Jenkins where Jenkinsfile lives
    }
  }
}