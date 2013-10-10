var isConnected = false;

$(document).ready(function()
{
    $("#videoWindow").hide();
    
    if($("[id='videoForm:addressID']").val()!= "")
        {
           // alert(location.port);
            document.location.replace("http://"+location.hostname+":"+location.port+location.pathname+$("[id='videoForm:addressID']").val());
        }
    $("#videoLoad").load("videoChat.html");
    
    
    $("#chatNowBtn").click(function()
    {
        $("#videoWindow").stop().slideToggle();
    });
    
});