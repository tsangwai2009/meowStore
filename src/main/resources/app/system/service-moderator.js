'use strict';
(function()
{
	var serviceApp = angular.module('sample.service');
	serviceApp.factory('moderatorService', ['$log', '$http', ModeratorService]);
	
	function ModeratorService($log, $http)
	{
		var ret = {};
		
		ret.findByUser = function(username,password)
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/moderators/login',
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
 				url: _contextPath + '/rest/moderators/check',
 				});
 	   	};
		return ret;
		
	}
	
})();