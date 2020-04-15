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

        emailext body: '''Autotests OTUS, <br>Duration:  ''' + BUILD_DURATION + ''' 

<!DOCTYPE html>
<html>
<body>
<table style="width=100%" border=1>
                <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Описание</font></b></td>
                               <td>Автотесты.Java</td>
                </tr>
    <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Ветка</td>
                               <td>$BRANCH</td>
                </tr>
    <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Браузер</td>
                               <td>$browser</td>
                </tr>
    <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Статус</td>
                               <td>$BUILD_STATUS</td>
                </tr>
                               <td BGCOLOR="#0099CC" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Всего автотестов</font></b></td>
                               <td BGCOLOR="#FFFFFF"><FONT COLOR=#0099CC FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="total"}</font></b></td>      
                </tr>
                <tr>
                               <td BGCOLOR="#04B431" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Успешно пройдено</font></b></td>
        <td BGCOLOR="#FFFFFF"><FONT COLOR=#04B431 FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="pass"}</font></b></td>       
                </tr>
                <tr>
                               <td BGCOLOR="#DF3A01" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Нестабильных тестов</b></font></td>
                               <td BGCOLOR="#FFFFFF"><FONT COLOR=#DF3A01 FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="fail"}</font></b></td>       
                </tr>   
                <tr>
               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Незапущенных автотестов</font></b></td>
               <td BGCOLOR="#FFFFFF"><FONT COLOR=#A4A4A4 FACE="Geneva, Arial" SIZE=2><b>${TEST_COUNTS,var="skip"}</font></b></td>       
        </tr>
        <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Время прогона</b></td>
                               <td>'''+ BUILD_DURATION +'''</td>
                </tr>
                <tr>
                               <td BGCOLOR="#A4A4A4" width="20%"><FONT COLOR=white FACE="Geneva, Arial" SIZE=2><b>Ссылка на отчет</b></td>
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
    //stage ('Generate Smoke report'){

    //}

    //stage ('SendMailReport'){


    //}




}