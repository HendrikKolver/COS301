$(document).ready(function()
{
    //websocket connection
    var ws = new WebSocket('ws://' +window.location.hostname+':1234'+ '/websocket');
    ws.onmessage = function(msg) {showMessage(msg.data);};//recieves a message

    listeners();


//----------------------------------------------------------------------------
    function listeners()
    {
        //make sticky notes draggable
        $('.draggable').draggable( {
            cursor: 'move',
            containment: 'document',

            stop: function(){
            current = null; 
            }
        });
        
        //listeners for text area editing
        $('.ta').bind('input propertychange', function() {
            //alert($(this).val());
            ws.send('text,'+ $(this).val() + "," +$(this).next().attr('id')+','+$(this).parent().parent().attr('id'));

        });
        
        
        //listeners for edit button and sticky note
        $(".edit").prev().children(":first").hide();
        $(".edit").prev().children(":nth-child(2)").html($(".edit").prev().children(":first").val());
        $(".edit").each(function(){
            $(this).prev().children(":nth-child(2)").show();
            $(this).text("Edit");
            $(this).prev().children(":first").hide();
            $(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());

        });
        $(".edit").on("click",function()
        {
                if ($(this).text() == "Edit")
                {
                        $(this).text("Done Editting");
                        $(this).prev().children(":first").show();
                        $(this).prev().children(":first").val($(this).prev().children(":nth-child(2)").text());
                        $(this).prev().children(":nth-child(2)").hide();
                        $(".ta").focus();
                }else
                {
                        $(this).prev().children(":nth-child(2)").show();
                        $(this).text("Edit");
                        $(this).prev().children(":first").hide();
                        $(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());
                }
        });
        
        //snap location listeners
        var drop = {
            drop: function(ev, ui) 
            {
                $(ui.draggable).offset({top: ($(this).offset().top), left: ($(this).offset().left)});
                var thisPos = $(this).position();
                var y = thisPos.left;
                var x = thisPos.top;

                ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
            }
        };
        
        $('.snapHere').droppable(drop);
    }

//----------------------------------------------------------------------------
    //return function that recieves server reply
    function showMessage(text) 
    {
        var chars = text.split(',');
        var text = (chars[0] +',' + chars[1]);

        if(chars[0] == 'position')
        {      
            var id= "#"+chars[3];

            if($(id).length == 0)
                {
                    addTask(chars[3]);
                }

            $(id).css('position', 'absolute');
            $(id).animate({
                top: '' + chars[1] +'',
                left: '' + chars[2]+''  
            }, 200, function() {

            }); 

        }
        else if(chars[0] == 'text')
        {

            var line = ""+chars[1]+"";
            var id= "#"+chars[2];
            if($(id).length == 0)
                {
                    var str = chars[2];
                    var l = str.length;

                    addTask(str.substring(0,(l-2)));
                }


            //alert(line)

            $(id).html(line);
        }
        else if(chars[0] == 'remove')
        {
            var id= "#"+chars[1];
            $(id).remove();
        }
        else if(chars[0] == 'add')
        {

            addTask(chars[1]);
        }
    }

//----------------------------------------------------------------------------
    //add Task button
    $("#addTask").click(function()
    {
        var id =prompt("Please enter the task ID","");
        ws.send('add,'+id);
        addTask(id); 
    });

    //remove task button
    $(".removeTask").click(function(){
        ws.send('remove,'+$(this).parent().attr('id'));
        $(this).parent().remove();
    });

//----------------------------------------------------------------------------
    //adding of the actual html and calling of listeners()
    function addTask(id){

        var divID = id;
        var textID = "textarea"+id;
        var taskID = id+"ID";
        var editID = "edit"+id;
        var divIDJquery = "#"+divID;
        var editIDJquery = "#"+editID;
        var textIDJquery = "#"+textID;

        var element = "<div class ='draggable' id='"+divID+"'><div class = 'content'><textarea id = '"+textID+"' class = 'ta' rows = '9' cols = '22'>Your Text Here...</textarea><div class = 'replaced' id='"+taskID+"'> </div></div><button class = 'edit' id='"+editID+"'>Edit</button><button class = 'removeTask'>Remove</button> </div>";
            $("#DragContainer").append(element);

        //adding listeners for new element
            $(divIDJquery).draggable( {
            cursor: 'move',
            containment: 'document',

            stop: function(){
            current = null; 
            }     

        });

        $(editIDJquery).prev().children(":first").hide();
        $(editIDJquery).prev().children(":nth-child(2)").html($(".edit").prev().children(":first").val());
        $(editIDJquery).each(function(){
            $(this).prev().children(":nth-child(2)").show();
            $(this).text("Edit");
            $(this).prev().children(":first").hide();
            $(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());

        });
        $(editIDJquery).on("click",function()
        {
                if ($(this).text() == "Edit")
                {
                        $(this).text("Done Editting");
                        $(this).prev().children(":first").show();
                        $(this).prev().children(":first").val($(this).prev().children(":nth-child(2)").text());
                        $(this).prev().children(":nth-child(2)").hide();
                        $(".ta").focus();
                }else
                {
                        $(this).prev().children(":nth-child(2)").show();
                        $(this).text("Edit");
                        $(this).prev().children(":first").hide();
                        $(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());
                }
        }); 

        $(textIDJquery).bind('input propertychange', function() {

            ws.send('text,'+ $(this).val() + "," +$(this).next().attr('id')+','+$(this).parent().parent().attr('id'));

        });

        $(".removeTask").click(function(){
            ws.send('remove,'+$(this).parent().attr('id'));
            $(this).parent().remove();
        });
        

    }
//----------------------------------------------------------------------------

});
            