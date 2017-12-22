function find(page) {
	if (page == null) {
		page = 1;
	}
	$.get("/share/search", {
		title : announcesList.search,
		page : page
	}, function(data) {
		announcesList.announces = data.content;
		pager.datas = data;
		pager.currentPage = page;
	});
}

function showDetail(id) {
	$.get("/share/findById", {
		id : id
	}, function(data) {
		detail.datas = data;
		if (data.author.username == $("#username").val()) {
			detail.editable = true;
		}
		$("#description").html(detail.datas.description);
		showDetailAnnounce();
		getComments();
	});
}

function showContact() {
	$("#contact").css("display", "block");
}

var announcesList = new Vue({
	el : '#list',
	data : {
		announces : '',
		search : '',
		test : 12
	}
});

var detail = new Vue({
	el : '#detail',
	data : {
		datas : '',
		comments:'',
		editable : false
	}
});

function addComment() {
	
	$.ajax({
		  type: "GET",
		  url: "/announce/addComment",
		  data: {
			  "announce.id":$("#comment_announce_id").val(),
				"content" : CKEDITOR.instances['comment_content'].getData()
			},
		  success: function(data) {
				console.log(data);
				CKEDITOR.instances['comment_content'].setData("");
			}
		});
	
}

function getComments() {
	
	$.ajax({
		  type: "GET",
		  url: "/announce/getComments",
		  data: {
			  "announce.id":$("#comment_announce_id").val(),
			  "page":1
			},
		  success: function(data) {
				console.log(data);
				detail.comments = data.content;
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
