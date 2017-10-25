<%--
  Created by IntelliJ IDEA.
  User: lsl
  Date: 2017/5/10
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>字典分类管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
          name="viewport" id="viewport"/>
    <meta name="renderer" content="webkit">
    <meta name="description" content="">
    <meta name="keywords" content="">

</head>
<body class="u_scroll">
<%@include file="/common/common.jsp" %>
    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
            <div class="up-form-group">
                <label for="">字典分类名称 </label>
                <input type="text" class="up-form-control" id="sel_zdfl" placeholder="">
            </div>
            <div class="up-form-group">
                <button type="button" class="up-btn up-btn-sm up-btn-sm up-btn-primary up-btn-block" id="selbtn"><i
                        class="icon-u-search"></i>查询
                </button>
            </div>
            <div class="up-form-group">
                <button type="button" class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i>新增
                </button>
            </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
            <tr class="up-active">
                <th>字典分类编码</th>
                <th>字典分类名称</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
        <div id="layer-add" class="u-padding-top15 up-container-fluid" style="display: none">
            <form id="add-form" class="u_form">
                <div class="up-form-group up-col-sm-24" style="display: none">
                    <label class="up-control-label">id：</label>

                    <div class="up-col-sm-24">
                        <input type="text" id="id" name="id" class="up-form-control">
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">字典分类编码 </label>

                    <div class="up-col-sm-24">
                        <input type="text" id="fd_flbm" name="fd_flbm" class="up-form-control">
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">字典分类名称 </label>

                    <div class="up-col-sm-24">
                        <input type="text" id="fd_flmc" name="fd_flmc" class="up-form-control">
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24">
                    <label for="" class="up-control-label">状态 </label>

                    <div id="fd_zt" class="up-col-sm-12">
                        <label class="u_check">
                            <input type="radio" name="fd_zt" value="Y" id="fd_zt_y" checked="true">
                            <i class="up-text-primary icon-u-ok-circle"></i>启用
                        </label>
                        <label class="u_check">
                            <input type="radio" name="fd_zt" value="N" id="fd_zt_n">
                            <i class="up-text-primary icon-u-circle-thin"></i>停用
                        </label>
                    </div>
                </div>
            </form>
        </div>

    </div>
    <script>

    function getParams() {
        var fd_flmc = $.trim($("#sel_zdfl").val());
        console.log(fd_flmc);
        params = {"fd_flmc": fd_flmc};
        return params;
    }
    require(['uskin'], function () {
        $("#u_pagination").pagination({
            pageIndex: 0,
            pageSize: 10,
            total: 60,
            pageBtnCount: 8,
            showFirstLastBtn: true,
            alwaysBtnShow: true,
            showJump: true,
            showPageSizes: true,
            pageSizeItems: [5, 10, 15, 20],
            pageElementSort: ['$page', '$size', '$jump', '$info'],
            firstBtnText: "首页",
            lastBtnText: "尾页",
            prevBtnText: "上一页",
            nextBtnText: "下一页",
            jumpBtnText: "跳转",
            loadFirstPage: true,
            showInfo: true,
            infoFormat: "{start} ~ {end} 共 {total} 条数据",
            noInfoText: "没有任何数据",
            remote: {
                url: "${basePath}/metazdflcontroller/ajaxAllData",
                success: function (data) {
                    recordList = data.list;
                    var infoStr = '';
                    for (var i = 0; i < recordList.length; i++) {
                        var zt;
                        if (recordList[i].FD_ZT == 'N') {
                            zt = "停用";
                        } else {
                            zt = "启用";
                        }
                        infoStr = infoStr + "<tr><td>" + recordList[i].FD_FLBM + "</td><td>" +
                                recordList[i].FD_FLMC + "</td><td>" + zt + "</td><td>" +
                                "<button onclick='editorFun(\"" + recordList[i].ID + "\",\"" + recordList[i].FD_FLBM + "\",\"" + recordList[i].FD_FLMC + "\",\"" + recordList[i].FD_ZT + "\")'  class='up-btn up-btn-default up-btn-xs u-editor'><i class='icon-u-edit'></i>编辑</button>" +
                                "<button onclick='deleteFun(\"" + recordList[i].ID + "\")' class='up-btn up-btn-default up-btn-xs u-detail'><i class='icon-u-eye'></i>删除</button>";
                    }
                    $("tbody").empty();
                    $("tbody").append(infoStr);
                },
                totalName: 'total',
                pageParams: function (data) {
                    return {
                        pageNum: data.pageIndex + 1,
                        pageSize: data.pageSize
                    };
                }
            }
        });

        $("#selbtn").click(function () {
            //提取参数
            params = getParams();
            require(['layer'], function () {
                $("#u_pagination").pagination('setParams', params);
                $("#u_pagination").pagination('remote');
            })
        })

        $(".u-add").click(function () {
            cleanForm($("#layer-add"))
            require(['layer'], function () {
                layer.open({
                    type: 1,
                    title: '新增字典分类',
                    content: $("#layer-add"),
                    btn: ['保存', '取消'],
                    btn1: function (index, layero) {
                        var id = $.trim($("#fd_id").val());
                        var fd_flbm = $.trim($("#fd_flbm").val());
                        var fd_flmc = $.trim($("#fd_flmc").val());
                        var fd_zt = $.trim($("#fd_zt input:radio:checked").val());
                        console.log("新增里的"+fd_zt);
                        if (!checkValue(fd_flbm,fd_flmc)) {
                            return false;
                        }
                        var params = {
                            "id": id,
                            "fd_flbm": fd_flbm,
                            "fd_flmc": fd_flmc,
                            "fd_zt": fd_zt
                        };
                        layer.load(2)
                        $.ajax({
                            type: "POST",
                            url: basePath + "/metazdflcontroller/savexDictionaryData",
                            dataType: "text",
                            data: params,
                            success: function (data) {
                                layer.closeAll('loading');
                                if (data == "success") {
                                    layer.alert("保存成功!");
                                    resetRadioButton();
                                    $("#u_pagination").pagination('remote');
                                } else if(data == "fail"){
                                    resetRadioButton();
                                    layer.alert("该字典分类编码已存在,请修改!");
    	            			}else if(data=="sessionTimeout"){
    								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
    									layer.closeAll();
    								});
                                } else{
                                    resetRadioButton();
                                    layer.alert("保存失败!");
                                }
                            }
                        });
                        layer.close(index)
                    },
                    btn2: function (index, layero) {
                        cleanupForm();
                        layer.close(index)
                    },
                    cancel: function (index, layero) {
                        cleanupForm();
                        layer.close(index)
                    },
                    area: '500px',
                });
                $("#fd_flbm").attr("disabled",false);
            })
        });

    })

    function editorFun(id, fd_flbm, fd_flmc, fd_zt) {
        require(['uskin', 'layer'], function () {
            layer.open({
                type: 1,
                title: '修改字典分类',
                content: $("#layer-add"),
                btn: ['保存', '取消'],
                btn1: function (index, layero) {
                    var fd_flmc = $.trim($("#fd_flmc").val());
                    var fd_zt = $.trim($("#fd_zt input:radio:checked").val());
                    var fd_ms = $.trim($("#fd_ms").val());
                    if (!checkValue(fd_flbm,fd_flmc)) {
                        return false;
                    }
                    var params = {
                        "id": id,
                        "fd_flbm":fd_flbm,
                        "fd_flmc": fd_flmc,
                        "fd_zt": fd_zt
                    };
                    $.ajax({
                        type: "POST",
                        url: basePath + "/metazdflcontroller/savexDictionaryData",
                        dataType: "text",
                        data: params,
                        success: function (data) {
                            if (data == "success") {
                                layer.alert("保存成功!");
                                resetRadioButton();
                                $("#u_pagination").pagination('remote');
	            			}else if(data=="sessionTimeout"){
								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
									layer.closeAll();
								});
                            } else {
                                resetRadioButton();
                                layer.alert("保存异常!")
                            }
                        }
                    });
                    layer.close(index)
                },
                btn2: function (index, layero) {
                    cleanupForm();
                    layer.close(index)
                },
                cancel: function (index, layero) {
                    cleanupForm();
                    layer.close(index)
                },
                area: '600px'
            });
            $("#id").val(id);
            $("#fd_flbm").val(fd_flbm);
            $("#fd_flbm").attr("disabled",true);
            $("#fd_flmc").val(fd_flmc);
            console.log(fd_zt);
            if (fd_zt == "Y") {
                resetRadioButton();
               // $('#fd_zt_y').attr("checked","checked")
                console.log("启用");
            } else {
                $(".u_check").find("i").removeClass("icon-u-ok-circle").removeClass("icon-u-circle-thin");
                $('#fd_zt_n').attr("checked", true);
                $('#fd_zt_n').next().addClass("icon-u-ok-circle");
                $('#fd_zt_y').removeAttr("checked");
                $('#fd_zt_y').next().addClass("icon-u-circle-thin");
                console.log("停用");
            }
        })
    }

    function deleteFun(id) {
        require(['layer'], function () {
            layer.confirm('确认删除', {
                btn: ['确定', '取消'],
                btn1: function (index, layero) {
                    var param = {"id": id};
                    $.ajax({
                        type: "POST",
                        url: basePath + '/metazdflcontroller/deletexDictionary',
                        dataType: "text",
                        data: param,
                        success: function (data) {
                            if (data == "success") {
                                layer.alert("删除成功!");
                                $("#u_pagination").pagination('remote');
                            } else if(data == "fail"){
                                layer.alert("字典明细中该分类编码下存在数据，不可删除!");
                            } else{
                                layer.alert("删除异常!");
                            }
                        }
                    });
                    layer.close(index)
                },
                btn2: function (index, layero) {
                    layer.close(index)
                },
                area: '300px'
            });
        })
    }
    //objE为form表单
    function cleanForm(objE) {//objE为form表单
        $(objE).find(':input.up-form-control').each(
            function () {
                $(this).val('');
            }
        );
    }

    function resetRadioButton(){
        $(".u_check").find("i").removeClass("icon-u-ok-circle").removeClass("icon-u-circle-thin");
        $('#fd_zt_y').attr("checked", true);
        $('#fd_zt_y').next().addClass("icon-u-ok-circle");
        $('#fd_zt_n').removeAttr("checked");
        $('#fd_zt_n').next().addClass("icon-u-circle-thin");
    }

    function checkValue(fd_flbm,fd_flmc) {
        var a ="%";
        if (!f_check_str(fd_flbm)) {
            layer.alert("请填写字典分类编码!");
            return false;
        }
        if (!f_check_str(fd_flmc)) {
            layer.alert("请填写字典分类名称!");
            return false;
        }
        if(fd_flmc.indexOf("%")>=0){
            layer.alert("名称中不可包含 %")
            return false;
        }
        return true;
    }

    function f_check_str(str) {
        if (str == "" || str == undefined || str == null) {
            return false;
        }
        return true;
    }

    function cleanupForm() {
        $("form .icon-u-warning").remove();
        $("form .u_form_remind").remove();
        $("form .up-has-warning").removeClass("up-has-warning");
    }
    </script>
</body>
</html>
