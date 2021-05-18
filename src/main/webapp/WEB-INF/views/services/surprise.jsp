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
	                    <h3>Surprise</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        
	                        <li>
	                            <a href="${Wayuparty_appUrl}/vendorServices?vendorUUID=${vendorUUID}">Services</a>
	                        </li>
                        
	                        <li>Surprise</li>
	                    </ul>
	             </div>
	             
	        <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                   <i class="far fa-hand-point-right bg-light-green3"></i>
                                  <span id="successMsg" style="color: white;"></span> 
            </div>
            
            <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                                   <i class="fas fa-times bg-pink3"></i>
                                  <span id="invalidMsg" style="color: white;"></span> 
            </div>
            
            <div class="card height-auto">
             <div id="surpriseDetailsLoadingDiv"></div>
                   <div class="card-body">
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Surprise Details</h3>
                            </div>
                    	</div>
                    	
                    	 <form class="new-added-form" id="add_surprise_service_form" name="add_surprise_service_form" >
                    	 	<div class="row mt-2">	
                    	 		
                    	 		<div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Surprise Type<span class="text-danger">&nbsp;*</span></label>
                                    <select class="select2" name="surpriseType" id="surpriseType" data-validation="required"  field-name="Surprise Type">
                                    </select>
                                 </div>
                                 
                                 <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Surprise Name<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="surpriseName" id="surpriseName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="Surprise Name">
                                </div>
                                 
                                <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Actual Price<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="actualPrice" id="actualPrice"  onblur="comparePrices();" data-validation="required validate_Space validate_float validate_length length1-10"  field-name="Actual Price">
                                </div>
                                
                                 <div class="col-xl-3 col-lg-6 col-12 form-group" id="priceDiv">
                                    <label>Offer Price<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="offerPrice" id="offerPrice" onblur="comparePrices();" data-validation="required validate_Space validate_float validate_length length1-10"  field-name="Offer Price">
                                </div>
                                
                                <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Allowed<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="allowed" id="allowed"  data-validation="required validate_Space validate_int validate_length length1-3"  field-name="Allowed">
                                </div>
                                
                                <div class="col-xl-3 col-lg-6 col-12 form-group" id="tableDiv">
                                         <label>Start Date<span class="text-danger">&nbsp;*</span></label>
                                         <input type="text" placeholder="dd/mm/yyyy" class="form-control start-date-datepicker"
                                             data-position='bottom right' name="startDate" id="startDate" data-validation="required" field-name="Start Date">
                                         <i class="far fa-calendar-alt"></i>
                               </div>
                               
                               <div class="col-xl-3 col-lg-6 col-12 form-group">
                                         <label>End Date<span class="text-danger">&nbsp;*</span></label>
                                         <input type="text" placeholder="dd/mm/yyyy" class="form-control end-date-datepicker"
                                             data-position='bottom right' name="endDate" id="endDate" data-validation="required" field-name="End Date">
                                         <i class="far fa-calendar-alt"></i>
                               </div> 
                               
                                <div class="col-xl-3 col-lg-6 col-12 form-group" id="serviceImg">
                                    <label>Upload Image<span class="text-danger">&nbsp;*</span></label>
                                    <input type="file" class="form-control-file" id="uploadImageBtn" field-name="Upload Image"  data-validation="required validateImg">
                                    <input type="text" class="form-control" readonly
										id="uploadImageFile" style="display: none;" name="docFile" >
                                 </div>
                                 
				                  <div class="col-xl-3 col-lg-6 col-12 form-group" id="savedServiceImg" style="display: none">
				                      <label>Uploaded Photo</label>
				                      <div class="summaryProfilePic-image" id="uploadImageBtn">
											<div id="savedImage"></div>			
									  </div>
									<div class="clearUpload clearUpload-profile-position" title="Delete Uploaded Photo" onclick="deleteServiceImage();"></div>
				                  </div>
                               
                              
                                <div class="col-lg-6 col-12 form-group">
                                    <label>Description<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="description" id="description" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Description" ></textarea>
                                </div>
                                
                                <div class="col-lg-6 col-12 form-group">
                                    <label>Service Offer<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="serviceOffer" id="serviceOffer" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Service Offer"></textarea>
                                </div>
                                
                                <div class="col-xl-6 col-lg-6 col-12 form-group">
						 		   <label class="text-muted">Start Time / End Time<span class="text-danger">&nbsp;*</span></label>
						 		   <div id="timeScheduleList"></div>
					 		    </div>
                                
                                <div class="col-lg-6 col-12 form-group">
                                    <label>Terms & Condidtions<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="termsAndConditions" id="termsAndConditions" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Terms & Conditions" ></textarea>
                                </div>
                                
					             
                    	   </div><br/>
                    	 
                    	 <div class="row">	
						   <div class="col-12 ui-btn-wrap">
						               <ul>
						               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="compareTimeSlots()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
						               
						                <c:if test="${empty surpriseUUID}">
						               		<li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/surprise?vendorUUID=${vendorUUID}&serviceUUID=${serviceUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
						                </c:if>
						                
						                <c:if test="${!empty surpriseUUID}">
						               		<li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/surprise?vendorUUID=${vendorUUID}&serviceUUID=${serviceUUID}&surpriseUUID=${surpriseUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
						                </c:if>
						                
						               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorServices?vendorUUID=${vendorUUID}'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
						               </ul>
						   </div>
                           
                           </div>
                           
                    	 </form>
                    	
                  </div>
            </div>
            
            </div>
            </div>
             <jsp:include page="../wayupartyMasterFooter.jsp" />
             <script src="/resources/js/bootstrap-timepicker.js"></script> 
            </div>
            
            
            <div id="divStartTimeTd" style="display: none; float: left;">
<div class="input-group bootstrap-timepicker mt-2">
				      <input type="text" class="form-control" readonly="readonly" id="REPLACEROWIDstartTime" name="timeSchedulerInfo[REPLACEROWID].startTime"  data-validation="required validate_space" field-name="Start Time" >
				      <span class="input-group-addon">&nbsp;<i class="fa fa-clock" style="top: 18px;"></i></span>&nbsp;&nbsp;&nbsp;
</div>
</div>


<div id="divEndTimeTd" style="display: none; float: left;">
<div class="input-group bootstrap-timepicker  mt-2">
				      <input type="text" class="form-control" readonly="readonly" id="REPLACEROWIDendTime" name="timeSchedulerInfo[REPLACEROWID].endTime"  data-validation="required validate_space" field-name="End Time" >
				      <span class="input-group-addon">&nbsp;<i class="fa fa-clock" style="top: 18px;"></i></span>&nbsp;&nbsp;&nbsp;
</div>
</div>

<div id="divRemoveTimeTd" style="display: none; float: left;">
<br/>
   <a href="javascript:void(0);">&nbsp;<span class="fa fa-times text-danger" style="top: 120px; right: 60px;" onclick="removeTimeSchedularRow(REPLACEROWID);"></span></a> 
</div> 
 
 
   <script type="text/javascript">
		window.onload = function () {
			getSurpriseTypes();	
			var surpriseUUID = '${surpriseUUID}';
			if(surpriseUUID != null && surpriseUUID != '' && surpriseUUID != 'undefined'){
				getSurpriseDetails(surpriseUUID);
				$('.bootstrap-timepicker > input').timepicker();
			}else{
				getTimeSchedular();
				$('.bootstrap-timepicker > input').timepicker();
			}
			
		 };
    </script>	
    
    
    <script>
	   function getSurpriseDetails(surpriseUUID){
	    	   var appUrl ='${appUrl}';
	    	   $("#surpriseDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	    	   $.ajax({
	    			  type: "GET",
	    			    url: appUrl+"/getVendorMasterServiceDetails?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+surpriseUUID,
	    			     success :function(response) {
	    			    	    
	    			    	 $("select[name=surpriseType]").val(response.object.categoryId); 
	    			    	 document.getElementById('select2-surpriseType-container').innerHTML = response.object.category;
	    			    	 $("#surpriseName").val(response.object.subCategory);
	    			    	 $("#actualPrice").val(response.object.actualPrice);
	    			    	 $("#offerPrice").val(response.object.offerPrice);
	    			    	 $("#allowed").val(response.object.allowed);
	    			    	 $("#startDate").val(response.object.serviceStartDate);
	    			    	 $("#endDate").val(response.object.serviceEndDate);
	    			    	 getSavedEndDateDatePicker();
	    			    	 
	    			    	 if(response.object.serviceImage != ''){
	    			    		 document.getElementById("serviceImg").style.display="none";
	    			    		 document.getElementById("savedServiceImg").style.display="block";
	    			    		 $("#savedImage").html('<img src="'+response.object.serviceImage+'" data-id= "vendorProfileImage" >');
	    			    		 $("#uploadImageBtn").removeAttr('data-validation');
	    			    		 $("#uploadImageBtn").attr('data-validation','validateImg');
	    			    		 $("#uploadImageBtn").attr('data-validation-optional','true');
	    			    	 }else{
	    			    		 document.getElementById("savedServiceImg").style.display="none";
	    			    		 document.getElementById("serviceImg").style.display="block";
	    			    	 }
	    			    	 
	    			    	 
	    			    	 $("textarea[name=description]").val(response.object.description);
	    			    	 $("textarea[name=serviceOffer]").val(response.object.serviceOffer);
	    			    	 $("textarea[name=termsAndConditions]").val(response.object.termsAndConditions);
	    			    	 
	    			    	 
	    			    	 var result = "";
	    			    	 var rowCount = parseInt(response.object.timeSlotList.length);
	    			    	 
	    			    	 result = result +"<table class='timeMultipleAddrow' cellpadding='0' cellspacing='0'>";
			        		 result = result +"<input type='hidden' id='savedTimeRowCount' value='"+response.object.timeSlotList.length+"' />";
	    			    	 for (var i=0; i<response.object.timeSlotList.length; i++){
	    			    		 var counter = i;
				    			 var opt = response.object.timeSlotList[i];
				    			 
				    			 if(i == 0){
					    			 result = result +"<input type='hidden' name='timeAttcahmentMaxRowNumber'  id='timeAttcahmentMaxRowNumber' value='"+rowCount+"' />";
					        	     result = result +"<input type='hidden' id='addedTimeRowCount' value='"+rowCount+"' />";
					        	 	 result = result +"<table class='timeMultipleAddrow'  id='timeAttachmentTable' cellpadding='0' cellspacing='0'>";
				    			 }
				    			 
				    			 result = result +"<tr id='divTimeRow"+counter+"'>";
								 	
				    			 result = result +"<td><div class='input-group bootstrap-timepicker mt-2'><input type='text' readonly='readonly' class='form-control' value='"+opt.startTime+"' data-validation='required' field-name='Start Time' name='timeSchedulerInfo["+counter+"].startTime' id='"+counter+"startTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
		    				 	 result = result +"<td><div class='input-group bootstrap-timepicker mt-2'><input type='text' readonly='readonly' class='form-control' value='"+opt.endTime+"' data-validation='required' field-name='End Time' name='timeSchedulerInfo["+counter+"].endTime' id='"+counter+"endTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
				        	 	 
				        	 	 result = result +"<td>"
				        	 	 
				        	 	 if(i == 0){
				        	 		result = result +"<td><a href='javascript:void(0);'>&nbsp;<span class='fa fa-plus text-success' style='top:50px!important; left: 405px;' id='addDocRow' onclick='addTimeSchedularRow()'></span></a></td>";
				        	 	 }else{
				        	 		 result = result +"<td><a href='javascript:void(0);'>&nbsp;<span class='fa fa-times text-danger'  style='top:50px!important; left: 405px;' onclick='deleteTimeRow("+counter+")'></span></a></td>";
				        	 	 }
							 	
							 	 result = result +"</tr>";
	    			    	 }
	    			    	 
	    			    	 result = result +"</table>"; 
	    			    	 
	    				 	$("#timeScheduleList").empty();
	    				   	$("#timeScheduleList").append(result);
	    			    	$("#surpriseDetailsLoadingDiv").removeAttr("style");
	    			    	
	    			    	$('.bootstrap-timepicker > input').timepicker();
	    			    	
	    					},
	    		});
	    }
	   
	   function deleteServiceImage(){
			document.getElementById("serviceImg").style.display="block";
			document.getElementById("savedServiceImg").style.display="none";
			$("#uploadImageBtn").removeAttr('data-validation-optional');
   		    $("#uploadImageBtn").attr('data-validation','required validateImg');
		}
	   
	   function deleteTimeRow(rowId){
		     document.getElementById("divTimeRow"+rowId).remove();
			 var checkMaxTsRowNum = $("#timeAttcahmentMaxRowNumber").val();
			 $("#timeAttcahmentMaxRowNumber").val(parseInt(checkMaxTsRowNum) - 1)
		}
    </script>
    
    <script>
    function getTimeSchedular(){
    	var result = "";
    	
    	 result = result +"<input type='hidden' name='timeAttcahmentMaxRowNumber'  id='timeAttcahmentMaxRowNumber' value='1' />";
	     result = result +"<input type='hidden' id='addedTimeRowCount' value='1' />";
	 	 result = result +"<table class='timeMultipleAddrow'  id='timeAttachmentTable' cellpadding='0' cellspacing='0'>";
	 	 result = result +"<tr id='divTimeRow0'>";
	 	 result = result +"<td><div class='input-group bootstrap-timepicker'><input type='text' readonly='readonly' class='form-control' data-validation='required' field-name='Start Time' name='timeSchedulerInfo[0].startTime' id='0startTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
	 	 result = result +"<td><div class='input-group bootstrap-timepicker'><input type='text' readonly='readonly' class='form-control' data-validation='required' field-name='End Time' name='timeSchedulerInfo[0].endTime' id='0endTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
	 	 result = result +"<td><a href='javascript:void(0);'>&nbsp;<span class='fa fa-plus text-success' style='top:50px!important; left: 405px;' id='addDocRow' onclick='addTimeSchedularRow()'></span></a></td>";
	 	 result = result +"</tr>";
	 	 result = result +"</table>";
	 	 
	 	$("#timeScheduleList").empty();
	   	$("#timeScheduleList").append(result);
    }
    </script>
    
    <script>
function addTimeSchedularRow(){  
			var checkMaxTsRowNum = $("#timeAttcahmentMaxRowNumber").val();
			var timeRowCount = $("#addedTimeRowCount").val();
			var tbl = document.getElementById("timeAttachmentTable");
			var maxRowNum = parseInt($("input[name = 'timeAttcahmentMaxRowNumber']").val());
			$("input[name = 'timeAttcahmentMaxRowNumber']").val(maxRowNum + 1);
			$("input[id = 'addedTimeRowCount']").val(parseInt(timeRowCount) + parseInt(1));
			var row = tbl.insertRow(maxRowNum);
			row.setAttribute("id", "divTimeRow" + timeRowCount);
			
			var cell0 = row.insertCell(0);
			var divDocTypeTdStr = document.getElementById("divStartTimeTd").innerHTML;
			divDocTypeTdStr = divDocTypeTdStr.replace(/REPLACEROWID/g, (timeRowCount));
			cell0.innerHTML = divDocTypeTdStr;
			
			var cell1 = row.insertCell(1);
			var divDocTypeTdStr = document.getElementById("divEndTimeTd").innerHTML;
			divDocTypeTdStr = divDocTypeTdStr.replace(/REPLACEROWID/g, (timeRowCount));
			cell1.innerHTML = divDocTypeTdStr;
			
			var cell2 = row.insertCell(2);
			var divDocRemoveTdStr = document.getElementById("divRemoveTimeTd").innerHTML;
			divDocRemoveTdStr = divDocRemoveTdStr.replace(/REPLACEROWID/g, (timeRowCount));
			cell2.innerHTML = divDocRemoveTdStr;
			
			$('.bootstrap-timepicker > input').timepicker();
}	

</script>

<script type="text/javascript">
	function removeTimeSchedularRow(rowId) {
		 var addedTimeRowCount = document.getElementById("addedTimeRowCount").value;
		 $("input[id = 'addedTimeRowCount']").val(parseInt(addedTimeRowCount));
		 
		 var documentsRow = document.getElementById("addedTimeRowCount").value;
		 
		 document.getElementById("divTimeRow"+rowId).style.display = 'none';
		 document.getElementById("divTimeRow"+rowId).value= '';
		 var  rId = "divTimeRow"+rowId;
		 var r = document.getElementById("timeAttachmentTable").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
	}
</script>
    
    <script>
    function getSurpriseTypes(){
    	$("#surpriseDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	 var appUrl ='${appUrl}';
    	 var serviceUUID = '${serviceUUID}';
		 $.ajax({
		    	type: "GET",
		        url: appUrl+"/getServiceCategoriesTypes?serviceUUID="+serviceUUID, 
		        success: function(response) {
		        	 var output = "";
		        	output = output + "<option value=''>Select Surprise</option>";
		        	for (var i=0; i<response.data.length; i++)
					{
			        		var opt = response.data[i];
			        		output = output + "<option value='" +opt.categoryId+ "' >"+opt.categoryName+"</option>";
			        		
			        		
					}
		        	$("#surpriseType").empty();
			        $("#surpriseType").append(output); 
			        $('#surpriseType').trigger("chosen:updated");
			        $("#surpriseDetailsLoadingDiv").removeAttr("style");
		        },
		    });
    }
    

    
    
    var dateToday = new Date();
    $('.start-date-datepicker').datepicker({
      language: {
        days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
        daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        today: 'Today',
        clear: 'Clear',
        dateFormat: 'dd/mm/yyyy',
        firstDay: 0,
      },
      minDate: dateToday,
      onSelect : function(selected) {
      	getEndDateDatePicker();
  	 }
    });
    
    function getEndDateDatePicker(){
    	 var endDatepicker = $('#endDate').datepicker().data('datepicker');
    	 endDatepicker.clear();
    	 var startDate = $('#startDate').val();
    	 var dateParts = startDate.split('/');
    	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
        $('.end-date-datepicker').datepicker({
            language: {
              days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
              daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
              daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
              months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
              monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
              today: 'Today',
              clear: 'Clear',
              dateFormat: 'dd/mm/yyyy',
              firstDay: 0,
            },
            minDate: minimumdate,
            onSelect : function(selected) {
              validateVendorService();
          	 }
            
        });
    }
    
    function getSavedEndDateDatePicker(){
      	 var startDate = $('#startDate').val();
      	 var dateParts = startDate.split('/');
      	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
          $('.end-date-datepicker').datepicker({
              language: {
                days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
                daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                today: 'Today',
                clear: 'Clear',
                dateFormat: 'dd/mm/yyyy',
                firstDay: 0,
              },
              minDate: minimumdate,
              onSelect : function(selected) {
            	  validateVendorService();
            	 }
              
          });
      }
    
    function compareTimeSlots() {
    	var checkMaxTsRowNum = $("#timeAttcahmentMaxRowNumber").val();
    	 var startTimeArray = new Array();
   	     var endTimeArray = new Array();
    	for(var i=0; i<=checkMaxTsRowNum; i++){
    		var myEle = document.getElementById("divTimeRow"+i);
    		if(myEle != null){
    			if(document.getElementById(i+"startTime") != null && document.getElementById(i+"endTime") != null){
    			    var strStartTime = document.getElementById(i+"startTime").value;
    		        var strEndTime = document.getElementById(i+"endTime").value;

    		        var startTime = new Date().setHours(GetHours(strStartTime), GetMinutes(strStartTime), 0);
    		        var endTime = new Date(startTime)
    		        endTime = endTime.setHours(GetHours(strEndTime), GetMinutes(strEndTime), 0);
    		        
    		        if((startTimeArray.includes(strStartTime) || endTimeArray.includes(strStartTime)) && i > 0){
    		        	$('#divTimeRow'+i).find('p.jquery_form_error_message').remove(); 
    		        	
    		        	$('input#'+i+'startTime').attr('class','error form-control');
    		    		$('input#'+i+'startTime').attr('record-exist','yes');
    		    		$('input#'+i+'startTime').attr('style','background-color: #f65151');
    		    		$('input#'+i+'startTime').after("<p class='jquery_form_error_message'></p>");
    		    		$('input#'+i+'startTime').attr('record-exist-errorMsg',''); 
    		    		
    		    		$('input#'+i+'endTime').attr('class','error form-control');
    		    		$('input#'+i+'endTime').attr('record-exist','yes');
    		    		$('input#'+i+'endTime').attr('style','background-color: #f65151');
    		    		$('input#'+i+'endTime').after("<p class='jquery_form_error_message'></p>");
    		    		$('input#'+i+'endTime').attr('record-exist-errorMsg',''); 
    					$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
    		        }
    		        else if (startTime < endTime) {
    		            $('input#'+i+'startTime').removeAttr("class record-exist record-exist-errormsg");
    					$('input#'+i+'startTime').attr('class','valid form-control');
    					$('input#'+i+'startTime').attr('style','background-color:#f0f1f3');
    					
    					$('input#'+i+'endTime').removeAttr("class record-exist record-exist-errormsg");
     					$('input#'+i+'endTime').attr('class','valid form-control');
     					$('input#'+i+'endTime').attr('style','background-color:#f0f1f3');
     					
    					$('#divTimeRow'+i).find('p.jquery_form_error_message').remove();
    					$( "#save_button" ).removeAttr("style");
    					$( "#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
    					startTimeArray.push(strStartTime);
    	    		    endTimeArray.push(strEndTime);
    		        }else if(startTime === endTime){
    		        	$('#divTimeRow'+i).find('p.jquery_form_error_message').remove(); 
    		    		$('input#'+i+'endTime').attr('class','error form-control');
    		    		$('input#'+i+'endTime').attr('record-exist','yes');
    		    		$('input#'+i+'endTime').attr('style','background-color: #f65151');
    		    		$('input#'+i+'endTime').after("<p class='jquery_form_error_message'></p>");
    		    		$('input#'+i+'endTime').attr('record-exist-errorMsg',''); 
    					$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
    		        }else{
    		        	$('#divTimeRow'+i).find('p.jquery_form_error_message').remove(); 
    		    		$('input#'+i+'startTime').attr('class','error form-control');
    		    		$('input#'+i+'startTime').attr('record-exist','yes');
    		    		$('input#'+i+'startTime').attr('style','background-color: #f65151');
    		    		$('input#'+i+'startTime').after("<p class='jquery_form_error_message'></p>");
    		    		$('input#'+i+'startTime').attr('record-exist-errorMsg',''); 
    					$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
    		        }
    		        
    			}
    		}
    		
    		
    	}
    	
    	saveSurpriseService();
  
    }
    function GetHours(d) {
        var h = parseInt(d.split(':')[0]);
        if (d.split(':')[1].split(' ')[1] == "PM") {
            h = h + 12;
        }
        return h;
    }
    function GetMinutes(d) {
        return parseInt(d.split(':')[1].split(' ')[0]);
    }
    
    </script>
    
    <script>
    function comparePrices(){
    	var actualPrice = $('#actualPrice').val();
    	var offerPrice = $('#offerPrice').val();
    	
    	if(actualPrice && offerPrice){
    		if((parseFloat(offerPrice) == parseFloat(actualPrice)) || 
    				(parseFloat(offerPrice) < parseFloat(actualPrice))){
    			
    			$("#save_button").removeAttr("disabled");
	    		$('input#offerPrice').removeAttr( "class record-exist record-exist-errormsg");
				$('#priceDiv').find('p.jquery_form_error_message').remove();
				$('input#offerPrice').attr('class','valid form-control');
				$( "#save_button" ).removeAttr("style");
				$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
				$( "#save_button" ).click(function() {
				});
    		}else{
    			$('#priceDiv').find('p.jquery_form_error_message').remove(); 
	    		$('input#offerPrice').attr('class','error form-control');
	    		$("input#offerPrice").after("<p class='jquery_form_error_message'>Offer Price should less than or equal to Actual Price</p>");
				$('input#offerPrice').attr('record-exist','yes');
				$('input#offerPrice').attr('record-exist-errorMsg',' Offer Price should less than or equal to Actual Price'); 
				$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
    		}
    	}
    	
    	
    }
    </script>
    
    
    <script>
    function validateVendorService(){
    	 var startDate = $('#startDate').val();
    	 var endDate = $('#endDate').val();
    	 var serviceId = $('#surpriseType').val();
    	 
    	 if(startDate != null  && startDate != '' && startDate != 'undefined' && 
    			 endDate != null  && endDate != '' && endDate != 'undefined'){
    		 var vendorUUID = '${vendorUUID}';
    		 var appUrl = '${appUrl}';
    		 var surpriseUUID = '${surpriseUUID}';
    		 var formData = new FormData();
    		 formData.append("vendorUUID", vendorUUID);
    		 formData.append("startDate", startDate);
    		 formData.append("endDate", endDate);
    		 formData.append("serviceId", serviceId);
    		 formData.append("masterServiceUUID", surpriseUUID);
    		 
    		     $.ajax({
    		    		data: formData,
    		    	    contentType: false,
    		    	    processData: false,
    			    	type: "POST",
    			    	url: appUrl+"/validateVendorService?${_csrf.parameterName}=${_csrf.token}", 
    			        success: function(result) {
    			        	
    			        	
    			        	if(result && result.response == "VALID_DATA"){
    			        		
    			        		$("#save_button").removeAttr("disabled");
    				    		$('input#startDate').removeAttr( "class record-exist record-exist-errormsg");
    							$('#tableDiv').find('p.jquery_form_error_message').remove();
    							$('input#startDate').attr('class','valid form-control');
    							$( "#save_button" ).removeAttr("style");
    							$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
    							$( "#save_button" ).click(function() {
    							});
    							
    			        	}else if (result && result.response === "AWKWARD") {
    			        		location.href = "/errorPage";
    			        	}else{
    			        		$('#tableDiv').find('p.jquery_form_error_message').remove(); 
    				    		$('input#startDate').attr('class','error form-control');
    				    		$("input#startDate").after("<p class='jquery_form_error_message'>Service is already exists between these dates</p>");
    							$('input#startDate').attr('record-exist','yes');
    							$('input#startDate').attr('record-exist-errorMsg',' Service is already exists between these dates'); 
    							$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
    			        	}
    	 
    			        },
    			    }); 
    	 }
    	 
    }
    </script>
    
 <script>
 function saveSurpriseService(){
	   if ($('#add_surprise_service_form').validate(false, validationSettings)){
		   $("#surpriseDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var serviceUUID = "${serviceUUID}";
           var surpriseUUID = "${surpriseUUID}";
           var formData = getWayupayFormData("add_surprise_service_form");
           formData.append("vendorUUID",vendorUUID);
           formData.append("serviceUUID",serviceUUID);
           formData.append("surpriseUUID",surpriseUUID);
             $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveSurpriseService?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#surpriseDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#surpriseDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new surprise service.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/vendorServices?vendorUUID="+vendorUUID);
			                       }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_surprise_service_form', function() {
		$("#add_surprise_service_form").showHelpOnFocus();
		$("#add_surprise_service_form").validateOnBlur(false, validationSettings);  
   });
 </script>
 
 
  <script>
   
   document.getElementById("uploadImageBtn").onchange = function () {
       $('#uploadImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadImageBtn").change(function () {
    	   var vendorId = '${vendorId}';
           fileUpload("uploadImageBtn", "vendor_services_images", "add_surprise_service_form",vendorId,0);
           $(".clearUpload").show();
       });
   });
 </script>
 
 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>

<style>
.clearUpload {
top: -90px !important;
margin-left: 120px !important;
}
</style>
            