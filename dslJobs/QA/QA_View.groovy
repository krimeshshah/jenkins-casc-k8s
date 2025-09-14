listView('QA') {
    description('All QA stage jobs')
    filterBuildQueue()
    filterExecutors()
    jobs {
        regex('QA_.*')   // Only show jobs with names starting with QA_
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
