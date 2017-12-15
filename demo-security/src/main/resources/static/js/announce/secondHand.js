function addSecondHandSubmit() {
	$("#addSecondHandForm").submit();
}
function showAddSecondHand() {
	hideAll();
	$("#add").css("display", "block");
}
function showListSecondHand() {
	hideAll();
	$("#listPage").css("display", "block");
}
function showDetailSecondHand() {
	hideAll();
	$("#detail").css("display", "block");
}
function hideAll(){
	$("#add").css("display", "none");
	$("#listPage").css("display", "none");
	$("#detail").css("display", "none");
}

function find(page) {
	if (page == null) {
		page = 1;
	}
	$.get("/secondHand/search", {
		title : announcesList.search,
		page : page
	}, function(data) {
		announcesList.announces = data.content;
		pager.datas = data;
		pager.currentPage = page;
	});
}

function showDetail(id) {
	$.get("/secondHand/findById", {
		id : id
	}, function(data) {
		detail.datas = data;
		$("#description").html(detail.datas.description);
		showDetailSecondHand();
	});
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
		datas : ''
	}
});

find();
