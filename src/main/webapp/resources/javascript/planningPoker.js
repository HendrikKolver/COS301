$(document).ready(function()
{
    
       var ws = new WebSocket('ws://' +window.location.hostname+':1236'+ '/planningPoker');
        
        ws.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
      
     //ws.send('remove,'+document.getElementById("stickyHiddenID").value); 
     
    function showMessage(text) 
    {
        alert(text);
        //ws.send("next,next");
    }
    
});
