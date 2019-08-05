<!DOCTYPE html>
<html>
<meta name="referrer" content="no-referrer"/>
<head>
    <meta charset="UTF-8">
    <title>Deploy</title>
</head>
<body>
<div>
	<form action="file/upload" method="post" enctype="multipart/form-data">
        <p>选择文件: <input type="file" name="file"/></p>
        <p><input type="submit" value="提交"/></p>
    </form>
    <#list warList as war>
        <p>
			<span stype="margin-right: 10px"><a target="_blank" href="file/download?file=${war.fileName}">${war.name}</a></span>
			<span>${war.deployState}</span>
		</p>
    </#list>
	<p><a target="_blank" href="logs">logs</a></p>
</div>
</body>
<script>
	IPCallBack = function (json) {
		console.log(json);
	};
</script>
<script src="http://whois.pconline.com.cn/ipJson.jsp"></script>
</html>