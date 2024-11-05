def PROJECT_NAME = "Slot-Vikings"
<<<<<<< HEAD
def UNITY_VERSION = "2022.3.48f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor\\Unity.exe"
def REPO_URL = "git@github.com:Prathm0025/Slot-Vikings.git"
=======
def UNITY_VERSION = "2022.3.51f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor\\Unity.exe"
def REPO_URL = "git@github.com:DingDingHouse/Slot-Vikings.git"
>>>>>>> a69ffde81e815300bd25ef2a9fc34f2e636f57bb

pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
    }

    environment {
<<<<<<< HEAD
        PROJECT_PATH = "D:\\Slot-Vikings"
=======
        PROJECT_PATH = "C:\\Games\\Slot-Vikings"
>>>>>>> a69ffde81e815300bd25ef2a9fc34f2e636f57bb
        S3_BUCKET = "vikingsbucket"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
<<<<<<< HEAD
                    bat '''
                    cd /d D:\\
                    git config --global http.postBuffer 3221225472
                    git clone git@github.com:Prathm0025/Slot-Vikings.git D:\\Slot-Vikings || echo "Repository already exists, pulling latest changes."
                    cd Slot-Vikings
                    git fetch --all
                    git reset --hard origin/develop
                    git checkout develop
                    '''
                }
            }
        }
=======
                    dir("${PROJECT_PATH}") {
                        bat '''
                        hostname
                        git config --global http.postBuffer 3221225472
                        git clone git@github.com:DingDingHouse/Slot-Vikings.git C:\\Games\\Slot-Vikings || echo "Repository already exists, pulling latest changes."
                        cd C:\\Games\\Slot-Vikings
                        git fetch --all
                        git reset --hard origin/develop
                        git checkout develop
                        '''
                    }
                }
            }
        }
        
>>>>>>> a69ffde81e815300bd25ef2a9fc34f2e636f57bb
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
<<<<<<< HEAD
                        git clean -fd
                        git stash --include-untracked
                        git checkout artifact 
                        git pull origin artifact
                        git rm -r -f Builds 
                        git add .
                        git commit -m "delete old Builds"
                        git push origin artifact

                        git checkout artifact
                        git checkout develop -- Builds
                        git add -f Builds
                        git commit -m "adding new Builds"
                        git pull
=======
                        whoami
                        git config --global user.email "prathamesh@underpinservices.com"
                        git config --global user.name "Prathm25"
                        git clean -fd
                        git checkout --orphan artifact
                        git clean -fd
                        git stash
                        git rm -rf Builds || echo "No existing artifact to delete"
                        git add -f Builds
                        git commit -m "Remove old Builds"
                        git push origin artifact
                        git pull
                        git checkout develop -- Builds 
                        git add -f Builds
                        git commit -m "Add new Builds"
>>>>>>> a69ffde81e815300bd25ef2a9fc34f2e636f57bb
                        git push origin artifact
                        '''
                    }
                }
            }
        }

<<<<<<< HEAD
=======
    
>>>>>>> a69ffde81e815300bd25ef2a9fc34f2e636f57bb
        stage('Deploy to S3') {
            steps {
                script {
                    dir("${PROJECT_PATH}") {
                        bat '''
<<<<<<< HEAD
                        REM Copy all files, including .html files, to S3
                        aws s3 cp "Builds/WebGL/" s3://%S3_BUCKET%/ --recursive --acl public-read

                        REM Move index.html to the root for S3 hosting
                        aws s3 cp "Builds/WebGL/index.html" s3://%S3_BUCKET%/index.html --acl public-read

                        REM Optional: Set S3 bucket for static web hosting
                        aws s3 website s3://%S3_BUCKET%/ --index-document index.html --error-document index.html
=======
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
>>>>>>> a69ffde81e815300bd25ef2a9fc34f2e636f57bb
                        '''
                    }
                }
            }
        }
    }
}
