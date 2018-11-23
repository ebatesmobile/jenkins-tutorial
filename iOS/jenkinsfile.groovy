pipeline {
    agent {
        node {
            label 'ios'
        }
    }
    stages {
        stage('Build and Test JenkinsTutorial') {
            steps {
                script {
                  sh "xcodebuild -workspace iOS/JenkinsTutorial/JenkinsTutorial.xcworkspace \
                    -scheme JenkinsTutorial \
                    -destination 'platform=iOS Simulator,name=iPhone X,OS=12.0' \
                    clean test"
                }
            }
        }
    }
    post {
        success {
            script {
                emailext (subject: "✅ Success",
                   body: "Build URL: ${BUILD_URL}",
                   to: "your@email.com")
            }
        }
        failure {
            script {
                emailext (subject: "🚫 Fail",
                   body: "Build URL: ${BUILD_URL}",
                   to: "your@email.com")
            }
        }
    }
}
