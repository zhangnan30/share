package org.devops

//判断项目是否为maven或gradel
def Project(){
    if ("${JOB_NAME}".contains("maven")){
        JDK("${env.jdk}")
        BuildMaven(env.build)
        sh " echo this_is_java-project"
    }
    if ("${JOB_NAME}".contains("gradle")){
        BuildGradle(env.build)
        sh "echo this_is_gradle-project"
    }
}

//设置jdk版本
def JDK(env_jdk){
     def jdks = ["JDK8": "/usr/local/jdk8/bin",
                "JDK11": "/usr/local/jdk11/bin"]
    sh """
        export JAVA_HOMES=${jdks["${jdk}"]}
        echo \$JAVA_HOMES
        """
}

//拉代码
def PullCode(branchname, srcUrl){
    checkout scmGit(branches: [[name: "${branchName}"]],
             extensions: [],
             userRemoteConfigs: [[url: "${srcUrl}"]])
}


//maven构建
def BuildMaven(env_build){
    data = libraryResource 'settings.xml'
    writeFile file: 'settings.xml', text: data
    sh "ls -l && cat settings.xml && ${env.build} && ls -l target/*"
}


//gradle构建
def BuildGradle(env_build){
    sh "ls -l && ${env.build} && ls -l "
}
