node {

    RECIPIENT = "tokio9507@gmail.com"

    try {
        stage('Run Tests') {
            /*parallel(
                    runTests: { build job: 'PipeJob', parameters: [string(name: 'browser', value: 'CHROME'), gitParameter(name: 'BRANCH', value: 'origin/master')] }
                    //runTests: { build 'Test' }
            )*/
            checkout([$class: 'GitSCM', branches: [[name: '*/master']],  userRemoteConfigs: [[credentialsId: '62b53291-36d6-4ccb-95cf-efa68b08f788', url: 'https://github.com/solomatinatatyana/project-arch-homework']]])
            bat 'echo Run'
            bat label: '', script: 'C:/Users/Tatiana/maven/apache-maven-3.6.3/bin/mvn clean test'
            //bat "mvn clean test -Dbrowser="+BROWSER
        }
    }catch(ex){
        currentBuild.result = 'FAILURE'
    }finally{
        stage('Reports') {
            allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
            ])
        }

        stage ('SendMailReport'){
            echo "Sending e-mail"
            BUILD_DURATION = "${currentBuild.durationString.replace(' and counting', '')}"
            emailext body: ''' 

<!DOCTYPE html>
<html>
<body>
<table style="width=100%" border=1>
                <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Build Number</font></b></td>
                               <td>$BUILD_NUMBER</td>
                </tr>
    <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Branch</td>
                               <td>$BRANCH</td>
                </tr>
    <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Browser</td>
                               <td>$browser</td>
                </tr>
    <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Status</td>
                               <td>$BUILD_STATUS</td>
                </tr>
                               <td BGCOLOR="#0099CC" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Total tests count</font></b></td>
                               <td BGCOLOR="#FFFFFF"><FONT COLOR=#0099CC FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="total"}</font></b></td>      
                </tr>
                <tr>
                               <td BGCOLOR="#04B431" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Success</font></b></td>
        <td BGCOLOR="#FFFFFF"><FONT COLOR=#04B431 FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="pass"}</font></b></td>       
                </tr>
                <tr>
                               <td BGCOLOR="#DF3A01" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Unstable tests</b></font></td>
                               <td BGCOLOR="#FFFFFF"><FONT COLOR=#DF3A01 FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="fail"}</font></b></td>       
                </tr>   
                <tr>
               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Skipped tests</font></b></td>
               <td BGCOLOR="#FFFFFF"><FONT COLOR=#A4A4A4 FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="skip"}</font></b></td>       
        </tr>
        <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Duration</b></td>
                               <td>'''+ BUILD_DURATION +'''</td>
                </tr>
                <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Link to report</b></td>
                               <td>${BUILD_URL}allure</td>
                </tr>  
</table>
</html>
</body>''',
                    mimeType: 'text/html',
                    subject: "Autotests OTUS, on browser ${BROWSER} build number: ${BUILD_NUMBER}",
                    to: "${RECIPIENT}",
                    replyTo: "${RECIPIENT}"
        }

    }
}