<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>

<div id="wrapper" class="wrapper bg-ash">
  <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
           <jsp:include page="../wayupartyMasterSideNav.jsp" />
	          <div class="dashboard-content-one">
	            <div class="breadcrumbs-area">
	                    <h3>Add Guest</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Add Guest</li>
	                    </ul>
	             </div>
	             
			        <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
		                                   <i class="far fa-hand-point-right bg-light-green3"></i>
		                                  <span id="successMsg" style="color: white;"></span> 
		            </div>
		            
		            <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
		                                   <i class="fas fa-times bg-pink3"></i>
		                                  <span id="invalidMsg" style="color: white;"></span> 
		            </div>
            
            
		            <div class="card height-auto">
		             <div id="userDetailsLoadingDiv"></div>
		                   <div class="card-body">
		                        <div class="heading-layout1">
		                            <div class="item-title">
		                                <h3>Guest Details</h3>
		                            </div>
		                        </div>    
		                            
		                            <form class="new-added-form" id="add_guest_form" name="add_guest_form" >
		                    	 		<div class="row">	
			                    	 		<div class="col-xl-3 col-lg-6 col-12 form-group">
			                                    <label>First Name<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="firstName" id="firstName" data-validation="required validate_Space validate_length length1-50"  field-name="First Name">
			                                </div>
			                                
								            <div class="col-xl-3 col-lg-6 col-12 form-group">
			                                    <label>Last Name</label>
			                                    <input type="text" class="form-control"  name="lastName" id="lastName" data-validation-optional="true" data-validation="validate_Space validate_length length1-50"  field-name="Last Name">
			                                </div>
			             
			             					<div class="col-xl-3 col-lg-6 col-12 form-group" id="emailDiv">
			                                    <label>Email<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="email" id="email" onblur="validateUserEmail();" data-validation="required validate_Space validate_email validate_length length1-100"  field-name="Email">
			                                </div>
			                               
			                               <!--  <div class="col-xl-3 col-lg-6 col-12 form-group" id="mobileDiv">
			                                    <label>Mobile<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="mobileNumber" onblur="validateUserMobile();" id="mobileNumber"  data-validation="required validate_Space"  field-name="Mobile Number">
			                                </div> -->
			                                
								            <div class="col-xl-3 col-lg-6 col-12 form-group">
								                <label>DOB<span class="text-danger">&nbsp;*</span></label>
								                <input type="text" placeholder="dd/mm/yyyy" class="form-control dob-datepicker"
								                                             data-position='bottom right' name="dob" id="dob" readonly="readonly" data-validation="required" field-name="Date Of Birth">
								                                         <i class="far fa-calendar-alt"></i>
								            </div>
								            
								            <div class="col-xl-3 col-lg-6 col-12 form-group" id="genderDiv">
								             <label>Gender<span class="text-danger">&nbsp;*</span></label>
									             <ul class="list-group list-group-horizontal">
									                            <li class="radiobtnstyles">
									                                <label>Male
									                                    <input type="radio"  name="genderTypeFilter" id="genderMale" value="Male" onclick="changeGenderType();">
									                                    <span class="checkmark"></span>
									                                  </label>
									                            </li>
									                            <li class="radiobtnstyles">
									                                <label>Female
									                                    <input type="radio"  name="genderTypeFilter" id="genderFemale" value="Female" onclick="changeGenderType();">
									                                    <span class="checkmark"></span>
									                                  </label>
									                            </li>
									              </ul>
									              <input type="hidden" id="gender" name="gender" data-validation="required" field-name="Gender">
								             </div> 
								             
								              <div class="col-xl-3 col-lg-6 col-12 form-group">
				                                         <label>Start Date<span class="text-danger">&nbsp;*</span></label>
				                                         <input type="text" placeholder="dd/mm/yyyy" class="form-control start-date-datepicker"
				                                             data-position='bottom right' name="startDate" id="startDate" data-validation="required" field-name="Start Date">
				                                         <i class="far fa-calendar-alt"></i>
				                               </div>
				                               
				                               <div class="col-xl-3 col-lg-6 col-12 form-group">
				                                         <label>End Date<span class="text-danger">&nbsp;*</span></label>
				                                         <input type="text" placeholder="dd/mm/yyyy" class="form-control end-date-datepicker"
				                                             data-position='bottom right' name="endDate" id="endDate" data-validation="required" field-name="End Date">
				                                         <i class="far fa-calendar-alt"></i>
				                               </div> 
			                                
		                                </div><br/>
		                                
				                          <div class="row">	
											   <div class="col-12 ui-btn-wrap">
											               <ul>
											               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveGuests()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
											               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/addGuest'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
											               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/guests'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
											               </ul>
											   </div>
				                          </div>
		                           </form>
		                   	</div>
		           	</div>
                </div>
          </div>
           <jsp:include page="../wayupartyMasterFooter.jsp" />
    </div>
    
   <script>
	   function changeGenderType(){
		   var genderTypeFilterRadio = $('input[name=genderTypeFilter]');
		   var genderTypeFilter = genderTypeFilterRadio.filter(':checked').val();
		   $('#gender').val(genderTypeFilter);
	   }
   </script>
    
    
    <script>
    var dateToday = new Date();
    $('.start-date-datepicker').datepicker({
      language: {
        days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
        daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        today: 'Today',
        clear: 'Clear',
        dateFormat: 'dd/mm/yyyy',
        firstDay: 0,
      },
      minDate: dateToday,
      onSelect : function(selected) {
      	getEndDateDatePicker();
  	 }
    });
    
    function getEndDateDatePicker(){
    	 var endDatepicker = $('#endDate').datepicker().data('datepicker');
    	 endDatepicker.clear();
    	 var startDate = $('#startDate').val();
    	 var dateParts = startDate.split('/');
    	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
        $('.end-date-datepicker').datepicker({
            language: {
              days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
              daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
              daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
              months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
              monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
              today: 'Today',
              clear: 'Clear',
              dateFormat: 'dd/mm/yyyy',
              firstDay: 0,
            },
            minDate: minimumdate
            
        });
    }
    </script>
    
 
 
