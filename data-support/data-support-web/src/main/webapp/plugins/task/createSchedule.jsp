   <%@page pageEncoding="utf-8" %>
    <div id="layer-create" class="u-padding-top5 up-container-fluid" style="display: none">
        <form  id="form-create" class="u_form u-margin-top20 up-clearfix u_cq_form" >
            <div class="up-form-group up-col-sm-24">
                <label class="up-control-label">规则名称 </label>
                <div class="up-col-sm-18">
                    <input type="text" name="fd_gzmc" id="fd_gzmc" class="up-form-control" placeholder=""  readonly="readonly" >
                </div>
                <div class="up-col-sm-4">
                    <button type="button" id="gz_check" class="up-btn up-btn-sm up-btn-default up-pull-right u-check"> <i class="icon-u-check"></i> 选择
                    </button>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24">
                <label for="" class="up-control-label">任务描述 </label>
                <div class="up-col-sm-18">
                    <textarea class="up-form-control" id="fd_rwms"  style="resize:none" rows="2"></textarea>
                </div>
            </div>
           	<div class="up-form-group up-col-sm-24" ${fd_lx=='1'?'':'hidden'}>
				<label for="fd_qzrw_id" class="up-control-label" >采集调度 </label> 
				<div class="up-col-sm-18" id="qz_div" > 
					    <table class="up-table up-table-bordered up-table-hover up-table-reset">
			                <thead>
			                    <tr class="up-active">
			                        <th style="padding:5px;width:190px">名称</th>
			                        <th style="padding:5px">状态</th>
			                        <th style="padding:5px">操作</th>
			                    </tr>
			                </thead>
			                <tbody id="qz_body">
			                </tbody>
			            </table>
				</div>
				<div class="up-col-sm-4">
                    <button type="button" id="qz_check" class="up-btn up-btn-sm up-btn-default up-pull-right u-check"> <i class="icon-u-check"></i> 选择
                    </button>
                </div>
			</div>
			 <div class="up-form-group up-col-sm-24" ${fd_lx=='2'?'':'hidden'}>
				<label for="fd_sjldw" class="up-control-label">挖掘周期 </label> 
				<div class="up-col-sm-18">
					<select name=""fd_sjldw"" id="fd_sjldw" class="up-form-control ">
							  <option value="">请选择周期</option>
						      <option value="M">月</option>
						      <option value="y">年</option>
					</select>
				</div>
			</div>
			<div class="up-form-group up-col-sm-24">
                <label for="" class="up-control-label">首次启动时间 </label>
                <div class="up-col-sm-18">
                    <input type="text"  id="fd_scqdsj" class="up-form-control u-daterange-single-init"  >
                    <i class="u_form_icon icon-u-calendar"></i>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24"  ${fd_lx=='2'?'hidden':''}>
                <label for="" class="up-control-label">起始数据时间戳 </label>
                <div class="up-col-sm-18">
                    <input type="text"  id="fd_sjsjc" class="up-form-control u-daterange-single-init"  >
                    <i class="u_form_icon icon-u-calendar"></i>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24"  ${fd_lx=='2'?'hidden':''}>
                <label for="" class="up-control-label">调度频率 </label>
                <div class="up-col-sm-18">
                    <input type="text" class="up-form-control" id="fd_ddpl" placeholder='cron表达式，如"0 0 12 * * ?"'>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24" ${fd_lx=='2'?'hidden':''}>
                <label for="" class="up-control-label">处理数据量/次 </label>
                <div class="up-col-sm-3">
                    <input type="text" class="up-form-control" id="fd_sjl" placeholder="">
                </div>
                <div class="up-col-sm-18">
                    <div class="u_form_textmiddle up-cq-pad-left">分钟</div>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24"  ${fd_lx!='2'?'hidden':''}>
                <label for="" class="up-control-label">智能调整 </label>
                <div id="fd_zntz"  class="up-col-sm-18">
                    <label class="u_check">
                        <input type="radio" name="fd_zntz" value="Y" id="fd_zntz_y" >
                        <i class="up-text-primary icon-u-ok-circle"></i>启用
                    </label>
                    <label class="u_check">
                        <input type="radio" name="fd_zntz" value="N" id="fd_zntz_n" checked="true">
                        <i class="up-text-primary icon-u-circle-thin"></i>不启用
                    </label>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24" style="margin-bottom:0">
                <label for="" class="up-control-label">是否启用 </label>
                <div id="fd_rwzt" class="up-col-sm-18">
                    <label class="u_check">
                        <input type="radio" name="fd_rwzt" value="Y" id="fd_rwzt_y" >
                        <i class="up-text-primary icon-u-ok-circle"></i>启用
                    </label>
                    <label class="u_check">
                        <input type="radio" name="fd_rwzt" value="N"  id="fd_rwzt_n" checked="true">
                        <i class="up-text-primary icon-u-circle-thin"></i>停用
                    </label>
                </div>
            </div>
        </form>
     </div>
     
     
        <div id="layer-check" class="u-padding-top15 up-container-fluid" style="display: none">
            <form class="up-form-inline u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">规则名称 </label>
                    <input type="text" class="up-form-control" id="fd_gzmc_c" placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" id="search-create" class="up-btn up-btn-primary up-btn-sm up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
            </form>
            <table class="up-table up-table-bordered up-table-hover">
                <thead>
                    <tr class="up-active">
                        <th>规则名称</th>
                        <th>输入库</th>
                        <th>输出库</th>
                        <th>数据操作</th>
                        <th>操作描述</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="body-create">
                </tbody>
            </table>
            <div class="up-row">
           		<div class="up-col-md-24">
                	<div id="u_pagination_create" class="up-pull-right">
                </div>
            </div>
        </div>
        </div>