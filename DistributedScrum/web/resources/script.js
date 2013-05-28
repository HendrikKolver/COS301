
$(document).ready(function()
    {
        
                        var tog1 = 0;

                        $("#tog1").click(function()
                        {
                                if (tog1 == 0)
                                {
                                        tog1 = 1;
                                        $('#toggle1').animate({
                                        left:'+=1160px'

                                        }, 1500, function(){

                                        });
                                }else
                                {
                                        tog1 = 0;
                                        $('#toggle1').animate({
                                        left:'-=1160px'

                                        }, 1500, function(){

                                        });
                                }
                        });
        });