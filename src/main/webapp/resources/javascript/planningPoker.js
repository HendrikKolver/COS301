$(document).ready(function()
{
    var person=prompt("Please enter your name","name");
    
       var ws = new WebSocket('ws://' +window.location.hostname+':1236'+ '/planningPoker');
        
        ws.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
      
     //ws.send('remove,'+document.getElementById("stickyHiddenID").value);
     $('body').on('click', '#nextTask', function() {
            ws.send("next,next");
    });
//     $("#nextTask").click(function(){
//         ws.send("next,next");
//     });
     
    function showMessage(text) 
    {
        var chars = text.split(',');
        //alert(text);
        
        if(chars[0] == "choice")
        {
            if(chars[2]!= person)
            {
                if ($('#myCard'+chars[2]).length != 0)
                    $('#myCard'+chars[2]).html("<div class = 'myChosenCardInner'>"+chars[1]+"</div>"+chars[2]);
                else
                        $("#selectedCardHolder").append("<div class = 'myChosenCard' id = 'myCard"+chars[2]+"'><div class = 'myChosenCardInner'>"+chars[1]+"</div>"+chars[2]+"</div>"); 
            }  
        }else if(chars[0] == "taskInfo")
        {
            $("#taskInfo").html(chars[1]+"</br>"+chars[2]);
        }
        else if(chars[0] == "done")
        {
            $("#taskInfo").html("No more tasks");
        }
        
    }
    
    //card selection
    $(".planningPokerCard, .myChosenCard").hover(function()
    {
            if ($(this).children(":first").html() == "1" || $(this).children(":first").html() == "1/2")
                    $(this).attr("title",$(this).children(":first").html()+" Point");
            else
                    $(this).attr("title",$(this).children(":first").html()+" Points");
    });

    $('body').on('click', '.planningPokerCard', function()
    {
        
            if ($('#myCard').length == 0)
                    $("#selectedCardHolder").append("<div class = 'myChosenCard' id = 'myCard'><div class = 'myChosenCardInner'>1</div>");
            $('#myCard').children(":first").html($(this).children(":first").html());
            //alert($(this).children(":first").html());
            
            //send data to other clients
            ws.send("choice,"+$(this).children(":first").html()+","+person);
          
            $("#cardHolder").find(".myChosenCard").children(":first").removeClass("myChosenCardInner").addClass("planningPokerCardInner");
            $("#cardHolder").find(".myChosenCard").removeClass("myChosenCard").addClass("planningPokerCard");

            $(this).removeClass("planningPokerCard").addClass("myChosenCard");
            $(this).children(":first").removeClass("planningPokerCardInner").addClass("myChosenCardInner");	
    });
    
});
