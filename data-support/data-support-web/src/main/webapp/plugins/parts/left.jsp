<%@page pageEncoding="utf-8"%>
<!-- sidebar start -->


<script>
    $(document).on('click', "#u_sidebar a,.u-nodestip-active a", function() {
        if ($(this).attr("href") !== "javascript:void(0);" && $(this).attr("target") !== "_blank") {
            $("#jg_iframe").attr("src", $(this).attr("href"));
            return false;
        }
    });
    $(document).on('click', ".u_sidebar_btn", function() {
        $(".jg_cont").toggleClass('cont_slide');
    });
    require(['uskin'], function() {
        var setting = {
            shrink: false,
            shrinkToggleBtn: true,
            accordion: true,
            nodestip: true
        };
        $.ajax({
      		type : "POST",
      		url : basePath + "/userManage/ajaxNodes",
      		dataType : "json",
      		success: function (data) {
      			var nodes = data;
      			$("#u_sidebar").sidebar(nodes, setting);
      	    }
        }); 
    });
</script>
    
<!-- sidebar end -->