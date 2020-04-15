node {

    RECIPIENT = "tokio9507@gmail.com"

    stage('Run Tests') {
        parallel(
                runTests:{build job: 'PipeJob', parameters: [string(name: 'browser', value: 'CHROME'), gitParameter(name: 'BRANCH', value: 'origin/master')]}
                //runTests: { build 'Test' }
        )
    }
    //stage ('Generate Smoke report'){

    //}

    stage ('SendMailReport'){
        echo "Sending e-mail"
        BUILD_DURATION = "${currentBuild.durationString.replace(' and counting', '')}"

        emailext body: "Автотесты OTUS, <br>Длительность прогона: " + BUILD_DURATION,

                mimeType: 'text/html',

                subject: "Автотесты OTUS, браузер ${BROWSER}",

                to: "${RECIPIENT}",

                replyTo: "${RECIPIENT}"

    }




}