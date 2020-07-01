<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<div class="row">
                <div class="col-md-12">
                    <fieldset class="group-border">
                        <legend class="group-border">Scenario Maker</legend>

                        <div class="row">
                            <div class="col-lg-4">
                                <div class="form-inline">
                                    <label class="control-label" for="NumDoc">Script Name</label>
                                   <select class="form-control" id="availableScript">
    <option value="0" selected>Select Available Scripts</option>
    <c:forEach items="${scripts}" var="s">
        <option value="${s.script_id}">${s.script_name}</option>
    </c:forEach>
  </select>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-inline">
                                    <label class="control-label" for="ProcessNum">Browser Name</label>
                         
                         <select class="form-control" id="availableBrowser">
   <option value="0" selected>Select Available Browsers</option>
    <c:forEach items="${browsers}" var="b">
        <option value="${b.browser_id}">${b.browser_name}</option>
    </c:forEach>
  </select>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-inline">
                                    <label class="control-label" for="State">Parameter</label>
                                 <select class="form-control" id="parameterid" name="parameterid" id="sel1">
                                  <option value="0" selected>Select Available Parameter</option>
  <c:forEach items="${catparams}" var="cat_param">
        <option value="${cat_param.cat_id}">${cat_param.cat_name}</option>
    </c:forEach>
  
  
  </select>
                                   </div>
                            </div>
                        </div>
                        <br/>
                           <div class="row">
                           
                            <div class="col-lg-4">
                                <div class="form-inline">
                                    <label class="control-label" for="NumDoc">Prefix</label>
                                   	<input  class="form-control" id="prefix" type="text" name="prefix" value="default"/>
                                </div>
                            </div>
                           	
                           </div>
                        <br>
                       
                    </fieldset>
                </div>
                
            </div>
            
             <a href="" id="run" class="btn btn-info" role="button">Run</a>
             
             <br/>
             <br/>
             
              <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Results</h3>
            </div>
            <div class="panel-body">
             
             <div id="tablelist">
             
             <table class="table table-striped table-hover" id="resulttable">
    <thead>
      <tr>
        <th>Case Name</th>
        <th>Description</th>
       
        <th>Status</th>
        <th>Screenshot</th>
      </tr>
    </thead>
    <tbody>
      
    </tbody>
  </table>
             
             </div>
             	</div>
             	</div>
