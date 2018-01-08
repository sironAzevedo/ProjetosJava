nihilApp.controller('LogController', ['$scope', 'Log', function ($scope, Log) {
        
    $scope.model = {
        content: null
    };
    
    $scope.showContent = function($fileContent){
        $scope.model.content = $fileContent;
    };    
    
    $scope.getLogApp = function() {
        Log.getLogApp().$promise.then(function (result) {
            $scope.model.monitores = result.pagedList;
            $scope.model.totalItems = result.totalGeral;
         }, function (error) {
            console.log(error);
        });
    };
    
    $scope.find();

}]);

