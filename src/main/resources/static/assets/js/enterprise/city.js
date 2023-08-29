const app = angular.module("city-app", []);
app.controller("city-ctrl", function ($scope, $http) {
    $scope.toCityId = "";
    $scope.loadCity = function (regionId) {
        $http.get(`/enterprise/api/toCity/${regionId}`).then(resp => {
            $scope.cities = resp.data;
        })
    }

    $scope.loadCityEvent = function (regionId) {
        $http.get(`/enterprise/api_event/toCity/${regionId}`).then(resp => {
            $scope.cities = resp.data;
        })
    }

    $scope.checkSelectToCity = function () {
        if ($scope.toCityId != "" && $scope.toCityId != undefined) {
            alert("Đã chọn")
        } else {
            alert("Chưa chọn")
        }
    }


})