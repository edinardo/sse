var EventSource = require('eventsource')
var sseServer = 'https://casino.dge-cert.fndl.dev/event/sse';
for (i = 0; i < 1000; i++) {
	let sse = new EventSource(sseServer);
	sse.onmessage = function(event) {
		console.log(event.data);
	}
}