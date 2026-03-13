organizationFolder('QA_Apps') {

    description('QA pipelines for all application repositories')

    organizations {
        github {

            repoOwner('krimeshshah')
            credentialsId('github-pat')

            traits {

                // Discover branches
                gitHubBranchDiscovery {
                    strategyId(1)
                }

                // Only build develop branch
                headWildcardFilter {
                    includes('develop')
                    excludes('')
                }

                // Only discover repos matching patterns
                sourceWildcardFilter {
                    includes('*flask* orders-* users-*')
                    excludes('')
                }

            }
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }

}




// multibranchPipelineJob('QA_Apps') {
//     description('QA pipelines for all application repositories')

//     branchSources {
//         git {
//             id('qa-apps')
//             repoOwner('krimeshshah')
//             repository('python-flaskapp')
//             credentialsId('github-pat')
//             // Limit repos Jenkins will scan
//             includes('*flask* orders-* users-*')
//         }
//     }

//     factory {
//         workflowBranchProjectFactory {
//             scriptPath('Jenkinsfile')
//         }
//     }

//     orphanedItemStrategy {
//         discardOldItems {
//             numToKeep(20)
//         }
//     }
    
// }