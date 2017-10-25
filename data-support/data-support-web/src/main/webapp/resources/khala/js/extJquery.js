/**
 * Create a cookie with the given key and value and other optional parameters.
 * 
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
 * @desc Create a cookie with all available options.
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example $.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value. Keep in mind that you have to use the same path and domain used when the cookie was set.
 * 
 * @param String
 *            key The key of the cookie.
 * @param String
 *            value The value of the cookie.
 * @param Object
 *            options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object. If a negative value is specified (e.g. a date in the past), the cookie will be deleted. If set to null or omitted, the cookie will be a session cookie and will not be retained when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will require a secure protocol (like HTTPS).
 * @type undefined
 * 
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 * 
 * Get the value of a cookie with the given key.
 * 
 * @example $.cookie('the_cookie');
 * @desc Get the value of a cookie.
 * 
 * @param String
 *            key The key of the cookie.
 * @return The value of the cookie.
 * @type String
 * 
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */
$.cookie = function(key, value, options) {
	if (arguments.length > 1 && (value === null || typeof value !== "object")) {
		options = $.extend({}, options);
		if (value === null) {
			options.expires = -1;
		}
		if (typeof options.expires === 'number') {
			var days = options.expires, t = options.expires = new Date();
			t.setDate(t.getDate() + days);
		}
		return (document.cookie = [ encodeURIComponent(key), '=', options.raw ? String(value) : encodeURIComponent(String(value)), options.expires ? '; expires=' + options.expires.toUTCString() : '', options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '', options.secure ? '; secure' : '' ].join(''));
	}
	options = value || {};
	var result, decode = options.raw ? function(s) {
		return s;
	} : decodeURIComponent;
	return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};
/**
 * 防止退格键的网页后退
 * 
 * @author 邓磊
 */
$(document).keydown(function (e) {
  var doPrevent;
  if (e.keyCode == 8) {
      var d = e.srcElement || e.target;
      if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
          doPrevent = d.readOnly || d.disabled;
      }
      else
          doPrevent = true;
  }
  else
      doPrevent = false;

  if (doPrevent)
      e.preventDefault();
});
/**
 * @author 邓磊
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
$.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * @author 李华龙
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成json
 * 
 * @returns object
 */
$.serializeJson = function(formId) {
	 var fid = "#" + formId;   
	 alert(fid);
	 var str = $(fid).serialize();  
	 alert(str);
	 //str= cardSelectDate=3&startdate=2012-02-01&enddate=2012-02-04    
	 str = str.replace(/&/g,"','");    
	 str = str.replace(/=/g,"':'");  
	 str = "({'"+str +"'})";    
	 var obj= eval(str);    
	 return 22;    
	 
}

/**
 * @author 邓磊
 * 
 * 增加formatString功能
 * 
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * @author 邓磊
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
$.stringToList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

/**
 * @author 邓磊
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		// alert(XMLHttpRequest.responseText);
		parent.$.messager.progress('close');
		parent.$.messager.alert('错误', XMLHttpRequest.responseText);
	}
});
/**
 * @author 邓磊
 * 
 * @requires jQuery
 * 
 * 扩展方法
 */
