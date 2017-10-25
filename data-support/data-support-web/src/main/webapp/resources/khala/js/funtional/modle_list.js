/**
*列表页面
*/

/**
 * <p>全选</p>
 *@param  checkbox
 *@param formId
 *@param name 
 */
function checkAll(checkbox,name){
	var cn=name||'ids';
	$('input[name='+cn+']').attr('checked',!!checkbox.checked);	
}
function listDelete(url,returnurl)
{
	$.ajax({
		url: url,
		type: 'POST',
		data:null,
		dataType: 'html',
		timeout: 1000,
		error: function(){alert('Error loading PHP document');},
		success: function(result){
			//location.reload(true);
			location.replace(returnurl);
			}
		});
	
}
function listRead(id)
{
	$('form[name=list_form]').attr('action','view/'+id).submit();
}
function listModify(id)
{
	$('form[name=list_form]').attr('action','tomodify/'+id).submit();
}

$(function(){
	$("#list_add").click(function(){
		window.location.href="tosave";
		return false;
	});
	
	$("#list_delete").click(function(){
		$('form[name=list_form]').attr('action','deleteAll').submit();
		//return false;
	});
	
	
	
	$("#list_modify").click(function(){
		var ids= $('input[name=ids]');
		var count=0;
		var id="";
		$('input[name=ids]').each(function(i,e){
			if(e.checked){
				count++;
				id=e.value;
			}
			
		});
		if(count!=1){
			alert("只能选择一条记录进行修改！");
			return false;
		}
		//alert(id);
		//if(ids.length>1)alert("只能选一条记录进修改！");
		$('form[name=list_form]').attr('action','tomodify/'+id).submit();
		//return false;
	});
	
	
});


/**
 *翻页 
 * @param p
 */
function goPage(p){
	$('#list_pageNum').val(p);
	//alert($('#list_pageNum').val());
	$('form[name=list_form]').attr('action',baseurl+"list").submit();
}