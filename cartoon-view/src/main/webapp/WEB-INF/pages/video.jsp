<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/res/easyui/jquery.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/res/easyui/locale/easyui-lang-zh_CN.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/res/ckplayer/ckplayer.js"></script>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/easyui/themes/default/easyui.css"/>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/easyui/themes/icon.css"/>
<title>${title }</title>
</head>
<body>
<div id="video" style="width:600px;height:400px;"></div>
<script type="text/javascript">
	var videoObject = {
		container: '#video',//“#”代表容器的ID，“.”或“”代表容器的class
		variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
		flashplayer:false,//如果强制使用flashplayer则设置成true
		video:'${pageContext.request.contextPath }/video/view?url=${videoUrl}'
	};
	var player=new ckplayer(videoObject);
</script>
</body>
</html>