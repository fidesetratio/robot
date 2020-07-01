<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    
<title><tiles:getAsString name="title" /></title>

	<!--Responsiveness-->
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    
	<!--FontAwesome-->
    <!--[if IE 7]>
    <link rel="stylesheet" href="font-awesome/css/font-awesome-ie7.min.css">
    <![endif]-->


  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/lightbox.css">
  
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>   
  
  <script src="http://malsup.github.io/jquery.form.js"></script>   
  <script src="js/lightbox.js"></script>
  <style>
  #lightbox #imageContainer img {
    max-width: 600px;
}
  .table-loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10;
  background:  #000000;
  opacity: 0.5;
}

.table-loading-inner {
  width: 100%;
  height: 100%;
}
  
  </style>
  
<script>


$(document).ready(function() {
	var path = location.pathname.split('?')[0];
    var start = path.lastIndexOf('/');
    var activeLink = path.substr(start);

    var parent = $('a[href*="' + activeLink + '"]').parent('li');
    // These two lines check if you have dropdowns in your nav-bar
    if (parent.closest('ul').hasClass('dropdown-menu')) {
        parent.parents('.dropdown').addClass('active');
    }
    parent.addClass('active');
	
	var options = {
			beforeSubmit : function(arr, $form, options){
			
			},
			success : function(res, status, xhr, form){
				var content = $(res).html();
				
				if($(res).find("#back_url").length>0){
					alert($(res).find("#message").text());
					var url = $(res).find("#back_url").val();
					$(location).prop('href', url);
					
				}else{
					form.html(content);
				}
				
			
				
			},
			error : function(xhr, textStatus, errorThrown){
				alert(JSON.stringify(xhr));
			}
			
			
	};
	
	
	
	
	$("#mod").find("form").each(function(e){

		$(this).ajaxForm(options); 
	});
	
	
	$("#run").click(function(event){
		event.preventDefault();
		var script_id = $("#availableScript").val();
		var browser_id = $("#availableBrowser").val();
		var param_id = $("#parameterid").val();
		var prefix = $("#prefix").val();
		var api_url = "/robot/exec";
		api_url = api_url + "?browser_id=" + browser_id + "&script_id=" + script_id+"&prefix"+prefix+"&param_id="+param_id;
		loadingTable("tablelist");
		$.ajax({
	        url: api_url,
	        contentType: "application/json",
	        dataType: 'json',
	        success: function(result){
	            //console.log(result.prefix);
	            
	            var urlresponse = "/robot/results?prefix="+result.prefix+"&refrensi="+result.reff_id;
	      
	             $.ajax({
	    	        url: urlresponse,
	    	        contentType: "application/json",
	    	        dataType: 'json',
	    	        success:function(result){
	    	        	    	          
	    	        	var tableBody = $("#resulttable tbody"); 
	    	        	$("#resulttable tbody tr").remove(); 
	    	        	$.each(result, function(i,d){
	    	        	
			                    var markup = "<tr><td>"+d.case_name+"</td>"+"<td>"+d.description+"</td>"+"<td>"+(d.pass==1?"PASS":"NOT PASS")+"</td>"+"<td>"+' <a class="example-image-link" href="/images/'+d.photo_id+'" data-lightbox="example-set" data-title="Click the right half of the image to move forward."><img class="example-image" width="50" height="50" src="/images/'+d.photo_id+'"/></td></tr>';
			    	        	
			                    tableBody.append(markup); 
	    	        	});
	                    
	    	        	removeLoading("tablelist");
	    	        	}
	             	}
	    	        );

	        }
	    });
	    
	    
		
	});
	
	
	
	function loadingTable(divID) {
		  var html = " <div class='table-loading-overlay'>"
		  + " <div class='table-loading-inner'>"
		  + "<div class=\"col-xs-4 col-xs-offset-4\">"
		  + "<div class=\"progress\"> "
		  + "<div class=\"progress-bar progress-bar-striped progress-bar-streit active\" role=\"progressbar\" aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%\">"
		  + "        <span class=\"sr-only\">Loading...</span>"
		  + "    </div>"
		  + "    </div>"
		  + " </div>"
		  + " </div>"
		  + " </div>";

		  $('#' + divID).append(html);
		  $('.table-loading-overlay').css('height', $('#' + divID).height() + 'px');
		  $('.table-loading-overlay').css($('#' + divID).offset());
		  $('.table-loading-overlay').css('width', $('#' + divID).width() + 'px');
		  $('.table-loading-inner').css('padding-top', ($('#' + divID).height() / 2) + 'px');
		}
	
	
	function removeLoading(divID) {
		  $('#' + divID).find(".table-loading-overlay").remove();
		  }
	
});

</script>
</head>


<body>

<div class="container" id="mod">
		 <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Robot</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="home">Home</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Scripts
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="editscript?script_id=0">Add Scripts</a></li>
          <li><a href="scripts">Scripts</a></li>
        	
        </ul>
      </li>
      <li><a  class="dropdown-toggle" data-toggle="dropdown" href="#">Parameter
       <span class="caret"></span>
      </a>
      <ul class="dropdown-menu">
          <li><a href="categoryparameter">Category Parameter</a></li>
        	
        </ul>
      </li>
      <li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Browser
        <span class="caret"></span></a>
          <ul class="dropdown-menu">
          <li><a href="editbrowser?browser_id=0">Add Browser</a></li>
          <li><a href="browsers">Browsers</a></li>
        	
        </ul>
      </li>
       <li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Run
        <span class="caret"></span></a>
          <ul class="dropdown-menu">
          <li><a href="executescenario">Execute</a></li>
        	
        </ul>
      </li>
    </ul>
  </div>
</nav> 
<tiles:insertAttribute name="body"/>
</div>

 
</body>
</html>