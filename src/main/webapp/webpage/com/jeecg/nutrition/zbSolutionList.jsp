<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!-- <h4>分类标题</h4> -->
	    <div class="row">
	      <div class="col-md-12 layout-header">
	        <button id="addBtn_ZbSolution" type="button" class="btn btn-default">添加</button>
	        <button id="delBtn_ZbSolution" type="button" class="btn btn-default">删除</button>
	        <script type="text/javascript"> 
			$('#addBtn_ZbSolution').bind('click', function(){   
		 		 var tr =  $("#add_zbSolution_table_template tr").clone();
			 	 $("#add_zbSolution_table").append(tr);
			 	 resetTrNum('add_zbSolution_table');
			 	 return false;
		    });  
			$('#delBtn_ZbSolution').bind('click', function(){   
		       $("#add_zbSolution_table").find("input:checked").parent().parent().remove();   
		        resetTrNum('add_zbSolution_table');
		        return false;
		    });
		    $(document).ready(function(){
		    	if(location.href.indexOf("load=detail")!=-1){
					$(":input").attr("disabled","true");
					$(".datagrid-toolbar").hide();
				}
		    	resetTrNum('add_zbSolution_table');
		    });
		</script>
	      </div>
	    </div>
<div style="margin: 0 15px; background-color: white;">    
	    <!-- Table -->
      <table id="zbSolution_table" class="table table-bordered table-hover" style="margin-bottom: 0;">
		<thead>
	      <tr>
	        <th style="white-space:nowrap;width:50px;">序号</th>
	        <th style="white-space:nowrap;width:50px;">操作</th>
					  <th>
							解决方案标题
					  </th>
					  <th>
							解决方案详情
					  </th>
	      </tr>
	    </thead>
        
	<tbody id="add_zbSolution_table">	
	<c:if test="${fn:length(zbSolutionList)  <= 0 }">
			<tr>
				<th scope="row"><div name="xh"></div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="zbSolutionList[0].id" type="hidden"/>
					<input name="zbSolutionList[0].createName" type="hidden"/>
					<input name="zbSolutionList[0].createBy" type="hidden"/>
					<input name="zbSolutionList[0].createDate" type="hidden"/>
					<input name="zbSolutionList[0].updateName" type="hidden"/>
					<input name="zbSolutionList[0].updateBy" type="hidden"/>
					<input name="zbSolutionList[0].updateDate" type="hidden"/>
					<input name="zbSolutionList[0].sysOrgCode" type="hidden"/>
					<input name="zbSolutionList[0].sysCompanyCode" type="hidden"/>
					<input name="zbSolutionList[0].bpmStatus" type="hidden"/>
					<input name="zbSolutionList[0].code" type="hidden"/>
					<input name="zbSolutionList[0].pkSolutionType" type="hidden"/>
					<input name="zbSolutionList[0].dr" type="hidden"/>
				  <td>
					  	<input name="zbSolutionList[0].title" maxlength="100" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">解决方案标题</label>
					</td>
				  <td>
					  	<input name="zbSolutionList[0].solutionDetail" maxlength="5000" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">解决方案详情</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(zbSolutionList)  > 0 }">
		<c:forEach items="${zbSolutionList}" var="poVal" varStatus="stuts">
			<tr>
				<th scope="row"><div name="xh">${stuts.index+1 }</div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="zbSolutionList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zbSolutionList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zbSolutionList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zbSolutionList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zbSolutionList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zbSolutionList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zbSolutionList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zbSolutionList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zbSolutionList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zbSolutionList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
					<input name="zbSolutionList[${stuts.index }].code" type="hidden" value="${poVal.code }"/>
					<input name="zbSolutionList[${stuts.index }].pkSolutionType" type="hidden" value="${poVal.pkSolutionType }"/>
					<input name="zbSolutionList[${stuts.index }].dr" type="hidden" value="${poVal.dr }"/>
				   <td align="left">
					  	<input name="zbSolutionList[${stuts.index }].title" maxlength="100" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.title }">
					  <label class="Validform_label" style="display: none;">解决方案标题</label>
				   </td>
				   <td align="left">
					  	<input name="zbSolutionList[${stuts.index }].solutionDetail" maxlength="5000" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.solutionDetail }">
					  <label class="Validform_label" style="display: none;">解决方案详情</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
