nihilApp.factory('Projeto', ['$resource', function ($resource) {

        var Projeto = $resource('/nihil/rest/projeto/:verbo/:id', {}, {
            list: {method: 'GET', params: {verbo: 'list'}, isArray: true},
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            remove: {method: 'DELETE', params: {verbo: 'remove'}, isArray: false},
            find: {method: 'GET', params: {verbo: 'find'}, isArray: false}
        });

        return Projeto;
    }]);