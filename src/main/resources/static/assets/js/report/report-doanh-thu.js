const app = angular.module("report-app", []);


app.controller("report-ctrl", function ($scope, $http) {

 	$scope.x = new Date().getFullYear();
    $http.get("/enterprise/api_report_doanh_thu?year="+$scope.x).then(resp => {

		
        var items = resp.data.map(item => [`Tháng ${item.month}`, item.doanhThuTour, item.doanhThuEvent]);
       
        var timer = setInterval(() => {
            if(ready){
                drawChartDoanhThu(items);
                clearInterval(timer);
            }
        }, 10)

    })

	$scope.layYear = function(){
			var x = document.getElementById("year").value;
			$scope.x = x;
			console.log($scope.x);
			    $http.get("/enterprise/api_report_doanh_thu?year="+$scope.x).then(resp => {

		
        var items = resp.data.map(item => [`Tháng ${item.month}`, item.doanhThuTour, item.doanhThuEvent]);
       
        var timer = setInterval(() => {
            if(ready){
                drawChartDoanhThu(items);
                clearInterval(timer);
            }
        }, 10)

    })
	}
})
	