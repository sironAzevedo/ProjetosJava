nihilApp.filter('simNao', function() {
    return function(input) {
        return input ? 'Sim' : 'Não';
    }    
});    

