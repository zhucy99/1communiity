function find(page) {
	if(page==null){
		page=1;
	}
	$.get("/user/search", {
		username : um.search,
		page:page
	}, function(data) {
		um.datas = data;
		pager.datas = data;
		pager.currentPage = page;
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
		um.datas = data;
		pager.datas = data;
	});
}

function add() {

	$.get("/user/all", function(data) {
		um.datas = data;

	});
}


var um = new Vue({
	el : '#userManage',
	data : {
		message : 'User list',
		datas : '',
		search: ''
	}
})

find();