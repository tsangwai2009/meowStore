'use strict';
(function()
{
	var serviceApp = angular.module('sample.service');
	serviceApp.factory('buyService', ['$log', '$http', BuyService]);
	
	function BuyService($log, $http)
	{
		var ret = {};
		
		ret.checkLogin = function()
 	   	{
 				return $http({
 				method : 'GET',
 				url: _contextPath + '/rest/user/check',
 				});
 	   	};
 	   	
 	   	ret.findBookById = function(bookId)
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-book-by-id',
 				params: {
 					bookId:bookId,
 				}
 			});
 	   	};
 	   	
 	   	ret.findChequeById = function(chequeId)
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/find-cheque-by-id',
 				params: {
 					chequeId:chequeId,
 				}
 			});
 	   	};
 	   	
 	   	ret.checkBalance = function()
 	   	{
 	   			return $http({
 				method : 'GET',
 				url: _contextPath + '/rest/buy/checkbalance',
 			});
 	   	};
 	   	
 	   	ret.cashBuy = function(bookId)
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/cashbuy',
 				params: {
 					bookId:bookId,
 				}
 			});
 	   	}
 	   	
 	    ret.chequeBuy = function(bookId,amount)
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/chequebuy',
 				params: {
 					bookId:bookId,
 					amount:amount,
 				}
 			});
 	   	}
 	   	
 	   	ret.findCashAll = function()
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/cashrecord',
 			});
 	   	}
 	   	
 	   	ret.findChequeAll = function()
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/chequerecord',
 			});
 	   	}
 	   	
 	   	ret.findUserChequeAll = function()
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/userchequerecord',
 			});
 	   	}
 	   	
 	   	ret.terminate = function(chequeId)
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/terminate',
 				params: {
 					chequeId:chequeId,
 				}
 			});
 	   	}
 	   	
 	   	ret.edit = function(contractId,chequeId,userId,bookId,amount,chequeStatus){
	 	   	return $http({
	 				method : 'POST',
	 				url: _contextPath + '/rest/buy/edit',
	 				params: {
	 					contractId:contractId,
	 					chequeId:chequeId,
	 					userId:userId,
	 					bookId:bookId,
						amount:amount,
						chequeStatus:chequeStatus,
	 				},
	 			});	
 	   	};
 	   	
 	    ret.approve = function(chequeId)
 	   	{
 	   		 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/buy/approve',
 				params: {
 					chequeId:chequeId,
 				}
 			});
 	   	}
 	   	
 	   	ret.summary = function()
 	   	{
 	   			return $http({
 				method : 'GET',
 				url: _contextPath + '/rest/buy/summary',
 			});
 	   	};
 	   	
		return ret;
	}
	
})();