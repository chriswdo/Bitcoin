// JavaScript Document
jQuery.fn.jParameterPan = function(targetId) {
	var _seft = this;
	var targetId = $(targetId);

	this.click(function(){
		var A_top = $(this).offset().top + $(this).outerHeight(true);  //  1
		var A_left =  $(this).offset().left;
		targetId.bgiframe();
		targetId.show().css({"position":"absolute","top":A_top+"px" ,"left":A_left+"px"});
	});

	targetId.find(".btnSubmit").click(function(){
		var arr = new Array();
		var input = targetId.find(":text");
		for(var i=0;i<input.length;i++){
			arr[i] = input.eq(i).val();
		}
		_seft.val(arr);
		targetId.hide();
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