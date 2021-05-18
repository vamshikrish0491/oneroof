<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab-pane fade show active" id="userProfile" role="tabpanel">
	<form class="new-added-form" id="user_profile_form" name="user_profile_form" >
 	 	<div class="row">
	 	 	<div class="col-xl-4 col-lg-6 col-12 form-group">
                       <label>First Name<span class="text-danger">&nbsp;*</span></label>
                       <input type="text" class="form-control"  name="firstName" id="firstName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="First Name">
	        </div>
	        
	        <div class="col-xl-4 col-lg-6 col-12 form-group">
                       <label>Last Name<span class="text-danger"></span></label>
                       <input type="text" class="form-control"  name="lastName" id="lastName" data-validation-optional="true" data-validation="validate_Space validate_AlphaNumber validate_length length1-100"  field-name="Last Name">
	        </div>
	        
       		<div class="col-xl-4 col-lg-6 col-12 form-group" id="emailDiv">
                      <label>Email<span class="text-danger">&nbsp;*</span></label>
                      <input type="text" class="form-control"  readonly="readonly" name="email" id="email" field-name="Email" data-validation="required validate_Space validate_email validate_length length1-100">
            </div>
                  
            <div class="col-xl-4 col-lg-6 col-12 form-group" id="mobileDiv">
                <label>Mobile<span class="text-danger">&nbsp;*</span></label>
                <input type="text" class="form-control" onblur="validateUserMobile();" name="mobile" id="mobile"  field-name="Mobile" data-validation="required validate_Space validate_int">
            </div>
            
            <div class="col-xl-4 col-lg-6 col-12 form-group" id="mobileDiv">
                <label>DOB<span class="text-danger">&nbsp;*</span></label>
                <input type="text" placeholder="dd/mm/yyyy" class="form-control dob-datepicker"
                                             data-position='bottom right' name="dob" id="dob" readonly="readonly" data-validation="required" field-name="Date Of Birth">
                                         <i class="far fa-calendar-alt"></i>
            </div>
            
            <div class="col-xl-4 col-lg-6 col-12 form-group" id="genderDiv">
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
             
           	  <div class="col-xl-4 col-lg-6 col-12 form-group" id="uploadProfile">
                     <label>Upload Photo</label>
                     <input type="file" class="form-control-file" id="uploadImageBtn" field-name="Upload Image" data-validation-optional="true" data-validation="validateImg">
                     <input type="text" class="form-control" readonly id="uploadImageFile" style="display: none;" name="docFile" >
              </div>
                 
               <div class="col-xl-4 col-lg-6 col-12 form-group" id="savedProfile">
                    <label>Uploaded Photo</label>
                    <div class="profilePic-image" id="uploadImageBtn">
					<div id="savedProfileImage"></div>			
			  </div>
			     <div class="clearUpload clearUpload-profile-position" title="Delete Uploaded Photo" onclick="deleteProfileImage();"></div>
              </div>
              
              <div class="col-xl-4 col-lg-6 col-12 form-group">
                 <label>Preferred Drinks</label>
                 <select class="select2" class="form-control" multiple name="preferredDrinks" id="preferredDrinks"  data-validation-optional="true" field-name="Preferred Drinks">
                    <c:forEach var="drinks" items="${preferredDrinksList}">
                    <option value="${drinks.categoryName}" ${fn:contains(drinksList, drinks.categoryName) ? 'selected' : ''}>${drinks.categoryName}</option>
                    </c:forEach>
                 </select>
	         </div>
	         
	         <div class="col-xl-4 col-lg-6 col-12 form-group">
                 <label>Preferred Music</label>
                 <select class="select2" class="form-control" multiple name="preferredMusic" id="preferredMusic"  data-validation-optional="true" field-name="Preferred Music">
                    <c:forEach var="music" items="${preferredMusicList}">
                    <option value="${music.categoryName}" ${fn:contains(musicList, music.categoryName) ? 'selected' : ''}>${music.categoryName}</option>
                    </c:forEach>
                 </select>
	         </div>
              
              <div class="col-12 ui-btn-wrap mt-5">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveUserProfileDetails()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/profile'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				               </ul>
				   </div>
                  
 	 	</div>
 	</form>
