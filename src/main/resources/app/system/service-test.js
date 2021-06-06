'use strict';
(function()
{
	var serviceApp = angular.module('sample.service');
	serviceApp.factory('testService', ['$log', '$http', TestService]);
	
	function TestService($log, $http)
	{
		var ret = {};
		
		ret.findTestAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/test/find-test-all',
 			});
 	   	};
 	   	
 	   	ret.addTest = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/test/add-test',
 			});
 	   	};
 	   	
 	   	ret.deleteTestByTestId = function(testId)
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/test/delete-test-by-test-id',
 				params: {testId: testId},
 			});
 	   	};
		return ret;
	}
	
})();