function getCaptcha(){
	console.log(1);
	$.get("/kaptcha/getKaptchaImg",function(data){
		console.log(data);
		$("#captcha").attr("src","data:image/jpg;base64, "+data);
	});
}

getCaptcha();