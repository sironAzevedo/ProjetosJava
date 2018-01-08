toggleFullScreen();

var app=angular.module('app', []);

app.controller('AgenciasPermitidas', function ($scope, $http) {

    $scope.estilos = [
        { nome: 'Opção 1', link: '../css/cores1.css'},
        { nome: 'Opção 2', link: '../css/cores.css'},
        { nome: 'Opção 3', link: '../css/cores2.css'}
    ];
    
    $scope.estiloSelecionado = $scope.estilos[0];
    
    updateEstilo();
    
    function updateEstilo() {
        
        for (var i=0; i < $scope.estilos.length; i++) {
            removejscssfile($scope.estilos[i].link, "css")
        }
        
        var lnk = document.createElement('link');
        lnk.setAttribute('type', "text/css" );
        lnk.setAttribute('rel', "stylesheet" );
        lnk.setAttribute('href', $scope.estiloSelecionado.link );
        document.getElementsByTagName("head").item(0).appendChild(lnk);    
    }
    
     $scope.selectEstilo = function() {
         updateEstilo()
     }


    function removejscssfile(filename, filetype){
        var targetelement=(filetype=="js")? "script" : (filetype=="css")? "link" : "none" //determine element type to create nodelist from
        var targetattr=(filetype=="js")? "src" : (filetype=="css")? "href" : "none" //determine corresponding attribute to test for
        var allsuspects=document.getElementsByTagName(targetelement)
        for (var i=allsuspects.length; i>=0; i--){ //search backwards within nodelist for matching elements to remove
            if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1)
                allsuspects[i].parentNode.removeChild(allsuspects[i]) //remove element by calling parentNode.removeChild()
        }
    }        

    $http.get('../mvc/agencias/sistam.do').
      success(function(data, status, headers, config) {
          $scope.agencias = data;
          $scope.agenciamentosEsperados = [];
          $scope.agenciamentosAtracados = [];
          $scope.agenciamentosFundeados = [];
          $scope.agenciamentosSaidos = [];
          $scope.agenciamentosDesviados = [];
          
          if ($scope.agencias.length > 0) {
              $scope.agenciaSelecionada = $scope.agencias[0];
              $scope.selectAction();
          }
          
      });

      
    $scope.selectAction = function() {

        $http.get('../mvc/agenciamentos/agencia/' + $scope.agenciaSelecionada.sigla + '/statusEmbarcacao/6/sistam.do').
          success(function(data, status, headers, config) {
              $scope.agenciamentosEsperados = data;
              $scope.agenciamentosEsperadosPageSize = 5;
              $scope.agenciamentosEsperadosCurrentPage = 0;
              $scope.agenciamentosEsperadosNumberOfPages=function(){
                return Math.ceil($scope.agenciamentosEsperados.length/$scope.agenciamentosEsperadosPageSize);                
              };
          });

      
        $http.get('../mvc/agenciamentos/agencia/' + $scope.agenciaSelecionada.sigla + '/statusEmbarcacao/1,2,4,7,9/sistam.do').
          success(function(data, status, headers, config) {
              $scope.agenciamentosFundeados = data;
              $scope.agenciamentosFundeadosPageSize = 5;
              $scope.agenciamentosFundeadosCurrentPage = 0;
              $scope.agenciamentosFundeadosNumberOfPages=function(){
                return Math.ceil($scope.agenciamentosFundeados.length/$scope.agenciamentosFundeadosPageSize);                
              };
          });

        $http.get('../mvc/agenciamentos/agencia/' + $scope.agenciaSelecionada.sigla + '/statusEmbarcacao/3/sistam.do').
          success(function(data, status, headers, config) {
              $scope.agenciamentosAtracados = data;  
              $scope.agenciamentosAtracadosPageSize = 5;
              $scope.agenciamentosAtracadosCurrentPage = 0;
              $scope.agenciamentosAtracadosNumberOfPages=function(){
                return Math.ceil($scope.agenciamentosAtracados.length/$scope.agenciamentosAtracadosPageSize);                
              };  
          });
  
        $http.get('../mvc/agenciamentos/agencia/' + $scope.agenciaSelecionada.sigla + '/statusEmbarcacao/5/sistam.do').
          success(function(data, status, headers, config) {
              $scope.agenciamentosSaidos = data;
              $scope.agenciamentosSaidosPageSize = 3;
              $scope.agenciamentosSaidosCurrentPage = 0;
              $scope.agenciamentosSaidosNumberOfPages=function(){
                return Math.ceil($scope.agenciamentosSaidos.length/$scope.agenciamentosSaidosPageSize);                
              };
          });

        $http.get('../mvc/agenciamentos/agencia/' + $scope.agenciaSelecionada.sigla + '/statusEmbarcacao/10/sistam.do').
          success(function(data, status, headers, config) {
              $scope.agenciamentosDesviados = data;
              $scope.agenciamentosDesviadosPageSize = 2;
              $scope.agenciamentosDesviadosCurrentPage = 0;
              $scope.agenciamentosDesviadosNumberOfPages=function(){
                return Math.ceil($scope.agenciamentosDesviados.length/$scope.agenciamentosDesviadosPageSize);                
              };
          });


    };
    
    
    var refreshAllData = function(intervalo) {
        setInterval(function(){
            if ($scope.agenciaSelecionada == undefined) {
                if ($scope.agencias.length > 0) {
                    $scope.agenciaSelecionada = $scope.agencias[0];
                }
            }
            $scope.selectAction();
        }, intervalo);
    }
    
    refreshAllData(300000);
    
    function setRefresh(intervalo, currentPageName, functionNumberOfPages){
        setInterval(function(){   
            if($scope[currentPageName] !== undefined){                
                    $scope[currentPageName]++;
                    if( $scope[currentPageName] === $scope[functionNumberOfPages]() ){
                        $scope[currentPageName] = 0;
                    }
                    $scope.$apply();
            }
        }, intervalo);
        
    }
    
    setRefresh(15000, "agenciamentosEsperadosCurrentPage", "agenciamentosEsperadosNumberOfPages");
    setRefresh(15000, "agenciamentosFundeadosCurrentPage", "agenciamentosFundeadosNumberOfPages");
    setRefresh(15000, "agenciamentosAtracadosCurrentPage", "agenciamentosAtracadosNumberOfPages");
    setRefresh(15000, "agenciamentosSaidosCurrentPage", "agenciamentosSaidosNumberOfPages");
    setRefresh(15000, "agenciamentosDesviadosCurrentPage", "agenciamentosDesviadosNumberOfPages");
    

}).filter('startFrom', function() {
    return function(input, start) {
        if (input === undefined || input === null || input.length === 0) return [];
        start = +start; //parse to int
        return input.slice(start);
    };
});




/*
app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    };
});

*/
//solucao originada de http://jsfiddle.net/2ZzZB/56/


function toggleFullScreen() {
  if ((document.fullScreenElement && document.fullScreenElement !== null) ||    // alternative standard method
      (!document.mozFullScreen && !document.webkitIsFullScreen)) {               // current working methods
    if (document.documentElement.requestFullScreen) {
      document.documentElement.requestFullScreen();
    } else if (document.documentElement.mozRequestFullScreen) {
      document.documentElement.mozRequestFullScreen();
    } else if (document.documentElement.webkitRequestFullScreen) {
      document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
    }
  } else {
    if (document.cancelFullScreen) {
      document.cancelFullScreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
      document.webkitCancelFullScreen();
    }
  }
}
