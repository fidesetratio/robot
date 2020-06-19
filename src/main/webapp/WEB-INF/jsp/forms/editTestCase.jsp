<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <form:form action="" id="edit"  method="post">
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
					<label class="control-label " for="name">TestCase Name</label>
					<form:input class="form-control" id="script_name" path="script_name" />
				</div>
 </form:form>