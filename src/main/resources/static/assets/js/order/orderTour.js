const app = angular.module("order-tour-app", []);
app.controller("order-tour-ctrl", function ($scope, $http, $rootScope) {
	let id = location.href.split("/").pop();
	$http.get(`/rest/order/tour/${id}`).then(resp => {
		$scope.tour = resp.data;
		
		var item = $scope.tour
		$scope.order = {
			createDate: new Date(),
			startDate: new Date(),
			status: "CHUA",
			note: "",
			payment: 'Momo',
			quantityAdult: 0,
			quantityChildren: 0,
			quantityBaby: 0,
			account: $("#username").text(),
			priceTour: item.priceTour,
			priceAdult: item.priceAdult,
			priceChildren: item.priceChildren,
			priceBaby: item.priceBaby,
			discount: item.discount,
			tourPost: {tourId: item.tourId},
			create() {
				var order = angular.copy(this);
				$http.post('/rest/order/tour', order).then(resp => {
					alert("Đặt hàng thành công!");
					location.href = "/profile/profile_order_tour_status";
				}).catch(error => {
					alert("đặt hàng thất bại :(");
					console.log(error)
				});
			}
		}
	});

})