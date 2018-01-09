function showAddAnnounce() {
	 window.location.href = '/secondHand?content=add';
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

var announcesList = new Vue({
	el : '#list',
	data : {
		announces : '',
		search : '',
		test : 12
	}
});

find();
