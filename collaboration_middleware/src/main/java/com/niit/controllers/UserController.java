package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;
public UserController(){
	System.out.println("UserController INSTANTIATED");
}
@RequestMapping(value="/register",method=RequestMethod.POST)
public ResponseEntity<?> registration(@RequestBody User user){
	if(!userDao.isEmailValid(user.getEmail())){
		ErrorClazz error=new ErrorClazz(2,"Email Id already exists.. please enter different email address");
		return new ResponseEntity<ErrorClazz>(error, HttpStatus.CONFLICT);//409 //2nd callback function(error)
	}
	try{
	userDao.registration(user);
	return new ResponseEntity<User>(user,HttpStatus.CREATED);//1st callback fun (success)
	}catch(Exception e){
		ErrorClazz error=new ErrorClazz(1,"Unable to register user details "+e.getMessage());
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);//2nd callback function
	}
}
@RequestMapping(value="/login",method=RequestMethod.POST)
//{'email':'john.s@abc.com','password':'123'}  - i/p
public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	User validUser=userDao.login(user);
	if(validUser==null)//invalid credentials, error
	{
		ErrorClazz error=new ErrorClazz(3,"Invalid email id/password.. please enter valid credentials");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401
		//response.data=error
		//response.status=401
	}
	else{//valid credentials, success
		validUser.setOnline(true);
		userDao.update(validUser);
		session.setAttribute("loginId",validUser.getEmail() ); //assigned email id to an attribute "loginId"
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
		//response.data=validUser object
		//response.status=200
	}
	
	}
@RequestMapping(value="/logout",method=RequestMethod.PUT)
public ResponseEntity<?> logout(HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null)
	{
		ErrorClazz error=new ErrorClazz(3,"Unauthorized access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	
	User user=userDao.getUser(email);
	user.setOnline(false);
	userDao.update(user); //update online=false where email=?
	session .removeAttribute("loginId");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
}


}