$.extend( {
	/**
	 * 根据给定日期，计算n天的日期
	 * @param dtstr
	 * @param n
	 * @returns
	 */
	addDate:function(dtstr,n) {
        var v = dtstr;
        if (v == "") return false;
        var dt = new Date(v.replace(/\-/g, "\/"));
        dt.setDate(dt.getDate() + n);
        var newdt = dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate();
        return newdt;
          
    },
    /**
     * 根据给定的月，计算n月后的月份
     */
    addMonth:function(dtstr, n) {       //   n个月后
        
        var s = dtstr.split("-");
        var yy = parseInt(s[0]);
        var mm = parseInt(s[1]) - 1; 
        var dd = parseInt(s[2]);
        var dt = new Date(yy, mm, dd); 
        dt.setMonth(dt.getMonth() + n);
        if ((dt.getYear() * 12 + dt.getMonth()) > (yy * 12 + mm + n)) {
            dt = new Date(dt.getYear(), dt.getMonth(), 0); 
        }

        return dt.getYear() + "-" + dt.getMonth() + "-" + dt.getDate();
    }, 
    /**
     * 根据给定的年，计算n年后的年份
     */
    addYear:function(dtstr,n)
    {
        var s = dtstr.split("-");
        var yy = parseInt(s[0]);
        var mm = parseInt(s[1]); 
        var dd = parseInt(s[2]);

        return yy + n + "-" + mm + "-" + dd;
    },
	/***
	 * 获得日期 yyyy-mm-dd
	 */
	getDate:function(n){
		var myDate = new Date();
		myDate.setDate(myDate.getDate()+n);
		var year=myDate.getFullYear();
		var mouth=myDate.getMonth()+1;		
		var day=myDate.getDate();
	
		if(mouth<10){
			mouth="0"+mouth;
		}
		if(day<10){
			day="0"+day;
		}
		var reDay=year+"-"+mouth+"-"+day;
		return reDay;
	},
	getMonth:function(m){
		var myDate = new Date();
		var year=myDate.getFullYear();
		var month=myDate.getMonth()+1+m;	
		
		if(month<0){
			var t=(-month)%12;
			var n=parseInt((-month)/12);
			year=year-n;
			month=t;
		}else{
			var t=(month)%12;
			var n=parseInt((month)/12);
			year=year+n;
			month=t;
		}
		
		var reMonth=year+"-"+(month<10?"0"+month:month);
		return reMonth;
	},
	//判断闰年  
	isLeapYear : function()   
	{   
		var myDate = new Date();		
		return (0==myDate.getYear()%4&&((myDate.getYear()%100!=0)||(myDate.getYear()%400==0)));   
	},
	//求两个时间的天数差  
	daysBetween:function(DateOne,DateTwo,FG)  
	{   
	 var OneMonth = DateOne.substring(5,DateOne.lastIndexOf (FG));  
	 var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf (FG)+1);  
	 var OneYear = DateOne.substring(0,DateOne.indexOf (FG));  

	 var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf (FG));  
	 var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf (FG)+1);  
	 var TwoYear = DateTwo.substring(0,DateTwo.indexOf (FG));  

	 var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
	 return Math.abs(cha);  
	},
	
	
	isSecurity:function(v,ruo,zhong,qiang){
		var iss = {
				width:["50","100","150","10"],
				reset:function(){
					$("#B").html("");
					},
					level0:function(){
					$("#"+ruo).css("background-color","#CC0000").css("color","#fff");
					$("#"+zhong).css("background-color","#fff").css("color","#000");
					$("#"+qiang).css("background-color","#fff").css("color","#000");
					passwordchecked="ruo";
					},
					level1:function(){
					$("#"+zhong).css("background-color","#FFCC33").css("color","#fff");
					$("#"+ruo).css("background-color","#fff").css("color","#000");
					$("#"+qiang).css("background-color","#fff").css("color","#000");
					passwordchecked="zhong";
					},
					level2:function(){
					$("#"+qiang).css("background-color","#66CC00").css("color","#fff");
					$("#"+ruo).css("background-color","#fff").css("color","#000");
					$("#"+zhong).css("background-color","#fff").css("color","#000");
					passwordchecked="qiang";
				}
			} 
		if (v.length < 3) { iss.reset();return;}
		var lv = -1;
		if (v.match(/[a-z]/ig)){lv++;}
		if (v.match(/[0-9]/ig)){lv++;}
		if (v.match(/(.[^a-z0-9])/ig)){lv++;}
		if (v.length < 6 && lv > 0){lv--;}
		iss.reset();
		switch(lv) {
			case 0:
				iss.level0();
			break;
			case 1:
				iss.level1();
			break;
			case 2:
				iss.level2();
			break;
			default:
				iss.reset();
		}
	}

});