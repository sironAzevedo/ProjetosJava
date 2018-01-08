<%@ page contentType="application/x-java-jnlp-file" session="false" %><%@ page contentType="application/x-java-jnlp-file" session="false" %>
<%
response.addHeader("Cache-Control", "public");
response.addHeader("Content-Disposition", "Inline; fileName=SistamDesktop.jnlp"); 
%><?xml version="1.0" encoding="UTF-8"?>
<jnlp spec="1.0+" codebase="@@codebase" href="sistam/SistamDesktop.jsp&#063;<%=request.getQueryString()%>">
    <information>
        <title>SISTAM</title>
        <vendor>PETROBRAS</vendor>
        <description>Sistema de Agenciamento Marítimo</description>
        <homepage href="sistam.petrobras.com.br"/>
    </information>
    <security>
      <all-permissions/>
    </security>
    <resources>
        <j2se version="1.6+" max-heap-size="256m" />
        <property name="javaws.jauthenticator" value="none" />
        <property name="deployment.proxy.auto.config.url" value="" />
        <property name="deployment.proxy.type" value="0" />
        <property name="proxySet" value="false" />
        <property name="config.applogspath" value="c:/temp/" />
        <property name="config.applogssuffix" value="log" />
        <property name="maxfiles" value="3" />
        <property name="maxfilesize" value="10MB" /> 
        <property name="severity-app" value="INFO" />
        <property name="severity-job" value="OFF" />
        <property name="severity-sql" value="OFF" />
        <jar href="sistam/sistam-desktop-@@version.jar" main="true"/>
@@jnlp-jars
    </resources>
    <application-desc main-class="br.com.petrobras.sistam.desktop.SistamApp">
        <argument><%= request.getQueryString()%></argument>
    </application-desc>
</jnlp>