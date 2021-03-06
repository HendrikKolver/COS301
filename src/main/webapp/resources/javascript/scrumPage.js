var ws;
var listernersCalled = false;

function joinScrum()
{
    //-----------------------------------------------------------------------------------------------
    //Check if user has active project and act accordingly
    if($("#ProjectIDHolder").val() == "")
    {
        $("#toggleStuff").hide();
        alert("please select a project first");
    }
  
    //-----------------------------------------------------------------------------------------------
    //Make websocket connection if not already established
    if(!listernersCalled)
    {
        initialListeners(); 
        ws = new WebSocket('ws://' +window.location.hostname+':1234'+ '/websocket');
    }
    
    ws.onmessage = function(msg) {showMessage(msg.data);};//recieves a message
    
    setTimeout(function () {
            listeners();
            ws.send("join,join,"+$("#ProjectIDHolder").val());
    }, 500);
    
    //-----------------------------------------------------------------------------------------------
    //Add initial listeners for scrum page
        function initialListeners()
        {
            
            listernersCalled = true;
            $(document.body).on("click", "#addTask",function()
            {
                
                $("[id='form2:testButton']").click();
                
            });
            

        }
        
    //-----------------------------------------------------------------------------------------------
    //Add listeners to elements on scrum page
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
        $('.synchingInputs').bind('input propertychange', function() { 
            ws.send('text,'+ $(this).val() + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value+','+$("#ProjectIDHolder").val());
            var jqueryID = "#"+ document.getElementById("stickyHiddenID").value+$(this).attr('id');
            $(jqueryID).html($(this).val());
            $("[id='setUpdateProjects:projectsUpdateButton']").click();
            $("[id='updateProjectsDB:updateProjectsDBButton']").click();
        });

        $('#StickyDescription').bind('input propertychange', function() {
            ws.send('text,'+ $(this).val() + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value+','+$("#ProjectIDHolder").val());
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
                    ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id')+','+$("#ProjectIDHolder").val());
                    dbUpdate($(ui.draggable).attr('id'));
                    $("[id='setUpdateProjects:projectsUpdateButton']").click();
                    $("[id='updateProjectsDB:updateProjectsDBButton']").click();
                    
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

                $(id).html(line);
                updateLightBox(chars[3]);
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

                updateLightBox(chars[3]);
            }else if (chars[0] == 'burndown')
            {
                drawChart(chars[1]);
            }else if(chars[0] == 'openOptions')
            {
                if($("#syncOptionsButton").html().trim()=="Unsync")
                {
                    openLightBox(chars[1]);
                    var htmlValue = $("#"+chars[1]+"Hidden").html();
                    $("#subTasks").html(htmlValue);
                    $("#StickyComments").val($("#"+chars[1]+"HiddenComments").html());
                }

            }else if(chars[0] == 'closeOptions')
            {
                if($("#syncOptionsButton").html().trim()=="Unsync")
                    {
                        closeOptions(chars[1]);
                        $('#StickyComments').unbind('input propertychange');
                    }
            }else if(chars[0] == 'commentsUpdate')
            {
            $('#StickyComments').unbind('input propertychange');
            var id = chars[2];
            $("#"+id+"HiddenComments").html(chars[1]);
            //alert( "hello");
            $("#StickyComments").val(chars[1]);
            addDelete();
            }else if(chars[0] == 'tasksUpdate')
            {
                var id = chars[2];
            $("#"+id+"Hidden").html(chars[1]);
            $("#subTasks").html(chars[1]);
            addDelete();
            }
        }

        //-----------------------------------------------------------------------------------------------
        //Draw the burndown chart
        function drawChart(values)
        {
            $("#container").html("");

            var split = values.split(';');
            var array = new Array();
            for (i = 0; i < split.length; i++)
            {
                array[i] = parseInt(split[i]);
            }

            $(function () {
                $('#container').highcharts({
                    chart: {
                        type: 'line',
                        marginRight: 130,
                        marginBottom: 25
                    },
                    title: {
                        text: 'Burndown Chart',
                        x: -20 //center
                    },
                    xAxis: {

                    },
                    yAxis: {
                        title: {
                            text: ' Points'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        headerFormat : '<span style="font-size: 12px color:#808080">Day {point.key}</span><br/>',
                        valueSuffix: ' Points'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -10,
                        y: 100,
                        borderWidth: 0
                    },
                    series: [{
                        name: 'Tasks',
                        data: array
                    }]
                });
            });
        }
        
        //----------------------------------------------------------------------------
        //remove task button
        $("#stickyDelete").click(function(){
            var r = confirm("Are you sure you want to delete this task?");
            if (r == true)
            {
                var id = $("#stickyHiddenID").val();
                dbDelete(id);
                ws.send('remove,'+document.getElementById("stickyHiddenID").value+','+$("#ProjectIDHolder").val());
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
            var hiddenDiv = id+"Hidden";
            var hiddenComments = id+"HiddenComments";

            var divIDJquery = "#"+ divID;
            var jqueryOptions = "#"+options;

            var element = '<div class ="draggable" id="'+divID+'"><table style ="width:100%; height: 100%;"><tr ><td title ="Task Name" id="'+TaskName+'" colspan ="3" style ="font-weight:bold; font-family:Lucida Casual, Comic Sans MS; font-size:12pt;">Task Name </td></tr><tr><td title ="Person Responsible" id="'+Responsible+'" colspan ="3" style ="font-size:10pt">Person Responsible</td></tr><tr><td colspan ="3"><div title ="Task Description" id="'+Description+'" class ="stickyContent" style =" width:100%; font-family:Lucida Casual, Comic Sans MS; overflow:auto; height:100px;">Description</div></td> </tr><tr><td class = "poker" title ="Story Points" id="'+Points+'" style ="font-size:14pt; font-weight:bold; padding-left:10px;">0</td><td><button id="'+options+'">Options</button></td><td class = "stopwatch" title ="Days Remaining" id="'+Days+'" style ="font-size:14pt; font-weight:bold; padding-right:10px;">0</td></tr></table><div id="'+hiddenDiv+'" style="display:none;"></div> <div id="'+hiddenComments+'" style="display:none;"></div> </div>';
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
                openLightBox(id);
                var htmlValue = $("#"+id+"Hidden").html();
                $("#subTasks").html(htmlValue);
                $("#StickyComments").val($("#"+id+"HiddenComments").html());
                $("#lightboxNewSubTask").val("");
                addDelete();
                ws.send("openOptions,"+id+','+$("#ProjectIDHolder").val());
            });  
        }
        
        
        //-----------------------------------------------------------------------------------------------
        //Allow client to sync and unsync the window popup
        $("#syncOptionsButton").click(function(){
            if($("#syncOptionsButton").html().trim()=="Sync")
            {
                $("#syncOptionsButton").html("Unsync");
            }
            else
            {
                $("#syncOptionsButton").html("Sync");
            }
        });

        //-----------------------------------------------------------------------------------------------
        //Open sticky note popup box
        function openLightBox(id)
        {
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
        }
        
        //-----------------------------------------------------------------------------------------------
        //Open sticky note update popup box
        function updateLightBox(id)
        {
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
        }

        //-----------------------------------------------------------------------------------------------
        //add a row to the bottom of the scrum page
        function addRow(i)
        {
            var rowCount = $('.snaptarget tr').length;
            rowCount = rowCount/3 - 1;
            if (rowCount<i)
            {
                $("#body").css('height','+=200px');
                $("#content").css('height','+=200px');
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

                            ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id')+','+$("#ProjectIDHolder").val());
                            dbUpdate($(ui.draggable).attr('id'));
                            $("[id='setUpdateProjects:projectsUpdateButton']").click();
                            $("[id='updateProjectsDB:updateProjectsDBButton']").click();

                        }
                    };
                    $(this).find('tr:last').find('.snapHere').droppable(drop);
                });
            }
        }
        
    //-----------------------------------------------------------------------------------------------
    //Listener for the add row function
    $("#addRow").click(function()
    {
        $("#body").css('height','+=200px');
        $("#content").css('height','+=200px');
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

                    ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id')+','+$("#ProjectIDHolder").val());
                    dbUpdate($(ui.draggable).attr('id'));
                    $("[id='setUpdateProjects:projectsUpdateButton']").click();
                    $("[id='updateProjectsDB:updateProjectsDBButton']").click();

                }
            };
            $(this).find('tr:last').find('.snapHere').droppable(drop);
        });
        ws.send("addRow,"+"1"+','+$("#ProjectIDHolder").val());
    });

    //-----------------------------------------------------------------------------------------------
    //Selection of sticky note colours
    $("#StickyYellowSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "yellow" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str+','+$("#ProjectIDHolder").val());
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/yellowstickynote.png')");

    });

    $("#StickyPurpleSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "purple" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str+','+$("#ProjectIDHolder").val());
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/purplestickynote.png')");
    });

    $("#StickyGreenSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "green" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str+','+$("#ProjectIDHolder").val());
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/greenstickynote.png')");
    });

    $("#StickyRedSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "red" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str+','+$("#ProjectIDHolder").val());
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/redstickynote.png')");
    });

    $("#StickyBlueSelect").click(function()
    {
        $(".colorActive").removeClass("colorActive");
        $(this).addClass("colorActive");
        var str = ('colour,'+ "blue" + "," +document.getElementById("stickyHiddenID").value+$(this).attr('id')+','+document.getElementById("stickyHiddenID").value);
        ws.send(str+','+$("#ProjectIDHolder").val());
        var jqueryID = "#"+ document.getElementById("stickyHiddenID").value;
        $(jqueryID).css('background-image',"url('resources/images/bluestickynote.png')");
    });
    
    //-----------------------------------------------------------------------------------------------
    //Closing and accepting sticky note changes
    $("#stickyFinished").click(function()
    {
        var id = $("#stickyHiddenID").val();
            dbUpdate(id);
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none';
            ws.send("closeOptions,"+id+','+$("#ProjectIDHolder").val());
            $("#StickyComments").val("");
            $('#StickyComments').unbind('input propertychange');
    });

    //-----------------------------------------------------------------------------------------------
    //Closing and accepting sticky note changes
    $("#closeLightBox").click(function()
    {
            var id = $("#stickyHiddenID").val();
            dbUpdate(id);
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none';
            ws.send("closeOptions,"+id+','+$("#ProjectIDHolder").val());
            $("#StickyComments").val("");
            $('#StickyComments').unbind('input propertychange');
    });

    //-----------------------------------------------------------------------------------------------
    //Allow sprint to progress one day 
    $("#addDay").on("click",function()
    {
        $("[id='setUpdateProjects:projectsUpdateButton']").click();
        $("[id='updateProjectsDB:updateProjectsDBButton']").click();
        ws.send('addDay,1'+','+$("#ProjectIDHolder").val());
    });

    //-----------------------------------------------------------------------------------------------
    //Finish current sprint
    $("#finishSprint").on("click",function()
    {
        var r=confirm("Finish sprint? This action cannot be undone");
        if (r==false)
            return;
        $("[id='finishSprintForm:finishSprintButton']").click();
        $("[id='setUpdateProjects:projectsUpdateButton']").click();
        $("[id='updateProjectsDB:updateProjectsDBButton']").click();
        $("[id='navForm:navScrumPage']").click();


    });

    //-----------------------------------------------------------------------------------------------
    //Update the database relating to task changes
    function dbUpdate(id)
    {
        $("[id='form:command']").click();  
    }

    //-----------------------------------------------------------------------------------------------
    //Close the sticky note oprions box
    function closeOptions(id)
    {
        document.getElementById('light').style.display='none';
        document.getElementById('fade').style.display='none';
    }

    //-----------------------------------------------------------------------------------------------
    //Add a subtask to a specific task
    $("#lightboxAddSubTask").on("click",function()
    {
        var value = $("#lightboxNewSubTask").val();
        var stringToAppend = '<tr><td style="width:60%;">'+value+'</td><td><input type ="checkbox"/></td><td><button class ="deleteSubTask">Delete</button></td></tr>';
        $("#subTasks").append(stringToAppend);
        addDelete();
        $("#lightboxNewSubTask").val("");
        var id = $("#stickyHiddenID").val();
        $("#"+id+"Hidden").html($("#subTasks").html());
        ws.send("tasksUpdate,"+$("#subTasks").html()+","+id+','+$("#ProjectIDHolder").val());
    });

    //-----------------------------------------------------------------------------------------------
    //Add the delete function to subtasks along with the listeners for the comments and checkboxes
    function addDelete()
    {
        $('#StickyComments').unbind('input propertychange');
        $('#StickyComments').bind('input propertychange', function() 
        {
            var id = $("#stickyHiddenID").val();
            $("#"+id+"HiddenComments").html($(this).val());
            ws.send("commentsUpdate,"+$(this).val()+","+id+','+$("#ProjectIDHolder").val());
        });

        $(".deleteSubTask").on('click',function()
        {
            var id = $("#stickyHiddenID").val();
            $(this).parent().parent().remove();
            $("#"+id+"Hidden").html($("#subTasks").html());
            ws.send("tasksUpdate,"+$("#subTasks").html()+","+id+','+$("#ProjectIDHolder").val());
        }); 

        $(":checkbox").off('click');
        $(":checkbox").on('click',function()
        {
            var parent = $(this).parent();
            if($(this).is(':checked'))
            {
                $(this).remove();
                var element ='<input type ="checkbox" checked="checked"/>';
                $(parent).append(element);
                addDelete();
            }
            else
            {
                $(this).remove();
                var element ='<input type ="checkbox"/>';
                $(parent).append(element);
                addDelete();

            }
            var id = $("#stickyHiddenID").val();
            $(this).parent().parent().remove();
            $("#"+id+"Hidden").html($("#subTasks").html());
            ws.send("tasksUpdate,"+$("#subTasks").html()+","+id+','+$("#ProjectIDHolder").val());
        });
    }

    //-----------------------------------------------------------------------------------------------
    //Update a specific task
    function dbUpdate(id)
    { 

        $("[id='form:updateID']").val(id);
        $("[id='form:updateForm']").click();
        //alert($("[id='form:updateID']").val());

    }

    //-----------------------------------------------------------------------------------------------
    //Delete a specific task
    function dbDelete(id)
    { 
        $("[id='form3:deleteID']").val(id);
        $("[id='form3:deleteForm']").click();

    }
}
            