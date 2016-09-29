<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!-- <h4>分类标题</h4> -->
	    <div class="row">
	      <div class="col-md-12 layout-header">
	        <button id="addBtn_ZbProductTypeH" type="button" class="btn btn-default">添加</button>
	        <button id="delBtn_ZbProductTypeH" type="button" class="btn btn-default">删除</button>
	        <script type="text/javascript"> 
			$('#addBtn_ZbProductTypeH').bind('click', function(){   
		 		 var tr =  $("#add_zbProductTypeH_table_template tr").clone();
			 	 $("#add_zbProductTypeH_table").append(tr);
			 	 resetTrNum('add_zbProductTypeH_table');
			 	 return false;
		    });  
			$('#delBtn_ZbProductTypeH').bind('click', function(){   
		       $("#add_zbProductTypeH_table").find("input:checked").parent().parent().remove();   
		        resetTrNum('add_zbProductTypeH_table');
		        return false;
		    });
		    $(document).ready(function(){
		    	if(location.href.indexOf("load=detail")!=-1){
					$(":input").attr("disabled","true");
					$(".datagrid-toolbar").hide();
				}
		    	resetTrNum('add_zbProductTypeH_table');
		    });
		</script>
	      </div>
	    </div>
<div style="margin: 0 15px; background-color: white;">    
	    <!-- Table -->
      <table id="zbProductTypeH_table" class="table table-bordered table-hover" style="margin-bottom: 0;">
		<thead>
	      <tr>
	        <th style="white-space:nowrap;width:50px;">序号</th>
	        <th style="white-space:nowrap;width:50px;">操作</th>
					  <th>
							类型名称
					  </th>
	      </tr>
	    </thead>
        
	<tbody id="add_zbProductTypeH_table">	
	<c:if test="${fn:length(zbProductTypeHList)  <= 0 }">
			<tr>
				<th scope="row"><div name="xh"></div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="zbProductTypeHList[0].id" type="hidden"/>
					<input name="zbProductTypeHList[0].createName" type="hidden"/>
					<input name="zbProductTypeHList[0].createBy" type="hidden"/>
					<input name="zbProductTypeHList[0].createDate" type="hidden"/>
					<input name="zbProductTypeHList[0].updateName" type="hidden"/>
					<input name="zbProductTypeHList[0].updateBy" type="hidden"/>
					<input name="zbProductTypeHList[0].updateDate" type="hidden"/>
					<input name="zbProductTypeHList[0].sysOrgCode" type="hidden"/>
					<input name="zbProductTypeHList[0].sysCompanyCode" type="hidden"/>
					<input name="zbProductTypeHList[0].bpmStatus" type="hidden"/>
					<input name="zbProductTypeHList[0].code" type="hidden"/>
					<input name="zbProductTypeHList[0].mainType" type="hidden"/>
					<input name="zbProductTypeHList[0].dr" type="hidden"/>
				  <td>
					  	<input name="zbProductTypeHList[0].typeName" maxlength="50" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">类型名称</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(zbProductTypeHList)  > 0 }">
		<c:forEach items="${zbProductTypeHList}" var="poVal" varStatus="stuts">
			<tr>
				<th scope="row"><div name="xh">${stuts.index+1 }</div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="zbProductTypeHList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zbProductTypeHList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zbProductTypeHList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zbProductTypeHList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zbProductTypeHList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zbProductTypeHList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zbProductTypeHList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zbProductTypeHList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zbProductTypeHList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zbProductTypeHList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
					<input name="zbProductTypeHList[${stuts.index }].code" type="hidden" value="${poVal.code }"/>
					<input name="zbProductTypeHList[${stuts.index }].mainType" type="hidden" value="${poVal.mainType }"/>
					<input name="zbProductTypeHList[${stuts.index }].dr" type="hidden" value="${poVal.dr }"/>
				   <td align="left">
					  	<input name="zbProductTypeHList[${stuts.index }].typeName" maxlength="50" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.typeName }">
					  <label class="Validform_label" style="display: none;">类型名称</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
