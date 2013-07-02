$(document).ready(function()
{
    //websocket connection
    var ws = new WebSocket('ws://' +window.location.hostname+':1234'+ '/websocket');
    ws.onmessage = function(msg) {showMessage(msg.data);};//recieves a message

    listeners();
    
   
//----------------------------------------------------------------------------

    
    function listeners()
    {
        
        $("#addTask").click(function(){
            $("[id='form2:testButton']").click(); 
        });
	
	
        //make sticky notes draggable
        $('.draggable').draggable( {
            cursor: 'move',
            containment: 'document',

            stop: function(){
            current = null; 
            }
        });
        
        //listeners for text area editing
        $('#light input').bind('input propertychange', function() { 
            ws.send('text,'+ $(this).val() + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
            var jqueryID = "#"+ document.getElementById("stickyHiddenID").value+$(this).attr('id');
            $(jqueryID).html($(this).val());

        });
        
        $('#light textarea').bind('input propertychange', function() {
            ws.send('text,'+ $(this).val() + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
            var jqueryID = "#"+ document.getElementById("stickyHiddenID").value+$(this).attr('id');
            $(jqueryID).html($(this).val());
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
                ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
                    dbUpdate($(ui.draggable).attr('id'));
                    
            }
        };
        $('.snapHere').droppable(drop);
        
    }
    
    function recieveID(ID)
    {
        document.getElementById('stickyHiddenID').value = ID;
        document.getElementById('light').style.display='block';
        document.getElementById('fade').style.display='block';
        var id =document.getElementById("stickyHiddenID").value;
        ws.send('add,'+id);

        var tmpID = "#"+id+"StickyTaskName";
        document.getElementById("StickyTaskName").value = "Task Name";
        tmpID = "#"+id+"StickyResponsible";
        document.getElementById("StickyResponsible").value = "Person Responsible";
        tmpID = "#"+id+"StickyDescription";
        document.getElementById("StickyDescription").value = "Task Description";
        tmpID = "#"+id+"StickyPoints";
        document.getElementById("StickyPoints").value = 0;
        tmpID = "#"+id+"StickyDays";
        document.getElementById("StickyDays").value = 0;
        addTask(id);  
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
        }else if (chars[0] == 'addRow')
        {
            rowCount = parseInt(chars[1]);
            for (i = 3; i < (3+rowCount); i++)
                addRow(i+1);
        }else if (chars[0] == 'colour')
        {
            if (chars[1] == 'yellow')
                $("#"+chars[3]).css('background-image',"url('resources/images/yellowstickynote.png')");
            else if (chars[1] == 'green')
                $("#"+chars[3]).css('background-image',"url('resources/images/greenstickynote.png')");
            else if (chars[1] == 'purple')
                $("#"+chars[3]).css('background-image',"url('resources/images/purplestickynote.png')");
            else if (chars[1] == 'red')
                $("#"+chars[3]).css('background-image',"url('resources/images/redstickynote.png')");
            else if (chars[1] == 'blue')
                $("#"+chars[3]).css('background-image',"url('resources/images/bluestickynote.png')");
        }
            
            
            
    }


//----------------------------------------------------------------------------
    

    //remove task button
    $("#stickyDelete").click(function(){
        var r = confirm("Are you sure you want to delete this task?");
        if (r == true)
        {
            var id = $("#stickyHiddenID").val();
	    dbDelete(id);
            ws.send('remove,'+document.getElementById("stickyHiddenID").value);
            var tmp ="#"+document.getElementById("stickyHiddenID").value;
            $(tmp).remove();
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none';
            
        }
        else
        {
            
        } 
        
    });

//----------------------------------------------------------------------------
    //adding of the actual html and calling of listeners()
    function addTask(id)
    {
        
        var divID = id;
        var TaskName = id+"StickyTaskName";
        var Responsible = id+"StickyResponsible";
        var Description = id+"StickyDescription";
        var Points = id+"StickyPoints";
        var Days = id+"StickyDays";
        var options = id+"Options";
        
        var divIDJquery = "#"+ divID;
        var jqueryOptions = "#"+options;

        var element = '<div class ="draggable" id="'+divID+'"><table style ="width:100%; height: 100%;"><tr ><td title ="Task Name" id="'+TaskName+'" colspan ="3" style ="font-weight:bold; font-family:Lucida Casual, Comic Sans MS; font-size:12pt;">Task Name </td></tr><tr><td title ="Person Responsible" id="'+Responsible+'" colspan ="3" style ="font-size:10pt">Person Responsible</td></tr><tr><td colspan ="3"><div title ="Task Description" id="'+Description+'" class ="stickyContent" style =" width:100%; font-family:Lucida Casual, Comic Sans MS; overflow:auto; height:100px;">Description</div></td> </tr><tr><td class = "poker" title ="Story Points" id="'+Points+'" style ="font-size:14pt; font-weight:bold; padding-left:10px;">0</td><td><button id="'+options+'">Options</button></td><td class = "stopwatch" title ="Days Remaining" id="'+Days+'" style ="font-size:14pt; font-weight:bold; padding-right:10px;">0</td></tr></table> </div>';
            $("#DragContainer").append(element);

       //adding listeners for new element
       $(divIDJquery).draggable({
            cursor: 'move',
            containment: 'document',

            stop: function(){
            current = null; 
            }     
        });
        
        $(jqueryOptions).click(function(){
            
            var id=$(this).attr('id');
            var tmp = id.length;
            id = id.substring(0,tmp-7);
            
            document.getElementById("stickyHiddenID").value = id;
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block';
            var tmpID = "#"+id+"StickyTaskName";
            document.getElementById("StickyTaskName").value = $(tmpID).html();
            tmpID = "#"+id+"StickyResponsible";
            document.getElementById("StickyResponsible").value = $(tmpID).html();
            tmpID = "#"+id+"StickyDescription";
            document.getElementById("StickyDescription").value = $(tmpID).html();
            tmpID = "#"+id+"StickyPoints";
            document.getElementById("StickyPoints").value = $(tmpID).html();
            tmpID = "#"+id+"StickyDays";
            document.getElementById("StickyDays").value = $(tmpID).html();
            
            var color = $("#"+id).css('background-image');
            if (color.indexOf("green") >= 0)
            {
                $(".colorActive").removeClass("colorActive");
                $("#light").find(".StickyGreen").addClass("colorActive");
            }else if (color.indexOf("purple") >= 0)
            {
                $(".colorActive").removeClass("colorActive");
                $("#light").find(".StickyPurple").addClass("colorActive");
            }else if (color.indexOf("yellow") >= 0)
            {
                $(".colorActive").removeClass("colorActive");
                $("#light").find(".StickyYellow").addClass("colorActive");
            }else if (color.indexOf("red") >= 0)
            {
                $(".colorActive").removeClass("colorActive");
                $("#light").find(".StickyRed").addClass("colorActive");
            }else if (color.indexOf("blue") >= 0)
            {
                $(".colorActive").removeClass("colorActive");
                $("#light").find(".StickyBlue").addClass("colorActive");
            }
                
            
        });
        

         

//        $(textIDJquery).bind('input propertychange', function() {
//
//            ws.send('text,'+ $(this).val() + "," +$(this).next().attr('id')+','+$(this).parent().parent().attr('id'));
//
//        });
//
//        $(".removeTask").click(function(){
//            ws.send('remove,'+$(this).parent().attr('id'));
//            $(this).parent().remove();
//        });
        

    }
    
    function addRow(i)
    {
            var rowCount = $('.snaptarget tr').length;
            rowCount = rowCount/3 - 1;
            if (rowCount<i)
            {
                $("#DragContainer").css('min-height','+=200px');
                $('.snaptarget').each(function()
                {
                    $(this).find('tr:last').after('<tr><td class="snapHere"></td><td class="snapHere"></td></tr>');

                    var drop = 
                    {
                        drop: function(ev, ui) 
                        {
                            $(ui.draggable).offset({top: ($(this).offset().top), left: ($(this).offset().left)});
                            var thisPos = $(this).position();
                            var y = thisPos.left;
                            var x = thisPos.top;

                            ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
                            ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
                            dbUpdate($(ui.draggable).attr('id'));
                            
                        }
                    };
                    $(this).find('tr:last').find('.snapHere').droppable(drop);
                });
            }
    }
//----------------------------------------------------------------------------

$("#addRow").click(function()
{
        $("#DragContainer").css('min-height','+=200px');
        $('.snaptarget').each(function()
        {
            $(this).find('tr:last').after('<tr><td class="snapHere"></td><td class="snapHere"></td></tr>');
            
            var drop = 
            {
                drop: function(ev, ui) 
                {
                    $(ui.draggable).offset({top: ($(this).offset().top), left: ($(this).offset().left)});
                    var thisPos = $(this).position();
                    var y = thisPos.left;
                    var x = thisPos.top;

                    ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
                    dbUpdate($(ui.draggable).attr('id'));
                    
                }
            };
            $(this).find('tr:last').find('.snapHere').droppable(drop);
        });
        ws.send("addRow,"+"1");
    });
    
    
    $("#StickyYellowSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "yellow" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str);
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/yellowstickynote.png')");
        
    });
    
    $("#StickyPurpleSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "purple" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str);
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/purplestickynote.png')");
    });
    
    $("#StickyGreenSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "green" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str);
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/greenstickynote.png')");
    });
    
    $("#StickyRedSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "red" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str);
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/redstickynote.png')");
    });
    
    $("#StickyBlueSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "blue" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str);
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/bluestickynote.png')");
    });
    
    $("#stickyFinished").click(function(){
	var id = $("#stickyHiddenID").val();
	    dbUpdate(id);
	    document.getElementById('light').style.display='none';
	    document.getElementById('fade').style.display='none';
	    
	});
	
	    
    function dbUpdate(id)
    { 
        
       $("[id='form:updateID']").val(id);
       $("[id='form:updateForm']").click();
       
    }
    
    function dbDelete(id)
    { 
        
       $("[id='form3:deleteID']").val(id);
       $("[id='form3:deleteForm']").click();
       
    }
    
    
});
            