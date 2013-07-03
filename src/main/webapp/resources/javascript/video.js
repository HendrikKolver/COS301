$(document).ready(function()
{
    if($("[id='videoForm:addressID']").val()!= "")
        {
            document.location.replace($("[id='videoForm:addressID']").val());
        }
    $("#videoWindow").load("videoChat.html");
    $("#StartChat").click(function(){
        
        $("[id='videoForm:addressID']").val(location.href);
        $("[id='videoForm:addressClick']").click();
        //alert($("[id='videoForm:addressID']").val());
    });
    
});