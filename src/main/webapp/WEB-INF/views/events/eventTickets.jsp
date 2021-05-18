<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="ui-modal-box">
  <div class="modal-box">
         <div class="modal fade sidebar-align" id="small-modal" tabindex="-1" role="dialog" aria-hidden="true">
                     <div class="modal-dialog modal-sm mt-10" role="document">
                         <div class="dashboard-content-one modal-content">
                          <div id="addTicketLoadingDiv"></div>
                         <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                   <i class="far fa-hand-point-right bg-light-green3"></i>
                                  <span id="successMsg" style="color: white;"></span> 
            			  </div>
                             <div class="modal-header">
                                 <h5 class="modal-title" id="ticketTitle"></h5>
                                 <button type="button" class="close" data-dismiss="modal"
                                     aria-label="Close">
                                     <span aria-hidden="true">&times;</span>
                                 </button>
                             </div>
                             <form class="new-added-form" id="add_ticket_form" name="add_ticket_form" >
                                 <div class="row gutters-15">
	                     			<div class="form-group col-12" id="ticketNameDiv">
	                                    <label>Event Ticket <span class="text-danger">&nbsp;*</span></label>
	                                    <input type="text" class="form-control" id="ticketName" name="ticketName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-50"  field-name="Section Name">
	                               	    <input type="hidden" name="ticketUUID" id="ticketUUID">
	                                </div>
	                                
	                                <div class="form-group col-12">
	                                    <label>Rating<span class="text-danger">&nbsp;*</span></label>
	                                    <input type="hidden" name="ticketRating" id="rating" field-name="Rating" data-validation="required" field-name="Rating">
	                                    <ul>
	                                    <c:set var="starValue" value="${0}" scope="request"/>
	                                    <c:forEach begin="0" end="4" varStatus="loop">
    										<li class="fa fa-star cursorPointer text-muted" id="star-select-${starValue + 1}" onclick="fillStarValues(${starValue + 1})"></li>
    										<c:set var="starValue" value="${starValue + 1}" scope="request"/>
										</c:forEach>
	                                    
	                                    </ul>
	                                </div>
	                               <div class="clearfix"></div>  
                                </div>
                               
                             <div class="modal-footer">
                                 <button type="button" class="footer-btn text-light gradient-pastel-green" id="save_button" onclick="validateTicket()">Save</button>
                                 <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal">Close</button>
                             </div>
                             </form>
                         </div>
                     </div>
        </div>
  </div>
</div>

    

<script>
function clearForm(){
	document.getElementById("add_ticket_form").reset();
	$("#rating").val('');
	$("#ticketName").val('');
	$("#ticketUUID").val('');
	$("#ticketTitle").html("Add New Ticket");
	
	
	for(var k=1; k<=5; k++){
		$("#star-select-"+k).removeClass('textStarFilling').addClass('text-muted');
	}
	
	$("#save_button").removeAttr("disabled");
	$('#ticketNameDiv').find('p.jquery_form_error_message').remove();
}

function getTicketEditForm(ticketUUID){
	
	document.getElementById("add_ticket_form").reset();
	$("#rating").val('');
	$("#ticketName").val('');
	$("#ticketUUID").val('');
	$("#ticketTitle").html("Edit Section");
	
	$("#save_button").removeAttr("disabled");
	$('#ticketNameDiv').find('p.jquery_form_error_message').remove();
	getTicketDetails(ticketUUID);
	
} 

</script>

<script>
function fillStarValues(i){
	
	$("#rating").val(i);
	
	for(var k=1; k<=i; k++){
		$("#star-select-"+k).removeClass('text-muted').addClass('textStarFilling');
	}
	
	for(var k=i+1; k<=5; k++){
		$("#star-select-"+k).removeClass('textStarFilling').addClass('text-muted');
	}
}
</script>



<script type="text/javascript">
	 function validateTicket() {   
		var ticketName = $('input#ticketName').val();
		var ticketUUID = $('input#ticketUUID').val();
		if(ticketName != null  && ticketName != '' && ticketName != 'undefined'){
			var appUrl ='${appUrl}';
			var formData = new FormData();
			var vendorUUID ='${vendorUUID}';
			formData.append("ticketName", ticketName);
			formData.append("ticketUUID", ticketUUID);
			formData.append("vendorUUID", vendorUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateTicket?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		$("#save_button").removeAttr("disabled");
				    		$('input#ticketName').removeAttr( "class record-exist record-exist-errormsg");
							$('#ticketNameDiv').find('p.jquery_form_error_message').remove();
							$('input#ticketName').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('footer-btn bg-linkedin').val('Save');
							saveTicket();
			        	}else if (result && result.response === "AWKWARD") {
			        		 location.href = "/errorPage";
			        	}else{
			        		$('#ticketNameDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#ticketName').attr('class','error form-control');
				    		$("input#ticketName").after("<p class='jquery_form_error_message'>Ticket Name already exists</p>");
							$('input#ticketName').attr('record-exist','yes');
							$('input#ticketName').attr('record-exist-errorMsg',' Ticket Name already exists'); 
							$("#save_button" ).addClass('footer-btn bg-linkedin').val('Save');
			        	}
	 
			        },
			    }); 
				
		}
		
	 }
 </script>
 
 <script>
 function saveTicket(){
	   if ($('#add_ticket_form').validate(false, validationSettings)){
		   $("#addTicketLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var formData = getWayupayFormData("add_ticket_form");
       	   var vendorUUID ='${vendorUUID}';
		   formData.append("vendorUUID", vendorUUID);
           
             $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveTicket?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	   if (result.response === "AWKWARD") {
				        		   $("#addTicketLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new ticket.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/eventSettings");
			                       }
				        	
				        		
				},
		});    
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_ticket_form', function() {
		$("#add_ticket_form").showHelpOnFocus();
		$("#add_ticket_form").validateOnBlur(false, validationSettings);  
   });
 </script>
 
  <script>
   function getTicketDetails(ticketUUID){
	   var appUrl ='${appUrl}';
	   $("#addTicketLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getTicketDetailsByTicketUUID?${_csrf.parameterName}=${_csrf.token}&ticketUUID="+ticketUUID,
			     success :function(result) {
			    	    
			    	 if (result && result.response === "SUCCESS") {
			    		 $("#addTicketLoadingDiv").removeAttr("style");
			    		 $("#ticketName").val(result.object.ticketName);
			    		 $("#ticketUUID").val(result.object.ticketUUID);
			    		 $("#rating").val(result.object.ticketRating);
			    		 fillStarValues(result.object.ticketRating)
			    		 
			    	 }else if (result && result.response === "AWKWARD") {
		        		 location.href = "/errorPage";
			          } 
			    	 
					},
		});
   }
   </script> 


 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>