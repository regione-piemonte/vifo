def postBuildCommands(build_map) {
    sh """
    if [ -d ${WORKSPACE}/dist_arch ];then
    rm -rf ${WORKSPACE}/dist_arch
    fi
    mkdir -p ${WORKSPACE}/dist_arch
    cd ${WORKSPACE}/dist/vivaife
    zip -r ${WORKSPACE}/dist_arch/dist_vifowcl.zip ./
    """
}

return this