function addAnnounceSubmit() {
	$("#addAnnounceForm").submit();
}
function showAddAnnounce() {
	hideAll();
	$("#add").css("display", "block");
}
function showListAnnounce() {
	hideAll();
	$("#listPage").css("display", "block");
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