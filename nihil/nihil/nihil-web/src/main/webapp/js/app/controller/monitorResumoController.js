nihilApp.controller('MonitorResumoController', ['$scope', 'Monitor', function ($scope, Monitor) {
        
    $scope.model = {
        monitores: []
    };
    
    $scope.find = function() {
        Monitor.findResumo().$promise.then(function (result) {
            $scope.model.monitores = result;
         }, function (error) {
            console.log(error);
        });
    };
    
    $scope.find();

}]);

