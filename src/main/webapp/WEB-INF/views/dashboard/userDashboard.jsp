<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
.mg-bt-20 {
 	margin-bottom: 20px;
 }
 #wrapper {
  background-image: url('/resources/img/Wayuparty_bg_Page.jpg');
  background-repeat: no-repeat;
  background-position: center;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
   background-size: cover;
}
.sidebar-color {
  border-right:1px solid;
}
</style>
<div id="preloader"></div>

   <div id="wrapper" class="wrapper bg-ash">
    <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
          <jsp:include page="../wayupartyMasterSideNav.jsp" />
                    <div id="registeredVendorsLoadingDiv"></div>
                          <div class="container mt-5">
					         <div class="card mb-3 search_blo">
					            <div class="input-group search-blo">
					                <input name="vendors_txtSearch" type="text"  id="vendors_txtSearch" class="form-control"  placeholder="Search">
					                <div class="input-group-btn">
					                    <a class="btn btn-vendor-search" style="margin-right: -10px;border-bottom-right-radius: 0px !important;border-top-right-radius: 0px !important;"><span class="fa fa-sync-alt" onclick="clearClubSearch();"></span></a>
					                     <a href="#" class="btn btn-vendor-search" data-toggle="modal" data-target="#large-modal" onclick="getPopularCities()">
							            <img src="/resources/img/cities/Locate_icon_1.png" alt="locate" style="width: 22%;margin-left: -42px;"/> Cities
							          </a>
					                </div>
							         
					            </div>
					        </div>
				             	        
						<div class="mg-bt-20">
					        <jsp:include page="../packageBanner.jsp" />
					     </div>
                          
 							<div id="registeredVendorsList" class="row"></div>
 							<jsp:include page="../clubLocationSearch.jsp" />
 						</div>
                           
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
   
 
 <script>
 getSpecialPackageBannersList();
 
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
	    var formData = new FormData();
		formData.append("latitude", latitude);
		formData.append("longitude", longitude);
	   
	   $("#registeredVendorsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
	    	   
		    	type: "GET",
		    	url: appUrl+"/ws/getAllregisteredRestaurantsList?latitude="+latitude+"&longitude="+longitude, 
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
			        		
			        	    result = result +'<a href="'+appUrl+'/bookService?vendorUUID='+opt.vendorUUID+'" class="card-link">';
			        		result = result +'<div class="col-md-4 mb-5">';
			        		result = result +'<div class="vendors-card">';
			        		result = result +'<div class="cardbody">';
			        		result = result +'<img src="'+opt.vendorProfileImg+'" onerror="predefineVendorProfileImage(this)">';
			        		result = result +'</div>';
			        		result = result +'<div class="card">';
			        		result = result +'<div class="card-footer">';
			        		
			        		result = result+'<div class="row">';
			        		
			        		result = result +'<div class="clearfix vendors-title col-xl-12 col-lg-6 col-12">';
			        		result = result +'<h5>';
			        		result = result +opt.vendorName;
			        		result = result +'</h5>';
			        		result = result +'</div>';
			        		
			        		result = result +'</div>';
			        		
			        		result = result +'<div class="divpadding">';
			        		result = result +'<div class="clearfix row">';
			        		result = result +'<div class="col-sm-1 col-xs-2">';
			        		result = result +'<i class="fas fa-map-marker mt-1 text-black" aria-hidden="true"></i>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-10 col-xs-10 text-black">'+location+'</div>';
			        		result = result +'<div class="col-sm-1 col-xs-2">';
			        		result = result +'<i class="fas fa-th-list mt-1 text-black" aria-hidden="true"></i>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-10 col-xs-10 text-black">'+opt.bestSellingItems+'</div>';
			        		result = result +'<div class="col-sm-1 col-xs-2 text-black">';
			        		result = result +'<span>'+currencyCode+'</span>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-10 col-xs-10 text-black">'+opt.costForTwoPeople+'</div>';
			        		//result = result +'<div class="col-sm-1 col-xs-2">';
			        		//result = result +'<i class="fa fa-map mt-1 text-black" aria-hidden="true"></i>';
			        		//result = result +'</div>';
			        		//result = result +'<div class="col-sm-10 col-xs-10 text-black">'+opt.kilometers+' KM</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'<div class="card-footer text-muted">';
			        		result = result +'<div class="container-fluid" style="position: relative; text-align: center; color: white;">';
			        		result = result +'<div class="bottom_left" style="position: absolute; bottom: -5px; left: 5px;">';
			        		result = result +'<a href="'+appUrl+'/vendorInfo?vendorUUID='+opt.vendorUUID+'" class="card-link"><span class="fa fa-search-plus">&nbsp;</span>Explore</a>';
			        		result = result +'</div>';
			        		result = result +'<div class="bottom_left" style="position: relative; text-align: center;bottom: -5px;right: 5px;">';
			        		result = result +'<a href="'+appUrl+'/clubEvents?vendorUUID='+opt.vendorUUID+'" class="card-link"><i class="fas fa-music" aria-hidden="true"></i>&nbsp;Events</a>';
			        		result = result +'</div>';
			        		result = result +'<div class="bottom_left" style="position: absolute; bottom: -5px; right: 5px;">';
			        		result = result +'<a href="'+appUrl+'/bookService?vendorUUID='+opt.vendorUUID+'" class="card-link"><i class="fas fa-bullhorn" aria-hidden="true"></i>&nbsp;Services</a>';
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
		        	
		        	 $("#registeredVendorsLoadingDiv").removeAttr("style");
		        	 $("#registeredVendorsList").empty();  
	   	    	     $("#registeredVendorsList").append(result);
	   	    	     $("#vendors_txtSearch").val('');
	   	    	     closeDataModal();
	   	    	     googleApiLocation('vendors_txtSearch','country'); 
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
   
 
   
   
   <script>
function vendorPagination(){ 
	$('#vendorPagination').DataTable( { 
    	"scrollCollapse": false,
    	"bLengthChange": true,
    	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
    	"bDestroy": true,
    	"ordering": false,
    	"oLanguage": {
     		"sSearch": "",
     		"sSearchPlaceholder":"Search Vendor",
     		 "sZeroRecords": "", "sEmptyTable": ""
     	}
    } );
}
</script> 
 
                