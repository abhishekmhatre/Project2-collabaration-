/**
 * BlogPostDetailController
 */
app.controller('BlogPostDetailController',function($scope,$routeParams,$location,BlogService,$sce,$rootScope){
	var id=$routeParams.id;
	
	BlogService.getBlog(id).then(function(response){
		$scope.blogPost=response.data; //select * from blogpost where id=?
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent);//blogContent HTML tags
	},function(response){
		$rootScope.error=response.data;
		if(response.status==401)
			$location.path('/login')
	})
	
	//select * from blogpostlikes where blog_id=? and user_email=?
	BlogService.hasUserLikedPost(id).then(function(response){
		if(response.data=='')
			{
			 $scope.isLiked=false //glyphicon is black Color
			}
		else
			{
			 $scope.isLiked=true // glyphicon is blue color
			}
		
	},function(response){
		$rootScope.error=response.data;
		if(response.status==401)
			$location.path('/login')
	})
	
	$scope.updatesLikes=function(id){
	BlogService.updateLikes(id).then(function(response){
		$scope.blogPost=response.data; //blogPost objects with updated likes
		$scope.isLiked=!$scope.isLiked
		
	},function(response){
		$rootScope.error=response.data;
		if(response.status==401)
			$location.path('/login')
		})
	}
})
