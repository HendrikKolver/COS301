//-----------------------------------------------------------------------------------------------
//Script used for the operating of the tab on the scrum page

    $("#tog1").hide();
    
    $("#tog1").click(function()
    {
        $('#toggle1').animate({
        marginLeft:'-=658px'

        }, 300, function()
        {
                $("#tog1").hide();
                $("#tblTog1").show();
        });
    });
        

    $("#tblTog1").click(function()
    {	
        $("#tog1").show();
        $("#tblTog1").hide();

        $('#toggle1').animate({
        marginLeft:'+=658px'

        }, 300, function()
        {

        });
    });

  