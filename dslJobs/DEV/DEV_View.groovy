listView('DEV') {
    description('All DEV stage jobs')
    filterBuildQueue()
    filterExecutors()
    jobs {
        regex('DEV_.*')   // Only show jobs with names starting with QA_
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
