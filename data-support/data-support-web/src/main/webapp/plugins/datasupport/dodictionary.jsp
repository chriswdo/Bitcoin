<%--
  Created by IntelliJ IDEA.
  User: lsl
  Date: 2017/5/10
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>字典明细管理</title>
    <%@include file="/common/common.jsp"%>
    <style>
        ::-ms-clear{display: none;}
    </style>
</head>
<body class="u_scroll">
    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
            <div class="up-form-group">
                <%--<label for="">字典分类 </label>--%>
                <%--<select name="opt" id="selid"  class="up-form-control">--%>
                    <%--<option value="">请选择字典分类</option>--%>
                    <%--<c:forEach items="${selist}" var="item">--%>
                        <%--<option value="${item.FD_FLBM}" >${item.FD_FLMC}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                    <label for="fd_zdfl_nopop" class="up-control-label">字典分类 </label>
                    <div class="up-dropdown multiple_dropdown">
                        <a class="up-dropdown-toggle" id="dropdownMenu" data-toggle="dropdown" aria-expanded="true">
                            <input type="text" class="up-form-control" name="fd_zdfl_nopop" id="fd_zdfl_nopop"  style="width:200px;background:#FFF;" placeholder="请选择字典分类" readonly >
                            <span class="icon-u-down-dir"></span>
                        </a>
                        <ul class="up-dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                            <li class="dropdown_search">
                                <div class="up-form-group nav_search" style="width:100%">
                                    <input type="text" class="up-form-control search" style="width:100%" placeholder="请输入字典分类关键字" >
                                </div>
                            </li>
                            <li class="up-divider"></li>
                            <li>
                                <ul class="arrow-li">
                                    <li role="presentation">
                                        <a role="menuitem"  href="#"><span>请选择</span></a>
                                    </li>
                                    <c:forEach items="${selist}" var="item">
                                        <li role="presentation">
                                            <a role="menuitem" tabindex="${item.FD_FLBM}" href="#"><span>${item.FD_FLMC}${item.FD_FLBM}</span><em class="icon-u-ok"></em></a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </ul>
                    </div>
            </div>
            <div class="up-form-group">
                <label >字典名称 </label>
                <input type="text" class="up-form-control" id="sel_zdmc" placeholder="">
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
            <div class="up-form-group">
                <button type="button" class="up-btn up-btn-sm up-btn-default u-delete"><i class="icon-u-remove"></i>删除
                </button>
            </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th><input type="checkbox" id="selectAll" /></th>
                    <th>字典分类</th>
                    <th>字典名称</th>
                    <th>字典编码</th>
                    <th>字典描述</th>
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
                        <input type="text" id="fd_id" name="fd_id" class="up-form-control">
                    </div>
                </div>
                <%--<div class="up-form-group up-col-sm-24">--%>
                    <%--<label for="fd_zdfl" class="up-control-label">字典分类 </label>--%>
                    <%--<select name="fd_zdfl" id="fd_zdfl" class="up-form-control">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${selist}" var="item">--%>
                            <%--<option value="${item.FD_FLBM}" >${item.FD_FLMC}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</div>--%>
                <div class="up-form-group up-col-sm-24">
                    <label for="fd_zdfl" class="up-control-label">字典分类 </label>
                    <div class="up-dropdown multiple_dropdown">
                        <a class="up-dropdown-toggle" id="dropdownMenu100" data-toggle="dropdown" aria-expanded="true">
                            <input type="text" class="up-form-control" name="fd_zdfl" id="fd_zdfl" placeholder="请选择字典分类">
                            <span class="icon-u-down-dir"></span>
                        </a>
                        <ul class="up-dropdown-menu" role="menu" aria-labelledby="dropdownMenu100">
                            <li class="dropdown_search">
                                <div class="up-form-group nav_search" >
                                    <input type="text" class="up-form-control search" placeholder="请输入字典分类关键字">
                                </div>
                            </li>
                            <li class="up-divider"></li>
                            <li>
                                <ul class="arrow-li">
                                    <c:forEach items="${selist}" var="item">
                                        <li role="presentation">
                                            <a role="menuitem" tabindex="${item.FD_FLBM}" href="#"><span>${item.FD_FLMC}${item.FD_FLBM}</span><em class="icon-u-ok"></em></a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">字典编码 </label>

                    <div class="up-col-sm-24">
                        <input type="text" id="fd_zdbm" name="fd_zdbm" class="up-form-control">
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">字典名称 </label>

                    <div class="up-col-sm-24">
                        <input type="text" id="fd_zdmc" name="fd_zdmc" class="up-form-control">
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">字典描述 </label>

                    <div class="up-col-sm-24">
                        <input type="text" id="fd_zdms" name="fd_zdms" class="up-form-control">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
    function getParams() {
        //filterKeyword();
        var fd_zdmc = $.trim($("#sel_zdmc").val());
        //var fd_flbm = $.trim($("#selid").val());
        var fd_flbm = $(".up-dropdown-menu li.active a").attr("tabindex");
        params = {"fd_flbm":fd_flbm,"fd_zdmc": fd_zdmc};
        return params;
    }
    require(['uskin','layer'], function () {
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
                url: "${basePath}/metazdmxcontroller/ajaxAllData",
                success: function (data) {
                    recordList = data.list;
                    var infoStr = '';
                    for (var i = 0; i < recordList.length; i++) {
                        if (recordList[i].FD_ZDMS == null || recordList[i].FD_ZDMS == "") {
                            recordList[i].FD_ZDMS = "";
                        }
                        infoStr = infoStr + "<tr><td><input type=\"checkbox\" name=\"ids\" value=\""+recordList[i].ID+"\" />"+
//                                "</td><td>" + $("option[value="+recordList[i].FD_FLBM+"]").html() + "</td><td>" +
                                "</td><td>" + recordList[i].FD_FLMC + "</td><td>" +
                                recordList[i].FD_ZDMC +"</td><td>" +
                                recordList[i].FD_ZDBM + "</td><td>" +
                                recordList[i].FD_ZDMS + "</td><td>" +
                                "<button onclick='editorFun(\"" + recordList[i].ID + "\",\"" + recordList[i].FD_ZDBM + "\",\"" + recordList[i].FD_ZDMC + "\",\"" + recordList[i].FD_FLBM + "\",\"" + recordList[i].FD_ZDMS + "\")'  class='up-btn up-btn-default up-btn-xs u-editor'><i class='icon-u-edit'></i>编辑</button>" +
                                "<button onclick='deleteFun(\"" + recordList[i].ID + "\")' class='up-btn up-btn-default up-btn-xs u-detail'><i class='icon-u-eye'></i>删除</button>";
                    }
                    $("tbody").empty();
                    $("tbody").append(infoStr);
                    $("#selectAll").attr("checked",false);
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
        });

//        $("#import").click(function(){
//            $.ajax({
//                type: "POST",
//                url: "/biz/impl/InfoBizImpl.java/getBySql2",// 我要调用InfoBizzImpl类中的getBySql2这个方法
//                contentType: "application/json; charset=utf-8",
//                success: function(msg) {
//                    $("#div1").html(msg);
//                },
//                error: function(xhr,msg,e) {
//                    alert(msg);
//                }
//            });
//
//        });

        $(".u-add").click(function () {
            cleanForm($("#layer-add"));
            $(".multiple_dropdown .up-dropdown-menu li").removeClass('active');
            filterKeyword();
            require(['layer'], function () {
                layer.open({
                    type: 1,
                    title: '新增字典明细',
                    content: $("#layer-add"),
                    btn: ['保存', '取消'],
                    btn1: function (index, layero) {
                        var id = $.trim($("#fd_id").val());
                        var fd_zdbm = $.trim($("#fd_zdbm").val());
                        var fd_zdmc = $.trim($("#fd_zdmc").val());
                        var fd_flbm = $(".up-dropdown-menu li.active a").attr("tabindex");
                        var fd_zdms = $.trim($("#fd_zdms").val());
                        if (!checkValue(fd_zdbm,fd_zdmc,fd_flbm)) {
                            return false;
                        }
                        var params = {
                            "id": id,
                            "fd_zdmc": fd_zdmc,
                            "fd_zdms": fd_zdms,
                            "fd_zdbm": fd_zdbm,
                            "fd_flbm":fd_flbm
                        };
                        layer.load(2)
                        $.ajax({
                            type: "POST",
                            url: basePath + "/metazdmxcontroller/savexDictionaryData",
                            dataType: "text",
                            data: params,
                            success: function (data) {
                                layer.closeAll('loading');
                                if (data == "success") {
                                    layer.alert("保存成功!");
                                    $("#u_pagination").pagination('remote');
                                } else if(data == "fail"){
                                    layer.alert("该字典分类下此字典编码已存在,请修改!");
                                    $("#u_pagination").pagination('remote');
    	            			}else if(data=="sessionTimeout"){
    								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
    									layer.closeAll();
    								});
                                } else{
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
                    success:function(index,layero){
                        $("#add-form .up-dropdown-menu li").show();
                    },
                    cancel: function (index, layero) {
                        cleanupForm();
                        layer.close(index)
                    },
                    area: '500px'
                });
                $("#fd_zdfl").attr("disabled",false);
                $("#fd_zdbm").attr("disabled",false);
            })
        });

        $(".u-delete").click(function () {
            var idlist = new Array();
            $("[name='ids']:checked").each(function(i, element) {
                console.log($(this).val() + i); //获取被选中的值
                idlist.push($(this).val());
            });
            if(!f_check_str(idlist)){
                layer.alert("请至少选择一条数据");
                return false;
            }else{
                require(['layer'], function () {
                    layer.confirm('确认批量删除选中项?', {
                        btn: ['确定', '取消'],
                        btn1: function (index, layero) {
                            $.ajax({
                                data: {'list':JSON.stringify(idlist)},
                                type: "POST",
                                url: basePath + '/metazdmxcontroller/deletexBatch',
                                dataType: "text",
                                success: function (data) {
                                    if (data == "success") {
                                        layer.alert("删除成功!");
                                        $("#u_pagination").pagination('remote');
                                    }  else {
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
        });

    })

    $("#selectAll").click(function(){
        var isChecked = $(this).prop("checked");
        $("input[name='ids']").prop("checked", isChecked);
    });

    function editorFun(id, fd_zdbm, fd_zdmc, fd_flbm,fd_zdms) {
        $(".multiple_dropdown a.up-dropdown-toggle").removeAttr('data-toggle');
        require(['uskin', 'layer'], function () {
            layer.open({
                type: 1,
                title: '修改字典明细',
                content: $("#layer-add"),
                btn: ['保存', '取消'],
                btn1: function (index, layero) {
                    var fd_zdbm = $.trim($("#fd_zdbm").val());
                    var fd_zdmc = $.trim($("#fd_zdmc").val());
                    var fd_zdms = $.trim($("#fd_zdms").val());
                    var fd_flbm = fd_flbm;
                    if (!checkEditorValue(fd_zdmc)) {
                        return false;
                    }
                    var params = {
                        "id": id,
                        "fd_zdbm": fd_zdbm,
                        "fd_zdmc": fd_zdmc,
                        "fd_flbm":fd_flbm,
                        "fd_zdms": fd_zdms
                    };
                    $.ajax({
                        type: "POST",
                        url: basePath + "/metazdmxcontroller/savexDictionaryData",
                        dataType: "text",
                        data: params,
                        success: function (data) {
                            if (data == "success") {
                                layer.alert("保存成功!");
                                $("#u_pagination").pagination('remote');
	            			}else if(data=="sessionTimeout"){
								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
									layer.closeAll();
								});
                            } else {
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
                success:function(index, layero){
                    $(".multiple_dropdown .up-dropdown-menu li[role='presentation']").each(function(){
                        var str=$.trim($(this).find("a").attr("tabindex"));
                        //console.log(str+"aaaaa"+fd_flbm);
                        if(str===fd_flbm){
                            $("#fd_zdfl").val($(this).find("span").text());
                           return;
                        }
                    });

                    $("#id").val(id);
                    console.log(fd_flbm+"------");

                    $("#fd_zdbm").val(fd_zdbm);
                    $("#fd_zdbm").attr("disabled",true);
                    //$("#fd_zdfl").val(fd_flbm);
                    $("#fd_zdfl").attr("disabled",true);
                    $("#fd_zdmc").val(fd_zdmc);
                    $("#fd_zdms").val(fd_zdms);
                },
                cancel: function (index, layero) {
                    cleanupForm();
                    layer.close(index)
                },
                area: '600px'
            });


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
                        url: basePath + '/metazdmxcontroller/deletexDictionary',
                        dataType: "text",
                        data: param,
                        success: function (data) {
                            if (data == "success") {
                                layer.alert("删除成功!");
                                $("#u_pagination").pagination('remote');
                            }  else {
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

    function checkEditorValue(fd_zdmc){
        if (!f_check_str(fd_zdmc)) {
            layer.alert("请填写字典名称!");
            return false;
        }
        if(fd_zdmc.indexOf("%")>=0){
            layer.alert("名称中不可包含 %")
            return false;
        }
        return true;
    }

    function checkValue(fd_zdbm,fd_zdmc,fd_flbm) {
        if (!f_check_str(fd_zdbm)) {
            layer.alert("请填写字典编码!");
            return false;
        }
        if (!f_check_str(fd_zdmc)) {
            layer.alert("请填写字典名称!");
            return false;
        }
        if (!f_check_str(fd_flbm)) {
            layer.alert("请选择字典分类!");
            return false;
        }
        if(fd_zdmc.indexOf("%")>=0){
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
    
    function filterKeyword() {
        require(['uskin'], function() {
            $(".multiple_dropdown a.up-dropdown-toggle").attr("data-toggle","dropdown");
            $(document).on("click", ".multiple_dropdown .up-dropdown-menu li", function() {
                if ($(this).is("[role='presentation']")) {
                    $(".multiple_dropdown .up-dropdown-menu li").removeClass('active');
                    $(this).toggleClass('active');
                }
                var str = '';
                $(this).parents('.multiple_dropdown').find("[role='presentation'].active").each(function () {
                    str= $(this).find("span").text();
                })
                $(this).parents('.multiple_dropdown').find(".up-dropdown-toggle input").val(str.replace(/,$/, ""));
                return false;

            });
            $(document).on("keyup", ".search", function(event) {
                event.stopPropagation();
                var text=$.trim($(this).val());
                $(".multiple_dropdown .up-dropdown-menu li[role='presentation']").hide();
                $(".multiple_dropdown .up-dropdown-menu li[role='presentation']").each(function(){
                    var str=$(this).find("span").text() ;
                    if(str.indexOf(text)>=0){
                        $(this).show();

                    }
                });
            });
            $(document).on("focus", ".multiple_dropdown .up-dropdown-toggle input", function() {
                $(this).blur();
            });
        })
    }
    require(['uskin'], function() {
        $(".multiple_dropdown a.up-dropdown-toggle").attr("data-toggle","dropdown");

        $(document).on("click", ".multiple_dropdown .up-dropdown-menu li", function() {
            if ($(this).is("[role='presentation']")) {
                $(".multiple_dropdown .up-dropdown-menu li").removeClass('active');
                $(this).toggleClass('active');
            }
            var str = '';
            $(this).parents('.multiple_dropdown').find("[role='presentation'].active").each(function () {
                str= $(this).find("span").text();
            })
            $(this).parents('.multiple_dropdown').find(".up-dropdown-toggle input").val(str.replace(/,$/, ""));
            return false;

        });
        $(document).on("keyup", ".search", function(event) {
            event.stopPropagation();
            var text=$.trim($(this).val());
            $(".multiple_dropdown .up-dropdown-menu li[role='presentation']").hide();
            $(".multiple_dropdown .up-dropdown-menu li[role='presentation']").each(function(){
                var str=$(this).find("span").text() ;
                if(str.indexOf(text)>=0){
                    $(this).show();

                }
            });
        });
        $(document).on("focus", ".multiple_dropdown .up-dropdown-toggle input", function() {
            $(this).blur();
        });
    })
    </script>
</body>
</html>
