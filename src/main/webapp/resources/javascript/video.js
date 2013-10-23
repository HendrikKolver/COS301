//-----------------------------------------------------------------------------------------------
//Script used for setting up the video conferencing
var isConnected = false;

$(document).ready(function()
{
    $("#videoWindow").hide();
    
    if($("[id='videoForm:addressID']").val()!= "")
        {
            document.location.replace("http://"+location.hostname+":"+location.port+location.pathname+$("[id='videoForm:addressID']").val());
        }
    $("#videoLoad").load("videoChat.html");
    
    $("#chatNowBtn").click(function()
    {
        $("#videoWindow").stop().slideToggle();
    });
    
});