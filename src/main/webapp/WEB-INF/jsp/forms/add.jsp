<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <form:form action="" id="add"  method="post" modelAttribute="script">
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
				

			<div class="form-group"> <!-- Name field -->
					<label class="control-label " for="name">Script Name</label>
					<form:input class="form-control" id="script_name" path="script_name" />
				</div>
				<div class="form-group"> <!-- Message field -->
					<label class="control-label " for="message">Scripts</label>
					<form:textarea class="form-control" path="scripts"  rows="10"/>
				</div>
				
					<div class="form-group"> <!-- Message field -->
					<label class="control-label " for="message">Descriptions</label>
					<form:textarea class="form-control" cols="40" id="description" path="description"  rows="10"/>
				</div>
				<div class="form-group">
					<input type="hidden" name="action" value="${action}"/>
					<input class="btn btn-primary " name="submit" type="submit" value="submit"/>
				</div>
  </form:form>