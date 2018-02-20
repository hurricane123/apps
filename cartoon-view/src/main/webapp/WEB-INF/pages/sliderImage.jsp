<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/res/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/res/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/res/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/res/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/res/easyui/themes/icon.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/res/swiper/css/swiper.css">
<script src="${pageContext.request.contextPath }/res/swiper/js/swiper.js"></script>
</head>
<body>



	<img id="img" />
	<div style="max-width: 80%; margin: 0 auto;">
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div class="swiper-zoom-container">
						<img id="img1">
					</div>
				</div>
			</div>
			<!-- Add Pagination -->
			<div class="swiper-pagination"></div>
			<!-- Add Arrows -->
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
		</div>
	</div>
	<input id="imgId" type="hidden" value="${imgId }" />

	<script type="text/javascript">
		var imgs;
		var index = -1;
		var prependNum = 0;
		var apendNum = 0;
		function fixPagesHeight() {
			$('.swiper-slide,.swiper-container').css({
				height : $(window).height(),
			})
		}
		$(window).on('resize', function() {
			fixPagesHeight();
		})
		fixPagesHeight();
	
		var mySwiper = new Swiper('.swiper-container', {
			direction : 'horizontal',//滑动方向vertical,horizontal
			mousewheelControl : true,//鼠标控制
			watchSlidesProgress : true,
			//autoHeight: true,//开启自适应高度
			pagination : {	//添加分页
				el : '.swiper-pagination',
				type : 'progressbar',//分页类型
			},
			navigation : {//添加导航按钮
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',
			},
			zoom: true,//配合swiper-zoom-container使用
			on:{
				slidePrevTransitionEnd: function(){
				  console.log('上一个'+this.activeIndex);
				  if(this.activeIndex==0&&((index-prependNum)>0)){
					  prependNum++;
					  mySwiper.prependSlide([
					        '<div class="swiper-slide"><div class="swiper-zoom-container"><img src="' + '${pageContext.request.contextPath }/image/viewDirect?url='+ encodeURIComponent(imgs[index-prependNum]) + '"/></div></div>',
					        ]);
				  }
				},
			    slideNextTransitionEnd: function(){
				  console.log('下一个'+this.activeIndex);
				  if((this.activeIndex==prependNum+apendNum)&&((index+apendNum+1)<imgs.length)){
					  apendNum++;
					  mySwiper.appendSlide([
					        '<div class="swiper-slide"><div class="swiper-zoom-container"><img src="' + '${pageContext.request.contextPath }/image/viewDirect?url='+ encodeURIComponent(imgs[index+apendNum]) + '"/></div></div>',
					        ]);
				  }
			    },
			    
			}
		});
		
		
	
		$(function() {
			$.get('${pageContext.request.contextPath }/image/getImgs', {
				id : $("#imgId").val()
			}, function(data) {
				index = parseInt(data.pop());
				imgs = data;
				$("#img1").attr("src",'${pageContext.request.contextPath }/image/viewDirect?url='+encodeURIComponent(imgs[index]));
				if(index>0){
					prependNum++;
					mySwiper.prependSlide([
				        '<div class="swiper-slide"><div class="swiper-zoom-container"><img src="' + '${pageContext.request.contextPath }/image/viewDirect?url='+ encodeURIComponent(imgs[index-1]) + '"/></div></div>',
				        ]);
				}
				if(index<imgs.length){
					apendNum++;
					mySwiper.appendSlide([
				        '<div class="swiper-slide"><div class="swiper-zoom-container"><img src="' + '${pageContext.request.contextPath }/image/viewDirect?url='+ encodeURIComponent(imgs[index+1]) + '"/></div></div>',
				        ]);
				}
			})
		});
	</script>
	
</body>
</html>