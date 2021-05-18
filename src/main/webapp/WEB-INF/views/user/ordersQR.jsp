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
  <div id="ordersLoaderDiv"></div>
    <div class="col-sm-12 col-md-12">
    <div class="cart_widget">
      <h4>Orders</h4>
		<div id="ordersListDiv"></div>
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
			getOrdersList();
		 };
 </script>
 
 <script>
 function getOrdersList(){
	 var orderUUID = '${orderUUID}';
	 var appUrl ='${appUrl}';
	 var formData = new FormData();
	 formData.append("orderUUID", orderUUID);
	 
	 $("#ordersLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 $.ajax({
		    data: formData,
 	        contentType: false,
 	        processData: false,
	    	type: "POST",
	    	 url: appUrl+"/ws/getQROrdersList?${_csrf.parameterName}=${_csrf.token}", 
	        success: function(resultData) {
	   			
	        	 var result = "";
	        	 if(resultData.data.length > 0){
		        	for (var i=0; i<resultData.data.length; i++)
	   				{ 
		        		 var opt = resultData.data[i];
		        		 var currencyType = getCurrency(opt.currency);
		        		 result = result +'<div class="clearfix row cart_widget_inner">';
		        		 result = result +'<div class="col-sm-12 col-md-10">';
		        		 result = result +'<div class="row">';
		        		
		        		 result = result +'<div class="col-sm-12 col-md-8 cart_margn_top">';
		        		 result = result +'<p>'+opt.vendorName+'</p>';
		        		 result = result +'<p class="small text-muted">'+opt.vendorLocation+'</p>';
		        		 result = result +'<p>'+opt.quantity+' x '+opt.serviceName+'</p>';
		        		 result = result +'<p>'+opt.quantity+' x '+currencyType+'&nbsp;'+opt.orderAmount+'</p>';
		        		 
		        	 	 if(opt.surpriseList.length > 0){
			        		 for (var j=0; j< opt.surpriseList.length; j++)
		  	        	    	{
			        			 var surprise = opt.surpriseList[j];
			        			 result = result +'<p>'+surprise.surpriseType+' : '+surprise.surpriseName+'</p>';
		  	        	    	}
			        		 result = result +'<p>Instructions : '+opt.surpriseInstructions+'</p>';
		        		 }
		        	 	 
		        		 result = result +'</div>';
		        		 
		        		 
		        		 result = result +'<div class="col-sm-12 col-md-4">';
		        		 result = result +'<div class="cart_margn_top">';
		        		 result = result +'<p>Order date on '+opt.serviceOrderDate+'</p>';
		        		 result = result +'<p>'+opt.serviceTimeSlot+'</p>';
		        		 result = result +'<p>Order Code : '+opt.orderCode+'</p>';
		        		 result = result +'<p>Total amount '+currencyType+' '+opt.rates+'</p>';
		        		 result = result +'<p>'+opt.orderStatus+'</p>';
		        		 result = result +'</div>';
		        		 result = result +'</div>';
		        		 
		        		 result = result +'</div>';
		        		 result = result +'</div>';
		        		 
		        		 result = result +'<div class="col-sm-12 col-md-10">'; 
		        		 result = result +'<div class="row">';
		        	 	 if(opt.packageItems.length > 0){
		        			 result = result +'<div class="col-sm-12 col-md-8">';
			        		 result = result +'<div class="cart_margn_top">';
			        		 for (var k=0; k< opt.packageItems.length; k++)
		  	        	    	{
			        			 var menu = opt.packageItems[k];
			        			 result = result +'<p>'+menu.menuOfferingItem+' : '+menu.menuItems+'</p>';
		  	        	    	}
			        		
			        		 result = result +'</div>';
			        		 result = result +'</div>';
		        		 } 
		        	 	 result = result +'</div>';
		        	 	 result = result +'</div>';
		        		 
		        		 
		        		 result = result +'</div>';
	   				}
		        	
		        	 $("#ordersListDiv").empty();  
		   	    	 $("#ordersListDiv").append(result);
		   	    	 $("#ordersLoaderDiv").removeAttr("style");
	        	 }else{
	        		 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
					 $("#ordersListDiv").empty();  
		   	    	 $("#ordersListDiv").append(result);
	        		 $("#ordersLoaderDiv").removeAttr("style");
	        	 }
	        	
	        },
	    });
	 
 }
 </script>
 
 <script>
function getCurrency(currency){
	var currencyType;
	switch (currency) {
	  case "USD":
		currencyType = "&#36";
	    break;
	  case "EUR":
		  currencyType = "&#128";
	    break;
	  case "CRC":
		  currencyType = "&#8353";
	    break;
	  case "GBP":
		  currencyType = "&#163";
	    break;
	  case "ILS":
		  currencyType = "&#8362";
	    break;
	  case "INR":
		  currencyType = "&#x20B9";
	    break;
	  case  "JPY":
		  currencyType = "&#165";
	    break;
	  case  "KRW":
		  currencyType = "&#8361";
		break;
	  case  "NGN":
		  currencyType = "&#8358";
		break;
	  case  "PHP":
		  currencyType = "&#8369";
	    break;
	  case  "PLN":
		  currencyType = "PLN";
	    break;
	  case  "PYG":
		  currencyType = "&#8370";
	    break;
	  case  "THB":
		  currencyType = "&#3647";
	    break;
	  case  "UAH":
		  currencyType = "&#8372";
	    break;
	  case  "VND":
		  currencyType = "&#8363";
	    break;
	  case  "":
		  currencyType = "&#164";
	    break;
	}
	
	return currencyType
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
