$(document).ready(function() {
	$("#submitButton").click(function(event){
		var success = true;
		var message = "";
		var trimmedName = jQuery.trim($('#computerName').val());
		if (trimmedName.length == 0)  {
  			success = false;
  			message += "Name is mandatory ";
  		}
  		if (trimmedName.length > 255){
  			success = false;
  			message += "Computer name must be less than 255 characters ";
  		}
  		var trimmedCompany = jQuery.trim($('#companyId option:selected').text());
  		if (trimmedCompany.length > 255){
  			success = false;
  			message += "Company name must be less than 255 characters ";
  		}
  		var trimmedIntroduced = jQuery.trim($('#introduced').val());
  		var trimmedDiscontinued = jQuery.trim($('#discontinued').val());
  		var pattern = new RegExp("/^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
  		if (trimmedIntroduced.length > 0 && !pattern.test(trimmedIntroduced)){
  			success = false;
  			message += "Invalid introduced date ";
  		}
  		if (trimmedDiscontinued.length > 0 && !pattern.test(trimmedDiscontinued)){
  			success = false;
  			message += "Invalid discontinued date ";
  		}
  		if (success === false){
  			$( ".error" ).append( "<div class='alert alert-danger' role='alert'>" + message + "</div>" );
  	  			event.preventDefault();
  		}
});
});