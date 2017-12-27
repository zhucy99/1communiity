var pager = new Vue({
	el : '#pager',
	data : {
		datas : '',
		currentPage: ''
	},
	computed : {
		// un accesseur (getter) calculé
		pageless10 : function() {
			// `this` pointe sur l'instance vm
			return this.datas.totalPages<=10;
		},
		pagemore10 : function() {
			// `this` pointe sur l'instance vm
			return this.datas.totalPages>10;
		}
	}
});