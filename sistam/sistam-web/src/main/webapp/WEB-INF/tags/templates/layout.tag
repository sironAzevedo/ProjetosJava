<%@ tag language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html ng-app="app">
    <head>
        <meta charset=utf-8"/>
        <title>Painel de Agenciamentos</title>
        <link href="<c:url value="/css/estilo-painel.css" />" rel="stylesheet" type="text/css" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body ng-controller="AgenciasPermitidas">
        <header>
            <div id="logoSistema"></div>
            <div id="logoPetrobras"></div>
            <span id="select-estilo" class="subtitulo">Tema: <select ng-change="selectEstilo()" ng-model="estiloSelecionado" ng-options="estilo.nome for estilo in estilos | orderBy:'nome'"></select></span>
            <div class="texto">
                <span class="titulo">PAINEL DE MOVIMENTAÇÕES</span>
                <span class="subtitulo">AGÊNCIA: <select ng-change="selectAction()" ng-model="agenciaSelecionada" ng-options="agencia.nome for agencia in agencias | orderBy:'nome'"></select></span>
            </div>
        </header>
        
        <div class="container">
            <jsp:doBody />
        </div>
        
        <script src="<c:url value="/js/angular.min.js" />" /></script>
        <script src="<c:url value="/js/painelController.js" />" /></script>
    </body>
</html>