<script>
 function saveGuests(){
	   if ($('#add_guest_form').validate(false, validationSettings)){
		   $("#userDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var formData = getWayupayFormData("add_guest_form");
			formData.append("vendorUUID", vendorUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveGuestUser?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#userDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#userDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }else if (result.response === "GUEST_USER_EXISTS") {   
				    			   $("#userDetailsLoadingDiv").removeAttr("style");
								    $('#emailDiv').find('p.jquery_form_error_message').remove(); 
						    		$('input#email').attr('class','error form-control');
						    		$("input#email").after("<p class='jquery_form_error_message'>User already exists as guest</p>");
									$('input#email').attr('record-exist','yes');
									$('input#email').attr('record-exist-errorMsg',' User already exists as guest'); 
								   
			                   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully updated you user details.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	    location.replace(appUrl+"/guests");
			                   }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_guest_form', function() {
		$("#add_guest_form").showHelpOnFocus();
		$("#add_guest_form").validateOnBlur(false, validationSettings);  
	});

</script>    
    
 <script>
 function validateUserMobile(){
	 
	 var mobileNumber = $('input#mobileNumber').val();
	 if(mobileNumber != null  && mobileNumber != '' && mobileNumber != 'undefined'){
		 $("#userDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');	 
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("mobileNumber", mobileNumber);
			
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/getGuestUserMobileNumber?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	$("#userDetailsLoadingDiv").removeAttr("style");
			        	
			        	if(result && result.response == "SUCCESS"){
			        		
				    		if(result.object != null){
				    			$("#firstName").val(result.object.firstName);
				    			$("#lastName").val(result.object.lastName);
				    			$("#email").val(result.object.email);
				    			$("#mobileNumber").val(result.object.mobileNumber);
				    			$("#dob").val(result.object.dob);
				    			if(result.object.gender != ''){
		    			    		 if(result.object.gender == 'Male'){
		    			    			 document.getElementById("genderMale").checked = true
		    			    		 }else{
		    			    			 document.getElementById("genderFemale").checked = true
		    			    		 }
		    			    		 $('#gender').val(result.object.gender);
		    			    	 }
				    		}
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/login";
			        	}
	 
			        },
			    }); 
	 }
 }
 
 function validateUserEmail(){
	 
	 var email = $('input#email').val();
	 if(email != null  && email != '' && email != 'undefined'){
		 $("#userDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		 
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("email", email);
			
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/getGuestUserEmail?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	$("#userDetailsLoadingDiv").removeAttr("style");
   						
			        	if(result && result.response == "SUCCESS"){
				    		if(result.object != null){
				    			$("#firstName").val(result.object.firstName);
				    			$("#lastName").val(result.object.lastName);
				    			$("#email").val(result.object.email);
				    			$("#mobileNumber").val(result.object.mobileNumber);
				    			$("#dob").val(result.object.dob);
				    			
				    			
				    			if(result.object.gender != ''){
		    			    		 if(result.object.gender == 'Male'){
		    			    			 document.getElementById("genderMale").checked = true
		    			    		 }else{
		    			    			 document.getElementById("genderFemale").checked = true
		    			    		 }
		    			    		 $('#gender').val(result.object.gender);
		    			    	 }
				    		}
				    	
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/login";
			        	}
	 
			        },
			    });
	 }
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
                    	