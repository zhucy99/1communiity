function showDetail() {
	console.log($("#id").val());
	$.get("/secondHand/findById", {
		id : $("#id").val()
	}, function(data) {
		detail.datas = data;
		if(data.author.username==$("#username").val()){
			detail.editable=true;
		}
		$("#description").html(detail.datas.description);
	});
}

function showContact() {
	$("#contact").css("display","block");
}

var detail = new Vue({
	el : '#detail',
	data : {
		datas : '',
		editable: false
	}
});

showDetail();
