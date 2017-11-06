angular.module('appModule').component('games', {
    templateUrl : 'app/appModule/game/game.component.html',
    controller: function($location, gameService, weekService, authService, $scope){
        var vm = this;
        vm.games = [];
        vm.selected = null;
        vm.selectedArr = [];
        var usersId = null;
        var picks = {}; // THIS IS A HASHMAP
        
        vm.selectGame = function(game, team) {
            if (vm.selectedArr.indexOf(game[team]) < 0){
				var opposingTeam = (team === 'home') ? 'away' : 'home';
				if (vm.selectedArr.indexOf(game[opposingTeam]) >= 0) {
            			vm.selectedArr.splice(vm.selectedArr.indexOf(game[opposingTeam]), 1);
				}
            		vm.selectedArr.push(game[team]);
            }
            //console.log(vm.selectedArr)
            
            var pickJson = {};
            pickJson.teamId = game[team].id;
            pickJson.userId = authService.getToken().id;
            pickJson.gameId = game.id;
            picks[pickJson.gameId] = pickJson;
            
        }
        
        
        
    	
    	var wid = 1;
    	$scope.$on('WeekBC', function(event, args){
    		console.log(args.weekNum.gameWeek);
    		wid = args.weekNum.gameWeek
    		reload(wid);
    		})
    	
   
    	
    	
    	
        
        vm.convertPicksToJson = function() {
        		var pickJsonArr = [];
        		console.log(picks)
        		for (var key in picks) {
//        			var stringifyPick = JSON.stringify(picks[key])
        			pickJsonArr.push(picks[key])
        		}
        		var card = {};
        		card.picks = pickJsonArr;
        		
        		console.log('pickJsonArr')
        		console.log(pickJsonArr)
        		gameService.createPicks(JSON.stringify(card)).then(function(res){
        			console.log('created');
        			console.log(res.data);
        		});
        }
        
    //INDEX           
        var reload = function(wid){
            gameService.indexGame(wid)
            .then(function(res){
//                console.log(res)
//                console.log(res.data)
                vm.games = res.data;
            })      
            .catch(function(err){
                console.log(err)
            })
        }    
        reload();
    },
    controllerAs: 'vm'
});