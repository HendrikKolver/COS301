$(document).ready(function()
{
    $("#videoWindow").hide();
    
    if($("[id='videoForm:addressID']").val()!= "")
        {
           // alert(location.port);
            document.location.replace("http://"+location.hostname+":"+location.port+location.pathname+$("[id='videoForm:addressID']").val());
        }
    $("#videoLoad").load("videoChat.html");
    $("#StartChat").click(function(){
        
        $("[id='videoStart:videoID']").val('false');
        $("[id='videoStart:videoClick']").click();
        
        //alert("here");
        //alert($("[id='videoForm:addressID']").val());
    });
    
    //--------------------------------------------------------------------------------------
    
    $("#chatNowBtn").click(function()
    {
        $("#videoWindow").stop().slideToggle();
    });
    
    //--------------------------------------------------------------------------------------
    
});