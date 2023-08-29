const app = angular.module("order-event-app", []);
app.controller("order-event-ctrl", function ($scope, $http, $rootScope) {
	let id = location.href.split("/").pop();
	$http.get(`/rest/order/event/${id}`).then(resp => {
		$scope.event = resp.data;
		console.log(resp.data);
		var item = $scope.event;
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
			priceEvent: item.priceEvent,
			priceAdult: item.priceAdult,
			priceChildren: item.priceChildren,
			priceBaby: item.priceBaby,
			discount: item.discount,
			eventPost: {eventPostId: item.eventPostId},
			create() {
				var order = angular.copy(this);
				$http.post('/rest/order/event', order).then(resp => {
					alert("Đặt hàng thành công!");
					location.href = "/profile/profile_order_event_status";
				}).catch(error => {
					alert("đặt hàng thất bại :(");
					console.log(error)
				});
			}
		}
	});

})