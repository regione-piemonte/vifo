import it.csi.alm.Constants

def buildNpmNodeCommand(build_map){
    nvm_version = Constants.buildToolVersions.versions.npm_node[build_map.artifactBuildToolVersion]
    sh """
        #!/bin/bash
        set -e
        source ${NVM_HOME}/nvm.sh
        nvm use ${nvm_version} #8 
        #npm config set registry http://repart.csi.it/artifactory/api/npm/npm
        npm install
        npm run ng build --configuration=${env.environment}
        #clean di node_modules per minimizzare problemi di spazio disco
        rm -rif node_modules
    """
}

return this