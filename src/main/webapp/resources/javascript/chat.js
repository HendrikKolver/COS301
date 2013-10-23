var wsChat;

//-----------------------------------------------------------------------------------------------
//Send a request via websockets to join the chat room
function joinChatRoom()
{
    wsChat.send("join,"+$("#ProjectIDHolder").val());
}

$(document).ready(function()
{
        //-----------------------------------------------------------------------------------------------
        //Initial setup of components and variables
        $("#chatName").val($("[id='userLogin:userUsername']").val());
        $("#textChatSection").html( $("[id='textChatHolder:htmlValue']").val());
    
        //-----------------------------------------------------------------------------------------------
        //initiate a connetion with the websocket server, The timeout is used to ensure the server is up
        setTimeout(function () {
            wsChat = new WebSocket('ws://' +window.location.hostname+':1237'+ '/chatSocket');
            wsChat.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
            
            setTimeout(function () {
                joinChatRoom();  
            }, 500);
        }, 500);

        //-----------------------------------------------------------------------------------------------
        //Function used to recieve websocket messages sent by the server
        function showMessage(text) 
        {
            loadLog(text);
        }
        
        //-----------------------------------------------------------------------------------------------
        //Function used to send a message to the chat server
        $("#textChatSend").click(function()
        {
                
                joinChatRoom();
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
                wsChat.send("message,"+str+","+$("#ProjectIDHolder").val());
                tmpVal = $("#textChatSection").html()+str;
                $("#textChatSection").html(tmpVal); //Output to html
                
	});
        
        //-----------------------------------------------------------------------------------------------
        //Function used to interperit the message recieved from the chat server
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
	
});
