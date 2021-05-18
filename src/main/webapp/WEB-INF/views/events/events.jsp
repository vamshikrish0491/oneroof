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
	                    <h3>Guests</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Events</li>
	                    </ul>
	             </div>
	             
            <div class="card height-auto">    
                    <div class="card-body">
                    <div id="eventsLoaderDiv"></div>
                    <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Events List</h3>
                            </div>
                            <div class="dropdown">
                               <a class="text-black" href="${Wayuparty_appUrl}/addEvent"><i class="fas fa-plus text-dark-pastel-green"></i>&nbsp;Add Event</a>
                            </div> 
                        </div>
                          <div class="table-responsive">
                            	<div id="eventsList"></div>
                           </div>
                  </div>
              </div>
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script type="text/javascript">
		window.onload = function () {
			getEventsList();
		 };
 </script> 
 
 <script>
   function getEventsList(){
	   
	    var appUrl ='${appUrl}';
	    var vendorUUID = '${vendorUUID}';
	   
	   $("#eventsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
		    	type: "GET",
		    	 url: appUrl+"/getVendorEventsList?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID, 
		        success: function(resultData) {
		   			
		        	var result = "";
		        	result = result +"<table class='table display data-table text-nowrap' id='eventsPagination'>";
		        	result = result +"<thead>";
		        	result = result +"<tr>";
		        	result = result +"<th>Photo</th>";
		        	result = result +"<th>Event Name</th>";
		        	result = result +"<th>Event Date</th>";
		        	result = result +"<th>Event Host</th>";
		        	result = result +"<th>Open Bookings</th>";
		        	result = result +"<th>Actions</th>";
		        	result = result +"</tr>";
		        	result = result +"</thead>";
                    
		        	result = result +"<tbody>";
		        	
		        	if(resultData.data.length > 0){
			        	for (var i=0; i<resultData.data.length; i++)
		   				{  
			        		var opt = resultData.data[i];
			        		
			        		result = result +"<tr>";
			        		result = result +"<td class='width80'>";
			        		
			        		result = result +'<div class="media">';
	  		    			result = result +'<div class="mediaimagezoo">';
	  		    			result = result +'<img src="'+opt.eventImage+'" onerror="predefineVendorProfileImage(this);" alt="image" class="img-fluid rounded mr-3" style="width:75px;">';
	  		    			result = result +'</div>';
	  		    			result = result +'</div>';
			        		
			        		result = result +"</td>";
			        		result = result +"<td>"+opt.eventName+"</td>";
			        		result = result +"<td>"+opt.eventDate+"</td>";
			        		result = result +"<td>"+opt.eventHost+"</td>";
			        		result = result +"<td>"+opt.bookingsOpen+"</td>";
			        		
			        		result = result +"<td class='width100'>";
			        		result = result +"<div class='header-inline item-header'>";
			        		result = result +"<div class='header-elements'>";
			        		result = result +"<ul>";
			        		
			        		result = result +'<li>';
			        		result = result +'<a href="#" data-toggle="modal" data-target="#right-slide-modal" onclick="getEventDetails(\''+opt.eventUUID+'\')"><i class="fas fa-eye mr-3"></i></a>';
			        	    result = result +'<a href="'+appUrl+'/eventBookings?eventUUID='+opt.eventUUID+'" ><i class="fas fa-user-tag mr-3" title="Bookings"></i></a>'; 
			        	    if(opt.dayDiff == 0){
			        	    	 result = result +'<a href="'+appUrl+'/editEvent?eventUUID='+opt.eventUUID+'" ><i class="fas fa-edit mr-3" title="Bookings"></i></a>'; 
			        	    }
			        		result = result +"</li>";
			        		result = result +"</ul>";
			        		result = result +"</div>";
			        		result = result +"</div>";
	                        result = result +"</td>";
	                        result = result +"</tr>";
		   				}
		        	}else{
		        		result = result +"<tr>";
	   					result = result+'<td colspan="5" class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
	   					result = result +"</tr>";
	   				}
		        	
	
		        	
		        	result = result +"</tbody>";
		        	result = result +"</table>";
                    
		        	 $("#eventsLoaderDiv").removeAttr("style");
		        	 $("#eventsList").empty();  
	   	    	     $("#eventsList").append(result);
	   	    	     eventsPagination();
		        },
		    }); 
	   
   }
   </script>
   
   <script>
function eventsPagination(){ 
	$('#eventsPagination').DataTable( { 
    	"scrollCollapse": false,
    	"bLengthChange": true,
    	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
    	"bDestroy": true,
    	"ordering": false,
    	"oLanguage": {
     		"sSearch": "",
     		"sSearchPlaceholder":"Search Event",
     		 "sZeroRecords": "", "sEmptyTable": ""
     	}
    } );
}
</script> 
 
 
 