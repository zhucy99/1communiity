function logout(){
	$( "#logoutForm" ).submit();
}

var navbar = new Vue({
	el : '#navbar',
	data : {
		username : 'User list'
	}
})