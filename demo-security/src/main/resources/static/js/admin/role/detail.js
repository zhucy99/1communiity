function showUserDetail(id){
	$.get("/user/findById", {
		id : id
	}, function(data) {
		$("#detail").css("display","block");
		$("#list").css("display","none");
		detail.datas = data;
	});
}

function back(){
	$("#list").css("display","block");
	$("#detail").css("display","none");
}

var detail = new Vue({
	el : '#detail',
	data : {
		datas : ''
	}
});
