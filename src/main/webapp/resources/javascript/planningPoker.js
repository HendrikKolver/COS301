var wsPoker = new WebSocket('ws://' +window.location.hostname+':1236'+ '/planningPoker');

var person = "";
function joinRoom()
{
$(document).ready(function()
{
    if(person == "")
        person=prompt("Please enter your name","name");

        wsPoker.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
        wsPoker.send('join,join');
      
     //wsPoker.send('remove,'+document.getElementById("stickyHiddenID").value);
     $('body').on('click', '#nextTask', function() {
        
            wsPoker.send("next,next");
            $(".side-2").attr("class",'flip side-2');
            $(".side-1").attr("class",'flip side-1');
            wsPoker.send("flip,flipBack");
    });

     
    function showMessage(text) 
    {
        var chars = text.split(',');
        //alert(text);
        
        if(chars[0] == "choice")
        {
            if(chars[2]!= person)
            {
                if ($('#chosenCard'+chars[2]).length != 0){
                    $('#chosenCard'+chars[2]).html("<div class = 'planningPokerInner'>"+chars[1]+"</div>");
                    //calculateAllScores();
                }else
                  addCard(chars[1],chars[2]);       
            } else
            {
               if ($('#chosenCard'+chars[2]).length != 0){
                    $('#chosenCard'+chars[2]).html("<div class = 'myChosenCardInner'>"+chars[1]+"</div>");
                    //calculateAllScores();
                }else
                {
                    $("#cardTableR1").append("<td><div class = 'moveUp cardContainer'><div class = 'myChosenCard' id='chosenCard"+person+"'><div class = 'myChosenCardInner'>"+chars[1]+"</div></div><span class = 'cardUser'>"+person+"</span></div></td>");
                    
                }
                  
            }
        }else if(chars[0] == "taskInfo")
        {
            //$("#taskInfo").html(chars[1]+"</br>"+chars[2]);
            $("#taskToPlan").html(chars[1]);
            $("#taskToPlanDescription").html(chars[2]);
        }
        else if(chars[0] == "done")
        {
            $("#taskInfo").html("No more tasks");
        }
        else if(chars[0] == "flip")
        {
            if(chars[1] == "flip")
            {
                $(".side-2").attr("class",'flip side-2 flip-side-1');
                $(".side-1").attr("class",'flip side-1 flip-side-2');
                calculateAllScores();
            }else
            {
                $(".side-2").attr("class",'flip side-2');
                $(".side-1").attr("class",'flip side-1');
            }
        }
        
    }
    
    $(".planningPokerCard").hover(function()
    {
            addTitles(this);
    });

    $(document.body).on("hover", ".myChosenCard", function(){
            addTitles($(".myChosenCard"));
    });

    function addTitles(elmnt)
    {
            if ($(elmnt).children(":first").html() == "?")
                    $(elmnt).attr("title", "Unknown Points");
            else if ($(elmnt).children(":first").html() == "1" || $(elmnt).children(":first").html() == "1/2")
                    $(elmnt).attr("title",$(elmnt).children(":first").html()+" Point");
            else
                    $(elmnt).attr("title",$(elmnt).children(":first").html()+" Points");
    }


    $(document.body).on("click", ".planningPokerCard",function()
    {
            if ($("#chosenCard"+person).length == 0)
                    $("#cardTableR1").append("<td><div class = 'moveUp cardContainer'><div class = 'myChosenCard' id='chosenCard"+person+"'><div class = 'myChosenCardInner'>1</div></div><span class = 'cardUser'>"+person+"</span></div></td>");
            $("#chosenCard"+person).children(":first").html($(this).children(":first").html());
            
            //send to other clients
            wsPoker.send("choice,"+$(this).children(":first").html()+","+person);

            $("#cardHolder").find(".myChosenCard").children(":first").removeClass("myChosenCardInner").addClass("planningPokerCardInner");
            $("#cardHolder").find(".myChosenCard").removeClass("myChosenCard").addClass("planningPokerCard");

            $(this).removeClass("planningPokerCard").addClass("myChosenCard");
            $(this).children(":first").removeClass("planningPokerCardInner").addClass("myChosenCardInner");	
    });


    $(document.body).on("click", "#flipCards",function()
    {
            $(".side-2").attr("class",'flip side-2 flip-side-1');
            $(".side-1").attr("class",'flip side-1 flip-side-2');
            calculateAllScores();
            wsPoker.send("flip,flip");
    });

    $(document.body).on("click", "#flipBack",function()
    {
            $(".side-2").attr("class",'flip side-2');
            $(".side-1").attr("class",'flip side-1');
            wsPoker.send("flip,flipBack");
    });

    function addCard(number,name)
    {
            //alert(number+" "+name); //Gets called too many times
            //$("#tdCard"+name).remove();
            $("#cardTableR1").append("<td id ='tdCard"+name+"'><div class = 'otherCards' style ='text-align: center'><div class='side-1 flip flip-side1'><div class = 'unrevealedCard'><div class = 'unrevealedCardInner'>X</div></div></div><div class='side-2 flip flip-side2'><div class = 'planningPokerCard' id='chosenCard"+name+"'><div class = 'planningPokerInner'>"+number+"</div></div></div><span style ='position:relative; top:-150px; text-align: center; margin-bottom: -150px;' class = 'cardUser'>"+name+"</span></div></td>");
    }


    function calculateAllScores()
    {
        var compute = 0;
            var counter = 0;
            if ($("#cardTableR1").find(".myChosenCardInner").length > 0){
                    counter++;
                    compute += parseInt($("#cardTableR1").find(".myChosenCardInner").html());
            }$(".otherCards").each(function()
            {	
                    counter++;
                    compute += parseInt($(this).find(".planningPokerInner").html());
            });
            $("#outputAAverages").html(Math.round((compute/counter)*100)/100);
    }
    
});
}
