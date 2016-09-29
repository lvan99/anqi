<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zbProductList" checkbox="true" fitColumns="false" title="产品表" actionUrl="zbProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
   <t:dgCol title="产品名称"  field="productName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="价格"  field="price"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格"  field="spec"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="浏览数"  field="seeCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品详情"  field="productDetails"    queryMode="single"  width="240"></t:dgCol>
   <t:dgCol title="图片地址"  field="imgAddr"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除标记"  field="dr"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品编码"  field="code"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="子分类id"  field="typeId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zbProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zbProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zbProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zbProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zbProductController.do?goUpdate" funname="detail"></t:dgToolBar>
<%--    <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/product/zbProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zbProductListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zbProductListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zbProductController.do?upload', "zbProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zbProductController.do?exportXls","zbProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zbProductController.do?exportXlsByT","zbProductList");
}
 </script>