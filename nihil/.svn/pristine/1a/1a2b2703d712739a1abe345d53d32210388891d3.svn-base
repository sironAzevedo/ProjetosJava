nihilApp.factory('Empresa', ['$resource', function ($resource) {

        var Empresa = $resource('/nihil/rest/empresa/:verbo/:id', {}, {
            list: {method: 'GET', params: {verbo: 'list'}, isArray: true},
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            find: {method: 'GET', params: {verbo: 'find'}, isArray: false}
        });

        return Empresa;
}]);