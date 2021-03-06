<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
  
	<div class="col-xs-12 col-sm-12">
	 <a href="editbrowser?script_id=0" class="btn btn-info" role="button">Add</a>
   <br/>
     <br/>
       <br/>
	                    <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Browsers</h3>
            </div>
            
         
            
  	<table id="result-table" class="table" style="overflow-x:auto;">
  	<thead>
										  <tr>
											<th>Browser Name</th>
                                            <th>Browser</th>
                                            <th>Action</th>
											
										  </tr>
										</thead>
										
										<tbody>
										<c:forEach items="${listbrowsers}" var="br">
										<tr>
										 <td>
        							    <c:out value="${br.browser_name}" />
        							</td>
        								 <td>
        							    <c:out value="${br.remote_url}" />
        							</td>
        							<td><a href="editbrowser?browser_id=${br.browser_id}">Edit</a> | <a href="editbrowser?browser_id=${br.browser_id}&action=delete">Delete</a></td>
										</tr>
										</c:forEach>
										</tbody>
										
  	</table>
  	</div>
  	</div>
</div>