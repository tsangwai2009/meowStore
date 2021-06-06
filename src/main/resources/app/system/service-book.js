'use strict';
(function()
{
	var serviceApp = angular.module('sample.service');
	serviceApp.factory('bookService', ['$log', '$http', BookService]);
	
	function BookService($log, $http)
	{
		var ret = {};
		
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
 	   	
 	   	ret.findBookByIdwithLogin = function(bookId)
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-book-by-id-with-login',
 				params: {
 					bookId:bookId,
 				}
 			});
 	   	};
 	   	
 	   	ret.findBookByIdwithAdmin = function(bookId)
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-book-by-id-with-admin',
 				params: {
 					bookId:bookId,
 				}
 			});
 	   	};
		
		ret.findBookAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-book-all',
 			});
 	   	};
 	   	
 	   	ret.findAuthorAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-author-all',
 			});
 	   	};
 	   	
 	   	ret.findPublisherAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-publisher-all',
 			});
 	   	};
 	   	
 	   	ret.findCategoryAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-category-all',
 			});
 	   	};
 	   	
		ret.add = function(subject,description,isbn,content,contentType,price,author,publisher,category){
 	   	 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/add',
 				params: {
 					subject:subject,
 					description:description,
 					isbn:isbn,
 					content:content,
 					contentType:contentType,
 					price:price,
 					author:author,
 					publisher:publisher,
 					category:category,
 				},
 			});	
 	   	};
 	   	
 	   	ret.addAuthor = function(name,email,country){
 	   	 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/addauthor',
 				params: {
 					name:name,
 					email:email,
 					country:country,
 				},
 			});	
 	   	};
 	   	

		ret.modify = function(id,subject,description,isbn,content,contentType,price,author,publisher,category){
 	   	 	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/modify',
 				params: {
 					bookId:id,
 					subject:subject,
 					description:description,
 					isbn:isbn,
 					content:content,
 					contentType:contentType,
 					price:price,
 					author:author,
 					publisher:publisher,
 					category:category,
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
 	   	
 	   			
 	   	ret.findShelfAll = function()
 	   	{
 	   	   	return $http({
 				method : 'POST',
 				url: _contextPath + '/rest/book/find-shelf-all',
 			});
 	   	};
 	   	
 	   	ret.updateAccess = function(bookId){
	 	   	return $http({
	 				method : 'POST',
	 				url: _contextPath + '/rest/book/update-access',
	 				params: {
	 					bookId:bookId,
	 				}
	 			});
 	   	}
 	   	
 	   	
		return ret;
	}
	
})();