/**
 *  NotificationController
 *  #/getnotification/id
 */

app.controller('NotificationController',function($scope,$location,NotificationService,$rootScope,$routeParams){
	var id=$routeParams.id;
	
	function getAllNotification(){
		NotificationService.getAllNotification().then(function(response){
			$rootscope.notifications=response.data;
			$rootscope.notificationsCount=$rootscope.notifications.length
		},function(response){
			$rootScope.error=response.data//ErrorClazz
			if(response.status==401)//Not Authenticated
				$location.path('/login')
		})
	}
	
	NotificationService.getNotification(id).then(function(response){
		$scope.notification=response.data;
	},function(response){
		$rootScope.error=response.data//ErrorClazz
		if(response.status==401)//Not Authenticated
			$location.path('/login')
	})
	
	NotificationService.updateNotification(id).then(function(response){
		getAllNotification() //select * from notification where email=? and viewed=0
	},function(response){
		$rootScope.error=response.data//ErrorClazz
		if(response.status==401)//Not Authenticated
			$location.path('/login')
	})

})

