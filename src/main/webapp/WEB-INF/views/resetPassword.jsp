

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/fav-icon.jpeg">
    <title>ONEROOF | Reset Password</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  
  <style>
  p {
    margin: 0 0 0px 0 !important;
    font-size: 11px !important;
    font-weight: bold !important;
  }
  
  img:hover {
    cursor: pointer;
  }
  </style>  
</head>

<body>
    <div id="preloader"></div>
    <!-- Login Page Start Here -->
    <div class="login-page-wrap">
        <div class="login-page-content">
        <div id="passwordLoadingDiv"></div>
              <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                    <i class="far fa-hand-point-right bg-light-green3"></i>
                                   <span id="successMsg" style="color: white;"></span> 
             </div>
             
             <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                                    <i class="fa fa-times bg-pink3"></i>
                                   <span id="invalidMsg" style="color: white;"></span> 
             </div>
            <div class="login-box">
                <div class="item-logo">
                    <img src="/resources/img/logo.png" alt="logo" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" style="width: 250px; height: auto">
                </div>
                
                 <form class="loginForm" id="reset_password_form"  name="reset_password_form">
                
                   <div class="form-group" id="verificationCodeDiv">
                        <label>Verification Code<span class="text-danger">&nbsp;*</span></label>
                        <input type="text" placeholder="Enter Verification Code" class="form-control"  name="verificationCode" id="verificationCode"  field-name="Verification Code" data-validation="required validate_Space">
                    </div>
                    
                    <div class="form-group passwordConditions" id="passwordDiv">
                        <label>Password<span class="text-danger">&nbsp;*</span></label>
                        <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,}$" field-name="Password"  data-validation="required validate_length length8-15" placeholder="Enter password" class="form-control" name="password" value="${password}" id="password"  onkeydown="$(this).clear();" onkeyup="passwordValidation('password','fp_letter','fp_capital','fp_number','fp_special','fp_length');">
                    
                      <div id="fp_message" class="passHint" style="left:-300px; box-shadow: 1px 1px 1px 2px #ddd; top:-10px;">
				  			<p  id="fp_letter" class="invalid">At least one lowercase letter</p>
				  			<p  id="fp_capital" class="invalid">At least one uppercase letter</p>
				  			<p  id="fp_number" class="invalid">At least one number</p>
				  			<p  id="fp_special" class="invalid">At least one special character</p>
				  			<p  id="fp_length" class="invalid">Minimum 8 characters required</p>
						</div> 
                    </div>
                    
                    
                    <div class="form-group">
                        <label>Confirm Password<span class="text-danger">&nbsp;*</span></label>
                        <input type="password" placeholder="Enter confirm password" class="form-control" name="confirmPassword" id="confirmPassword"  data-validation="required" field-name="Confirm Password">
                    </div>
                    
                     <div class="modal-footer">
                         <input type="button"  value="Submit" id="submit_button" class="btn btn-success" onclick="checkPasswords();"/>
                         <input type="button"  value="Login" class="btn btn-success" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/login'" class="btn btn-login" />
                         <input type="button"  value="Cancel" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" class="btn btn-danger" />
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
    <!-- Login Page End Here -->
    
    <!-- jquery-->
    <script src="/resources/js/jquery-3.3.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery.scrollUp.min.js"></script>
    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/common.js?"></script>
    <script src="/resources/js/formvalidator.js?"></script>
    
    
  <script type="text/javascript">
		window.onload = function () {
			     $("#successMsgDiv").removeAttr("style");
	    		 $("#successMsgDiv").css({ display: "block" });
		         $("#successMsg").html("Verification Code sent to your registered mail id");
		         $("#successMsgDiv").fadeIn(200);
		 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
		 };
 </script>
    
    
   <script type="text/javascript">
	function checkPasswords() {   
		var pass1 = document.getElementById('password');  
	    var pass2 = document.getElementById('confirmPassword');
	    if(pass1.value == pass2.value){
			    $('input#password').removeAttr( "class record-exist record-exist-errormsg");
				$('#passwordDiv').find('p.jquery_form_error_message').remove();
				$('input#password').attr('class','valid form-control');
				resetUserPassword();
	    }
	    else{ 
				
				$('#passwordDiv').find('p.jquery_form_error_message').remove(); 
	    		$('input#password').attr('class','error form-control');
	    		$("input#password").after("<p class='jquery_form_error_message'>Passwords Not Matched</p>");
				$('input#password').attr('record-exist','yes');
				$('input#password').attr('record-exist-errorMsg',' Password and confirm password should be same'); 
	    }
   }
	
	
	 function resetUserPassword(){
		   if ($('#reset_password_form').validate(false, validationSettings)){
			   $("#passwordLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			   var appUrl = "${appUrl}";
	           var formData = getWayupayFormData("reset_password_form");
	           var verificationUUID = '${verificationUUID}';
	           formData.append("verificationUUID", verificationUUID);
 	            $.ajax({
					 type : "POST",
					 data: formData,
		    	     processData: false,
		    	     contentType:false,
		        	    	 url: appUrl+"/ws/resetUserPassword?${_csrf.parameterName}=${_csrf.token}", 
					        success : function(result) {
					        	
					        	   if (result.response === "AWKWARD") {
					        		   $("#passwordLoadingDiv").removeAttr("style");
					        		   location.href = "/login";
					    		   }  else if (result.response === "SUCCESS") {     
								         $("#passwordLoadingDiv").removeAttr("style");
								 	     $("#successMsgDiv").removeAttr("style");
							    		 $("#successMsgDiv").css({ display: "block" });
								         $("#successMsg").html("Well done! You successfully rest password.<br> Please click on Login to continue");
								         $("#successMsgDiv").fadeIn(500);
								 	     $('#successMsgDiv').delay(5000).fadeOut('slow');
								 	     document.getElementById("reset_password_form").reset();
				                     } else if (result.response === "INVALID_VERIFICATION_CODE") {     
								         $("#passwordLoadingDiv").removeAttr("style");
								 	     $("#invalidMsgDiv").removeAttr("style");
							    		 $("#invalidMsgDiv").css({ display: "block" });
								         $("#invalidMsg").html("Invalid Verification Code.<br> Please check your mail for verification code");
								         $("#invalidMsgDiv").fadeIn(500);
								 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow');
				                     }else if (result.response === "INVALID_DATA") {     
								         $("#passwordLoadingDiv").removeAttr("style");
								 	     $("#invalidMsgDiv").removeAttr("style");
							    		 $("#invalidMsgDiv").css({ display: "block" });
								         $("#invalidMsg").html("Invalid Data");
								         $("#invalidMsgDiv").fadeIn(500);
								 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow');
				                     }
					        	
					        		
					},
			});    
	           return true;
		   }else{
				return false;
			  } 
	 }
	 
	 $('body').on('blur', '#reset_password_form', function() {
			$("#reset_password_form").showHelpOnFocus();
			$("#reset_password_form").validateOnBlur(false, validationSettings);  
		});
   </script>
   
    <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 

<script>
$(document).ready(function(){
	$(".passwordConditions input").focus(function(){
		$(".passHint").show();
	});
	$(".passwordConditions input").blur(function(){
		$(".passHint").hide();
	});	
});
</script>

<script>
function passwordValidation(idValue,fp_letter,fp_capital,fp_number,fp_special,fp_length){
	var myInput = $("#"+idValue).val();
	 
	if(myInput.match(/[a-z]/g)){
		$("#"+fp_letter).removeClass("invalid");
		$("#"+fp_letter).addClass("actTe");
	}else{
		$("#"+fp_letter).removeClass("actTe");
		$("#"+fp_letter).addClass("invalid");
	} 
	if(myInput.match(/[A-Z]/g)){
		$("#"+fp_capital).removeClass("invalid");
		$("#"+fp_capital).addClass("actTe");
	}else{
		$("#"+fp_capital).removeClass("actTe");
		$("#"+fp_capital).addClass("invalid");
	}
	if(myInput.match(/[0-9]/g)){
		$("#"+fp_number).removeClass("invalid");
		$("#"+fp_number).addClass("actTe");
	}else{
		$("#"+fp_number).removeClass("actTe");
		$("#"+fp_number).addClass("invalid");
	}
	if(myInput.match(/[$&+,:;=?@#|'<>.^*()%!-]/g)){
		$("#"+fp_special).removeClass("invalid");
		$("#"+fp_special).addClass("actTe");
	}else{
		$("#"+fp_special).removeClass("actTe");
		$("#"+fp_special).addClass("invalid");
	}
	if(myInput.length >= 8){
		$("#"+fp_length).removeClass("invalid");
		$("#"+fp_length).addClass("actTe");
	}else{
		$("#"+fp_length).removeClass("actTe");
		$("#"+fp_length).addClass("invalid");
	}
	
}
</script>

<script type="text/javascript">
		var tokenParamName = "${_csrf.parameterName}";
		var tokenValue = "${_csrf.token}";
		var appUrl = "${Wayuparty_appUrl}";
		
		
		   $(document).bind("contextmenu",function(e){
		      return false;
		   }); 
		 
		 document.onkeydown = function(e) {
			  if(event.keyCode == 123) {
			     return false;
			  }
			  if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
			     return false;
			  }
			  if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
			     return false;
			  }
			  if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
			     return false;
			  }
			  if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
			     return false;
			  }
			}
</script>