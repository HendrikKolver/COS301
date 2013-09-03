$(document).ready(function()
{
     
    $(".tblButton").click(function()
    {
        alert('Some pop-up of sorts will be displayed here');
    });
    
    
        $("#accordion").accordion();
        $("#accordionSprint").accordion();


        $("#accordionContainerSprint").hide();
        
        $("#selectViewBacklog").on("change",function()
        { 
            
            if($("#accordionContainer").is(":visible"))
                {
                    
                    $("#accordionContainer").hide();
                    $("#accordionContainerSprint").show(); 
                }else
                {
                    
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
                closeLightBoxProjectBacklog();
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
               
            }else
            {
                //not all filled in
                alert("Please fill in all the fields");
            }
        }

    
});

