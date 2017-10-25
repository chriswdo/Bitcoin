(function($){
   if ($.fn.datagrid){
		$.fn.datagrid.defaults.loadMsg = '正在处理，请稍待。。。';
		/*修改分页信息 by liujp*/
		$.fn.datagrid.defaults.pageSize =20;
		$.fn.datagrid.defaults.pageList =[10,20,30,50,100];
	};
    
	var ie = (function() {
		var undef, v = 3, div = document.createElement('div'), all = div
				.getElementsByTagName('i');
		while (div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->', all[0]);
		return v > 4 ? v : undef;
	}());
	/**
	 * add by cgh
	 * 针对panel window dialog三个组件调整大小时会超出父级元素的修正
	 * 如果父级元素的overflow属性为hidden，则修复上下左右个方向
	 * 如果父级元素的overflow属性为非hidden，则只修复上左两个方向
	 * @param width
	 * @param height
	 * @returns
	 */
	var easyuiPanelOnResize = function(width, height) {
//
		if (!$.data(this, 'window') && !$.data(this, 'dialog'))
			return;
	
		if (ie === 8) {
			var data = $.data(this, "window") || $.data(this, "dialog");
			if (data.pmask) {
				var masks = data.window.nextAll('.window-proxy-mask');
				if (masks.length > 1) {
					$(masks[1]).remove();
					masks[1] = null;
				}
			}
		}
		if ($(this).panel('options').maximized == true) {
			$(this).panel('options').fit = false;
		}
		$(this).panel('options').reSizing = true;
		if (!$(this).panel('options').reSizeNum) {
			$(this).panel('options').reSizeNum = 1;
		} else {
			$(this).panel('options').reSizeNum++;
		}
		var parentObj = $(this).panel('panel').parent();
		var left = $(this).panel('panel').position().left;
		var top = $(this).panel('panel').position().top;

		if ($(this).panel('panel').offset().left < 0) {
			$(this).panel('move', {
						left : 0
					});
		}
		if ($(this).panel('panel').offset().top < 0) {
			$(this).panel('move', {
						top : 0
					});
		}

		if (left < 0) {
			$(this).panel('move', {
						left : 0
					}).panel('resize', {
						width : width + left
					});
		}
		if (top < 0) {
			$(this).panel('move', {
						top : 0
					}).panel('resize', {
						height : height + top
					});
		}
		if (parentObj.css("overflow") == "hidden") {
			var inline = $.data(this, "window").options.inline;
			if (inline == false) {
				parentObj = $(window);
			}

			if ((width + left > parentObj.width())
					&& $(this).panel('options').reSizeNum > 1) {
				$(this).panel('resize', {
							width : parentObj.width() - left
						});
			}

			if ((height + top > parentObj.height())
					&& $(this).panel('options').reSizeNum > 1) {
				if(height>parentObj.height()) {
					top=10;
					$(this).panel('move', {
						top : 10
					});
				}
				
				$(this).panel('resize', {
							height : parentObj.height() - top
						});
			}
		}
		$(this).panel('options').reSizing = false;
	};
	/**
	 * add by cgh
	 * 针对panel window dialog三个组件拖动时会超出父级元素的修正
	 * 如果父级元素的overflow属性为hidden，则修复上下左右个方向
	 * 如果父级元素的overflow属性为非hidden，则只修复上左两个方向
	 * @param left
	 * @param top
	 * @returns
	 */
	var easyuiPanelOnMove = function(left, top) {
		if ($(this).panel('options').reSizing)
			return;

		var parentObj = $(this).panel('panel').parent();
		var width = $(this).panel('options').width;
		var height = $(this).panel('options').height;
		var right = left + width;
		var buttom = top + height;
		var parentWidth = parentObj.width();
		var parentHeight = parentObj.height();

		if (left < 0) {
			$(this).panel('move', {
						left : 0
					});
		}
		if (top < 0) {
			$(this).panel('move', {
						top : 0
					});
		}

		if (parentObj.css("overflow") == "hidden") {
			var inline = $.data(this, "window").options.inline;
			if (inline == false) {
				parentObj = $(window);
			}
			if ((left > parentObj.width() - width )&& (parentObj.width() - width) >0) {
				$(this).panel('move', {
							"left" : parentObj.width() - width
						});
			}
			if (top > parentObj.height() - height && (parentObj.height() - height)>0) {
				$(this).panel('move', {
							"top" : parentObj.height() - height
						});
			}
		}
	};
	if($.fn.window && $.fn.window.defaults){
		$.fn.window.defaults.onResize = easyuiPanelOnResize;
		$.fn.dialog.defaults.onResize = easyuiPanelOnResize;
		$.fn.window.defaults.onMove = easyuiPanelOnMove;
		$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	}
	

if($.fn.validatebox){
$.extend($.fn.validatebox.defaults.rules, {
    minLength : { // 判断最小长度
        validator : function(value, param) {
            return value.length >= param[0];
        },
        message : '最少输入 {0} 个字符。'
    },
    maxLength : { // 判断最大长度
        validator : function(value, param) {
            return value.length <= param[0];
        },
        message : '最大输入 {0} 个字符。'
    },
    length:{validator:function(value,param){
        var len=$.trim(value).length;
            return len>=param[0]&&len<=param[1];
        },
            message:"输入内容长度必须介于{0}和{1}之间."
        },
    phone : {// 验证电话号码
        validator : function(value) {
        	return /^(\d{3,4}\-)?\d{6,8}\-?\d+$/i.test(s);
        },
        message : '格式不正确,请使用下面格式:020-12345678'
    },
    mobile : {// 验证手机号码
        validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message : '手机号码格式不正确'
    },
    idcard : {// 验证身份证
        validator : function(value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message : '身份证号码格式不正确'
    },
    intOrFloat : {// 验证整数或小数
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '请输入数字，并确保格式正确'
    },
    currency : {// 验证货币
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '货币格式不正确'
    },
    qq : {// 验证QQ,从10000开始
        validator : function(value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message : 'QQ号码格式不正确'
    },
    integer : {// 验证整数
        validator : function(value) {
            return /^-?\d+$/i.test(value);
        },
        message : '请输入整数'
    },
    positiveInteger : {// 验证正整数
        validator : function(value) {
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message : '请输入正整数'
    },
    chinese : {// 验证中文
        validator : function(value) {
            return /^[\u0391-\uFFE5]+$/i.test(value);
        },
        message : '请输入中文'
    },
    english : {// 验证英语
        validator : function(value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message : '请输入英文'
    },
    normal : {// 验证普通字符,包括英文数字中划线下划线
        validator : function(value) {
        	return /^[a-zA-Z0-9_-]{1,40}$/i.test(value);
        },
        message : '请勿输入英文数字中划线下划线之外的字符'
    },
    unnormal : {// 验证是否包含空格和非法字符
        validator : function(value) {
            return /.+/i.test(value);
        },
        message : '输入值不能为空和包含其他非法字符'
    },
    username : {// 验证用户名
        validator : function(value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno : {// 验证传真
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '传真号码不正确'
    },
    zip : {// 验证邮政编码
        validator : function(value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message : '邮政编码格式不正确'
    },
    ip : {// 验证IP地址
        validator : function(value) {
            return /\d+.\d+.\d+.\d+/i.test(value);
        },
        message : 'IP地址格式不正确'
    },
    name : {// 验证姓名，可以是中文或英文
            validator : function(value) {
            	  return /^[\u0391-\uFFE5]+\w+[\w\s]+\w+$/i.test(value);
                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
            },
            message : '输入的值不能包含非法字符'
    },
    carNo:{
        validator : function(value){
            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
        },
        message : '车牌号码无效（例：粤J12350）'
    },
    carenergin:{
        validator : function(value){
            return /^[a-zA-Z0-9]{16}$/.test(value); 
        },
        message : '发动机型号无效(例：FG6H012345654584)'
    },
    email:{
        validator : function(value){
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    },
    message : '请输入有效的电子邮件账号(例：abc@126.com)'    
    },
    msn:{
        validator : function(value){
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    },
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    startDate:{
        validator : function(value, param){
        	var s=$("#"+param[0]).combobox("getValue");
            if(s!= "" && value != ""){
                return s>= value; 
            }else{
                return true;
            }
        },
        message : '开始日期不能大于结束日期！'    
    },
    endDate:{
        validator : function(value, param){
        	var s=$("#"+param[0]).combobox("getValue");
            if(s!= "" && value != ""){
                return s<= value; 
            }else{
                return true;
            }
        },
        message : '结束日期不能小于开始日期！'    
    },
    same:{
        validator : function(value, param){
            if($("#"+param[0]).val() != "" && value != ""){
                return $("#"+param[0]).val() == value; 
            }else{
                return true;
            }
        },
        message : '两次输入的密码不一致！'    
    },
    notsame:{
        validator : function(value, param){
            if($("#"+param[0]).val() != "" && value != ""){
                return !($("#"+param[0]).val() == value); 
            }else{
                return true;
            }
        },
        message : '新密码和旧密码一致！'    
    }
});
}

})(jQuery);



/**
  * @requires jquery, EasyUI 1.2.6+
  * 
  * 此方法是对 EasyUI.window 和 EasyUI.dialog 的扩展
  * 可以实现如下功能：
  * 1、使用框架页面时可以控制窗口是否跨框架弹出在框架最顶层页面，还是框架内当前页面。默认框架最顶层页面
  * 2、可以控制url加载页面方式，是使用默认方式，还是iframe加载， 默认iframe加载
  * 3、使用iframe加载页面时，可以实现父页面向子页面传递javaScript对象
  * 4、使用iframe加载页面时，可以订制iframe onLoad事件
  * 5、扩展content属性，自动识别是静态文本内容，还是加载页面
  * 6、通过赋值ID属性，控制弹出窗体唯一性
  * 7、toolbar、buttons中定义按钮的handler属性，支持弹出窗体iframe中方法调用。
  * 8、弹出窗体关闭方式更灵活
  * 
  * 
  * @author zhaojh<zjh527@163.com>
  * @date 2012.11.15
  * 
  */
 (function($){
 	$.namespace('easyui.ext');
 	
 	/**
 	 * 普通窗体
 	 * 
 	 * 新增属性说明如下
 	 * @param isFrame	是否开启使用iframe加载给定url页面, 此属性设置为true时则开启使用iframe加载页面。 值：true|false,  默认值true
 	 * @param self		用于框架页面，如果值为true则不跨框架，否则跨框架弹出在框架最顶层页面。 值:true|false,	默认值false
 	 * @param data		用于在使用iframe加载给定页面时，父页面给子页面传递数据。	默认值null
 	 * 
 	 * 扩展属性说明如下
 	 * @param onLoad	当使用iframe加载给定url页面时，在iframe加载完成后调用。
 	 * 					默认接收一个参数对象，参数对象属性说明参见下面toolbar、buttons说明第2项。
 	 * @param content	可根据内容前缀关键字'url:',来判断是显示静态文本还是加载页面。
 	 * @param id		此属性用来标识弹出窗体的唯一性，不再用来充当panel的id属性
 	 * 
 	 * 特殊属性说明如下
 	 * this.content		iframe方式加载内容页的window对象。 用于onLoad方法中的调用
 	 * 
 	 * 
 	 * toolbar、buttons	属性定义按钮handler属性扩展说明如下
 	 * 1、当handler 被赋值字符串时，表示调用弹出窗体iframe中已有的与字符串值同名的方法
 	 * 2、被调用方法默认接收一个参数对象，对象属性如下：
 	 *   data: 类型：Object，是对vseaf.open方法参数data的引用
 	 *   close: 类型：Function，用来关闭弹出窗体
 	 * 
 	 * 
 	 * 
 	 * 
 	 * 注：其他属性请参考EasyUI API文档。
 	 * 
 	 */
 	easyui.ext.open = function(opts){
 		var win=null;
 		var defaults = {
     		minimizable: true,
     		maximizable: true,
     		width:500,
     		height:500,
     		collapsible: true,
     		resizable: true,
     		autoHeight:true,
     		isFrame: true, //是否使用iframe
     		self: false, //用于框架页面，如果值为true则不跨框架，否则跨框架弹出在框架最顶层页面
     		data: null, //iframe方式下用来父页面向弹出窗体中子页面传递数据
     		content: '',
     		onLoad: null,
     		onClose: function(){
     			win.dialog('destroy');
     		}
 		};
 		
 		var options = $.extend({}, defaults, opts);
 		
 		//取顶层页面
 		var _doc, _top = (function(w){
 			try{
 				_doc = w['top'].document;
 				_doc.getElementsByTagName;
 			}catch(e){
 				_doc = w.document; 
 				return w;
 			}
 			
 			if(options.self || _doc.getElementsByTagName('frameset').length >0){
 				_doc = w.document; 
 				return w;
 			}
 			
 			return w['top'];
 		})(window);
 		
 		
 		//如填写ID属性，则窗体唯一
 		var winId=null;
 		if(options.id){
 			winId = options.id;
 			delete options.id;
 			
 			//检查创建窗口是否已经存在，存在则不在创建
 			if($('#'+winId).size()>0){
 				return;
 			}
 		}
 		
 		//检查content内容是静态文本，还是url地址
 		var iframe=null;
 		var isUrl = /^url:/.test(options.content);
 		if(isUrl){
 			var url = options.content.substr(4, options.content.length);
 			//构建iframe加载方式
 			if(options.isFrame){
 				if(options.autoHeight){
 					iframe = $('<iframe style=""></iframe>')
			            .attr('height', '100%')
			            .attr('width', '100%')
			            .attr('marginheight', '0')
			            .attr('marginwidth', '0')
			            .attr('frameborder','0');
 				}else{
 					iframe = $('<iframe ></iframe>')
			            .attr('height', '100%')
			            .attr('width', '100%')
			            .attr('marginheight', '0')
			            .attr('marginwidth', '0')
			            .attr('frameborder','0');
 				}
 				
 				
 				var _this = this;
 				var frameOnLoad = function(){
 					//_this.content = iframe.get(0).contentWindow;
 					var autoSizeFunc=function(){
 						var ch1=$(iframe.get(0)).contents().find("body")[0].scrollHeight;
	 					var ch2=0;//$(iframe.get(0)).contents().find("html")[0].scrollHeight;
	 					var ch3=$($(iframe.get(0)).contents().find("body")[0]).height();
	 					
	 					ch1=Math.min(ch1,ch3);
	 					if(options.autoHeight){
	 					  //$(iframe.get(0)).contents().find("body").css("overflow","hidden");
	 					}
	 					var ch= Math.max(ch1, ch2)+38;
	 					//alert(ch);
	 					if(win){
							win.dialog("resize",{  
								height: ch
							});
							win.dialog("center");
	 					}else{
	 					 // alert(111);
	 					}
 					};
 					options.onLoad && options.onLoad.call(_this, {
 						data: options.data,
 						window:iframe.get(0).contentWindow,
 						instance:win,
 					
 						autoSize:autoSizeFunc,
 						close: function(){
 							win.dialog('close');
 						}
 					});
 					
 					if(options.autoHeight){
 						autoSizeFunc();
 					}
 				};
 				
 				delete options.content;
 				
 			}else{//使用默认页面加载方式
 				options.href = url;
 			}
 		}
 		
 		//加工toolbar和buttons中定义的handler方法，使其可以接收给定参数，用于iframe方式下的父子页面传值和窗口关闭
 		var warpHandler = function(handler){
 			var args = {data: options.data,close: function(){win.dialog('close');}};
 	
 			if(typeof handler =='function'){
 				return function(){
 					handler(args);
 				};
 			}
 			
 			if(typeof handler == 'string' && options.isFrame){
 				return function(){
 					iframe.get(0).contentWindow[handler](args);
 				};
 			}
 		};
 		
 		//处理toolbar数组事件定义,选择器形式不做处理
 		if(options.toolbar && $.isArray(options.toolbar)){
 			for(var i in options.toolbar){
 				options.toolbar[i].handler = warpHandler(options.toolbar[i].handler);
 			}
 		}
 		
 		//处理buttons数组事件定义,选择器形式不做处理
 		if(options.buttons && $.isArray(options.buttons)){
 			for(var i in options.buttons){
 				options.buttons[i].handler = warpHandler(options.buttons[i].handler);
 			}
 		}
 		
 
 		if(options.isFrame && iframe){
	
 			
 			
 			win = _top.$('<div  style="overflow:hidden">', {id: winId}).append(iframe).dialog(options);
 			setTimeout(function(){
 				iframe.attr('src', url);
 				iframe.bind('load', frameOnLoad);
				
			}, 50);
 			
 			
 		}else{
 			win = _top.$('<div >', {id: winId}).dialog(options);
 		}
 		return win;
 	}
 	
 	
 	/**
 	 * 
 	 * 模式窗体
 	 * 
 	 * 参数说明请看easyui.ext.open
 	 * 
 	 */
 	easyui.ext.showModalDialog = function(opts){
 		var defaults = $.extend(
 					{}, 
 					opts, 
 					{
 						modal: true, 
 						minimizable: false, 
 						maximizable: false, 
 						resizable: false, 
 						collapsible: false 
 					}
 				);
 		return easyui.ext.open(defaults);
 	};
 	
 	
 	easyui.ext.up=function(tableid){
 		var selections = $('#'+tableid).datagrid("getSelections");
 		//排序
 		selections = $.merge([], selections);;
 		
 		if(selections.length==0){
 			$.messager.alert("提示", "请选择需要排序的记录。",'info');
 			return;
 		}

 		selections.sort(function(x,y){ //升序
 		   var xi=$('#'+tableid).datagrid("getRowIndex",x);
 		   var yi=$('#'+tableid).datagrid("getRowIndex",y);
 		   if (xi>yi){
 		      return 1;
 		   } else if(xi==yi){
 			   return 0;
 		   }else{
 		      return -1;
 		   }
 		 });
 		for(var i=0; i<selections.length; i++){
 			var selIndex=$('#'+tableid).datagrid("getRowIndex",selections[i]);
 			var obj=selections[i];
 			var flag=false;
 			if((selIndex-1)>=0 ){
 				var sels=$('#'+tableid).datagrid("getSelections");
 				for(var n=0; n<sels.length; n++){
 					var index=$('#'+tableid).datagrid("getRowIndex",sels[n]);
 					if(index==selIndex)continue;
 					if(index==selIndex-1) {flag=true;break;};
 				}
 				if(flag) continue;
 				$('#'+tableid).datagrid("deleteRow" ,selIndex);
 				//$('#'+tableid).datagrid("acceptChanges");
 				$('#'+tableid).datagrid("insertRow" ,{
 					index:(selIndex-1),
 					row:obj
 				});
 				//$('#'+tableid).datagrid("acceptChanges");
 				$('#'+tableid).datagrid("selectRow",(selIndex-1));
 			}
 			
 			
 		}
 	};
 	easyui.ext.tobottom=function(tableid){
 		var selections = $('#'+tableid).datagrid("getSelections");
 		//
 		selections = $.merge([], selections);;
 		if(selections.length==0){
 			$.messager.alert("提示", "请选择需要排序的记录。",'info');
 			return;
 		}
 		
 		selections.sort(function(x,y){ //倒序
 			   var xi=$('#'+tableid).datagrid("getRowIndex",x);
 			   var yi=$('#'+tableid).datagrid("getRowIndex",y);
 			   if (xi>yi){
 			      return -1;
 			   } else if(xi==yi){
 				   return 0;
 			   }else{
 			      return 1;
 			   }
 			 });
 		
 		var rowLeng=$('#'+tableid).datagrid("getRows").length;
 		for(var i=0; i<selections.length; i++){
 			var selIndex=$('#'+tableid).datagrid("getRowIndex",selections[i]);
 			if(selIndex >= rowLeng-1-i){
 				continue;
 			}
 			var obj=selections[i];//getRows
 		
 			$('#'+tableid).datagrid("deleteRow" ,selIndex);
 			
 			$('#'+tableid).datagrid("insertRow" ,{
 				index:rowLeng-1-i,
 				row:obj
 			});
 			//$('#'+tableid).datagrid("acceptChanges");
 			$('#'+tableid).datagrid("selectRow",rowLeng-1-i);
 			
 			
 		}
 	};
 	easyui.ext.totop=function(tableid){
 		var selections = $('#'+tableid).datagrid("getSelections");
 		//排序
 		selections = $.merge([], selections);;
 		
 		if(selections.length==0){
 			$.messager.alert("提示", "请选择需要排序的记录。",'info');
 			return;
 		}

 		selections.sort(function(x,y){ //升序
 		   var xi=$('#'+tableid).datagrid("getRowIndex",x);
 		   var yi=$('#'+tableid).datagrid("getRowIndex",y);
 		   if (xi>yi){
 		      return 1;
 		   } else if(xi==yi){
 			   return 0;
 		   }else{
 		      return -1;
 		   }
 		 });
 		for(var i=0; i<selections.length; i++){
 			var selIndex=$('#'+tableid).datagrid("getRowIndex",selections[i]);
 			if(selIndex<=i){
 				continue;
 			}
 			var obj=selections[i];
 			$('#'+tableid).datagrid("deleteRow" ,selIndex);
 			//$('#'+tableid).datagrid("acceptChanges");
 			$('#'+tableid).datagrid("insertRow" ,{
 				index:i,
 				row:obj
 			});
 			//$('#'+tableid).datagrid("acceptChanges");
 			$('#'+tableid).datagrid("selectRow",(i));
 			
 			
 			
 		}
 		
 	};
 	
	easyui.ext.down=function down(tableid){
 		var selections = $('#'+tableid).datagrid("getSelections");
 		//
 		selections = $.merge([], selections);
 		if(selections.length==0){
 			$.messager.alert("提示", "请选择需要排序的记录。",'info');
 			return;
 		}
 		
 		selections.sort(function(x,y){ //倒序
 			   var xi=$('#'+tableid).datagrid("getRowIndex",x);
 			   var yi=$('#'+tableid).datagrid("getRowIndex",y);
 			   if (xi>yi){
 			      return -1;
 			   } else if(xi==yi){
 				   return 0;
 			   }else{
 			      return 1;
 			   }
 			 });
 		
 		var rowLeng=$('#'+tableid).datagrid("getRows").length;
 		for(var i=0; i<selections.length; i++){
 			var selIndex=$('#'+tableid).datagrid("getRowIndex",selections[i]);
 			var obj=selections[i];//getRows
 			var flag=false;
 			if((selIndex+1) < rowLeng ){
 				var sels=$('#'+tableid).datagrid("getSelections");
 				for(var n=0; n<sels.length; n++){
 					var index=$('#'+tableid).datagrid("getRowIndex",sels[n]);
 					
 					if(index==selIndex+1) {flag=true;break;};
 				}
 				if(flag) continue;
 				$('#'+tableid).datagrid("deleteRow" ,selIndex);
 				//$('#'+tableid).datagrid("acceptChanges");
 				$('#'+tableid).datagrid("insertRow" ,{
 					index:(selIndex+1),
 					row:obj
 				});
 				//$('#'+tableid).datagrid("acceptChanges");
 				$('#'+tableid).datagrid("selectRow",(selIndex+1));
 			}
 			
 			
 		}
 	};
 })(jQuery);
 
 function formReset(selector){
	 $(selector)[0].reset();
	 $(selector).form("disableValidation");
	 $(selector).form("enableValidation");
 }