nihilApp.controller('EmpresaListController', ['$scope', 'Empresa', function ($scope, Empresa) {
        $scope.model = {
            empresas: []
        };

        $scope.list = function () {
            Empresa.list().$promise.then(function (result) {
                $scope.model.empresas = result;
            }, function (error) {
                console.log(error);
            });
        };
        
        $scope.list();
        
    }]);