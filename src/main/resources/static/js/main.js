var app = new Vue({
	el: '#app',
    data: {
		message: null,
		sse: null
    },
    methods: {
    	connect: function () {
			let thi$ = this;
    		thi$.sse = new EventSource('/event/sse');
    	    thi$.sse.onmessage = function(event) {
    	    	thi$.message = event.data;
    	    };
        },
        close: function () {
			let thi$ = this;
        	thi$.sse.close();
        }
    }
});