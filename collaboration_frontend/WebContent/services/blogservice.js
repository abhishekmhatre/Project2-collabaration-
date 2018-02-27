/**
 *BlogService 
 */

app.factory('BlogService',function($http){
	var blog={}
	
	var BASE_URL = "http://localhost:8085/collaboration_middleware"
		
	blog.addBlog=function(blog){
		return $http.post(BASE_URL + "/addblogpost",blog)
	}
	
	blog.getBlogsWaitingForApproval=function(blog){
		return $http.get(BASE_URL + "/getblogs/"+false)
	}
	
	blog.getApprovedBlogs=function(blog){
		return $http.get(BASE_URL + "/getblogs/"+true)
	}
	
	blog.getBlog=function(id){
		return $http.get(BASE_URL + "/getblog/"+id)
	}
	
	blog.hasUserLikedBlogPost=function(id){
		return $http.get(BASE_URL + "/haspostliked/"+id) //response.data-----BlogPostLikes [1 or null]
	}
	
	blog.updateLikes=function(id){
		return $http.put(BASE_URL + "/updatelikes/"+id) //response.data-----BlogPost with updated likes
	}
	
	return blog;
	
	
})
	