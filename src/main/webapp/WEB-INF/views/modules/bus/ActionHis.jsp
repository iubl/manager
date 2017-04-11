<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/bus/action_his/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/bus/action_his/list");

					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/bus/action_his/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" ><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/bus/action_his/import/template">下载模板</a>
		</form>
	</div>
	<form:form id="searchForm" modelAttribute="actionHis" action="${ctx}/bus/action_his/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>身份证号:</label>
		<form:input path="idCard" htmlEscape="false" maxlength="50" class="input-medium" />
		<label>活动号:</label>
		<form:input path="actionId" htmlEscape="false" maxlength="50" class="input-medium" />
		
		<label>日期范围：&nbsp;</label>
		<input id="createDateStart"  name="createDateStart" path="createDateStart" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${actionHis.createDateStart}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input id="createDateEnd" name="createDateEnd"  path="createDateEnd" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${actionHis.createDateEnd}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;
		<input id="btnExport" class="btn btn-primary" type="button" value="导出" />
		&nbsp;&nbsp;
		<input id="btnImport" class="btn btn-primary" type="button" value="导入" />

	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>业务流水</th>
				<th>身份证号</th>
				<th>活动ID</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="col">
			<tr>
				<td>${col.id}</td>
				<td>${col.idCard}</td>
				<td>${col.actionId}</td>
				<td><fmt:formatDate value="${col.createDate}" type="both"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>