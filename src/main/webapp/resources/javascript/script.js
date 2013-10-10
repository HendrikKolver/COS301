//js for the working of the tabs

//$(document).ready(function()
//{
    //alert("4");
    $("#replacement1").hide();
    $("#replacement2").hide();
    $("#replacement3").hide();
    
    $("#tog1").hide();
    $("#tog2").hide();
    $("#tog3").hide();

    $("#tblTog1").click(function()
    {	
            if ($(".t2Active")[0])
            {
                    $("#tog2").show();
                    $("#replacement2").hide();
                    $('.t2Active').animate({
                    marginLeft:'-=658px'
                    }, 300, function()
                    {
                            $('#toggle2').removeClass('t2Active');
                            $("#tblTog2").show();
                            $("#tog2").hide();

                            $("#tog1").show();
                            $("#tblTog1").hide();
                            $('#toggle1').addClass('t1Active');
                            $('#toggle1').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog1").hide();
                                    $("#replacement1").show();
                            });
                    });
            }else if ($(".t3Active")[0]){
                    $("#tog3").show();
                    $("#replacement3").hide();
                    $('.t3Active').animate({
                    marginLeft:'-=658px'
                    }, 300, function()
                    {
                            $('#toggle3').removeClass('t3Active');
                            $("#tblTog3").show();
                            $("#tog3").hide();

                            $("#tog1").show();
                            $("#tblTog1").hide();
                            $('#toggle1').addClass('t1Active');
                            $('#toggle1').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {       
                                    $("#tog1").hide();
                                    $("#replacement1").show();
                            });
                    });
            }else
            {
                            $("#tog1").show();
                            $("#tblTog1").hide();
                            $('#toggle1').addClass('t1Active');
                            $('#toggle1').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog1").hide();
                                    $("#replacement1").show();
                            });
            }
    });

    $("#replacement1").click(function()
    {
            $("#tog1").show();
            $("#replacement1").hide();
            $('#toggle1').animate({
            marginLeft:'-=658px'

            }, 300, function()
            {
                    $('#toggle1').removeClass('t1Active');
                    $("#tog1").hide();
                    $("#tblTog1").show();
            });
    });

    $("#tblTog2").click(function()
    {	
            if ($(".t1Active")[0]){
                    $("#tog1").show();
                    $("#replacement1").hide();
                    $('.t1Active').animate({
                    marginLeft:'-=658px'
                    }, 300, function()
                    {
                            $('#toggle1').removeClass('t1Active');
                            $("#tblTog1").show();
                            $("#tog1").hide();

                            $("#tog2").show();
                            $("#tblTog2").hide();
                            $('#toggle2').addClass('t2Active');
                            $('#toggle2').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog2").hide();
                                    $("#replacement2").show();
                            });
                    });
            }else if ($(".t3Active")[0]){
                    $("#tog3").show();
                    $("#replacement3").hide();
                    $('.t3Active').animate({
                    marginLeft:'-=658px'
                    }, 300, function()
                    {
                            $('#toggle3').removeClass('t3Active');
                            $("#tblTog3").show();	
                            $("#tog3").hide();

                            $("#tog2").show();
                            $("#tblTog2").hide();
                            $('#toggle2').addClass('t2Active');
                            $('#toggle2').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog2").hide();
                                    $("#replacement2").show();
                            });
                    });
            }else
            {
                            $("#tog2").show();
                            $("#tblTog2").hide();
                            $('#toggle2').addClass('t2Active');
                            $('#toggle2').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog2").hide();
                                    $("#replacement2").show();
                            });
            }
    });

    $("#replacement2").click(function()
    {
            $("#tog2").show();
            $("#replacement2").hide();
            $('#toggle2').animate({
            marginLeft:'-=658px'

            }, 300, function()
            {
                    $("#tog2").hide();
                    $('#toggle2').removeClass('t2Active');
                    $("#tblTog2").show();
            });
    });

    $("#tblTog3").click(function()
    {	
            if ($(".t1Active")[0])
            {
                    $("#tog1").show();
                    $("#replacement1").hide();
                    $('.t1Active').animate({
                    marginLeft:'-=658px'
                    }, 300, function()
                    {
                            $('#toggle1').removeClass('t1Active');
                            $("#tblTog1").show();
                            $("#tog1").hide();

                            $("#tog3").show();
                            $("#tblTog3").hide();
                            $('#toggle3').addClass('t3Active');
                            $('#toggle3').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog3").hide();
                                    $("#replacement3").show();
                            });
                    });
            }else if ($(".t2Active")[0])
            {
                    $("#tog2").show();
                    $("#replacement2").hide();
                    $('.t2Active').animate({
                    marginLeft:'-=658px'
                    }, 300, function()
                    {
                            $('#toggle2').removeClass('t2Active');
                            $("#tblTog2").show();
                            $("#tog2").hide();

                            $("#tog3").show();
                            $("#tblTog3").hide();
                            $('#toggle3').addClass('t3Active');
                            $('#toggle3').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog3").hide();
                                    $("#replacement3").show();
                            });
                    });
            }else
            {
                            $("#tog3").show();
                            $("#tblTog3").hide();
                            $('#toggle3').addClass('t3Active');
                            $('#toggle3').animate({
                            marginLeft:'+=658px'

                            }, 300, function()
                            {
                                    $("#tog3").hide();
                                    $("#replacement3").show();
                            });
            }

    });

    $("#replacement3").click(function()
    {
            $("#tog3").show();
            $("#replacement3").hide();
            $('#toggle3').animate({
            marginLeft:'-=658px'

            }, 300, function()
            {
                    $("#tog3").hide();
                    $('#toggle3').removeClass('t3Active');
                    $("#tblTog3").show();
            });
    });
    

//});