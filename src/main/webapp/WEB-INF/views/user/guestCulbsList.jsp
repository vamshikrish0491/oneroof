<div class="tab-pane fade show active" id="orders" role="tabpanel">
		<div class="modal-body">
		    <div class="table-responsive">
		    
		    <ul class="float-right list-unstyled list-inline" style="margin-bottom: 5px;">
	  			<li id="guestsListSearch" style="position: relative;"><input type="text" class="repSearchBar form-control"  id='guestsDataTableSearch'/><span class="repSearchIcon" id="searchDiv"></span></li>
       		 </ul>
       		 
       		 <div class="service_wrap_table datatable_filter positionRelative">
			    <div class="service_tabs_inn_table dataFilterNew pdtop">
			    	<div id="guestsList" class="datatable_filter"></div>
				</div>	  
             </div>
		    
            </div>
		</div>
</div>

<jsp:include page="../guest/guestDetails.jsp" />


<script>
 function getGuestsList(){
	  
	 $("#guestsLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	  var result = "";

	 	result = result + '<table id="guestsListTable" class="display mt10" cellspacing="0" width="100%" >';
		result = result + '<thead>';
		result = result + '<tr>';
	  	result = result +'  <th><i class="fas fa-building mr-2"></i>Club</th>';
	  	result = result +'  <th><i class="fas fa-map-marked-alt mr-2"></i>Location</th>';
	  	result = result +'  <th><i class="fas fa-code mr-2"></i>Guest Code</th>'
	  	result = result +'  <th><i class="fas fa-calendar-alt mr-2"></i>Date</th>';
		result = result +'  <th><i class="fas fa-cogs mr-2"></i>Actions</th>';
		result = result + '</tr>';
		result = result + '</thead>	';
	    result = result +"</table>";
	    $("#guestsList").empty();
	    $("#guestsList").append(result);
	    $("#guestsListSearch").show();
	    $("#guestsDataTableSearch").val('');
	    getGuestClubs();
	   
 }
 </script>
 
 <script>
 function getGuestClubs(){
	 var appUrl = '${appUrl}';
	 var userUUID = '${userUUID}';
	 var searchValue = $("#guestsDataTableSearch").val();
	 searchValue = searchValue ? searchValue : "";
	 var url = appUrl+"/userGuestsList?userUUID="+userUUID+"&searchValue="+searchValue;
	 guestsPagination(url); 
	 $("#guestsLoaderDiv").removeAttr("style");
 }
 </script>
 
 <script>
 function guestsPagination(url){
	 var colIndex = 0;
	    $('#guestsListTable').DataTable( { 
	      "processing": true,
	      "serverSide": true,
	      "searching": false,
	      "ajax": $.fn.dataTable.pipeline( {
	    	  url: url,
	          method: 'GET',
	          pages: 2
	        }),
	        
	        "columns": [
	            { "data": "clubName"},
	            { "data": "clubLocation"},
	            { "data": "guestCode"},
	            { "data": "startDate"},
	            { "data": "guestActions"}
	        ],
	        "columnDefs": [ {
		    	  	"targets": colIndex++,
		    	  	"data": "clubName",
		    	  	"sortable": false,
		    	  	"render": function(data, type, opt, meta) {
		    	  		 var result = "";
		    	  	   	 result = result +"<td>";
	                	 result = result +"<div class='width150px'>";
	                	 result = result +"<div class='tbldata-dotdot-big pull-left' title='"+opt.club+"'>"+opt.club;
	                	 result = result +"</div>"
	                	 result = result +"</td>";
			             return result;
		    	  	}
		      },{
		        	  "targets": colIndex++,
		              "data": "clubLocation",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.clubLocation+"</span></div></td>";
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "guestCode",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.guestCode+"</span></div></td>";
			              return result;
		              }
			      },{
		        	  "targets": colIndex++,
		              "data": "startDate",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		            	  if(opt.endDate != ''){
		            		  result = result +"<td><div class='tblminwidth-90'><span>"+opt.startDate+" to "+opt.endDate+"</span></div></td>";
		            	  }else{
		            		  result = result +"<td><div class='tblminwidth-90'><span>"+opt.startDate+"</span></div></td>";
		            	  }
		             	 
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "guestActions",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	    var result = "";
		            	    
		            	    	    result = result +'<td>';
				            	    result = result +'<div class="tblminwidth-90">';
				            	    result = result +'<ul class="list-unstyled list-inline  clearfix">';
				            	    result = result +'<li style="float: left; margin-left: 15px;"><a href="javascript:void(0)" data-toggle="modal" data-target="#right-slide-modal" onclick="getGuestDetails(\''+opt.guestUUID+'\')"><i class="fas fa-eye cursorPointer text-info" title="Details"></i></a></li>';
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
		 getServiceOrders(bottleServiceUUID);
   	 }
	    
	   $("#guestsListTable").wrap("<div style='overflow-x: auto; overflow-y: hidden;'></div>"); 
	    $("input[type=search]").hide();
	    $("#guestsDataTableSearch").keyup(function(e) {
	    	   if(e.which === 13) {
	   		    $('#guestsDataTableSearch').parent().find('p.jquery_form_error_message').remove();
	   	    	var searchData = $('#guestsDataTableSearch').val();
	   	    	
	   	 	if(searchData.endsWith("-") || searchData.endsWith("\\") || searchData.endsWith("//") || searchData.endsWith("%")){
		    		$("#guestsDataTableSearch").after("<p class='jquery_form_error_message'><span><font color='red'>Enter valid Search Data</font></span>");
		    	}else{
		    		getGuestClubs();
		    	}
	   	    	$('#guestsDataTableSearch').val(searchData);
	    	   }
	    	}); 
 }
 </script>
 
 
 
 

 
