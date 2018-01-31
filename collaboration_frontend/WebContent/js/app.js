/**
 * AngularJS Module 
 */

var app=angular.module("app",['ngRoute','ngCookies'])

app.config(function($routeProvider)
		{
			$routeProvider
			.when('/register',{
				templateUrl:'views/registration.html',
				controller:'UserController'
			})
			
			.when('/login',{
				templateUrl:'views/login.html',
				controller:'UserController'
			})
			.otherwise({
				templateUrl:'views/home.html'
			})
			
		})
app.run(function($rootScope,$cookieStore,UserService,$location)
		{
				if($rootScope.loggedInUser==undefined)
				$rootScope.loggedInUSer=$cookieStore.get('loggedInUser')
					
				$rootScope.logout=function(){
					UserService.logout().then(function(response){
					$rootScope.message="Logout Successfully"
					delete $rootScope.loggedInUser
					$cookieStore.remove("loggedInUser")
					$location.path('/login')	
				},function(response){
					$rootScope.message="Please Login"
					$location.path('/login')
				})
	}
		
	})