var signup = new Vue({
	el : '#signup',
	data : {
		model: {}
	},
	methods: {
        onSubmit: function() {
        	
        	var formData = JSON.stringify(this.model);
            console.log(formData);
            if(this.model.password==this.model.password2){
            	$("#signupform").submit();
            }
            
            	
        }
    }
})

function gotoSignupPage(){
	window.location.href = '/signup';
}

function signup(){
	
}

function logout(){
	$( "#logoutForm" ).submit();
}



