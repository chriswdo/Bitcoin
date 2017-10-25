// JavaScript Document
jQuery.fn.jDefaultValPan = function(targetId) {
	var _seft = this;
	var targetId = $(targetId);

	this.click(function(){
		var A_top = $(this).offset().top + $(this).outerHeight(true);  //  1
		var A_left =  $(this).offset().left;
		targetId.bgiframe();
		targetId.show().css({"position":"absolute","top":A_top+"px" ,"left":A_left+"px"});
	});

	targetId.find("tr").click(function(){
		var arr = new Array();
		var trSeq = $(this).parent().find("tr").index(this);
		if(trSeq!=0){
			var td = $(this).find("td");
			for(var i=0; i<td.length; i++){
				arr[i] = td.eq(i).html();
			}
			_seft.val(arr);
			targetId.hide();
		}
	});

	$(document).click(function(event){
		if(event.target.id!=_seft.selector.substring(1)){
			targetId.hide();	
		}
	});

	targetId.click(function(e){
		e.stopPropagation(); //  2
	});

    return this;
}