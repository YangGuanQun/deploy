<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deploy</title>
</head>
<body>
<div>
    <#list logList as log>
        <p>
			<span><a target="_blank" href="logs/download?name=${log}">${log}</a></span>
		</p>
    </#list>
</div>
</body>
</html>