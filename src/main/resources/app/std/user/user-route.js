'use strict';
(function()
{
	var stdRouteApp = angular.module('std.route.app');
	
	stdRouteApp.config(['$stateProvider','$urlRouterProvider', config]);
	
	function config($stateProvider, $urlRouterProvider){
		$stateProvider.state('login',
		{
			url: '/user/login',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/user/user-login.htm',
				},
			},
		});
		$stateProvider.state('user_index',
		{
			url: '/user',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/user-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/user/user-shelf.htm',
				},
			},
		});
		
		$stateProvider.state('user_reg',
		{
			url: '/user/reg',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/user/user-reg.htm',
				},
			},
		});
		
		$stateProvider.state('user_shelf',
		{
			url: '/user/shelf',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/user-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/user/user-shelf.htm',
				},
			},
		});
		
		$stateProvider.state('user_shelf_detail',
		{
			url: '/user/shelf/detail/{bookId}',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/user-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/user/user-shelf-detail.htm',
				}
			},
		});
		
		$stateProvider.state('user_record',
		{
			url: '/user/record',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/user-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/user/user-record.htm',
				}
			},
		});
		
	}             
})();