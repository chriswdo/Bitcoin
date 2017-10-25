/**
*修改页面
*
*/

/**
 * <p>全选</p>
 *@param  checkbox
 *@param formId
 *@param name 
 */

$(function(){
	$("#modle_save").click(function(){
		$('form[name=form_item]').submit();
	});
	$("#modle_close").click(function(){
		//alert(window.close);
		try{
			//window.close();
			history.go(-1);
		}catch(e){}
		window.history.go(-1);
	});
});