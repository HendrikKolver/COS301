    
        $(document).ready(function()
	{	
		/*Called every time the text area is changed, TODO: Update with WebSockets*/
		$('.ta').bind('input propertychange', function() {
			//alert($(this).val());
                        ws.send('text,'+ $(this).val() + "," +$(this).next().attr('id'));
		});
			   
                
		$('.snapHere').droppable({
		   drop: function(ev, ui) 
		   {
			$(ui.draggable).offset({ top: ($(this).offset().top+5), left: ($(this).offset().left+5)});
                        var thisPos = $(this).position();
                        var y = thisPos.left+5;
                        var x = thisPos.top+5;
                           
                        ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
		    }
		});
		
		$(".edit").prev().children(":first").hide();
		$(".edit").prev().children(":nth-child(2)").html($(".edit").prev().children(":first").val());
                $(".edit").each(function(){
                    $(this).prev().children(":nth-child(2)").show();
                    $(this).text("Edit");
                    $(this).prev().children(":first").hide();
                    $(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());
                    
                });
		$(".edit").click(function()
		{
			if ($(this).text() === "Edit")
			{
				$(this).text("Done Editing");
				$(this).prev().children(":first").show();
				$(this).prev().children(":first").val($(this).prev().children(":nth-child(2)").text());
				$(this).prev().children(":nth-child(2)").hide();
				$(".ta").focus();
			}else
			{
				$(this).prev().children(":nth-child(2)").show();
				$(this).text("Edit");
				$(this).prev().children(":first").hide();
				$(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());
			}
		});
               
                    
                    
                    
                var current = null;   
                function showMessage(text) {
                    var chars = text.split(',');
                    var text = (chars[0] +',' + chars[1]);
                    
                    if(chars[0] == 'position')
                    {      
                        var id= "#"+chars[3];
                        $(id).css('position', 'absolute');
                        $(id).animate({
                            top: '' + chars[1] +'',
                            left: '' + chars[2]+''  
                        }, 35, function() {
                            
                        }); 
                           
                    }
                    else if(chars[0] == 'text')
                    {
                        
                        var line = ""+chars[1]+"";
                        var id= "#"+chars[2];
                        //alert(id);
                        $(id).html(line);
                    }
                }
//                
                var ws = new WebSocket('ws://' +window.location.hostname+':1234'+ '/websocket');
                ws.onopen = function() { };//connection opened
                ws.onclose = function() {  };//connection lost/closed
                ws.onmessage = function(msg) { showMessage(msg.data); };//recieves a message

                $( init );

                function init() {
                var dragger = 0;
                $('.draggable').draggable( {
                    cursor: 'move',
                    containment: 'document',
                    drag: function() {

                        var $this = $(this);
                        current = $this.attr('id')
                        var thisPos = $this.position();
                        var parentPos = $this.parent().position();
                        
                        var y = thisPos.left;// - parentPos.left;
                        var x = thisPos.top;// - parentPos.top;
                        

                        if(dragger > 10)
                        {
                           // alert(x)
                                ws.send('position,'+x + "," + y + "," +$this.attr('id'));
                                dragger = 0;
                        }

                        dragger++;
                        },
                    stop: function(){
                    current = null; 
                    }
                });
                }
            
            });