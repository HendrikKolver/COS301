//-----------------------------------------------------------------------------------------------
//Initial setup
var wsPoker; 
var person = "";
var isPlanningPokerListeners = false;
var isChanged = false;


function joinRoom()
{ 
    //-----------------------------------------------------------------------------------------------
    //Function used to check if user has a current active project and disables the page if they don't
    if($("#ProjectIDHolder").val() == "")
    {
        $("#planningPokerContent").hide();
        alert("please select a project first");
    }
    
    
    isChanged = false;
        
        //-----------------------------------------------------------------------------------------------
        //Establish a connection with the websocket server if a connection does not already exist
        if(!isPlanningPokerListeners)
        {
            planningPokerlisteners();
            wsPoker = new WebSocket('ws://' +window.location.hostname+':1236'+ '/planningPoker');
        }
        
        //-----------------------------------------------------------------------------------------------
        //Get the username of the current user to be used as identifier in the planning poker process
        if(person == "")
        {
            person=$("[id='userLogin:userUsername']").val(); 
        }
        
        //-----------------------------------------------------------------------------------------------
        //declare which function should be used to recieve websocket messages
        wsPoker.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
        
        //-----------------------------------------------------------------------------------------------
        //Request to join the planning poker session for the current project, the timeout is to ensure the server is up and running
        setTimeout(function () {

             wsPoker.send('join,join,'+$("#ProjectIDHolder").val());
             setTimeout(function () {
                $("#planningPokerCardSelector").val($("#planningPokerCardSelector").children(":first-child").val()).change();
                wsPoker.send("changeTask,"+$("#planningPokerCardSelector").val+","+$("#ProjectIDHolder").val());
            }, 100);
        }, 500);
        
        //-----------------------------------------------------------------------------------------------
        //Adding all the appropriate listeners to the various html elements
        function planningPokerlisteners()
        {
            isPlanningPokerListeners = true;
           
            //-----------------------------------------------------------------------------------------------
            //Commit selected value of points to specific task and add task to sprint
            $('body').on('click', '#sumbitPlanningAmount', function() 
            {
                    var currentTask = $("#planningPokerCardSelector").val();
                    if(currentTask == null)
                    {
                        alert("There are no tasks left to plan");
                        return;  
                    }
                    var r=confirm("Add '"+currentTask+"' to Sprint?")
                    if (r==false)
                        return;
                    var finalPoints = $("#selectFinalPlanningPoker").val();
                    if ($("#previouslyPlannedTaskList").find("#planningFindThis").length > 0)   //If no tasks yet
                        $("#previouslyPlannedTaskList").html("");   //Erase the 'No tasks' message
                        $("#previouslyPlannedTaskList").append('<div class="plannedAlreadyInner">'+
                                                                    '<table style="width:100%">'+
                                                                        '<tr style="width:100%">'+
                                                                            '<td style="width:85%">'+currentTask+'</td>'+
                                                                            '<td style="border-left:1px solid #ccc; text-align: center; width:15%"><b>'+finalPoints+'pts</b></td>'+
                                                                        '</tr>'+
                                                                    '</table>'+
                                                            ' </div>');
                        wsPoker.send("finishTask,"+currentTask+","+finalPoints+","+$("#ProjectIDHolder").val());
                        wsPoker.send("removeAllCards,removeAllCards,"+$("#ProjectIDHolder").val());


                        $("#planningPokerCardSelector").children().each(function()
                        {
                            if ($(this).val() == currentTask)
                            {    
                                //TODO: add actual values to DB & reset cards
                                $("#selectFinalPlanningPoker").val("0");
                                $(this).remove();
                                return;
                            }   
                        });
                        removeAllCards();

                        $("#planningPokerCardSelector").val($("#planningPokerCardSelector").children(":first-child").val()).change();
                        wsPoker.send("changeTask,"+$("#planningPokerCardSelector").val+","+$("#ProjectIDHolder").val());
                });

                //-----------------------------------------------------------------------------------------------
                //Make a selection when you click on a specific card
                $(document.body).on("click", ".planningPokerCard",function()
                {
                        if ($("#chosenCard"+person).length == 0)
                                $("#cardTableR1").append("<td><div class = 'moveUp cardContainer'><div class = 'myChosenCard' id='chosenCard"+person+"'><div class = 'myChosenCardInner'>1</div></div><span class = 'cardUser'>"+person+"</span></div></td>");
                        $("#chosenCard"+person).children(":first").html($(this).children(":first").html());

                        //send to other clients
                        wsPoker.send("choice,"+$(this).children(":first").html()+","+person+","+$("#ProjectIDHolder").val());

                        $("#cardHolder").find(".myChosenCard").children(":first").removeClass("myChosenCardInner").addClass("planningPokerCardInner");
                        $("#cardHolder").find(".myChosenCard").removeClass("myChosenCard").addClass("planningPokerCard");

                        $(this).removeClass("planningPokerCard").addClass("myChosenCard");
                        $(this).children(":first").removeClass("planningPokerCardInner").addClass("myChosenCardInner");	
                });

                //-----------------------------------------------------------------------------------------------
                //Send the mesage to flip the cards over in all conected users sessions
                $(document.body).on("click", "#flipCards",function()
                {
                        $(".side-2").attr("class",'flip side-2 flip-side-1');
                        $(".side-1").attr("class",'flip side-1 flip-side-2');
                        calculateAllScores();
                        wsPoker.send("flip,flip,"+$("#ProjectIDHolder").val());
                });

                //-----------------------------------------------------------------------------------------------
                //Send the mesage to flip the cards back in all conected users sessions
                $(document.body).on("click", "#flipBack",function()
                {
                        $(".side-2").attr("class",'flip side-2');
                        $(".side-1").attr("class",'flip side-1');
                        wsPoker.send("flip,flipBack,"+$("#ProjectIDHolder").val());
                });
                    
        }
        
        //-----------------------------------------------------------------------------------------------
        //Populates drop-down list dynamically based on the cards on screen
        $(".planningPokerCard").each(function()
        {
            if ($(this).children(":first").html() != '?') //Question mark would break DB
                $("#selectFinalPlanningPoker").append("<option>"+$(this).children(":first").html()+"</option>");
        });
        
        //-----------------------------------------------------------------------------------------------
        //Checks if the task to be planned changes and notifies all users accordingly
        $("#planningPokerCardSelector").change(function () {
                var txt = $(this).val();
                    
                    wsPoker.send("changeTask,"+txt+","+$("#ProjectIDHolder").val());

                
            }).trigger('change');

        //-----------------------------------------------------------------------------------------------
        //Loads the next task to be planed(Not currently used)
        $('body').on('click', '#nextTask', function() { 
            wsPoker.send("next,next,"+$("#ProjectIDHolder").val());
        });

        //-----------------------------------------------------------------------------------------------
        //Helper function used to add a taks to the sprnt
        function addToSprint(points,task)
        {
            var currentTask = task; 
            var finalPoints = points;
            if ($("#previouslyPlannedTaskList").find("#planningFindThis").length > 0)   //If no tasks yet
                $("#previouslyPlannedTaskList").html("");   //Erase the 'No tasks' message
                $("#previouslyPlannedTaskList").append('<div class="plannedAlreadyInner">'+
                                                            '<table style="width:100%">'+
                                                                '<tr style="width:100%">'+
                                                                    '<td style="width:85%">'+currentTask+'</td>'+
                                                                    '<td style="border-left:1px solid #ccc; text-align: center; width:15%"><b>'+finalPoints+'pts</b></td>'+
                                                                '</tr>'+
                                                            '</table>'+
                                                    ' </div>');

                $("#planningPokerCardSelector").children().each(function()
                {
                    if ($(this).val() == currentTask)
                    {    
                        //TODO: add actual values to DB & reset cards
                        $("#selectFinalPlanningPoker").val("0");
                        $(this).remove();
                        return;
                    }   
                });

                $("#planningPokerCardSelector").val($("#planningPokerCardSelector").children(":first-child").val()).change();
                wsPoker.send("changeTask,"+$("#planningPokerCardSelector").val+","+$("#ProjectIDHolder").val());
        }

        //-----------------------------------------------------------------------------------------------
        //Helper function used to remove all current active cards
        function removeAllCards()
        {
            $("#cardTableR1").html("");
            var x = $(".myChosenCard");
            $(x).removeClass("myChosenCard").addClass("planningPokerCard");
            $(x).children(":first").removeClass("myChosenCardInner").addClass("planningPokerCardInner");
        }

        //-----------------------------------------------------------------------------------------------
        //Function used ti interperit the messages recieved by the websocket server
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
            }else 
            if(chars[0] == "unplannedTask")
            {
                $("#planningPokerCardSelector").append("<option id='taskOption"+chars[2]+"'>"+chars[1]+"</option>");
            }
            else if(chars[0] == "changeTask")
            {
                //alert("here");
                $("#planningPokerCardSelector").val(chars[1]);
            }
            else if(chars[0] == "description")
            {
                //insert code here$
                //alert("here");
                $("#taskToPlanDescription").html("<b><i>Description:</i></b>"+chars[1]);
            }
            else if(chars[0] == "finishTask")
            {
                addToSprint(chars[2],chars[1]);
            }
            else if(chars[0] == "removeAllCards")
            {
                removeAllCards();
            }
            else if(chars[0] == "plannedTask")
            {
                addToSprint(chars[3],chars[1]);
            }


        }

        //-----------------------------------------------------------------------------------------------
        //Triggers if you hover over a card
        $(".planningPokerCard").hover(function()
        {
                addTitles(this);
        });

        //-----------------------------------------------------------------------------------------------
        //Adds the on hover event to your chosen card
        $(document.body).on("hover", ".myChosenCard", function(){
                addTitles($(".myChosenCard"));
        });

        //-----------------------------------------------------------------------------------------------
        //Helper function used to add the on hover events to specific cards
        function addTitles(elmnt)
        {
                if ($(elmnt).children(":first").html() == "?")
                        $(elmnt).attr("title", "Unknown Points");
                else if ($(elmnt).children(":first").html() == "1" || $(elmnt).children(":first").html() == "1/2")
                        $(elmnt).attr("title",$(elmnt).children(":first").html()+" Point");
                else
                        $(elmnt).attr("title",$(elmnt).children(":first").html()+" Points");
        }
        
        //-----------------------------------------------------------------------------------------------
        //Adds a card to the page
        function addCard(number,name)
        {
                $("#cardTableR1").append("<td id ='tdCard"+name+"'><div class = 'otherCards' style ='text-align: center'><div class='side-1 flip flip-side1'><div class = 'unrevealedCard'><div class = 'unrevealedCardInner'>X</div></div></div><div class='side-2 flip flip-side2'><div class = 'planningPokerCard' id='chosenCard"+name+"'><div class = 'planningPokerInner'>"+number+"</div></div></div><span style ='position:relative; top:-150px; text-align: center; margin-bottom: -150px;' class = 'cardUser'>"+name+"</span></div></td>");
        }

        //-----------------------------------------------------------------------------------------------
        //Calculates the average between all cards
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
}
