nihilApp.controller('ProjetoController', ['$scope', '$location', '$routeParams', 'Projeto', function ($scope, $location, $routeParams, Projeto) {
        $scope.model = {
            projetos: [],
            projeto: new Projeto()
        };

        $scope.model.projeto.dataCriacao = new Date();
        $scope.status = {
            opened: false
        };

        $scope.list = function () {
            Projeto.list().$promise.then(function (result) {
                $scope.model.projetos = result;
            }, function (error) {
                console.log(error);
            });
        };
        
        var find = function (projetoId) {
            Projeto.find({id: projetoId}).$promise.then(function (result) {
                $scope.model.projeto = result;
            }, function (error) {
                console.log(error);
            });
        };

        $scope.save = function () {
            $scope.model.projeto.$save(function (result) {
                $scope.model.projeto = result;
                $location.path('/projeto/list');
            }, function (error) {
                console.log(error);
            });

        };

        $scope.remove = function (projeto) {
            Projeto.remove({'id': projeto.id}).$promise.then(function () {
                $location.path('/projeto/list');
            }, function (error) {
                console.log(error);
            });

        };

        $scope.open = function ($event) {
            $scope.status.opened = true;
        };

        var id = $routeParams.id;

        if (id === undefined) {
            $scope.list();
        } else if (id != 0) {
            find(id);
        }
    }]);