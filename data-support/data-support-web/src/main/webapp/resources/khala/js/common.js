//把json转为字符串
 function jsonToString(obj) {
 	var THIS = this;
 	switch(typeof(obj)) {
 		case 'string':
 			return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
         case 'array':
 			return '[' + obj.map(THIS.jsonToString).join(',') + ']';
 		case 'object':
 			if(obj instanceof Array) {
 				var strArr = [];
 				var len = obj.length;
 				for(var i = 0; i < len; i++) {
 					strArr.push(THIS.jsonToString(obj[i]));
                 }
                 return '[' + strArr.join(',') + ']';
 			} else if(obj == null) {
 				return 'null';
 			} else {
 				var string = [];
 				for (var property in obj) string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));
 				return '{' + string.join(',') + '}';
             }
 		case 'number':
 			return obj;
 		case false:   
 			return obj;
 		case 'boolean':   
 			return obj;
 		case 'function':
 			return '"function"';
     }
 }
 
 //扩展jq ajax error方法，提示没有权限访问
(function($){
	//备份jquery的ajax方法
	var _ajax=$.ajax;
	//重写jquery的ajax方法
	$.ajax=function(opt){
		//备份opt中error
		var fn = {
			error:function(XMLHttpRequest, textStatus, errorThrown){},
		}
		if(opt.error){
			fn.error=opt.error;
		}
		//扩展增强处理
		var _opt = $.extend(opt,{
			error:function(XMLHttpRequest, textStatus,errorThrown){
				if("403"==XMLHttpRequest.status){
					var pathUrl=this.url;
					if(pathUrl.indexOf(basePath)==0){
						pathUrl=pathUrl.substring(basePath.length);
					}
					alert(pathUrl+"无权限访问，请联系管理员！");
					return;
				}
				//运行自定义的error方法
				fn.error(XMLHttpRequest, textStatus, errorThrown);  
			}
		});
		_ajax(_opt);
	};
})(jQuery);

$(function(){
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); 
	/*ie6,7下拉框美化*/
	try{
    if ( $.browser.msie ){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    		
    	}
    }
	}catch(e)
	{
		
	}
});

/**
 * 对ajax请求的全局session处理,common.jsp默认添加
 */
$(function(){

	// 全局的ajax访问，处理ajax清求时session超时
	$.ajaxSetup({
		cache : false,
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		timeout : 1000*60*5
	});
	$(document).ajaxComplete(function(event, XMLHttpRequest, ajaxOptions){
		if(XMLHttpRequest.getResponseHeader){
			var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
			//alert("sessionstatus="+sessionstatus);
			if (sessionstatus == "timeout") {
				// 如果超时就处理 ，指定要跳转的页面
				// window.location.replace("/login.do");
				// alert("你已经超时，请重新登陆！");
				var xmlDoc=XMLHttpRequest.responseText;
				var ret=eval('(' + xmlDoc + ')');//$.evalJSON(xmlDoc);
				alert(ret.content);
				if(window.top){
					window.top.location=ret.login;
				}else{
					window.location=ret.login;
				}
			}
		}
	});   
});

function modlink(skin){
	var href = contextPath+"js/jquery/jquery-easyui/themes/"+skin+"/easyui.css";
	$("link[href*='easyui.css']").prop("href",href);	//父窗口改样式
	var iframes = document.getElementsByTagName('iframe');//子窗口该样式
	for(var i = 0 ;i<iframes.length; i++){	
		if(iframes[i]){
			$(iframes[i].contentWindow.document).find("link[href*='easyui.css']").prop("href",href);
		}
	}
}