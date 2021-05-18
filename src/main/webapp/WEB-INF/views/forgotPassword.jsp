

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/fav-icon.jpeg">
    <title>ONEROOF | Forgot Password</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

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
<body>
    <div id="preloader"></div>
    <!-- Login Page Start Here -->
    <div class="login-page-wrap">
        <div class="login-page-content">
        <div id="passwordLoadingDiv"></div>
            <div class="login-box">
             <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                                    <i class="fas fa-times bg-pink3"></i>
                                   <span id="invalidMsg" style="color: white;"></span> 
             </div>
                <div class="item-logo">
                    <img src="/resources/img/logo.png" alt="logo" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" style="width: 250px; height: auto">
                </div>
               <form class="loginForm" name="forgotPassword_form" id="forgotPassword_form">
                    <div class="form-group" id="emailDiv">
                        <label>Email<span class="text-danger">&nbsp;*</span></label>
                        <input type="text" placeholder="Enter Email" class="form-control" name="email" id="email" field-name="Email"  data-validation="required validate_Space validate_email">
                        
                    </div>
                    <div class="modal-footer">
                                <input type="button"  value="Submit" class="btn btn-success" id="save_button" onclick="validateEmail();" />
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
	 function validateEmail(){
		   if ($('#forgotPassword_form').validate(false, validationSettings)){
			   $("#passwordLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			   var appUrl = "${appUrl}";
			   var email = $('input#email').val();
	  	     $.ajax({
			    	type: "POST",
			    	 url: appUrl+"/ws/validateEmail?${_csrf.parameterName}=${_csrf.token}&email="+email, 
			        success: function(result) {
			           
			        	if(result && result.response == "SUCCESS"){
			        		 location.replace("/ws/resetPassword/"+result.object.verificationUUID); 
			        	}else if (result && result.response === "AWKWARD") {
			        		 location.href = "/login";
			        	}else if (result && result.response === "NOT_REGISTERED_EMAIL") {
			        		$("#passwordLoadingDiv").removeAttr("style");
			        		$('#emailDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#email').attr('class','error form-control');
				    		$("input#email").after("<p class='jquery_form_error_message'>No user exists with this email</p>");
							$('input#email').attr('record-exist','yes');
							$('input#email').attr('record-exist-errorMsg',' No user exists with this email'); 
			        	}
			        },
			    });     
	           return true;
		   }else{
				return false;
			  } 
	 }
	 
	 $('body').on('blur', '#forgotPassword_form', function() {
			$("#forgotPassword_form").showHelpOnFocus();
			$("#forgotPassword_form").validateOnBlur(false, validationSettings);  
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
    
    



