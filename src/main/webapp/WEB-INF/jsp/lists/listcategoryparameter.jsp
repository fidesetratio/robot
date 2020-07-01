<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
  
	<div class="col-xs-12 col-sm-12">
	 <a href="editcategory?cat_id=0" class="btn btn-info" role="button">Add</a>
   <br/>
     <br/>
       <br/>
	                    <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Category Parameter</h3>
            </div>
            
         
            
  	<table id="result-table" class="table" style="overflow-x:auto;">
  	<thead>
										  <tr>
											<th>Category Name</th>
                                            <th>Description</th>
                                            <th>Action</th>
										  </tr>
										</thead>
										
										<tbody>
										<c:forEach items="${listcategoryparameter}" var="br">
										<tr>
										 <td>
        							    <c:out value="${br.cat_name}" />
        							</td>
        								 <td>
        							    <c:out value="${br.description}" />
        							</td>
        							<td><a href="editcategory?cat_id=${br.cat_id}">Edit</a> | <a href="editcategory?cat_id=${br.cat_id}&action=delete">Delete</a> | <a href="parameters?cat_id=${br.cat_id}">Add Parameters</a></td>
										</tr>
										</c:forEach>
										</tbody>
										
  	</table>
  	</div>
  	</div>
</div>