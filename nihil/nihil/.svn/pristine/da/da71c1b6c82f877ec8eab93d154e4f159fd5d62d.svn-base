nihilApp.factory('Log', ['$resource', function ($resource) {

        var Log = $resource('/nihil/rest/log/:verbo', {}, {
            getLogApp: {method: 'GET', params: {verbo: 'app'}, isArray: false}
        });

        return Log;
    }]);