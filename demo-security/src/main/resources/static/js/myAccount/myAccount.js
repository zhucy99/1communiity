function changeTab(id){
	$('#tabs').children().children().removeClass( "active" );
	$('div[name=content]').css("display", "none");
	
	$("#"+id).css("display", "block");
	$("#"+id+"_tab").addClass( "active" );
	
	//通过函数名来调用函数
	var strFun = id+"Find"; //Name of the function to be called
	var fn = window[strFun];
	fn();

}

function find(page) {
	if(page==null){
		page=1;
	}
	$.get("/announce/search", {
		title : am.search,
		page:page
	}, function(data) {
		am.datas = data;
		pager.datas = data;
		pager.currentPage = page;
		$("html, body").animate({ scrollTop: 0 }, "slow");
	});
}

function userManageFind(){
	find();
}

function deleteAnnounce(id) {
	$.get("/announce/delete", {
		id : id,
		page : pager.currentPage
	}, function(data) {
		am.datas = data;
		pager.datas = data;
	});
}

var am = new Vue({
	el : '#announceManage',
	data : {
		datas : '',
		search: ''
	},
	filters: {
        formatDate(time) {
        }
    }
})

find();

