def preBuildCommands(build_map) {
    sh """
        unzip src/web/dist_arch/dist_vifowcl.zip -d src/web/private
        rm src/web/dist_arch/dist_vifowcl.zip
    """
}

return this