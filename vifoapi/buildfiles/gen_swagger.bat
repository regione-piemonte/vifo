REM 
REM   SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
REM   
REM   SPDX-License-Identifier: EUPL-1.2
REM 
REM La directory dove sono presenti i jar del generatore csi-swagger
set CSI_SWAGGER_GEN_HOME=c:\devtools\csi-swagger-codegen

set CLI_JAR=%CSI_SWAGGER_GEN_HOME%\swagger-codegen-cli-2.2.1.jar
set CUSTOM_GEN_JAR=%CSI_SWAGGER_GEN_HOME%\csi-java-swagger-codegen-1.1.0.001.jar

set SWAGGER_CP=%CLI_JAR%;%CUSTOM_GEN_JAR%

REM impostare solo per debug del generatore
set DEBUG_OPTS=
rem set DEBUG_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=9797,server=y,suspend=y

rem pause "#### generazione skeleton jaxrs ####"
rem java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\lgspa.yaml -l jaxrs-resteasy-eap-csi -o .. --config swagger_config_java.json

rem pause "#### generazione client jaxrs petstore ####"
rem java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\swagger_petstore_v2.yaml -l jaxrs-resteasy-eap-csi -o .. --config swagger_config_java_petstore_client.json

java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\swagger_test.yaml -l java -o .. --config swagger_config_java_vifobe.json
rem echo
rem pause "#### generazione stub angular2 ####"
rem java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\lgspa.yaml -l typescript-angular -o ..\..\lgspaapi --config swagger_config_angular.json

rem echo
rem pause "#### generazione documentazione html ####"
rem java -jar %SWAGGER_CP% generate -l html -i ..\src\yaml\anagrafica.yaml -o ..\temphelp

rem pause "#### generazione stub ####"
rem java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\lgspa.yaml -l java -o ..\..\lgspaapi --config swagger_config_java.json