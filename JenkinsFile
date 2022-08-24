pipeline{
    agent any
    stages{
    stage("Build"){
        steps{
            echo("build the code")
        }
    }
    stage("Deploy on qa"){
        steps{
            echo("Deploy on qa")
        }
    }
    stage("Test on qa"){
        steps{
            echo("Running tests on qa")
        }
    }
    stage("Deploy on stage"){
        steps{
            echo("Sanity tests on stage")
        }
    }
    stage("Deploy on prod"){
        steps{
            echo("Deploy on prod")
        }
    }
}
}