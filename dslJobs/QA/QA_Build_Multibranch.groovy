multibranchPipelineJob('QA_Apps') {
    description('QA pipelines for all application repositories')

    branchSources {
        git {
            id('qa-apps')
            remote('https://github.com/krimeshshah')
            credentialsId('github-pat')

            // Limit repos Jenkins will scan
            includes('flask-* orders-* users-*')
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }

    factory {
        workflowBranchProjectFactory {
            scriptPath('Jenkinsfile')
        }
    }
}
