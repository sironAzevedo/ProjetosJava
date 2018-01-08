nihilApp.controller('MonitorController', ['$scope', 'Monitor', function ($scope, Monitor) {
        
    $scope.model = {
        monitores: [],
        currentPage: 1,
        totalItems: 0, 
        itemsPerPage: 70
    };
    
    $scope.setPage = function (pageNo) {
        $scope.model.currentPage = pageNo;
    };    
    
    $scope.pageChanged = function() {
        $scope.find();
        //$log.log('Page changed to: ' + $scope.model.currentPage);
    };    

    $scope.find = function() {
        Monitor.find({pagina: $scope.model.currentPage, maxPageSize: $scope.model.itemsPerPage, obrderByDesc: true}).$promise.then(function (result) {
            $scope.model.monitores = result.pagedList;
            $scope.model.totalItems = result.totalGeral;
         }, function (error) {
            console.log(error);
        });
    };
    
    $scope.find();

}]);

