const app = angular.module("my-favorite-app", []);
app.controller("my-favorite-ctrl", function ($scope, $http) {
	
    $scope.favorite = {
        items: [],
        add(id) {
			var idEvent = 'BKTE'+id;
			var itemEvent = this.items.find(item => item.eventPostId == idEvent);
            
            if(itemEvent){               
                var index = this.items.findIndex(item => item.eventPostId == idEvent);
                this.items.splice(index, 1);
                this.saveToLocalStorage();
            } else{
                $http.get(`/rest/favorite/event/${idEvent}`).then(resp => {
                    resp.data.qty = 1
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        addTour(id){
            var idTour = 'BKTT'+id;
            var itemTour = this.items.find(item => item.tourId == idTour);

            if(itemTour){
                var index = this.items.findIndex(item => item.tourId == idTour);
                this.items.splice(index, 1);
                this.saveToLocalStorage();
            }else{
                $http.get(`/rest/favorite/tour/${idTour}`).then(resp => {
                    resp.data.qty = 1
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        checkadd(id){
            var idEvent = 'BKTE'+id;
            if(this.items){
                return this.items.find(item => item.eventPostId == idEvent);
            }
        },
        checkTour(id){
            var idTour = 'BKTT'+id;
            if(this.items){
                return this.items.find(item => item.tourId == idTour);
            }
        },  
        removeEvent(idEvent){
            var index = this.items.findIndex(item => item.eventPostId == idEvent);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        removeTour(idTour){
            var index = this.items.findIndex(item => item.tourId == idTour);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        get count(){
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("favoriteEvent", json);
        },        
        loadFromLocalStorage(){
            var json = localStorage.getItem("favoriteEvent");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.favorite.loadFromLocalStorage();
})