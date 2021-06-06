'use strict';
(function()
{
	var stdRouteApp = angular.module('std.route.app');
	
	stdRouteApp.config(['$stateProvider','$urlRouterProvider', config]);
	
	function config($stateProvider, $urlRouterProvider)
	{
		$urlRouterProvider.otherwise('/home');
		$stateProvider.state('home',
		{
			url: '/home',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/home/home-content.htm',
				}
			},
		});
		
		$stateProvider.state('buy_cheque',
		{
			url: '/buybook/cheque/{bookId}',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/home/buy-cheque.htm',
				},
			},
		});
		
				
		$stateProvider.state('buy_cash',
		{
			url: '/buybook/cash/{bookId}',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/home/buy-cash.htm',
				},
			},
		});
		
		$stateProvider.state('feature',
		{
			url: '/feature',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/home/feature.htm',
				},
			},
		});
		
	}
	                    
	
})();
