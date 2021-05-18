<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/fav-icon.jpeg">
    <title>ONEROOF | DEALS</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://maps.google.com/maps/api/js?libraries=places&v=3&key=${googleMapsLocationApiKey}" type="text/javascript"></script>
</head>

<style>
body {
  background-image: url('/resources/img/Wayuparty_bg_Page.jpg');
  background-repeat: no-repeat;
  background-position: center;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-size: cover;
}
.navbar-default .navbar-nav>li>a {
    color: white !important;
}
.col-sm-2 {
    width: 10.666667%;
}
.btn-vendor-search {
    color: #fff;
}

 .btn-secondary {
    color: #fff !important;
    background-color: #6c757d !important; 
    border-color: #6c757d !important;
    height: 32px !important;
    border-bottom-right-radius: 10px !important; 
    border-top-right-radius: 10px !important; 
} 

.search-blo .input-group-btn {
    position: relative;
    white-space: nowrap;
    left: -29px;
}
.input-group-btn:last-child>.btn, .input-group-btn:last-child>.btn-group {
    margin-left: -9px;
}
</style>

<body>
<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="clearfix">
	    <div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
	      <img src="/resources/img/logo.png" alt="logo" class="homeHeader">
	    </div>
	      <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav navbar-right">
	        
	        <li><a href="/clubs">CLUBS</a></li>
	       <li><a href="/services" target="_blank">SERVICES</a></li>
	        <li><a href="/deals">DEALS</a></li>	        
	        <li><a href="/login">LOGIN</a></li>
	      </ul>
	      </div>
	  </div>
</nav>


    <div class="container mt-10">
		   <div class="card mb-3 search_blo">
	            <div class="input-group search-blo">
	                <input name="vendors_txtSearch" type="text"  id="vendors_txtSearch" class="form-control"  placeholder="Search Deals">
	                <div class="input-group-btn">
	                    <a class="btn btn-vendor-search" style="border-bottom-right-radius: 0px !important;border-top-right-radius: 0px !important;"><span class="fa fa-refresh" onclick="clearClubSearch();"></span></a>
	                     <a href="#" class="btn btn-vendor-search" data-toggle="modal" data-target="#large-modal" onclick="getPopularCities()">
			            <img src="/resources/img/cities/Locate_icon_1.png" alt="locate" style="width: 30%;float: left;"/>Cities
			          </a>
	                </div>
	            </div>
	        </div><br/>
 	<div id="registeredVendorsList" class="row"></div>
 	<jsp:include page="clubLocationSearch.jsp" />
 	</div>
 
 <footer class="footer" style="background-color: #000;">
      <div align="center">ONEROOF � 2019. copyrights all rights reserved. Developed by Acculytixs Pvt Ltd.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
         	<a href="/privacyPolicy" target="_blank" style="color:rgb(212,175,55);">PRIVACY POLICY</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
      		<a href="/termsAndConditions" target="_blank" style="color:rgb(212,175,55);">TERMS AND CONDITIONS</a>
      </div>            
			
</footer>


 <script>
function googleApiLocation(locationId) {
	initialize(locationId);
}
function initialize(locationId) {
    	autocomplete = new google.maps.places.Autocomplete((document.getElementById(locationId)), {
    		types: ['geocode']
      
        });
  google.maps.event.addListener(autocomplete, 'place_changed', function() {
  place = autocomplete.getPlace();
  var latitude = place.geometry.location.lat();
  var longitude = place.geometry.location.lng();
  getRestaurantsList(latitude,longitude);
  });
}
</script>

