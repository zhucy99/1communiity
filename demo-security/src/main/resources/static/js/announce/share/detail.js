function find(page){
	if (page == null) {
		page = 1;
	}
	
	getComments(page,$("#id").val());
}

function showDetail() {
	
	$.get("/share/findById", {
		id : $("#id").val()
	}, function(data) {
		detail.datas = data;
		if (data.author.username == $("#username").val()) {
			detail.editable = true;
		}
		$("#description").html(detail.datas.description);
		getComments(1,data.id);
	});
}

var detail = new Vue({
	el : '#detail',
	data : {
		datas : '',
		comments:'',
		editable : false
	}
});

function addComment() {
	var id = $("#id").val();
	$.ajax({
		  type: "GET",
		  url: "/announce/addComment",
		  data: {
			  "announce.id":id,
				"content" : CKEDITOR.instances['comment_content'].getData()
			},
		  success: function(data) {
				console.log(data);
				CKEDITOR.instances['comment_content'].setData("");
				getComments(1,id);
			}
		});
	
}

function getComments(page,announce_id) {
	
	if(announce_id==null){
		announce_id =$("#id").val();
	}
	
	$.ajax({
		  type: "GET",
		  url: "/announce/getComments",
		  data: {
			  "announce.id":announce_id,
			  "page":page
			},
		  success: function(data) {
				detail.comments = data.content;
				pager.datas = data;
				
				pager.currentPage = page;
				console.log(pager.currentPage);
			}
		});
	
}

$("#file").change(function() {
	console.log(this.files);

	for (var i = 0; i < this.files.length; i++) {
		console.log(i);
		let imgFile = this.files[i];
		let fr = new FileReader();
		fr.onload = function() {
			$("img").attr("src", fr.result);
		};

		fr.readAsDataURL(imgFile);
	}

});

showDetail();
