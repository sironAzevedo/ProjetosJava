nihilApp.config(['$routeProvider', 
  function($routeProvider) {
    $routeProvider.
      when('/projeto/list', {
        templateUrl: 'partials/projeto/list.html',
        controller: 'ProjetoController'
      }).
      when('/projeto/edit/:id', {
        templateUrl: 'partials/projeto/edit.html',
        controller: 'ProjetoController'
      }).
      when('/monitor/list', {
        templateUrl: 'partials/monitor/list.html',
        controller: 'MonitorController'
      }).
      when('/monitor/list/resumo', {
        templateUrl: 'partials/monitor/listResumo.html',
        controller: 'MonitorResumoController'
      }).
      when('/empresa/list', {
        templateUrl: 'partials/empresa/list.html',
        controller: 'EmpresaListController'
      }).
      when('/empresa/edit/:id', {
        templateUrl: 'partials/empresa/edit.html',
        controller: 'EmpresaEditController'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);