'use strict';
(function()
{

	var serviceApp = angular.module('sample.service');
	serviceApp.factory('userService', ['$log', '$http', UserService]);
	
	function UserService($log, $http)
	{
		var ret = {};
		
		ret.findUserAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/user/find-user-all',
 			});
 	   	};
 	   
 	   	ret.findByUser = function(username,password)
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/user/login',
 				params: {
 					username: username,
 					password: password,
 				},
 			});
 	   	};
 	   	
 	   	ret.checkLogin = function()
 	   	{
 				return $http({
 				method : 'GET',
 				url: _contextPath + '/rest/user/check',
 				});
 	   	};
 	   	
 	   	ret.approve = function(username){
 	   	 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/user/approve',
 				params: {username: username},
 			});	
 	   	};
 	   	
 	   	ret.reset = function(username){
 	   	 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/user/reset',
 				params: {username: username},
 			});	
 	   	};
 	   	
 	   	ret.reg = function(username, password, name, email, telephone, mobile, address){
 	   	 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/user/reg',
 				params: {
 					username: username,
 					password: password,
 					name:name,
 					email:email,
 					telephone:telephone,
 					mobile:mobile,
 					address:address,
 				},
 			});	
 	   	};
 	   	
		return ret;
	}
	
})();
