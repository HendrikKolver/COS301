$(document).ready(function()
{
    var wsChat;
    setTimeout(function () {
            wsChat = new WebSocket('ws://' +window.location.hostname+':1237'+ '/chatSocket');
            wsChat.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
        }, 500);
        
      
     //ws.send('remove,'+document.getElementById("stickyHiddenID").value); 
     
        function showMessage(text) 
        {
           // alert(text);
            loadLog(text);
            //ws.send("next,next");
        }
    
        $("#textChatSection").html( $("[id='textChatHolder:htmlValue']").val());
        $("#textChatSend").click(function()
        {	
		var clientmsg = $("#clientMsg").val();
                var user = $("#chatName").val();
                $("#clientMsg").val("");
                
                var currentTime = new Date();
                var hours = currentTime.getHours();
                var minutes = currentTime.getMinutes();
                if (minutes < 10)
                {
                    minutes = "0" + minutes;
                }
                var localTime = hours + ":" + minutes + " ";
                var str = "<div class='chatMsg'><table><tr><td class ='chatMsgUser'>"+user+"</td><td class ='chatMsgContent'>"+clientmsg+"</td><td class ='chatMsgTime'>"+localTime+"</td></tr></table></div>";
                //$("[id='textChatHolder:htmlValue']").val(str); //Set value
                //$("[id='textChatHolder:htmlChatClick']").click();
                wsChat.send(str);
                tmpVal = $("#textChatSection").html()+str;
                $("#textChatSection").html(tmpVal); //Output to html
                
	});
        
        function loadLog(str){
           // alert(str);
               
             //$("[id='textChatHolderGetter:getHtmlChatClick']").click();
               var oldscrollHeight = $("#textChatSection").attr("scrollHeight") - 20;
              tmpVal = $("#textChatSection").html()+str;
               $("#textChatSection").html(tmpVal);
               
              
               
               var newscrollHeight = $("#textChatSection").attr("scrollHeight") - 20;
               if(newscrollHeight > oldscrollHeight)
               {
               	$("#textChatSection").animate({ scrollTop: newscrollHeight }, 'normal'); //Autoscroll to bottom of div
               }				
	}
	//setInterval (loadLog, 4000);	//Reload file every 10 seconds
});
