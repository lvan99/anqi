<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!-- <h4>分类标题</h4> -->
	    <div class="row">
	      <div class="col-md-12 layout-header">
	        <button id="addBtn_ZbFaq" type="button" class="btn btn-default">添加</button>
	        <button id="delBtn_ZbFaq" type="button" class="btn btn-default">删除</button>
	        <script type="text/javascript"> 
			$('#addBtn_ZbFaq').bind('click', function(){   
		 		 var tr =  $("#add_zbFaq_table_template tr").clone();
			 	 $("#add_zbFaq_table").append(tr);
			 	 resetTrNum('add_zbFaq_table');
			 	 return false;
		    });  
			$('#delBtn_ZbFaq').bind('click', function(){   
		       $("#add_zbFaq_table").find("input:checked").parent().parent().remove();   
		        resetTrNum('add_zbFaq_table');
		        return false;
		    });
		    $(document).ready(function(){
		    	if(location.href.indexOf("load=detail")!=-1){
					$(":input").attr("disabled","true");
					$(".datagrid-toolbar").hide();
				}
		    	resetTrNum('add_zbFaq_table');
		    });
		</script>
	      </div>
	    </div>
<div style="margin: 0 15px; background-color: white;">    
	    <!-- Table -->
      <table id="zbFaq_table" class="table table-bordered table-hover" style="margin-bottom: 0;">
		<thead>
	      <tr>
	        <th style="white-space:nowrap;width:50px;">序号</th>
	        <th style="white-space:nowrap;width:50px;">操作</th>
<!-- 					  <th> -->
<!-- 							编码 -->
<!-- 					  </th> -->
					  <th>
							问题标题
					  </th>
					  <th>
							常见问题详情
					  </th>
	      </tr>
	    </thead>
        
	<tbody id="add_zbFaq_table">	
	<c:if test="${fn:length(zbFaqList)  <= 0 }">
			<tr>
				<th scope="row"><div name="xh"></div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="zbFaqList[0].id" type="hidden"/>
					<input name="zbFaqList[0].createName" type="hidden"/>
					<input name="zbFaqList[0].createBy" type="hidden"/>
					<input name="zbFaqList[0].createDate" type="hidden"/>
					<input name="zbFaqList[0].updateName" type="hidden"/>
					<input name="zbFaqList[0].updateBy" type="hidden"/>
					<input name="zbFaqList[0].updateDate" type="hidden"/>
					<input name="zbFaqList[0].sysOrgCode" type="hidden"/>
					<input name="zbFaqList[0].sysCompanyCode" type="hidden"/>
					<input name="zbFaqList[0].bpmStatus" type="hidden"/>
					<input name="zbFaqList[0].pkFaqType" type="hidden"/>
					<input name="zbFaqList[0].code" type="hidden"/>
					<input name="zbFaqList[0].dr" type="hidden"/>
<!-- 				  <td> -->
<!-- 					  	<input name="zbFaqList[0].code" maxlength="32"  -->
<!-- 					  		type="text" class="form-control"  style="width:120px;" > -->
<!-- 					  <label class="Validform_label" style="display: none;">编码</label> -->
<!-- 					</td> -->
				  <td>
					  	<input name="zbFaqList[0].title" maxlength="100" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">问题标题</label>
					</td>
				  <td>
					       	<input name="zbFaqList[0].faqDetail" maxlength="5000" 
						  		type="text" class="form-control"  style="width:120px;"   >
					  <label class="Validform_label" style="display: none;">常见问题详情</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(zbFaqList)  > 0 }">
		<c:forEach items="${zbFaqList}" var="poVal" varStatus="stuts">
			<tr>
				<th scope="row"><div name="xh">${stuts.index+1 }</div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="zbFaqList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zbFaqList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zbFaqList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zbFaqList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zbFaqList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zbFaqList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zbFaqList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zbFaqList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zbFaqList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zbFaqList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
					<input name="zbFaqList[${stuts.index }].pkFaqType" type="hidden" value="${poVal.pkFaqType }"/>
					<input name="zbFaqList[${stuts.index }].code" type="hidden" value="${poVal.code }"/>
					<input name="zbFaqList[${stuts.index }].dr" type="hidden" value="${poVal.dr }"/>
<!-- 				   <td align="left"> -->
<%-- 					  	<input name="zbFaqList[${stuts.index }].code" maxlength="32"  --%>
<%-- 					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.code }"> --%>
<!-- 					  <label class="Validform_label" style="display: none;">编码</label> -->
<!-- 				   </td> -->
				   <td align="left">
					  	<input name="zbFaqList[${stuts.index }].title" maxlength="100" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.title }">
					  <label class="Validform_label" style="display: none;">问题标题</label>
				   </td>
				   <td align="left">
					       	<input name="zbFaqList[${stuts.index }].faqDetail" maxlength="5000" 
						  		type="text" class="form-control"  style="width:120px;"   value="${poVal.faqDetail }">
					  <label class="Validform_label" style="display: none;">常见问题详情</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
