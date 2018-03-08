#!/usr/bin/groovy

def call(Map parameters) {
    String componentName = parameters.componentName
    String componentVersion = parameters.componentVersion
    String componentBuild = parameters.componentBuild
    String sourceBranch = parameters.sourceBranch ?: ''
    String targetBranch = parameters.targetBranch ?: ''
    String changeUrl = parameters.changeUrl ?: ''

    String jobName = 'feedhenry-sdks-container-component-update'
    def jobParams = [
            [$class: 'StringParameterValue', name: 'componentName', value: componentName],
            [$class: 'StringParameterValue', name: 'componentVersion', value: componentVersion],
            [$class: 'StringParameterValue', name: 'componentBuild', value: componentBuild],
            [$class: 'StringParameterValue', name: 'sourceBranch', value: sourceBranch],
            [$class: 'StringParameterValue', name: 'targetBranch', value: targetBranch],
            [$class: 'StringParameterValue', name: 'changeUrl', value: changeUrl]
    ]

    if (changeUrl || (sourceBranch && targetBranch)) {
        build job: jobName, parameters: jobParams, wait: false
    }
}
