<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <form:form action="" id="add"  method="post" modelAttribute="browser">
 <c:if test="${error > 0}">  
				<div  class="alert alert-success alert-dismissible">
  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  <div id="message">
  		<c:out value="${message_error}" />
  		
  		</div>
</div>
				</c:if>
				
				 <c:if test="${back > 0}">  
						<input type="hidden" name="backurl" id="back_url" value="${back_url}"/>
				</c:if>
				

			<div class="form-group"> <!-- Browser Name -->
					<label class="control-label " for="browser_name">Browser Name</label>
					<form:input class="form-control" id="browser_name" path="browser_name" />
				</div>
				
					<div class="form-group"> <!-- Browser Type -->
					<label class="control-label " for="browser_type">Browser Type</label>
					<form:input class="form-control" id="browser_type" path="browser_type" />
				</div>
				
					<div class="form-group"> <!-- Browser Type -->
					<label class="control-label " for="remote_url">Remote Url</label>
					<form:input class="form-control" id="remote_url" path="remote_url" />
				</div>
				
				<div class="form-group">
					<input type="hidden" name="action" value="${action}"/>
					<input class="btn btn-primary " name="submit" type="submit" value="submit"/>
				</div>
  </form:form>