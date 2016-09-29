<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>产品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zbProductController.do?doUpdate" tiptype="1" >
				<input id="id" name="id" type="hidden" value="${zbProductPage.id }">
				<input id="createName" name="createName" type="hidden" value="${zbProductPage.createName }">
				<input id="createBy" name="createBy" type="hidden" value="${zbProductPage.createBy }">
				<input id="createDate" name="createDate" type="hidden" value="${zbProductPage.createDate }">
				<input id="updateName" name="updateName" type="hidden" value="${zbProductPage.updateName }">
				<input id="updateBy" name="updateBy" type="hidden" value="${zbProductPage.updateBy }">
				<input id="updateDate" name="updateDate" type="hidden" value="${zbProductPage.updateDate }">
				<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zbProductPage.sysOrgCode }">
				<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zbProductPage.sysCompanyCode }">
				<input id="bpmStatus" name="bpmStatus" type="hidden" value="${zbProductPage.bpmStatus }">
				<input id="seeCount" name="seeCount" type="hidden" value="${zbProductPage.seeCount }">
				<input id="dr" name="dr" type="hidden" value="${zbProductPage.dr }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">产品名称:</label>
		     	 <input id="productName" name="productName" type="text" style="width: 150px" class="inputxt"  value='${zbProductPage.productName}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">价格:</label>
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  value='${zbProductPage.price}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">规格:</label>
		     	 <input id="spec" name="spec" type="text" style="width: 150px" class="inputxt"  value='${zbProductPage.spec}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">产品详情:</label>
			    <textarea id="productDetails" style="width:600px;" class="inputxt" rows="6" name="productDetails">${zbProductPage.productDetails}</textarea>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">图片地址:</label>
		     	 <input id="imgAddr" name="imgAddr" type="text" style="width: 150px" class="inputxt"  value='${zbProductPage.imgAddr}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">产品编码:</label>
		     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt"  value='${zbProductPage.code}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">类型:</label>
		      	 <select id="typeId" name="typeId" style="width: 150px" class="inputxt" >
		      	 	 <option value="">--------请选择--------</option>
		      	 </select>
<%-- 		     	 <input id="typeId" name="typeId" type="text" style="width: 150px" class="inputxt"  value='${zbProductPage.typeId}'> --%>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/product/zbProduct.js"></script>		
