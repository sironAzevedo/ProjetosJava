nihilApp.factory('Monitor', ['$resource', function ($resource) {

        var Monitor = $resource('/nihil/rest/monitor/:verbo', {}, {
            find: {method: 'GET', params: {verbo: 'find'}, isArray: false},
            findResumo: {method: 'GET', params: {verbo: 'resumo'}, isArray: true}
        });

        return Monitor;
    }]);