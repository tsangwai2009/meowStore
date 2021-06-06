<#import "/spring.ftl" as spring />
<!doctype html>
<html lang="en_US" ng-app="std.app">
	<head>
		<script>
			var _contextPath = '${contextPath}';
		  	var _applicationPath = "${contextPath}/${app}/";
		</script>
	</head>
	<body>
		<div ui-view="header"></div>
		
		<div class="container" style="margin-left: 10%">
			<div ui-view="content"></div> 
		</div>
		
		<div ui-view="footer"></div>
		
		<script type="text/javascript" src="<@spring.url '/resources/${app}.js'/>"></script>
		
	</body>
</html>