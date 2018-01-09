function find(page) {
	console.log(1);
	if(page==null){
		page=1;
	}
	$.get("/role/search", {
		username : list.search,
		page:page
	}, function(data) {
		console.log(data);
		list.datas = data;
		pager.datas = data;
		pager.currentPage = page;
		//滑到页头
		$("html, body").animate({ scrollTop: 0 }, "slow");
	});
}

var list = new Vue({
	el : '#list_',
	data : {
		datas : '',
		search: ''
	}
})

find();