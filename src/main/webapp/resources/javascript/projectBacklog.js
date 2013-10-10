$(document).ready(function()
{
    
    
    if($("#ProjectIDHolder").val() == "")
    {
        alert("please select a project first");
    }
    
                
            
    var IDtmp; 
    var name;
    //Editing of Tasks...
    
    var elementToUpdate;
    $(".tblButton").click(function()
    {
        elementToUpdate = $(this);
        var values = $(this).parent().parent().children();
        $("#StickyTaskNameEdit").val($(values[0]).html().trim());
        $("#StickyResponsibleEdit").val($(values[1]).html().trim());
        $("#StickyStoryPointsEdit").val($(values[3]).html().trim());
        $("#StickyDaysRemainingEdit").val($(values[4]).html().trim());
        $("#StickyDescriptionEdit").val($(values[2]).html().trim());
        IDtmp = $(this).next().html().trim();
        name = $("#StickyTaskNameEdit").val();

        document.getElementById('light2').style.display='block';
        document.getElementById('fade').style.display='block';
    });
    
    $("#stickyCancelEdit").click(function()
    {
               document.getElementById('light2').style.display='none';
               document.getElementById('fade').style.display='none';
               
    });
    
    function editTasksFinish()
    {
        
        
        $("[id='updateSpecificTask:updateProjectID']").val(IDtmp);
        $("[id='updateSpecificTask:updateProjectIDIndex']").val(IDtmp);
        $("[id='updateSpecificTask:updateTaskName']").val($("#StickyTaskNameEdit").val());
        $("[id='updateSpecificTask:updatePersonResponsible']").val($("#StickyResponsibleEdit").val());
        $("[id='updateSpecificTask:updateDescription']").val($("#StickyDescriptionEdit").val()); 
        $("[id='updateSpecificTask:updateStoryPoints']").val($("#StickyStoryPointsEdit").val());
        $("[id='updateSpecificTask:updateDaysLeft']").val($("#StickyDaysRemainingEdit").val());
        $("[id='updateSpecificTask:updateSpecificTaskButton']").click();
        
        $("."+IDtmp).each(function()
        {
            
            var values = $(this).parent().parent().children();

            $(values[0]).html($("#StickyTaskNameEdit").val());
            $(values[1]).html($("#StickyResponsibleEdit").val());
            $(values[3]).html($("#StickyStoryPointsEdit").val());
            $(values[4]).html($("#StickyDaysRemainingEdit").val());
            $(values[2]).html($("#StickyDescriptionEdit").val()); 
            
        });          
    }
    
    $("#closeLightBoxEdit").click(function()
        {
                document.getElementById('light2').style.display='none';
               document.getElementById('fade').style.display='none';
        });
        
        $("#stickyFinishedEdit").click(function()
        {
            if($("#StickyTaskNameEdit").val().length != 0 && $("#StickyResponsibleEdit").val().length != 0 && $("#StickyDescriptionEdit").val()&& $("#StickyStoryPointsEdit").val()&& $("#StickyDaysRemainingEdit").val())
            {
            
                editTasksFinish();
                document.getElementById('light2').style.display='none';
               document.getElementById('fade').style.display='none';
            }
            else
            {
                alert("Please fill in all the fields");
            }
        });
    
    
        $("#accordion").accordion();
        $("#accordionSprint").accordion();


        $("#accordionContainerSprint").hide();
        
        $("#selectViewBacklog").on("change",function()
        { 
            if($("#accordionContainer").is(":visible") )
                {
                    $("#stickySprint").html("Remove from sprint");
                    $("#accordionContainer").hide();
                    $("#accordionContainerSprint").show(); 
                }else 
                {
                    $("#stickySprint").html("Add to sprint");
                    $("#accordionContainer").show();
                    $("#accordionContainerSprint").hide(); 
                }
        });
        
        $("#backlogAddTask").click(function()
        {
                document.getElementById('light').style.display='block';
                document.getElementById('fade').style.display='block';
        });
        
        $("#stickyCancel").click(function()
        {
               document.getElementById('light').style.display='none';
               document.getElementById('fade').style.display='none';
               
        });
        
        $("#closeLightBox").click(function()
        {
                document.getElementById('light').style.display='none';
               document.getElementById('fade').style.display='none';
        });
        
         $("#stickyFinished").click(function()
        {
                closeLightBoxProjectBacklog();
        });
        
        function closeLightBoxProjectBacklog()
        {
            
            if($("#StickyTaskName").val().length != 0 && $("#StickyResponsible").val().length != 0 && $("#StickyDescription").val())
            {
                //all filled in
               
               $("[id='form2:testButton']").click();
               
               var taskName = $("#StickyTaskName").val();
               var personResponsible = $("#StickyResponsible").val();
               var description = $("#StickyDescription").val();
               
               $("[id='addTaskProjectBacklog:taskNameAdd']").val(taskName);
               $("[id='addTaskProjectBacklog:personResponsibleAdd']").val(personResponsible);
               $("[id='addTaskProjectBacklog:descriptionAdd']").val(description);        
               $("[id='addTaskProjectBacklog:addTaskClick']").click();
               
               document.getElementById('light').style.display='none';
               document.getElementById('fade').style.display='none';
               
               $("[id='updateLastTaskForm:updateLastTask']").click();
               $("[id='navForm:navBacklog']").click();
               
            }else
            {
                //not all filled in
                alert("Please fill in all the fields");
            }
        }
        
        $("#stickySprint").click(function(){
            $("[id='updateAddRemoveSprint:updateTaskID']").val(IDtmp);
            $("[id='updateAddRemoveSprint:updateAddRemoveTaskButton']").click();
            $("[id='navForm:navBacklog']").click();
            if($("#accordionContainer").is(":visible") )
                alert(name+" has been added to Sprint");
            else 
                alert(name+" has been removed from Sprint");
        });

    
});

