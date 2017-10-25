<%--
  Created by IntelliJ IDEA.
  User: lsl
  Date: 2017/4/24
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>新增主题</title>
    <meta name="description" content="">
    <meta name="keywords" content="">

</head>
<body class="u_scroll">
<%@include file="/common/common.jsp"%>
    <div class="up-container u-padding-left100">
        <form class="u_form u-margin-top20 up-clearfix u_cq_form" style="max-width: 700px;">
            <div class="up-form-group up-col-sm-24">
                <label class="up-control-label">主题名称</label>
                <div class="up-col-sm-12">
                    <input type="text" name="fd_ztmc" id="fd_ztmc" class="up-form-control" placeholder="" >
                </div>
            </div>
            <div class="up-form-group up-col-sm-24">
                <label for="" class="up-control-label">主题状态</label>
                <div id="fd_zt" class="up-col-sm-12">
                    <label class="u_check">
                        <input type="radio" name="fd_zt" value="Y" id="fd_zt_y" checked="true">
                        <i class="up-text-primary icon-u-ok-circle"></i>启用
                    </label>
                    <label class="u_check">
                        <input type="radio" name="fd_zt" value="N" id="fd_zt_n" >
                        <i class="up-text-primary icon-u-circle-thin"></i>停用
                    </label>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24">
                <label for="" class="up-control-label">主题描述</label>
                <div class="up-col-sm-12">
                    <textarea class="up-form-control" id="fd_ms" ></textarea>
                </div>
            </div>

        </form>
        <div class="up-form-group up-col-sm-24">
            <label class="up-control-label"></label>
            <div class="up-col-sm-6 up-text-center">
                <button class="up-btn up-btn-primary" id="save">保 存</button>
            </div>
            <div class="up-col-sm-6">
                <button class="up-btn" id="cancel">取 消</button>
            </div>
        </div>
    </div>
<script>
    require(['uskin','layer'], function() {
        //编辑界面
        if("${editorFlag}"=="1"){
            $("#fd_ztmc").val("${ztObj.fd_ztmc}");
            $("#fd_ms").val("${ztObj.fd_ms}");
            var fd_id = "${ztObj.id}";
            if("${ztObj.fd_zt}"=="Y"){
                $('#fd_zt_y').attr("checked","checked")
            }else{
                $('#fd_zt_n').attr("checked","checked")
            }
        }

        $("#save").click(function(){
            fd_id = $.trim(fd_id);
            var fd_ztmc =  $.trim($("#fd_ztmc").val());
            var fd_zt = $.trim( $("#fd_zt input:radio:checked").val());
            var fd_ms = $.trim($("#fd_ms").val());
            var params = {
                "id":fd_id,
                "fd_ztmc":fd_ztmc,
                "fd_zt":fd_zt,
                "fd_ms":fd_ms
            };
            if(!checkValue(fd_ztmc,fd_ms)){
                return false;
            }

            $.ajax({
                type : "POST",
                url : basePath +'/metaztcontroller/savexNewThemeData',
                dataType : "text",
                data: params,
                success:function (data) {
                    if (data == "success") {
                        layer.open({
                            type: 0,
                            content: '保存成功',
                            offset: '50px',
                            yes:function(index, layero){
                                window.location.href = '${basePath}/metaztcontroller/getAll';
                            }
                        });
        			}else if(data=="sessionTimeout"){
						layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
							layer.closeAll();
						});
                    } else {
                        console.log("保存失败");
                        layer.alert("保存失败");
                    }
                }
            });



            function checkValue(fd_ztmc,fd_ms){
                if(!f_check_str(fd_ztmc)){
                    console.log("请填写主题名称");
                    layer.alert("请填写主题名称!") ;
                    return false;
                }
//        if(!f_check_str(fd_ms)){
//            layer.alert("请填写主题描述!");
//            return false;
//        }
                return true;
            }

            function f_check_str(str){
                if(str=="" || str==undefined || str==null ){
                    return false;
                }
                return true;
            }
        });
        $("#cancel").click(cancelFun);

        function cancelFun(){
            $("#fd_ztmc").val("");
            $("#fd_ms").val("");
            window.location.href='${basePath}/plugins/metadata/themeDefine.jsp';
            return false;
        }
    })

</script>
</body>

</html>
