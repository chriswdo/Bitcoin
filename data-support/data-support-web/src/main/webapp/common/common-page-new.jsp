<%@page pageEncoding="utf-8"%>
<%@ page import = "com.eshore.khala.common.model.PageConfig" %>
<input type="hidden" id="list_pageNum" name="pageNum" value="${page.pageNum }">
<c:set var="curr_page" value="${page.pageNum}"/>
<ul data-am-widget="pagination" class="up-pagination up-pagination-default">
	<c:if test="${page.pageCount>1 }">
		<c:if test="${page.pageNum>1 }">
			<li class="firstPageBtn margin_top_5px"><a class="up-icon-step-backward " href="javascript:goPage(1)" title="首页"></a></li>
			<li class="previousPageBtn margin_top_2px margin_left_-6px"><a class=" up-icon-caret-left up-icon-sm" href="javascript:goPage(${page.pageNum-1<=0?page.pageNum:page.pageNum-1})" title="上一页"></a></li>
		</c:if>
		<c:if test="${page.pageNum<=1 }">
			<li class="firstPageBtn margin_top_5px up-disabled"><a class="up-icon-step-backward " href="javascript:goPage(1)" title="首页"></a></li>
			<li class="previousPageBtn margin_top_2px margin_left_-6px up-disabled"><a class=" up-icon-caret-left up-icon-sm" href="javascript:goPage(${page.pageNum-1<=0?page.pageNum:page.pageNum-1})" title="上一页"></a></li>
		</c:if>
		<li>第${page.pageNum}页</li>
	</c:if>
	<c:if test="${page.pageCount>1}">
		<c:if test="${page.pageNum<page.pageCount}">
			<li class="nextPageBtn margin_top_2px"><a class="up-icon-caret-right up-icon-sm" href="javascript:goPage(${page.pageNum+1})" title="下一页"></a></li>
			<li class="lastPageBtn margin_top_5px margin_left_-6px"><a class="up-icon-step-forward" href="javascript:goPage(${page.pageCount})" title="最后一页"></a></li>
		</c:if>
		<c:if test="${page.pageNum>=page.pageCount}">
			<li class="nextPageBtn margin_top_2px up-disabled"><a class="up-icon-caret-right up-icon-sm" href="javascript:goPage(${page.pageNum+1})" title="下一页"></a></li>
			<li class="lastPageBtn margin_top_5px margin_left_-6px up-disabled"><a class="up-icon-step-forward" href="javascript:goPage(${page.pageCount})" title="最后一页"></a></li>
		</c:if>		
	</c:if>
	<li class="m-totalInfo up-fr">共${page.pageCount}页&nbsp&nbsp&nbsp&nbsp共${page.rowCount}条记录</li>	
	</li>
</ul>