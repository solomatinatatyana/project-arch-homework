node {

    RECIPIENT = "tokio9507@gmail.com"

    stage('Run Tests') {
        parallel(
                //runTests:{build job: 'PipeJob', parameters: [string(name: 'browser', value: 'CHROME'), gitParameter(name: 'BRANCH', value: 'origin/master')]}
                runTests: { build 'Test' }
        )
    }
}