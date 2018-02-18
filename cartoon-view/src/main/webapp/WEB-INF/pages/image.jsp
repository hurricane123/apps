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
	<img id="img"/>
	<a id="pre" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'">上一个</a>  
	<a id="next" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-more'">下一个</a>  
	<input id="imgId" type="hidden" value="${imgId }"/>
	
	
	<script type="text/javascript">
		var imgs;
		var index = -1;
		$(function(){
	        $.get('${pageContext.request.contextPath }/image/getImgs',{id:$("#imgId").val()},function(data){
	        	index = data.pop();
	        	imgs = data;
		        $.get('${pageContext.request.contextPath }/image/view',{url:imgs[index]},function(data){
		        	console.log(data.length)
		        	$("#img").attr("src","data:image/jpeg;base64,"+data);

		        	//$("#img").html(data)
		        	//document.getElementById("img").src = data;
		        })  
	        })  
	        
			$('#pre').bind('click', function(){  
				if(index>0) index--;
		        $.get('${pageContext.request.contextPath }/image/view',{url:imgs[index]},function(data){
		        	$("#img").attr("src","data:image/jpeg;base64,"+data);
		        })  
		    });
			$('#next').bind('click', function(){    
				if(index<imgs.length-1) index++;
		        $.get('${pageContext.request.contextPath }/image/view',{url:imgs[index]},function(data){
		        	$("#img").attr("src","data:image/jpeg;base64,"+data);
		        })  
		    });
		});
	</script>
</body>
</html>