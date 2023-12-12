@Library("mylib2")_

//导入共享库里的函数

def project = new org.devops.BUILD()



pipeline {
    agent any

    stages {
        stage('判断项目') {
            steps {
                script{
                    project.PullCode("${env.branchName}", "${env.srcUrl}")
                    sh "echo \$JOB_NAME"
                    project.Project()
                }
                echo "$JOB_NAME"
                
            }
        }

        /*stage('saomiao'){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'sao',
                                     passwordVariable: 'pass', 
                                     usernameVariable: 'name')]) {
                 sh """
                    sonar-scanner \
                    -Dsonar.login=${name} \
                    -Dsonar.password=${pass} \
                    -Dsonar.host.url=http://10.97.58.25:9000 
                    """
                    }
                }
            }
        }
        */

        stage("use plugen"){
            steps{

                script{
                    withSonarQubeEnv(credentialsId: 'sao-123') {
                            
                        sh "sonar-scanner -Dsonar.login=${SONAR_AUTH_TOKEN} "
                             }
                }
            }
        }
    //post{
        //always{
            //cleanWs()
        //}
    //}
    }
}
