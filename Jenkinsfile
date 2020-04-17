pipline{
    agent {}
    stages{
        stage('RunTest'){
            steps{
                parallel(
                    runTests:{build 'Test'}
                )
            }
        }
    }
}
