const app = angular.module("report-app", []);
app.controller("report-ctrl", function ($scope, $http) {

    $http.get("/admin/api_reportCount").then(resp => {

        var items = resp.data.map(item => [item.type, item.soLuongTour]);

        var timer = setInterval(() => {
            if(ready){
                drawChartsoLuongLoai(items);
                clearInterval(timer);
            }
        }, 10)
        console.log(resp.data.map(item => [item.type, item.soLuongTour]));
    })
})

app.controller("report-sluong-ctrl", function ($scope, $http) {

    $http.get("/admin/api_reportCountTranport").then(resp => {

        var items = resp.data.map(item => [item.tranport, item.soLuongTour]);

        var timer = setInterval(() => {
            if(ready){
                drawChartsoLuongXe(items);
                clearInterval(timer);
            }
        }, 10)
        console.log(resp.data.map(item => [item.tranport, item.soLuongTour]));
    })
})