<script>
function clearClubSearch(){
	  $("#vendors_txtSearch").val('');
		var latitude = '';
		var longitude = '';
		 if (navigator.geolocation) {
			 navigator.geolocation.getCurrentPosition(function(position) {
			     latitude = position.coords.latitude;
			     longitude = position.coords.longitude;
			     getRestaurantsList(latitude,longitude);
			  });
		 }else{
			 getRestaurantsList(latitude,longitude);
		 }
}
</script>


  <script type="text/javascript">
		window.onload = function () {
			var latitude = '';
			var longitude = '';
			 if (navigator.geolocation) {
				 navigator.geolocation.getCurrentPosition(function(position) {
				     latitude = position.coords.latitude;
				     longitude = position.coords.longitude;
				     getRestaurantsList(latitude,longitude);
				  });
			 }else{
				 getRestaurantsList(latitude,longitude);
			 }
			 
			 googleApiLocation('vendors_txtSearch','country'); 
			 
		 };
 </script>
 
 <script>
   function getRestaurantsList(latitude,longitude){
	   
	    var appUrl ='${appUrl}';
	   $("#registeredVendorsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
		    	type: "GET",
		    	 url: appUrl+"/ws/getAllregisteredRestaurantsList?latitude="+latitude+"&longitude="+longitude+"&deals=Y", 
		        success: function(resultData) {
		   			
		        	var result = "";
		        	var currencyCode = '';
		        	 
		        	if(resultData.data.length > 0){
			        	for (var i=0; i<resultData.data.length; i++)
		   				{  
			        		var opt = resultData.data[i];
			        		
			        		var location = '';
			        		if(opt.location != ''){
			        			location = opt.location;
			        		}else{
			        			location = 'NO DATA';
			        		}
			        		
			        		currencyCode = getCurrency(opt.currency);
			        		
			        		result = result +'<a href="'+appUrl+'/ws/bookService?vendorUUID='+opt.vendorUUID+'" class="card-link"><div class="col-md-4 mb-5">';
			        		result = result +'<div class="vendors-card">';
			        		result = result +'<div class="cardbody">';
			        		result = result +'<img src="'+opt.vendorProfileImg+'" onerror="predefineVendorProfileImage(this)">';
			        		result = result +'</div>';
			        		result = result +'<div class="card">';
			        		result = result +'<div class="card-footer">';
			        		result = result +'<div class="clearfix vendors-title text-success">';
			        		result = result +'<h5>';
			        		result = result +opt.vendorName;
			        		result = result +'</h5>';
			        		result = result +'</div>';
			        		result = result +'<div class="divpadding">';
			        		result = result +'<div class="clearfix row">';
			        		result = result +'<div class="col-sm-1 col-xs-2">';
			        		result = result +'<i class="fa fa-map-marker text-black" aria-hidden="true"></i>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-11 col-xs-10 text-black">'+location+'</div>';
			        		result = result +'<div class="col-sm-1 col-xs-2">';
			        		result = result +'<i class="fa fa-th-list text-black" aria-hidden="true"></i>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-11 col-xs-10 text-black">'+opt.bestSellingItems+'</div>';
			        		result = result +'<div class="col-sm-1 col-xs-2 text-black">';
			        		result = result +'<span>'+currencyCode+'</span>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-11 col-xs-10 text-black">'+opt.costForTwoPeople+'</div>';
			        		//result = result +'<div class="col-sm-1 col-xs-2">';
			        		//result = result +'<i class="fa fa-map text-black" aria-hidden="true"></i>';
			        		//result = result +'</div>';
			        		//result = result +'<div class="col-sm-11 col-xs-10 text-black">'+opt.kilometers+' KM</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'<div class="card-footer text-muted">';
			        		result = result +'&nbsp;';
			        		result = result +'<div class="container-fluid" style="position: relative; text-align: center; color: white;">';
			        		result = result +'<div class="bottom_left" style="position: absolute; bottom: 0px; left: 5px;">';
			        		result = result +'<a href="'+appUrl+'/ws/vendorInfo?vendorUUID='+opt.vendorUUID+'" class="card-link"><span class="fa fa-search-plus">&nbsp;</span>Explore</a>';
			        		result = result +'</div>';
			        		result = result +'<div class="bottom_left" style="position: absolute; bottom: 0px; right: 5px;">';
			        		result = result +'<a href="'+appUrl+'/ws/bookService?vendorUUID='+opt.vendorUUID+'" class="card-link"><i class="fa fa-bullhorn" aria-hidden="true"></i>&nbsp;Services </a>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div></a>';
			        		
			        	
		   				}
		        	}else{
		        		result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_clubs_found.jpg" alt=""/></td>';
	   				}
		        	
		        	 $("#registeredVendorsList").empty();  
	   	    	     $("#registeredVendorsList").append(result);
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
   
<script>
	function predefineVendorProfileImage(imageId){
		$(imageId).attr('src','${Wayuparty_appUrl}/resources/img/default-vendor-img.jpg');
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

