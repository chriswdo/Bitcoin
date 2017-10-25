   <%@page pageEncoding="utf-8" %>
        <div id="qz_layer-check" class="u-padding-top15 up-container-fluid" style="display: none">
            <form class="up-form-inline u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">名称 </label>
                    <input type="text" class="up-form-control" id="qzrw_mc" placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" id="qz_search-create" class="up-btn up-btn-primary up-btn-sm up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
            </form>
            <table class="up-table up-table-bordered up-table-hover">
                <thead>
                    <tr class="up-active">
                   		 <th></th>
                        <th>任务编号</th>
                        <th>名称</th>
                        <th>状态</th>
                        <th>任务描述</th>
                    </tr>
                </thead>
                <tbody id="qz_body-create">
                </tbody>
            </table>
            <div class="up-row">
           		<div class="up-col-md-24">
                	<div id="u_pagination_create_qz" class="up-pull-right">
               		</div>
            	</div>
        	</div>
        </div>