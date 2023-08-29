const app = angular.module("reportTop-app", []);
app.controller("reportTop-ctrl", function ($scope, $http) {
 
    $http.get("/admin/api_report_top_enterprise_tour").then(resp => {
        
        var itemTop = resp.data.map(item => [item.enterprise, item.tongThuTour]);

        var timer = setInterval(() => {
            if(ready){
                drawChartTop(itemTop);
                clearInterval(timer);
            }
        }, 10)
        console.log(resp.data.map(item => [item.enterprise, item.tongThuTour]));
    })

    $http.get("/admin/api_report_top_enterprise_event").then(resp => {
        
        var itemTops = resp.data.map(item => [item.enterprise, item.tongThuEvent]);

        var timer = setInterval(() => {
            if(ready){
                drawChartTops(itemTops);
                clearInterval(timer);
            }
        }, 10)
        console.log(resp.data.map(item => [item.enterprise, item.tongThuEvent]));
    })
})