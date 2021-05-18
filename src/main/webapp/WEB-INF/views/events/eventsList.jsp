<div id="wrapper" class="wrapper bg-ash">
        <div class="dashboard-page-one">
                          <div class="container">
 							<div id="eventsList" class="row"></div>
 							<br/>
 						</div>
                           
       </div>
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
	   
	   $("#eventInfoLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
		    	type: "GET",
		    	 url: appUrl+"/ws/getVendorEvents?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID, 
		        success: function(resultData) {
		   			
		        	var result = "";
		        			        	
		        	if(resultData.data.length > 0){
			        	for (var i=0; i<resultData.data.length; i++)
		   				{  
			        		var opt = resultData.data[i];
			        		
	                        result = result +'<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">';
	                        result = result +'<a class="event-cards" href="'+appUrl+'/ws/eventDetails?eventUUID='+opt.eventUUID+'&vendorUUID='+vendorUUID+'">';
	                        result = result +'<div class="col6">';
	                        result = result +'<div class="clearfix text-center event-dates">';
	                        result = result +'<h4>'+opt.day+'</h4>';
	                        result = result +'<h3>'+opt.date+'</h3>';
	                        result = result +'<h4>'+opt.month+'</h4>';
	                        result = result +'</div>';
	                        result = result +'<div class="clearfix"></div>';
	                        result = result +'<div class="event-detials">';
	                        result = result +'<div>';
	                        result = result +'<i class="fas fa-music"></i>';
	                        result = result +'<div class="eventHost">'+opt.eventName+'</div>';
	                        result = result +'</div>';
	                        result = result +'<div>';
	                        result = result +'<i class="fas fa-clock"></i>';
	                        result = result +'<div class="eventTimerClock">'+opt.time+'</div>';
	                        result = result +'</div>';
	                        result = result +'<div>';
	                        result = result +'<i class="fas fa-map-marked-alt"></i>';
	                        result = result +'<div class="eventLocation">'+opt.eventLocation+'</div>';
	                        result = result +'</div>';
	                        result = result +'</div>';
	                        result = result +'</div>';
	                        result = result +'<div class="col6 eventImage">';
	                        result = result +'<img src="'+opt.eventImage+'" alt="EventImage" class="img-responsive">';
	                        result = result +'</div>';
	                        result = result +'</a>';
	                        result = result +'</div>';
	                        
		   				}
		        	}else{
		        		result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
	   				}
		        	
		        	 $("#eventInfoLoadingDiv").removeAttr("style");
		        	 $("#eventsList").empty();  
	   	    	     $("#eventsList").append(result);
		        },
		    }); 
	   
   }
   </script>	 