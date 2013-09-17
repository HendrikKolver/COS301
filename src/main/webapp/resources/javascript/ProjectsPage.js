$(document).ready(function()
{
    $(".projectButton").on("click",function(){
        
       var id = $(this).attr("id"); 
       $("[id='selectSessionProject:sessionProjectID']").val(id);
       $("[id='selectSessionProject:selectSessionProjectUpdate']").click();
       $("[id='projectNameForm:projectNameFormButton']").click();
    });
});
