<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="ui-modal-box">
  <div class="modal-box">
       <div class="modal right-slide-modal fade sidebar-align" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                        <div id="guestDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Guest Details</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="modal-body service-info">
                                            <ul class="row"> 
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Guest Name  : </strong><span id="guestName"></span></li>  
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Email  : </strong><span id="guestEmail"></span></li>
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Date Of Birth  : </strong><span id="guestDOB"></span></li>
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Gender  : </strong><span id="guestGender"></span></li>
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Guest Code  : </strong><span id="guestCode"></span></li>
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Guest Date  : </strong><span id="guestDate"></span></li>
                                               <li class="col-xl-8 col-lg-6 col-12"><span id="qrCode" style="height: 350px; width: 350px;"></span></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fas fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       </div>
       
       <script>
       function getGuestDetails(guestUUID){
    	   
    	   var appUrl ='${appUrl}';
    	   $("#guestDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/getGuestUserDetails?${_csrf.parameterName}=${_csrf.token}&guestUUID="+guestUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#guestDetailsLoaderDiv").removeAttr("style");
    			    	 
    			    	 $("#qrCode").html('<img src="'+response.object.qrCode+'">');
    			    	 $("#guestName").html(response.object.guestUserName);
    			    	 $("#guestEmail").html(response.object.email);
    			    	 $("#guestDOB").html(response.object.dob);
    			    	 $("#guestGender").html(response.object.gender);
    			    	 $("#guestCode").html(response.object.guestCode);
    			    	 if(response.object.endDate != ''){
    			    		 $("#guestDate").html(response.object.startDate+" to "+response.object.endDate);
    			    	 }else{
    			    		 $("#guestDate").html(response.object.startDate);
    			    	 }
    			    	 
    	
    			    	
    			    	 
    					},
    		});
       }
       </script>
       