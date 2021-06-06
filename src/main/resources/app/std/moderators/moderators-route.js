'use strict';
(function()
{
	var stdRouteApp = angular.module('std.route.app');
	
	stdRouteApp.config(['$stateProvider','$urlRouterProvider', config]);
	
	function config($stateProvider, $urlRouterProvider){
		$stateProvider.state('moderators_login',
		{
			url: '/moderators/login',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/header.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-login.htm',
				}
			},
		});
		$stateProvider.state('moderators_center',
		{
			url: '/moderators',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-userlist.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_userlist',
		{
			url: '/moderators/userlist',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-userlist.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_booklist',
		{
			url: '/moderators/booklist',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-booklist.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_addbook',
		{
			url: '/moderators/booklist/addbook',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-addbook.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_addinfo',
		{
			url: '/moderators/booklist/addinfo',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-addinfo.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_modify',
		{
			url: '/moderators/booklist/modify/{bookId}',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-modifybook.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_contract',
		{
			url: '/moderators/contract',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-contract.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_edit',
		{
			url: '/moderators/contract/edit/{chequeId}',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-editcontract.htm',
				}
			},
		});
		
		$stateProvider.state('moderators_summary',
		{
			url: '/moderators/summary',
			views:
			{
				'header': { templateUrl: _applicationPath + '/template/moderators-center.htm' },
				'footer': { templateUrl: _applicationPath + '/template/footer.htm' },
				'content' : 
				{ 
					templateUrl: _applicationPath + '/moderators/moderators-summary.htm',
				}
			},
		});
		
	}             
})();
