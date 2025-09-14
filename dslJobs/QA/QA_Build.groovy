pipelineJob('QA_Build') {
  definition {
    cpsScm {
      scm {
        git {
          remote { url('https://github.com/your-org/your-repo.git') }
          branch('*/main')
        }
      }
      scriptPath('pipelines/QA-Build.groovy')   // tells Jenkins where Jenkinsfile lives
    }
  }
}