</div>

	<div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successMsg" style="color: white;"></span> 
    </div>
          
    <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidMsg" style="color: white;"></span> 
    </div>
    
    
    
    <script type="text/javascript">
		window.onload = function () {
			getUserProfileInfo();
		 };
		 
			function getUserProfileInfo(){
				 var userUUID = '${userUUID}';
				 var appUrl ='${appUrl}';
		    	   $("#profileDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		    	   $.ajax({
		    			  type: "GET",
		    			    url: appUrl+"/getUserDetails?${_csrf.parameterName}=${_csrf.token}&userUUID="+userUUID,
		    			     success :function(response) {
		    			    	    
		    			    	 $("#profileDetailsLoadingDiv").removeAttr("style");
		    			    	 $("#firstName").val(response.object.firstName);
		    			    	 $("#lastName").val(response.object.lastName);
		    			    	 $("#email").val(response.object.userEmail);
		    			    	 $("#mobile").val(response.object.userMobile);
		    			    	 $("#dob").val(response.object.dob);
		    			    	 
		    			    	 if(response.object.gender != ''){
		    			    		 if(response.object.gender == 'Male'){
		    			    			 $("#genderMale").attr("checked",true);
		    			    		 }else{
		    			    			 $("#genderFemale").attr("checked",true);
		    			    		 }
		    			    		 $('#gender').val(response.object.gender);
		    			    	 }
		    			    	 
		    			    	 if(response.object.userImage != ''){
		    			    		 document.getElementById("uploadProfile").style.display="none";
		    			    		 document.getElementById("savedProfile").style.display="block";
		    			    		 $("#savedProfileImage").html('<img src="'+response.object.userImage+'" data-id= "vendorProfileImage" >');
		    			    	 }else{
		    			    		 document.getElementById("savedProfile").style.display="none";
		    			    		 document.getElementById("uploadProfile").style.display="block";
		    			    	 }
		    					
		    			     },
		    		}); 
			}
			
			function deleteProfileImage(){
				document.getElementById("uploadProfile").style.display="block";
				document.getElementById("savedProfile").style.display="none";
			}
		 
   </script>
   
   
   <script>
   function changeGenderType(){
	   var genderTypeFilterRadio = $('input[name=genderTypeFilter]');
	   var genderTypeFilter = genderTypeFilterRadio.filter(':checked').val();
	   $('#gender').val(genderTypeFilter);
   }
   </script>
   
   <script>
 function saveUserProfileDetails(){
	   if ($('#user_profile_form').validate(false, validationSettings)){
		   $("#profileDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var userUUID = "${userUUID}";
           var formData = getWayupayFormData("user_profile_form");
			formData.append("userUUID", userUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveUserProfileDetails?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#profileDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }else if (result && (result.response === "DRINK_PREFERENCES" || result.response === "MUSIC_PREFERENCES")) {
					        		 $("#profileDetailsLoadingDiv").removeAttr("style");
					    		     $("#invalidMsgDiv").removeAttr("style");
						    		 $("#invalidMsgDiv").css({ display: "block" });
							         $("#invalidMsg").html(result.responseMessage);
							         $("#invalidMsgDiv").fadeIn(500);
							 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
					    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#profileDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully updated you user details.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     getUserProfileInfo();
			                   }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }

</script>

<script>
 function validateUserMobile(){
	 
	 var userMobile = $('input#mobile').val();
	 if(userMobile != null  && userMobile != '' && userMobile != 'undefined'){
		 
		 var appUrl ='${appUrl}';
		 var userUUID ='${userUUID}';
			var formData = new FormData();
			formData.append("userMobile", userMobile);
			formData.append("userUUID", userUUID);
			
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateUserMobile?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_MOBILE_DATA"){
			        		
				    		$('input#mobile').removeAttr( "class record-exist record-exist-errormsg");
							$('#mobileDiv').find('p.jquery_form_error_message').remove();
							$('input#mobile').attr('class','valid form-control');
							
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/login";
			        	}else{
			        		$('#mobileDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#mobile').attr('class','error form-control');
				    		$("input#mobile").after("<p class='jquery_form_error_message'>User already exists with this mobile number</p>");
							$('input#mobile').attr('record-exist','yes');
							$('input#mobile').attr('record-exist-errorMsg',' User already exists with this mobile number'); 
							document.getElementById('submit_button').disabled = true;
			        	}
	 
			        },
			    }); 
	 }
 }
 
 function validateUserEmail(){
	 
	 var userEmail = $('input#email').val();
	 if(userEmail != null  && userEmail != '' && userEmail != 'undefined'){
		 
		 var appUrl ='${appUrl}';
		 var userUUID ='${userUUID}';
			var formData = new FormData();
			formData.append("userEmail", userEmail);
			formData.append("userUUID", userUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateUserEmail?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_EMAIL_DATA"){
			        		
			        		document.getElementById('submit_button').disabled = false;
				    		$('input#email').removeAttr( "class record-exist record-exist-errormsg");
							$('#emailDiv').find('p.jquery_form_error_message').remove();
							$('input#email').attr('class','valid form-control');
							$( "#submit_button" ).click(function() {
							});
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/login";
			        	}else{
			        		$('#emailDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#email').attr('class','error form-control');
				    		$("input#email").after("<p class='jquery_form_error_message'>User already exists with this email</p>");
							$('input#email').attr('record-exist','yes');
							$('input#email').attr('record-exist-errorMsg',' User already exists with this email'); 
							document.getElementById('submit_button').disabled = true;
			        	}
	 
			        },
			    }); 
	 }
 }
 
 </script>  
    