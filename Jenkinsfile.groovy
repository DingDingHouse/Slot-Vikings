def PROJECT_NAME = "Slot-Vikings"
def UNITY_VERSION = "2022.3.51f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor\\Unity.exe"
def REPO_URL = "git@github.com:DingDingHouse/Slot-Vikings.git"

pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
    }

    environment {
        PROJECT_PATH = "C:\\Games\\Slot-Vikings"
        S3_BUCKET = "vikingsbucket"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    dir("${PROJECT_PATH}"){
                    bat '''
                    hostname
                    git config --global http.postBuffer 3221225472
                    git clone git@github.com:DingDingHouse/Slot-Vikings.git C:\\Games\\Slot-Vikings || echo "Repository already exists, pulling latest changes."
                    cd Slot-Vikings
                    git fetch --all
                    git reset --hard origin/develop
                    git checkout develop
                    '''
                    }
                }
            }
        }
        stage('Build WebGL') {
            steps {
                script {
                    withEnv(["UNITY_PATH=${UNITY_INSTALLATION}"]) {
                        bat '''
                        "%UNITY_PATH%" -quit -batchmode -projectPath "%PROJECT_PATH%" -executeMethod BuildScript.BuildWebGL -logFile -
                        '''
                    }
                }
            }
        }

        stage('Push Build to GitHub') {
            steps {
                script {
                    dir("${PROJECT_PATH}") {
                        bat '''
                        hostname
                        git clean -fd
                        git stash --include-untracked
                        git checkout develop
                        git add -f Builds
                        git commit -m "Add new Buils"
                        git push origin develop
                        '''
                    }
                }
            }
        }

        stage('Deploy to S3') {
            steps {
                script {
                    dir("${PROJECT_PATH}") {
                        bat '''
                        REM Copy all .html files to S3 with the correct content type
                        aws s3 cp "Builds/WebGL/" s3://%S3_BUCKET%/ --recursive --acl public-read --exclude "*" --include "*.html" --content-type "text/html"
                        
                        REM Copy .data files to S3 with the correct content type
                        aws s3 cp "Builds/WebGL/" s3://%S3_BUCKET%/ --recursive --acl public-read --exclude "*" --include "*.data" --content-type "application/octet-stream"
                        
                        REM Copy .framework.js files to S3 with the correct content type
                        aws s3 cp "Builds/WebGL/" s3://%S3_BUCKET%/ --recursive --acl public-read --exclude "*" --include "*.framework.js" --content-type "application/javascript"
                        
                        REM Copy .loader.js files to S3 with the correct content type
                        aws s3 cp "Builds/WebGL/" s3://%S3_BUCKET%/ --recursive --acl public-read --exclude "*" --include "*.loader.js" --content-type "application/javascript"
                        
                        REM Copy .wasm files to S3 with the correct content type
                        aws s3 cp "Builds/WebGL/" s3://%S3_BUCKET%/ --recursive --acl public-read --exclude "*" --include "*.wasm" --content-type "application/octet-stream"
                        
                        REM Move index.html to the root for S3 hosting
                        aws s3 cp "Builds/WebGL/index.html" s3://%S3_BUCKET%/index.html --acl public-read --content-type "text/html"
                        
                        REM Optional: Set S3 bucket for static web hosting
                        aws s3 website s3://%S3_BUCKET%/ --index-document index.html --error-document index.html
                        '''
                    }
                }
            }
        }
    }
}
