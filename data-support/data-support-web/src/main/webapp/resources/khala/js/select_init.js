$(document).ready(function(){  
	$("select").each(function(i,obj){
		var v = $(obj).attr("value")||"";
		var dict_type = $(obj).attr("dict_type")||"";
		if(dict_type!=""){
			$.ajax({
		        url : basePath+"/marketing/rssaleorderbase/searchSelect?rdType=" + dict_type,
		        async : false,
		        dataType : 'json',
		        type: "POST",
		        success: function(data){
		  		  $.each(data.rdList,function(idx,rd){
		      		  var option = document.createElement("option");
		      		  option.text = rd.dictName;
		      		  option.value = rd.dictCode;
		      		  obj.add(option);
				  });
			  		if(v!=""){
						$(obj).val(v);
					}
		        }
		    });
		}else{
			if(v!=""){
				$(obj).val(v);
			}
		}
		
	});
	
	
	
	
}); 