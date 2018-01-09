function find(page) {
	if(page==null){
		page=1;
	}
	$.get("/user/search", {
		username : list.search,
		page:page
	}, function(data) {
		list.datas = data;
		pager.datas = data;
		pager.currentPage = page;
		$("html, body").animate({ scrollTop: 0 }, "slow");
	});
}

function userManageFind(){
	find();
}

function deleteUser(id) {
	$.get("/user/delete", {
		id : id,
		page : pager.currentPage
	}, function(data) {
		list.datas = data;
		pager.datas = data;
	});
}

function add() {

	$.get("/user/all", function(data) {
		list.datas = data;

	});
}


var list = new Vue({
	el : '#list_',
	data : {
		message : 'User list',
		datas : '',
		search: ''
	}
})

find();