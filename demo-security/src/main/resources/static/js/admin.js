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

