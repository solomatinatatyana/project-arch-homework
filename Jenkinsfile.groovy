node {

    RECIPIENT = "tokio9507@gmail.com"

    try {
        stage('Run Tests') {
            parallel(
                    runTests: { build job: 'PipeJob', parameters: [string(name: 'browser', value: 'CHROME'), gitParameter(name: 'BRANCH', value: 'origin/master')] }
                    //runTests: { build 'Test' }
            )
        }
    }catch(ex){

    }finally{
        echo "Sending e-mail"
        BUILD_DURATION = "${currentBuild.durationString.replace(' and counting', '')}"

        emailext body: "Автотесты OTUS, <br>Длительность прогона: " + BUILD_DURATION + " "   + "${env.BUILD_NUMBER}",
                mimeType: 'text/html',
                subject: "Автотесты OTUS, браузер ${BROWSER}",
                to: "${RECIPIENT}",
                replyTo: "${RECIPIENT}"
    }
    //stage ('Generate Smoke report'){

    //}

    //stage ('SendMailReport'){


    //}




}