var YC ={version:'v1.4',toString:function (){return this.version;},format:{}};
	if(!YC){var YC={format:{}};}
	
	YC.format.DateFormat=function(formatString){
		this.formatString=formatString;
	}
	YC.format.DateFormat.config={
		cnWeek:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		enWeek:['Sun','Mon','Tue','Wed','Thu','Fri','Sat']
	}
	YC.format.DateFormat.prototype.format=function(date){
		if(date){
			if(date instanceof Date){
				    var y = date.getFullYear();  
					var M = date.getMonth() + 1;  
					var d = date.getDate();
					var day=date.getDay();
					var h = date.getHours();  
					var m = date.getMinutes();  
					var s = date.getSeconds();
					var shortY=y%100;
					var shortYStr=shortY<10?'0'+shortY:''+shortY;
					var ms = date.getMilliseconds();
					var strM=M<10?'0'+M:''+M;
					var strd=d<10?'0'+d:''+d;
					var strh=h<10?'0'+h:''+h;
					var strm=m<10?'0'+m:''+m;
					var strs=s<10?'0'+s:''+s;
					var isAfternoon=h>12;
					var astr =isAfternoon?'下午':'上午';
					var hh=h%12;
					var strhh=hh<10?'0'+hh:''+hh;
					var dateString=this.formatString;
					var weekArrayCN= YC.format.DateFormat.config.cnWeek;
					var weekArrayEN= YC.format.DateFormat.config.enWeek;
					return dateString.replace(/y{4}/g,''+y).replace(/y{2}/g,''+shortYStr)
					.replace(/M{2}/g,strM).replace(/M/g,''+M)
					.replace(/d{2}/g,strd).replace(/d/g,''+d)
					.replace(/H{2}/g,strh).replace(/H/g,''+h)
					.replace(/h{2}/g,strhh).replace(/h/g,''+hh)
					.replace(/m{2}/g,strm).replace(/m/g,''+m)
					.replace(/s{2}/g,strs).replace(/s/g,''+s)
					.replace(/a/g,astr).replace(/E/g,weekArrayEN[day]).replace(/CN/g,weekArrayCN[day]);
			}
		}
	
	}
	YC.format.DateFormat.patterns=[
		/^ *(\d{2,4})-(\d{1,2})-(\d{1,2}) *(\d{1,2})?:?(\d{1,2})?:?(\d{1,2})? *$/,
		/^ *(\d{2,4})年(\d{1,2})月(\d{1,2})日 *(\d{1,2})?[:时]?(\d{1,2})?[:分]?(\d{1,2})?秒? *$/,
		/^ *(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2}) *$/,
	]
	YC.format.DateFormat.parse=function(dateStr){
		
		var patterns=YC.format.DateFormat.patterns;
		
		var pattern ={};
		for(var i=0;i<patterns.length;i++){
			
			if(patterns[i].test(dateStr)){
				pattern=patterns[i];
				break;
				
			}
		}
		//alert(dateStr);
		if(pattern=={})return null;
		
		var dateArray=pattern.exec(dateStr);
		var y=dateArray[1];
		var M=dateArray[2];
		var d=dateArray[3];
		var H=dateArray[4];
		var m=dateArray[5];
		var s=dateArray[6];
		var inty=0;
		var intM=0;
		var intd=0;
		var intH=0;
		var intm=0;
		var ints=0;
		if(y!=null&&y!='')inty=parseInt(y);
		if(M!=null&&M!='')intM=parseInt(M);
		if(d!=null&&d!='')intd=parseInt(d);
		if(H!=null&&H!='')intH=parseInt(H);
		if(m!=null&&m!='')intm=parseInt(m);
		if(s!=null&&s!='')ints=parseInt(s);
		if(intM>0)intM--;
		var date =new Date();
		date.setFullYear(inty);
		date.setMonth(intM);
		date.setDate(intd);
		date.setHours(intH);
		date.setMinutes(intm);
		date.setSeconds(ints);
		return date;
	}

	//var format = new YC.format.DateFormat('yyyy-MM-dd-yy E CN hh:mm:ss a yy-M-d H:m:s');
	//alert(format.format(new Date()));
	//var  standarDate=/^ *(\d{2,4})-(\d{1,2})-(\d{1,2}) *(\d{1,2})?:?(\d{1,2})?:?(\d{1,2})? *$/;
	//var cnDate =/^ *(\d{2,4})年(\d{1,2})月(\d{1,2})日 *(\d{1,2})?[:时]?(\d{1,2})?[:分]?(\d{1,2})?秒? *$/;
	//var dateNumber=/^ *(\d{8,8}) *$/;
	//arr = standarDate.exec('   2002-3-32  12:23               ');
	//alert(arr);
	//arr =cnDate.exec('2011年2月03日 16时04分12秒');
	//alert(arr);
	//arr =dateNumber.exec('20110102');
	//alert(arr);
	//YC.format.DateFormat.parse('2011年2月03日 16时04分12秒');
	//document.write( format.format(YC.format.DateFormat.parse('2011年2月03日 16时04分12秒')));