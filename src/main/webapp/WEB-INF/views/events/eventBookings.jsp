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
	                    <h3>Event Bookings</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        
	                         <li>
	                            <a href="${Wayuparty_appUrl}/events">Events</a>
	                        </li>
                        
	                        <li>Event Bookings</li>
	                    </ul>
	             </div>
	             
            <div class="card height-auto">    
                    <div class="card-body">
                    <div id="eventsLoaderDiv"></div>
                    <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Booking List</h3>
                            </div>
                           
                        </div>
						    <div class="tab-content">
						      <div class="tab-pane fade show active">
								<div class="modal-body">
								    <div class="table-responsive">
								    
								    <ul class="float-right list-unstyled list-inline" style="margin-bottom: 5px;">
							  			<li id="eventsListSearch" style="position: relative;"><input type="text" class="repSearchBar form-control"  id='eventsDataTableSearch'/><span class="repSearchIcon" id="searchDiv"></span></li>
						       		 </ul>
						       		 
						       		 <div class="service_wrap_table datatable_filter positionRelative">
									    <div class="service_tabs_inn_table dataFilterNew pdtop">
									    	<div id="eventOrdersList" class="datatable_filter"></div>
										</div>	  
						             </div>
								    
						            </div>
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
			getEventsBookingList();
		 };
 </script> 
 
 
 <script>
 function getEventsBookingList(){
	  
	 $("#eventsLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	  var result = "";

	 	result = result + '<table id="eventsListTable" class="display mt10" cellspacing="0" width="100%" >';
		result = result + '<thead>';
		result = result + '<tr>';
	  	result = result +'  <th><i class="fas fa-user mr-2"></i>User Name</th>';
	  	result = result +'  <th><i class="fas fa-ticket-alt mr-2"></i>Ticket Type</th>';
	  	result = result +'  <th><i class="fas fa-clipboard-list mr-2"></i>Ticket Category</th>'
	  	result = result +'  <th><i class="fas fa-money-bill-alt mr-2"></i>Event Amount</th>';
	  	result = result +'  <th><i class="fas fa-code mr-2"></i>Order Code</th>';
		result = result +'  <th><i class="fas fa-cogs mr-2"></i>Actions</th>';
		result = result + '</tr>';
		result = result + '</thead>	';
	    result = result +"</table>";
	    $("#eventOrdersList").empty();
	    $("#eventOrdersList").append(result);
	    $("#eventsListSearch").show();
	    $("#eventsDataTableSearch").val('');
	    getEventBooking();
	   
 }
 </script>
 
 <script>
 function getEventBooking(){
	 var appUrl = '${appUrl}';
	 var vendorUUID = '${vendorUUID}';
	 var eventUUID = '${eventUUID}';
	 var searchValue = $("#eventsDataTableSearch").val();
	 searchValue = searchValue ? searchValue : "";
	 var url = appUrl+"/getUserBookedEventsList?vendorUUID="+vendorUUID+"&eventUUID="+eventUUID+"&searchValue="+searchValue;
	 eventOrdersPagination(url); 
	 $("#eventsLoaderDiv").removeAttr("style");
 }
 </script>
 
 <script>
 function eventOrdersPagination(url){
	 var colIndex = 0;
	    $('#eventsListTable').DataTable( { 
	      "processing": true,
	      "serverSide": true,
	      "searching": false,
	      "ajax": $.fn.dataTable.pipeline( {
	    	  url: url,
	          method: 'GET',
	          pages: 2
	        }),
	        
	        "columns": [
	            { "data": "userName"},
	            { "data": "ticketType"},
	            { "data": "ticketCategory"},
	            { "data": "totalAmount"},
	            { "data": "orderCode"},
	            { "data": "orderActions"}
	        ],
	        "columnDefs": [ {
		    	  	"targets": colIndex++,
		    	  	"data": "userName",
		    	  	"sortable": false,
		    	  	"render": function(data, type, opt, meta) {
		    	  		 var result = "";
		    	  	   	 result = result +"<td>";
	                	 result = result +"<div class='width150px'>";
	                	 result = result +"<div class='tbldata-dotdot-big pull-left' title='"+opt.userName+"'>"+opt.userName;
	                	 result = result +"</div>"
	                	 result = result +"</td>";
			             return result;
		    	  	}
		      },{
		        	  "targets": colIndex++,
		              "data": "email",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.ticketType+"</span></div></td>";
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "guestCode",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.quantity+" x "+opt.ticketCategory+"</span></div></td>";
			              return result;
		              }
			      },{
		        	  "targets": colIndex++,
		              "data": "startDate",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		            	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.currency+" "+opt.totalAmount+"</span></div></td>";
		             	 
			              return result;
		              }
			     },{
		        	  "targets": colIndex++,
		              "data": "orderCode",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		            	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.orderCode+"</span></div></td>";
		             	 
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "orderActions",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	    var result = "";
		            	    
		            	    	    result = result +'<td>';
				            	    result = result +'<div class="tblminwidth-90">';
				            	    result = result +'<ul class="list-unstyled list-inline  clearfix">';
				            	    result = result +'<li style="float: left; margin-left: 15px;"><a href="javascript:void(0)" data-toggle="modal" data-target="#right-slide-modal" onclick="saveUserArrived(\''+opt.orderUUID+'\')"><i class="fas fa-user-plus cursorPointer text-info" title="Details"></i></a></li>';
				            	    result = result +'</ul>';
				            	    result = result +'</div>';
				            	    result = result +'</td>'; 
		            	   
		            	    return result;
		              }
			     }],
	        
		    	"scrollCollapse": false,
		    	"lengthMenu": [[20, 30, 40, 50], [20, 30, 40, 50]],
		    	"bLengthChange": true,
		    	"bDestroy": true,
		    	"ordering": true,
		    	"oLanguage": {
		     		"sSearch": "",
		     		 "sZeroRecords": "", "sEmptyTable": "", 
		     		 "sProcessing": "<img src='/resources/img/loader.gif' style='width: 75px; height: 75px;'/>"
		     }
	    });
	    
	 var searchOrderElement = document.getElementById("searchDiv");
	 searchOrderElement.onclick = function() {
		 getEventBooking();
   	 }
	    
	   $("#eventsListTable").wrap("<div style='overflow-x: auto; overflow-y: hidden;'></div>"); 
	    $("input[type=search]").hide();
	    $("#eventsDataTableSearch").keyup(function(e) {
	    	   if(e.which === 13) {
	   		    $('#eventsDataTableSearch').parent().find('p.jquery_form_error_message').remove();
	   	    	var searchData = $('#eventsDataTableSearch').val();
	   	    	
	   	 	if(searchData.endsWith("-") || searchData.endsWith("\\") || searchData.endsWith("//") || searchData.endsWith("%")){
		    		$("#eventsDataTableSearch").after("<p class='jquery_form_error_message'><span><font color='red'>Enter valid Search Data</font></span>");
		    	}else{
		    		getEventBooking();
		    	}
	   	    	$('#eventsDataTableSearch').val(searchData);
	    	   }
	    	}); 
 }
 </script>
 
 
 