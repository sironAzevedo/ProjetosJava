nihilApp.controller('EmpresaEditController', ['$scope', '$location', '$routeParams', 'Empresa', function ($scope, $location, $routeParams, Empresa) {
        $scope.model = {
            empresa: new Empresa()
        };

        var find = function (empresaId) {
            Empresa.find({id: empresaId}).$promise.then(function (result) {
                $scope.model.empresa = result;
            }, function (error) {
                console.log(error);
            });
        };

        $scope.save = function () {
            $scope.model.empresa.$save(function (result) {
                $scope.model.empresa = result;
                $location.path('/empresa/list');
            }, function (error) {
                console.log(error);
            });

        };

        var id = $routeParams.id;

        if (id !== 0) {
            find(id);
        }
}]);