<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/faviconlogo.png">
    <title>ONEROOF | QR CODE</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <script src="/resources/js/jquery.input-stepper.js"></script>  
   <link rel="stylesheet" href="/resources/css/style.css">
   
   <style>
   body { background-color: #f2f3f7; padding-top: 0px; box-sizing: border-box; }
.cart_widget { background-color: #fff; padding: 15px; border: 1px solid rgba(203,213,224,var(--border-opacity))!important; border-radius: 6px; min-height: 200px;
 font-size: 14px;
}
   </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
                <a href="/home" id="homeLinkIcon" style="color: #be9c52;" class="fa fa-home fa-2x"></a>
                <a class="navbar-brand" href="#" style="padding-left: 21px; text-align: center;">
                    <img id="Image1" src="/resources/img/logo.png" style="height:50px;width:200px;">
                </a>
                <a id="user" class="nav-link" href="/login"><span class="fa fa-user fa-2x" style="color: #be9c52;"></span></a>
</nav>
<div id="wrapper" class="wrapper bg-ash mt-3">
<div class="dashboard-page-one">
<div class="dashboard-content-one">
  <div class="row">
  <div id="guestDetailsLoaderDiv"></div>
    <div class="col-sm-12 col-md-12">
    <div class="cart_widget">
      <h4>Guest User</h4>
		<div class="modal-body service-info">
             <ul class="row"> 
                <li class="col-xl-8 col-lg-6 col-12"><span id="userImage" class="qr_images_img item-img"></span></li>
                <li class="col-xl-8 col-lg-6 col-12 mt-3"><strong>Club  : </strong><span id="clubName"></span></li>
                <li class="col-xl-8 col-lg-6 col-12"><strong>Club Location  : </strong><span id="clubLocation"></span></li>
                <li class="col-xl-8 col-lg-6 col-12"><strong>Guest Name  : </strong><span id="guestName"></span></li>  
                <li class="col-xl-8 col-lg-6 col-12"><strong>Email  : </strong><span id="guestEmail"></span></li>
                <li class="col-xl-8 col-lg-6 col-12"><strong>Date Of Birth  : </strong><span id="guestDOB"></span></li>
                <li class="col-xl-8 col-lg-6 col-12"><strong>Gender  : </strong><span id="guestGender"></span></li>
                <li class="col-xl-8 col-lg-6 col-12"><strong>Guest Code  : </strong><span id="guestCode"></span></li>
                <li class="col-xl-8 col-lg-6 col-12"><strong>Guest Date  : </strong><span id="guestDate"></span></li>
                </ul>
             </div>
    </div>
  </div>
  </div>
</div>
</div>
</div>
<footer class="footer" style="background-color: #343a40;">
            <div class="container">
                ONEROOF � 2019. copyrights all rights reserved. Developed by Acculytixs Pvt Ltd.
            </div>
 </footer>
 

 
 <script type="text/javascript">
		window.onload = function () {
			var guestUUID = '${guestUUID}';
			getGuestDetails(guestUUID);
		 };
 </script>
 
 <script>
       function getGuestDetails(guestUUID){
    	   
    	   var appUrl ='${appUrl}';
    	   $("#guestDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/ws/getGuestUserDetails?${_csrf.parameterName}=${_csrf.token}&guestUUID="+guestUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#guestDetailsLoaderDiv").removeAttr("style");
    			    	 
    			    	 $("#userImage").html('<img src="'+response.object.userImg+'">');
    			    	 $("#clubName").html(response.object.club);
    			    	 $("#clubLocation").html(response.object.clubLocation);
    			    	 $("#guestName").html(response.object.guestUserName);
    			    	 $("#guestEmail").html(response.object.email);
    			    	 $("#guestDOB").html(response.object.dob);
    			    	 $("#guestGender").html(response.object.gender);
    			    	 $("#guestCode").html(response.object.guestCode);
    			    	 if(response.object.endDate != ''){
    			    		 $("#guestDate").html(response.object.startDate+" to "+response.object.endDate);
    			    	 }else{
    			    		 $("#guestDate").html(response.object.startDate);
    			    	 }
    			    	 
    					},
    		});
       }
       </script>
 

 <script type="text/javascript">
		 
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
 
 
</body>
</html>
