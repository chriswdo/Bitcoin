/*
 * JQuery工具类扩展
 * 作者: Mark
 */
 

(function($){
	
	var utils={
	        ltrim:function(str,compart){//去除左边界字符
	          if(!str){return ""};
	          if(!compart){compart=/(^\s*)/g};
	          if(typeof compart =='string'){
	          	compart=new RegExp("(^"+compart+"*)","g")
	          }
	          return  str.replace(compart, ""); 
	        	
	        },
	        rtrim:function(str,compart){//去除右边界字符
	        	if(!str){return ""};
	          	 if(!compart){compart=/(\s*$)/g};
		          if(typeof compart =='string'){
		          	compart=new RegExp("("+compart+"*$)","g")
		          }
		          return  str.replace(compart, ""); 
	        },
	        trim:function(str,compart){//去除右边界字符
	        	return this.ltrim(this.rtrim(str,compart));
	        },
	        strBytes:function(str){//获取字符的字节长度 
    			var cArr = str.match(/[^\x00-\xff]/ig);
    			return str.length + (cArr == null ? 0 : cArr.length);    
	        },
			debug:function(str){
				alert(str);
			},
			//字符串格式化处理
			strFormat:function(){
			
			},
			//字符串转换成日期类型
			strToDate:function(str){
				var strs = str.replace(/([-:\s])0*/g,"$1").split(/-|:|\s/); 	
				var date=new Date(parseInt(strs[0]),parseInt(strs[1])-1,parseInt(strs[2]),parseInt(strs[3]),parseInt(strs[4])); 
				return date;
			},
			//日期转换成字符串
			dateToStr:function(data){
			
			} ,
			//给日期增加天数
			dateTimeAddDate:function (paramDate,_dateCount){
				if(!_dateCount){_dateCount=0};
				if(typeof _dateCount !='number'){this.debug("增加日期天数必需为数字"+_dateCount)}
				var date="";
				if( typeof paramDate == 'string'){
					var strs = paramDate.replace(/([-:\s])0*/g,"$1").split(/-|:|\s/); 	
					//默认值加3天
					date=new Date(parseInt(strs[0]),parseInt(strs[1])-1,parseInt(strs[2])+_dateCount,parseInt(strs[3]),parseInt(strs[4]));
				}else if(this.isDate(paramDate)){
					date=new Date(paramDate.getTime());
					date.setDate(date.getDate()+_dateCount);
				}else{
					this.debug("日期参数格式错误："+paramDate);
				}
				
				var value2= date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes();
				
				return value2;
			},
			parseStringToDate:function (str){
				
			},
			//公共界面提示
			alert:function(){
				window.alert("公共界面，暂未实现！");
			},
			//判断是否为日期
			isDate : function(data) {
				  return (data && data.toUTCString) ? true : false;
			},
			//判断是否为数组
			isArray : function(data) {
  				return (data && data.join) ? true : false;
			},
			//对象的详细类型
			detailedTypeOf : function(x) {
			  var reply = typeof x;
			  if (reply == "object") {
			    reply = Object.prototype.toString.apply(x); // Returns "[object class]"
			    reply = reply.substring(8, reply.length-1);  // Just get the class bit
			  }
			  return reply;
			},
			//处理中文空格问题
			indent : function(level, depth) {
			  var reply = "";
			  if (level != 0) {
			    for (var j = 0; j < depth; j++) {
			      reply += "\u00A0\u00A0";
			    }
			    reply += " ";
			  }
			  return reply;
			},
			//检查是否为HTML元素
			isHTMLElement : function(ele, nodeName) {
			  if (ele == null || typeof ele != "object" || ele.nodeName == null) {
			    return false;
			  }
			  if (nodeName != null) {
			    var test = ele.nodeName.toLowerCase();
			    if (typeof nodeName == "string") {
			      return test == nodeName.toLowerCase();
			    }
			    if (this.isArray(nodeName)) {
			      var match = false;
			      for (var i = 0; i < nodeName.length && !match; i++) {
			        if (test == nodeName[i].toLowerCase()) {
			          match =  true;
			        }
			      }
			      return match;
			    }
			    this.debug("isHTMLElement was passed test node name that is neither a string or array of strings");
			    return false;
			  }
			  return true;
			},
			//输出JSON格式内容
			toDescriptiveString : function(data, level, depth) {
				  var reply = "";
				  var i = 0;
				  var value;
				  var obj;
				  if (level == null) level = 0;
				  if (depth == null) depth = 0;
				  if (data == null) return "null";
				  if (this.isArray(data)) {
				    if (data.length == 0) reply += "[]";
				    else {
				      if (level != 0) reply += "[\n";
				      else reply = "[";
				      for (i = 0; i < data.length; i++) {
				        try {
				          obj = data[i];
				          if (obj == null || typeof obj == "function") {
				            continue;
				          }
				          else if (typeof obj == "object") {
				            if (level > 0) value = this.toDescriptiveString(obj, level - 1, depth + 1);
				            else value = this.detailedTypeOf(obj);
				          }
				          else {
				            value = "" + obj;
				            value = value.replace(/\/n/g, "\\n");
				            value = value.replace(/\/t/g, "\\t");
				          }
				        }
				        catch (ex) {
				          value = "" + ex;
				        }
				       if (level != 0)  {
				          reply += this.indent(level, depth + 2) + value + ", \n";
				       }
				        else {
				          if (value.length > 13) value = value.substring(0, 10) + "...";
				          reply += value + ", ";
				          if (i > 5) {
				            reply += "...";
				            break;
				          }
				        }
				      }
				      if (level != 0) reply += this.indent(level, depth) + "]";
				      else reply += "]";
				    }
				    return reply;
				  }
				  if (typeof data == "string" || typeof data == "number" || this.isDate(data)) {
				    return data.toString();
				  }
				  if (typeof data == "object") {
				    var typename = this.detailedTypeOf(data);
				    if (typename != "Object")  reply = typename + " ";
				    if (level != 0) reply += "{\n";
				    else reply = "{";
				    var isHtml = this.isHTMLElement(data);
				    for (var prop in data) {
				      if (isHtml) {
				        // HTML nodes have far too much stuff. Chop out the constants
				        if (prop.toUpperCase() == prop || prop == "title" ||
				          prop == "lang" || prop == "dir" || prop == "className" ||
				          prop == "form" || prop == "name" || prop == "prefix" ||
				          prop == "namespaceURI" || prop == "nodeType" ||
				          prop == "firstChild" || prop == "lastChild" ||
				          prop.match(/^offset/)) {
				          continue;
				        }
				      }
				      value = "";
				      try {
				        obj = data[prop];
				        if (obj == null || typeof obj == "function") {
				          continue;
				        }
				        else if (typeof obj == "object") {
				          if (level > 0) {
				            value = "\n";
				            value += this.indent(level, depth + 2);
				            value = this.toDescriptiveString(obj, level - 1, depth + 1);
				          }
				          else {
				            value = this.detailedTypeOf(obj);
				          }
				        }
				        else {
				          value = "" + obj;
				          value = value.replace(/\/n/g, "\\n");
				          value = value.replace(/\/t/g, "\\t");
				        }
				      }
				      catch (ex) {
				        value = "" + ex;
				      }
				      if (level == 0 && value.length > 13) value = value.substring(0, 10) + "...";
				      var propStr = prop;
				      if (propStr.length > 30) propStr = propStr.substring(0, 27) + "...";
				      if (level != 0) reply += this.indent(level, depth + 1);
				      reply += prop + ":" + value + ", ";
				      if (level != 0) reply += "\n";
				      i++;
				      if (level == 0 && i > 5) {
				        reply += "...";
				        break;
				      }
				    }
				    reply += this.indent(level, depth);
				    reply += "}";
				    return reply;
				  }
				  return data.toString();
				},
				/*
				 * 从URL中取得指定的参数的值
				 * */
				getUrlParam:function (paramName){
					var parastr =this.getUrlParamString();
					var paramK_V=parastr.split("&");
					var paramValues=new Array();
					for(var i=0;i<paramK_V.length;i++){
						var k_vArray=paramK_V[i].split("=");
						if(paramName==k_vArray[0]){
							paramValues.push(this.trim(k_vArray[1],'#'));
						}
					}
					return paramValues;
				},
				
				
				/*
				 * 从URL中取得指定的参数的值，可多选,返回的是对象，{parma1:1,param2:2},
				 * */
				getUrlParams:function (){
					var parastr =this.getUrlParamString();
					var paramK_V=parastr.split("&");
					var objString="{";
					var params=[];
					for(var i=0;i<paramK_V.length;i++){
						var k_vArray=paramK_V[i].split("=");
						params.push(k_vArray[0]+":'"+k_vArray[1]+"'");
						
					}
					objString+=params.join(",");
					objString+="}";
					return this.objectEval(objString);
				},
				
				/*
				 * 从URL中取得参数参数和值的字符串param1=1&param2=2
				 * */
				getUrlParamString:function (){
					var str,pos,parastr
					str = window.location.href;
					pos = str.indexOf("?");
					return parastr = str.substring(pos+1);
				},
				
				/*
				 * 从将Json对象转换成查询字符串形式
				 * */
				toUrlParamString:function (obj){
					var str="";
					for(property in obj){
						str+="&"+property+"="+obj[property];
					}
					return str;
				},
				/*
				 * 
				 * 根据字符串动态的生成对象如："{userName:'aaa',passWord:'bbbb'}"
				 */
				objectEval:function(text){
				    // eval() breaks when we use it to get an object using the { a:42, b:'x' }
				    // syntax because it thinks that { and } surround a block and not an object
				    // So we wrap it in an array and extract the first element to get around
				    // this.
				    // The regex = [start of line][whitespace]{[stuff]}[whitespace][end of line]
				    text = text.replace(/\n/g, ' ');
				    text = text.replace(/\r/g, ' ');
				    if (text.match(/^\s*\{.*\}\s*$/)){
				      text = '[' + text + '][0]';
				    }
				    return eval(text);
				},
				stringToJson:function(text){
					return this.objectEval(text);
				},
				/** 
				  * @see 将javascript数据类型转换为json字符串 
				  * @param 待转换对象,支持object,array,string,function,number,boolean,regexp 
				  * @return 返回json字符串 
				  */ 
			   jsonToString: function(object) { 
			     var type = typeof object; 
			     if ('object' == type) { 
			       if (Array == object.constructor) type = 'array'; 
			       else if (RegExp == object.constructor) type = 'regexp'; 
			       else type = 'object'; 
			     } 
			     switch (type) { 
			     case 'undefined': 
			     case 'unknown': 
			       return; 
			       break; 
			     case 'function': 
			     case 'boolean': 
			     case 'regexp': 
			       return object.toString(); 
			       break; 
			     case 'number': 
			       return isFinite(object) ? object.toString() : 'null'; 
			       break; 
			     case 'string': 
			       return '"' + object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function() { 
			         var a = arguments[0]; 
			         return (a == '\n') ? '\\n': (a == '\r') ? '\\r': (a == '\t') ? '\\t': "" 
			       }) + '"'; 
			       break; 
			     case 'object': 
			       if (object === null) return 'null'; 
			       var results = []; 
			       for (var property in object) { 
			         var value = this.jsonToString(object[property]); 
			         if (value !== undefined) results.push(this.jsonToString(property) + ':' + value); 
			       } 
			       return '{' + results.join(',') + '}'; 
			       break; 
			     case 'array': 
			       var results = []; 
			       for (var i = 0; i < object.length; i++) { 
			         var value = this.jsonToString(object[i]); 
			         if (value !== undefined) results.push(value); 
			       } 
			       return '[' + results.join(',') + ']'; 
			       break; 
			     } 
			   }, 
				
				//表格序列化工具，扩展jQuery的序列化，并在Param对象中加入参数ID项
				/*
				 *   [
				 *      {id:"", name: "", value: ""}
				 *     ,{id:"", name: "", value: ""}
				 *     ,{id:"", name: "", value: ""}
				 *   ]
				 */
				serializeArray: function(serializeEle,callback) {
					//回调处理，用于收集参数
					callback = callback||function(serializeEle,elem,val){
						var $elem = $(elem);
						return {id:$elem.attr("id"), name: $elem.attr("name"), value: $elem.val()};
					};
					
					var objparam =  jQuery(serializeEle).map(function(){
						return jQuery.nodeName(this, "form") ?
							jQuery.makeArray(this.elements) : this;
					})
					.filter(function(){
						return this.name && !this.disabled &&
							(this.checked || /select|textarea/i.test(this.nodeName) ||
								/text|hidden|password/i.test(this.type));
					})
					.map(function(i, elem){
						var val = jQuery(this).val();
						return val == null ? null :
							val.constructor == Array ?
								jQuery.map( val, function(val, i){
									return callback(serializeEle,elem,val);
								}) :
								callback(serializeEle,elem,val);
					}).get();
					//alert($.utils.toDescriptiveString(objparam,2,2));
					return objparam;
				},
				//表格反序列化工具，扩展jQuery的序列化，并根据在Param对象中参数ID项与Name值设置元素的值
				//暂不支持select的多选情况
				/*
				 *   [
				 *      {id:"", name: "", value: ""}
				 *     ,{id:"", name: "", value: ""}
				 *     ,{id:"", name: "", value: ""}
				 *   ]
				 */				
				reverseSerializeArray: function(serializeEle,paramArray,callback) {
					callback = callback ||function($serializeEle,singleEle,param){
						var id = param.id;
					    var name = param.name;
					    var value = param.value;
					    //alert("name:"+name+"  value:"+value);
					    $(singleEle).val(value);
					    if(jQuery.nodeName(singleEle, "select")){
					    	$(singleEle).change();
					    }
					};
					
					
					if(!this.isArray(paramArray)){
						this.debug("$.utils.reverseSerializeArray方法参数非法，要求是数组类型");
					}
					var $serializeEle =jQuery(serializeEle);
					jQuery.each(paramArray,function(i,param){
					    var id = param.id;
					    var name = param.name;
					    var value = param.value;
					    var singleEle={};
					    if(id&&id!=""){
					    	singleEle = $serializeEle.find("[id="+id+"]").get(0);
					    }else if(name&&name!=""){
					    	singleEle = $serializeEle.find("[name="+name+"]").get(0);
					    }else{
					    	
					    }
					    if(!singleEle){return;}
					    callback($serializeEle,singleEle,param)
					})
				}
				//修复下拉框在IE中内容过大但宽度太小，不能选项值会被隐去的BUG
				,select_fix_ie:function (el) {	
						if($.browser.msie!=true){
							    	return;
						}
								function select_fix_ie_close(el) {
									var isOpen = $(el).data("isOpen");
									if (isOpen) {
										var clone_selection = $(el).data("clone");
										$(clone_selection).css("display", "none");
										$(el).css("position", "");
										$(el).css("width", $(clone_selection).css("width"));
										$(el).css("top", "");
										$(el).css("left", "");
										$(el).css("z-index", $(clone_selection).css("z-index"));
										$(el).data("isOpen", false);
									}
								}
					var isOpen = $(el).data("isOpen");
					if (isOpen != null) {
						return;
					}
				
					$(el).mousedown(function() {
								var isOpen = $(this).data("isOpen");
								if (isOpen) {
									return;
								}
								$(this).data("isOpen", true);
				
								var clone_selection = $(this).data("clone");
								if (clone_selection == null) {
									clone_selection = $("<span></span>").get(0);
									$(clone_selection).appendTo($(this).parent());
									$(clone_selection).css("display", "none");
									$(clone_selection).css("width", $(this).css("width"));
									$(clone_selection).css("height", "20px");
									$(clone_selection).css("position", $(this).css("position"));
									$(clone_selection).css("z-index", $(this).css("z-index"));
									$(this).data("clone", clone_selection);
								}
				
								$(clone_selection).show();
								$(clone_selection).css("visibility", "hidden");
				
								var position = $(this).position();
								var oldWidth = $(this).width();
								$(this).css("width", "auto");
								var newWidth = $(this).width();
								if (oldWidth >= newWidth) {
									$(this).css("width", $(clone_selection).css("width"));
								}
								$(this).css("top", position.top + "px");
								$(this).css("left", position.left + "px");
								$(this).css("position", "absolute");
								$(this).css("z-index", "1000");
							}).blur(function() {
								select_fix_ie_close(this);
							}).change(function() {
								select_fix_ie_close(this);
							})
				
					$(el).data("isOpen", false);
				}
	}	
	jQuery.extend({"utils":utils});


    var windows={
    	openWindow: function (url,w,h,n) {
    		return	openShowModal(url,w,h);
    		/*	
			    var top = (screen.availHeight-h)/2;
				var left = (screen.availWidth-w)/2;
				var options = "width=" + w + ",height=" + h + ",";
			    options += "status=yes,scrollbars=yes,,resizable=yes,location=no,menubar=no,toolbar=no,directories=no,top="+top+",left="+left;
			    if(n == "") {
			    	var date=new Date();
			    	n=date.getSeconds();
			    }
			    n=n+"ab";
			    var newWin=window.open(url,n, options);
			    newWin.focus();
			   
			    $(newWin).blur(function(){
			    	if(newWin){
			    		newWin.focus();
			    	}
			    	
			    	return false;
			    });
			    
			    return newWin;
			*/   
		}
    }
	jQuery.extend({"windows":windows});
	
	
	
    var grids={
         //全选 /取消 
		 checkAll:function(selectedCheckboxName) {
			var $selected = $('input[@name=' + selectedCheckboxName+ ']:not(:disabled)');
			$selected.attr('checked', '');
			if (event.srcElement.checked) {
				var $selectedf = $selected.filter('[show!=false]').attr('checked', 'checked');
			}
		}


    }
    jQuery.extend({"grids":grids});	
    
    
    var events={
    	//阻止事件的冒泡,避免往上触发事件
    	stopEvent:function(){
				var e = window.event;
				if ( e && e.stopPropagation ){
					//因此它支持W3C的stopPropagation()方法
					e.stopPropagation(); 
				}else{
					//否则，我们需要使用IE的方式来取消事件冒泡 
					e.cancelBubble = true;
				}
    	}
    }
	jQuery.extend({"events":events});	
})(jQuery);
