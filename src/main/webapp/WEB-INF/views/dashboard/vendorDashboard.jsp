<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="/resources/js/amcharts.js" type="text/javascript"></script>
<script src="/resources/js/serial.js" type="text/javascript"></script>

<div id="preloader"></div>

   <div id="wrapper" class="wrapper bg-ash">
    <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
          <jsp:include page="../wayupartyMasterSideNav.jsp" />
            <div class="dashboard-content-one">
                <div class="breadcrumbs-area">
                    <h3>Admin Dashboard</h3>
                </div>
                
                <div class="row gutters-20">
                   <div id="portletsLoadingDiv"></div>
                    <!-- <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <div class="serve-iconblo">
                                        <img src="/resources/img/bottle.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Cheers</div>
                                        <div class="item-number"><span id="bottleCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/table.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Venue</div>
                                        <div class="item-number"><span  id="tableCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                   <div class="serve-iconblo">
                                        <img src="/resources/img/packages.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Cuisine</div>
                                        <div class="item-number"><span  id="packageCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<!--                     
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/entry.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Make a Jump</div>
                                        <div class="item-number"><span  id="entryCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    
                    <!-- <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/guest.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Guest List</div>
                                        <div class="item-number"><span  id="guestCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    
                    <!-- <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/surprise.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Make It Personal</div>
                                        <div class="item-number"><span  id="surpriseCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    
                    <!-- <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/offers.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Deals</div>
                                        <div class="item-number"><span  id="offersCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                </div>
                
                <div class="row gutters-20">
                    <div class="col-xl-16 col-sm-16 col-6-xxxl">
                        <div class="card dashboard-card-one pd-b-20">
                            <div class="card-body">
	                            <div class="earning-chart-wrap">
	                            	<div id="chartdiv" style="width: 100%; height:400"></div>
	                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                            
               
             
                
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 
 <script type="text/javascript">
		window.onload = function () {
			getVendorDashboardCounts();
			getVendorServicesSerialChart();
			
		 };
		 
		 
   function getVendorDashboardCounts(){
	   var vendorUUID = '${Wayuparty_vendorUUID}';

			 var appUrl ='${appUrl}';
				var formData = new FormData();
				formData.append("vendorUUID", vendorUUID);
			     $.ajax({
			    		data: formData,
			    	    contentType: false,
			    	    processData: false,
				    	type: "POST",
				    	 url: appUrl+"/getVendorServicesDashboradCount?${_csrf.parameterName}=${_csrf.token}", 
				        success: function(result) {
				        	
				        	
				    	if(result && result.response == "SUCCESS"){
								
				    		$("#bottleCount").html(result.object.bottleServiceCount);
				    		$("#tableCount").html(result.object.tableServiceCount);
				    		$("#packageCount").html(result.object.packagesServiceCount);
				    		$("#entryCount").html(result.object.entryServiceCount);
				    		$("#surpriseCount").html(result.object.surpriseServiceCount);
				    		$("#offersCount").html(result.object.offersServiceCount);
				    		
				    		
				        	}else if (result && result.response === "AWKWARD") {
				        		location.href = "/errorPage";
				        	}
		 
				        },
				    }); 
	 }		
   
   
   function getVendorServicesSerialChart(){
	   
	   var vendorUUID = '${Wayuparty_vendorUUID}';

		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("vendorUUID", vendorUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/getVendorServiceOrdersSerialChart?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			    	if(result && result.response == "SUCCESS"){
			    		
			    		 var chartData = [];
			    		if(result.data.length > 0){
			    			for (var i=0; i<result.data.length; i++)
			   				{
			    				var orders = result.data[i];
			    			    chartData.push( {
			        			      day: orders.serviceOrderDate,
			        			      bottle:orders.servicesCount.bottleServiceCount,
			        			      table:orders.servicesCount.tableServiceCount,
			        			      packages:orders.servicesCount.packagesServiceCount,
			        			      entry:orders.servicesCount.entryServiceCount,
			        			      surprise:orders.servicesCount.surpriseServiceCount,
			        			      offers:orders.servicesCount.offersServiceCount
			        			    } );
			   				}
			    			loadVendorsServicesSerialChart(chartData);
			    		}
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/errorPage";
			        	}
	 
			        },
			    }); 
}		

		 
 </script> 
 
 
 <script>
 function loadVendorsServicesSerialChart(result){
  var chart = AmCharts.makeChart("chartdiv", {
	    "type": "serial",
	    "theme": "light",
	    "legend": {
	        "useGraphSettings": true
	    },
	    "dataProvider": result,
	    "startDuration": 1,
	    "valueAxes": [{
	        "integersOnly": true,
	        "maximum": 500,
	        "minimum": 1,
	        "reversed": false,
	        "axisAlpha": 0,
	        "dashLength": 5,
	        "gridCount": 10,
	        "position": "left",
	        "title": "ORDERS COUNT"
	    }],
	    "startDuration": 0.5,
	    "graphs": [{
	        "balloonText": "Venue: [[value]]",
	        "bullet": "round",
	        "columnWidth": 0.3,
	        "title": "Venue",
	        "valueField": "table",
			"fillAlphas": 0
	    },{
	        "balloonText": "Cuisine: [[value]]",
	        "bullet": "round",
	        "columnWidth": 0.3,
	        "title": "Cuisine",
	        "valueField": "packages",
			"fillAlphas": 0
	    },{
	        "balloonText": "Event: [[value]]",
	        "bullet": "round",
	        "columnWidth": 0.3,
	        "title": "Deals",
	        "valueField": "offers",
			"fillAlphas": 0
	    }],
	    "chartCursor": {
	        "cursorAlpha": 0,
	        "zoomable": false
	    },
	    "categoryField": "day",
	    "categoryAxis": {
	        "gridPosition": "start",
	        "axisAlpha": 0,
	        "fillAlpha": 0.05,
	        "fillColor": "#000000",
	        "gridAlpha": 0,
	        "position": "top"
	    },
	    "export": {
	    	"enabled": true,
	        "position": "bottom-right"
	     }
	});
 }
  </script> 