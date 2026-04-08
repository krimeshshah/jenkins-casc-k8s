pipelineJob('QA_Build') {
  parameters {
        stringParam('IMAGE_TAG', '', 'Optional: Provide semantic version (e.g., 1.0.0). Leave empty for auto short SHA tag.')
        stringParam('BRANCH', '', 'Optional: Provide Source Code Branch to be built')
  }
  definition {
    cpsScm {
      scm {
        git {
          remote { url('https://github.com/krimeshshah/python-flaskapp') }
          branch('*/develop')
        }
      }
      scriptPath('pipelines/QA-Build.groovy')   // tells Jenkins where Jenkinsfile lives
    }
  }
}