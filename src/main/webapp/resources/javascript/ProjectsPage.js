var contextVarID;
$(document).ready(function()
{
    
    //-----------------------------------------------------------------------------------------------
    //Initial setup and variable declarations
    $("#tabContent div").hide(); // Initially hide all content
    $("#tabs li:first").attr("id","current"); // Activate first tab
    
    $("#tabContent div:first").fadeIn(); // Show first tab content
    $("#tabContent div:first").find("div").show();
    
    contextVarID= $("#tabContent div:first").attr("id");
    
    //Function to select the burndown charts from different sprints
    $(".burndownChartSelect").each(function()
    {
        drawSomeChart($(this));
    });
    
    
    function drawSomeChart(chartToDraw)
    {
        var selectedOption = $(chartToDraw).val();
        var name = "";
        var elemToPass = $(chartToDraw).prev();
        
        
        
        $(chartToDraw).children().each(function()
        {
            if (selectedOption == $(this).html().trim())
                name = $(this).attr("name");
        });
        
        
        $(chartToDraw).next().find(".hiddenInfo").each(function()//children().each(function()
        {
            if ($(this).html() == name)
            {
                drawChartNew($(this).next().html().trim(), elemToPass); 
            }
        });
    }
    
    //-----------------------------------------------------------------------------------------------
    //Listener to redraw the burndownchart if the selection changes
    $(".burndownChartSelect").on("change",function()
    {
        drawSomeChart($(this));
    });

    //-----------------------------------------------------------------------------------------------
    //Function to draw the burndown chart (highchart)
    
    function drawChartNew(values, elemToPass)
        {
           
            $(elemToPass).html("");
            values = values.substring(0,values.length-1);
            
            var split = values.split(';');
            var array = new Array();
            for (i = 0; i < split.length; i++)
            {
                array[i] = parseInt(split[i]);
            }
                                    
            $(function () {
                $(elemToPass).highcharts({
                    chart: {
                        type: 'line',
                        marginRight: 25,
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
    
    //-----------------------------------------------------------------------------------------------
    //Function to select the project to work on
    $(".projectButton").on("click",function(e){
       e.preventDefault(); 
       //$("#hiddenValue").hide();
       var id = $(this).attr("id"); 
       $("[id='selectSessionProject:sessionProjectID']").val(id);
       $("[id='selectSessionProject:selectSessionProjectUpdate']").click();
       $("[id='projectNameForm:projectNameFormButton']").click();
       $("#ProjectIDHolder").val(id);
       joinChatRoom();
       
       setTimeout(function () {
                location.reload(); 
            }, 300);
    });
    
    //-----------------------------------------------------------------------------------------------
    //Function to start the video chat, chatroom
    $(".startVideoChat").click(function(e){
        e.preventDefault();
       
        $("[id='videoStart:videoID']").val('false');
        $("[id='videoStart:videoClick']").click();
        
        startRoom();
        
    });
    
    //-----------------------------------------------------------------------------------------------
    //Function to change the project tab you are viewing
    $(document).on('click', '#tabs a', function(e){
            e.preventDefault();
            if ($(this).closest("li").attr("id") == "current")//detection for current tab
                    return;
            else
            {             
                    $("#tabContent div").hide(); //Hide all content
                    $("#tabs li").attr("id",""); //Reset id's
                    $(this).parent().attr("id","current"); // Activate this
                    contextVarID = $(this).attr('name');
                    
                    $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
                    $('#' + $(this).attr('name')).find("div").show();
            }
    });

    //-----------------------------------------------------------------------------------------------
    //Functions used to handle the project content
    $("#createNewProject").click(function()
    {
            var projectName = "New Project";	
            var lastTab = $("#tabs li:last-child").find('a');	//Find last project at the moment
            var lastTabNumber = parseInt(lastTab.attr('name').substring(3));	//Tab number of last tab	
            $("#tabs").append("<li><a href='#' name='tab"+(lastTabNumber+1)+"'>"+projectName+"</a></li>");
            $("#tabContent").append("<div id='tab"+(lastTabNumber+1)+"'>...</div>");

            while ($("#tabs").css("height").substring(0,2) > "42px")	//Try fit in the tabs if possible
            {
                    if ($("#tabs a").css("padding-right") <= "1px")
                    {
                            $("#tabs a").each(function()
                            {
                                    $(this).css("font-size","-=1px");
                            });
                            if ($("#tabs a").css("font-size") <= "10px")
                                    break;	//No more space!
                    }else 
                    {
                            $("#tabs a").each(function()
                            {
                                    $("#tabs a").css("padding-right","-=1px");
                                    $("#tabs a").css("padding-left","-=1px");
                            });
                    }					
            }
            $("#tabs a").css("padding-right",$("#tabs li:first-child").find('a').css("padding-right"));
            $("#tabs a").css("padding-left",$("#tabs li:first-child").find('a').css("padding-left"));
            $("#tabs a").css("font-size",$("#tabs li:first-child").find('a').css("font-size"));
            $(lastTab).parent().next().find('a').click();	//Select latest project
    });	

   
   //-----------------------------------------------------------------------------------------------
    //Adding a member to a project listener
    $(".addMemberToProj").click(function(e)
    {
        
            e.preventDefault();
            addMemberToProject($(this).prev().val(), this);
            
            $("#"+contextVarID).find(".memberSelect option:selected").remove();
            
             
    });

    //-----------------------------------------------------------------------------------------------
    //Function to add a member to a specific project    
    function addMemberToProject(memberName, here)
    {
        
        
            if (memberName == null)
            {
                    alert("No More People to Add");
                    return;
            }
            $("[id='userToAddForm:userToAddName']").val($(here).prev().val());
            $("[id='userToAddForm:userToAddID']").val($(here).next().html().trim());
            $("[id='userToAddForm:userToAddButton']").click();
            $("[id='updateProjectsDB:updateProjectsDBButton']").click();
            
            
            if($("[id='userdetails:userdetailsAdmin']").val()== "true")
            {
                $("#"+contextVarID).find(".tableOfCurrentMembers").append("<table style = 'border-bottom:1px solid #ccc'><tr><td>"+memberName+"</td><td style ='width:15%'><a class = 'removeUserFromProj' style ='font-size:9pt' href = '#'>Remove</a><span style='display:none;'>"+$(here).next().html().trim()+"</span></td></tr></table>");	//Add user to the project
                
            }
            else
            {
              $("#"+contextVarID).find(".tableOfCurrentMembers").append("<table style = 'border-bottom:1px solid #ccc'><tr><td>"+memberName+"</td><td style ='width:15%'></td></tr></table>");
                
            }
            $("#"+contextVarID).find(".tableOfCurrentMembers").scrollTop($(".tableOfCurrentMembers")[0].scrollHeight);	//Scroll to the bottom of chat
            

    }
    
    //-----------------------------------------------------------------------------------------------
    //Prevent multiple listeners
    $(document).off('click', ".removeUserFromProj");

    //-----------------------------------------------------------------------------------------------
    //Function to remove a user from a specific project
    $(document).on('click', ".removeUserFromProj", function(e)
    {
            e.preventDefault();
            addMemberToSelectList($(this).parent().prev().text());

            $("[id='userToRemoveForm:userToRemoveName']").val($(this).parent().prev().html().trim());
            $("[id='userToRemoveForm:userToRemoveID']").val($(this).next().html().trim());
            $("[id='userToRemoveForm:userToRemoveButton']").click();
            $("[id='updateProjectsDB:updateProjectsDBButton']").click();
            $(this).parent().parent().parent().remove();
            
    });

    //-----------------------------------------------------------------------------------------------
    //Helper function used when you remove users from projects
    function addMemberToSelectList(memberName)
    {
            if (memberName == null)
                    return;
            $("#"+contextVarID).find(".memberSelect ").append("<option>"+memberName+"</option>");	//Add user to the project      
    }
    
    //-----------------------------------------------------------------------------------------------
    //Function to autoselect your current working project
    $("#tabs li").each(function()
    {
        if ($(this).text().trim() == $("#ProjectNameDisplay").html().trim()){
            $(this).find('a').click();
        }   
    });
    
    //-----------------------------------------------------------------------------------------------
    //Hides the remove user functionality if user not an admin
    if($("[id='userdetails:userdetailsAdmin']").val()!= "true")
        $(".removeUserFromProj").hide();

    
});
