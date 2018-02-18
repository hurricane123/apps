<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/easyui/jquery.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/res/easyui/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/res/easyui/locale/easyui-lang-zh_CN.js"></script>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/easyui/themes/default/easyui.css"/>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/easyui/themes/icon.css"/>   
</head>
<body>
	<ul id="tt"></ul>  
	<script type="text/javascript">
		$('#tt').tree({    
		    data: [{
		    	id:'',
				text: 'ROOT',
				state: 'closed',
				children:[]
			}],
		    url:'${pageContext.request.contextPath }/file/listFile',
		    onClick: function(node){
			//	console.log(node.text);  // 在用户点击的时候提示
				if(node.text.match("\.jpg"))
					window.open('${pageContext.request.contextPath }/image/jumpToImgView?fileName='+node.id,"_blank",null,false);
			}
		});  
	</script>
</body>
</html>