


                $(document).ready(function()
                {
                    //dynamically added note as proof of concept for adding from db
                    var element = "<div class ='draggable' id='Task6'><div class = 'content'><textarea id = 'textareaID6' class = 'ta' rows = '9' cols = '22'>Your Text Here...</textarea><div class = 'replaced' id='Task6ID'> </div></div>	<button class = 'edit'>Edit</button> </div>";
                     $("#DragContainer").append(element);                      
            
		/*Called every time the text area is changed*/

		$('.ta').bind('input propertychange', function() {
			//alert($(this).val());
                        ws.send('text,'+ $(this).val() + "," +$(this).next().attr('id')+','+$(this).parent().parent().attr('id'));
                        
		});
                
                var drop = {
		   drop: function(ev, ui) 
		   {
                       var relativeTop = $("#DragContainer").offset().top - $(this).offset().top;
                       var relativeLeft = $("#DragContainer").offset().left - $(this).offset().left;
                       //alert(relativeTop);
                       //alert(relativeLeft);

			$(ui.draggable).offset({top: ($(this).offset().top), left: ($(this).offset().left)});

                        var thisPos = $(this).position();
                        var y = thisPos.left;
                        var x = thisPos.top;
                           
                        ws.send('position,'+x + "," + y + "," +$(ui.draggable).attr('id'));
		    }
		};
			   
                
		$('.snapHere').droppable(drop);
		$(".edit").prev().children(":first").hide();
		$(".edit").prev().children(":nth-child(2)").html($(".edit").prev().children(":first").val());
                $(".edit").each(function(){
                    $(this).prev().children(":nth-child(2)").show();
                    $(this).text("Edit");
                    $(this).prev().children(":first").hide();
                    $(this).prev().children(":nth-child(2)").html($(this).prev().children(":first").val());
                    
                });
		$(".edit").on("click",function()
		{
			if ($(this).text() == "Edit")
			{
				$(this).text("Done Editting");
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
                   // alert(text);
                    var chars = text.split(',');
                    var text = (chars[0] +',' + chars[1]);
                    
                    if(chars[0] == 'position')
                    {      
                        var id= "#"+chars[3];
                        $(id).css('position', 'absolute');
                        $(id).animate({
                            top: '' + chars[1] +'',
                            left: '' + chars[2]+''  
                        }, 200, function() {
                            
                        }); 
                           
                    }
                    else if(chars[0] == 'text')
                    {
                        
                        var line = ""+chars[1]+"";
                        var id= "#"+chars[2];
                        //alert(line)
                        //alert(id);
                        $(id).html(line);
                    }
                }
//                
                var ws = new WebSocket('ws://' +window.location.hostname+':1234'+ '/websocket');
                ws.onopen = function() { };//connection opened
                ws.onclose = function() {  };//connection lost/closed
                ws.onmessage = function(msg) {showMessage(msg.data);};//recieves a message

                $( init );

                function init() {
                var dragger = 0;
                
                $('.draggable').draggable( {
                    cursor: 'move',
                    containment: 'document',
                    
                    stop: function(){
                    current = null; 
                    }
                });
                }
                
                
                
                
                        
              
            
            });
            
