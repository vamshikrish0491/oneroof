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
	                    <h3>Registered Users</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Registered Users</li>
	                    </ul>
	             </div>
	             
            <div class="card height-auto">    
                    <div class="card-body">
                    <div id="usersLoaderDiv"></div>
                    <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Users List</h3>
                            </div>
                            <div class="dropdown">
                               <a class="text-black" href="${Wayuparty_appUrl}/createCoupon"><i class="fas fa-plus text-dark-pastel-green"></i>&nbsp;Add Coupon</a>
                            </div> 
                        </div>
					     <div class="tab-content">
								<div class="tab-pane fade show active" id="orders" role="tabpanel">
										<div class="modal-body">
										    <div class="table-responsive">
										    
										    <ul class="float-right list-unstyled list-inline" style="margin-bottom: 5px;">
									  			<li id="usersListSearch" style="position: relative;"><input type="text" class="repSearchBar form-control"  id='usersDataTableSearch'/><span class="repSearchIcon" id="searchDiv"></span></li>
								       		 </ul>
								       		 
								       		 <div class="service_wrap_table datatable_filter positionRelative">
											    <div class="service_tabs_inn_table dataFilterNew pdtop">
											    	<div id="usersList" class="datatable_filter"></div>
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
			getUsersList();
		 };
 </script>
 
 
 <script>
 function getUsersList(){
	  
	 $("#usersLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	  var result = "";

	 	result = result + '<table id="usersListTable" class="display mt10" cellspacing="0" width="100%" >';
		result = result + '<thead>';
		result = result + '<tr>';
	  	result = result +'  <th><i class="fas fa-user mr-2"></i>User Name</th>';
	  	result = result +'  <th><i class="fas fa-envelope mr-2"></i>Email</th>';
	  	result = result +'  <th><i class="fas fa-mobile-alt mr-2"></i>Mobile</th>'
	  	result = result +'  <th><i class="fas fa-calendar-alt mr-2"></i>Registered Date</th>';
		result = result +'  <th><i class="fas fa-cogs mr-2"></i>Actions</th>';
		result = result + '</tr>';
		result = result + '</thead>	';
	    result = result +"</table>";
	    $("#usersList").empty();
	    $("#usersList").append(result);
	    $("#usersListSearch").show();
	    $("#usersDataTableSearch").val('');
	    getRegisteredUsers();
	   
 }
 </script>
 
 <script>
 function getRegisteredUsers(){
	 var appUrl = '${appUrl}';
	 var searchValue = $("#usersDataTableSearch").val();
	 searchValue = searchValue ? searchValue : "";
	 var url = appUrl+"/getRegisteredUsersList?&searchValue="+searchValue;
	 usersPagination(url); 
	 $("#usersLoaderDiv").removeAttr("style");
 }
 </script>
 
 <script>
 function usersPagination(url){
	 var colIndex = 0;
	    $('#usersListTable').DataTable( { 
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
	            { "data": "email"},
	            { "data": "mobileNumber"},
	            { "data": "registeredDate"},
	            { "data": "userActions"}
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
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.email+"</span></div></td>";
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "mobileNumber",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.mobileNumber+"</span></div></td>";
			              return result;
		              }
			      },{
		        	  "targets": colIndex++,
		              "data": "registeredDate",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		                  result = result +"<td><div class='tblminwidth-90'><span>"+opt.registeredDate+"</span></div></td>";
		             	 
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "userActions",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	    var result = "";
		            	    
		            	    var checked = '';
			        		if(opt.isActiveUser == 'Y'){
			        			checked = 'checked'
			        		}
		            	    	    result = result +'<td>';
				            	    result = result +'<div class="tblminwidth-90">';
				            	    result = result +'<ul class="list-unstyled list-inline  clearfix">';
				            	    result = result +'<li>';
				            	    result = result +'<span class="clubs-toggle-button">';
					        		result = result +'<label class="switch"><input type="checkbox" '+checked+' id="userActive'+opt.userUUID+'" onclick="getUserActiveStatus(\''+opt.userUUID+'\')" />';
					        		result = result +'<span class="slider round"></span>';
					        		result = result +'</label>';
					        		result = result +'</span>';
					        		result = result +'</uli>';
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
		 getRegisteredUsers();
   	 }
	    
	   $("#usersListTable").wrap("<div style='overflow-x: auto; overflow-y: hidden;'></div>"); 
	    $("input[type=search]").hide();
	    $("#usersDataTableSearch").keyup(function(e) {
	    	   if(e.which === 13) {
	   		    $('#usersDataTableSearch').parent().find('p.jquery_form_error_message').remove();
	   	    	var searchData = $('#usersDataTableSearch').val();
	   	    	
	   	 	if(searchData.endsWith("-") || searchData.endsWith("\\") || searchData.endsWith("//") || searchData.endsWith("%")){
		    		$("#usersDataTableSearch").after("<p class='jquery_form_error_message'><span><font color='red'>Enter valid Search Data</font></span>");
		    	}else{
		    		getRegisteredUsers();
		    	}
	   	    	$('#usersDataTableSearch').val(searchData);
	    	   }
	    	}); 
 }
 </script>
 
 
 
 <script>
 function getUserActiveStatus(userUUID){
		var checked = document.getElementById("userActive"+userUUID).checked;
	 	var appUrl ='${appUrl}';
		var formData = new FormData();
		formData.append("userUUID", userUUID);
		if(checked == true){
			formData.append("userActive", "Y");
		}else{
			formData.append("userActive", "N");
		}
	     $.ajax({
	    		data: formData,
	    	    contentType: false,
	    	    processData: false,
		    	type: "POST",
		    	 url: appUrl+"/userActiveStatus?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(result) {
		        	getUsersList();
		        },
		    }); 
}
 </script>

 
   
 
 
 