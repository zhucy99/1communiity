var signup = new Vue({
	el : '#signup',
	data : {
		model: {},
		usernameOk:-1, //0 ko，1 ok, -1 unknow
		mailOk:-1
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

function isUsernameNotUsed(){
	if(signup.model.username!=null&&signup.model.username!=""){
		$.get("/signup/isUsernameNotUsed",
				{username:signup.model.username},
				function(data){
					if(data){
						signup.usernameOk =  1;
					}else{
						signup.usernameOk =  0;
					}
			
		});
	}else{
		signup.usernameOk = -1;
	}
	
}

function isMailNotUsed(){
	if(signup.model.mail!=null&&signup.model.mail!=""){
		$.get("/signup/isUsernameNotUsed",
				{mail:signup.model.mail},
				function(data){
					if(data){
						signup.mailOk =  1;
					}else{
						signup.mailOk =  0;
					}
			
		});
	}else{
		signup.mailOk = -1;
	}
}

function gotoSignupPage(){
	window.location.href = '/signup';
}

function signup(){
	
}





