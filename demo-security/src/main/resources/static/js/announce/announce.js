function addAnnounceSubmit() {
	$("#addAnnounceForm").submit();
}
function returnLastURL(url) {
	document.location.href=url;
}
function showDetailAnnounce() {
	hideAll();
	$("#detail").css("display", "block");
}
function hideAll(){
	$("#add").css("display", "none");
	$("#listPage").css("display", "none");
	$("#detail").css("display", "none");
}

$('#someButton').click(function() {
   
    return false;
});