<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sistam - Login</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/estilo-painel.css">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        
        
        <form id="login" name="login" method="post" accept-charset="utf-8" action="https:///servicoca.petrobras.com.br/fwca/Authentication.svlt">
            <div id="logoSistema"></div>
                <div id="logoPetrobras"></div>
            <fieldset id="inputs">
                <input id="txt_user_login" name="txt_user_login" type="text" placeholder="Sua chave" autofocus required>   
                <input id="pwd_user_password" name="pwd_user_password" type="password" placeholder="Sua senha" required>
            </fieldset>
            <fieldset id="actions">
                <input type="submit" id="submit" value="Login">
            </fieldset>
                    <input type="hidden" id="hdn_successful_url" name="hdn_successful_url" value="<c:out value="${param.successfulUrl}" />" />
                    <input type="hidden" id="hdn_global_session_id" name="hdn_global_session_id" value="<c:out value="${param.globalSessionId}" />" />
                    <input type="hidden" id="hdn_sso_enabled" name="hdn_sso_enabled" value="<c:out value="${param.ssoEnabled}" />" />
                    <input type="hidden" id="hdn_app_env_uid" name="hdn_app_env_uid" value="<c:out value="${param.appEnvUid}" />" />
                    <c:if test="${param.errorMessage != null}">
                        <span><%= request.getParameter("errorMessage") %></span>
                    </c:if>
                    <c:if test="${param.infoMessage != null}">
                        <span><%= request.getParameter("infoMessage") %></span>
                    </c:if>
                </form>
       

       
        
    </body>
</html>
