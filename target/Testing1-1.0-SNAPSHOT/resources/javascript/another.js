
// left: 37, up: 38, right: 39, down: 40,
// spacebar: 32, pageup: 33, pagedown: 34, end: 35, home: 36
var keys = [37, 38, 39, 40];

function preventDefault(e) {
  e = e || window.event;
  if (e.preventDefault)
      e.preventDefault();
  e.returnValue = false;  
}

function keydown(e) {
    for (var i = keys.length; i--;) {
        if (e.keyCode === keys[i]) {
            preventDefault(e);
            return;
        }
    }
}

function wheel(e) {
  preventDefault(e);
}

function disable_scroll() {
  if (window.addEventListener) {
      window.addEventListener('DOMMouseScroll', wheel, false);
  }
  window.onmousewheel = document.onmousewheel = wheel;
  document.onkeydown = keydown;
}

function enable_scroll() {
    if (window.removeEventListener) {
        window.removeEventListener('DOMMouseScroll', wheel, false);
    }
    window.onmousewheel = document.onmousewheel = document.onkeydown = null;  
}



function isScrolledIntoView(elem) 
    {
        var docViewTop = $(window).scrollTop();
        var docViewBottom = docViewTop + $(window).height();
        var elemTop = $(elem).offset().top;
        var elemBottom = elemTop + $(elem).height();
        return ((elemBottom >= docViewTop) && (elemTop <= docViewBottom));
    }
    
$(document).ready(function() 
{
    $(window).scroll(function() {
    //checkPosition('#onBottom');
});

function checkPosition( element ){
//choose your limit
var positionLimit = 916;
var y = getOffsetY();

if( y <= positionLimit ){
    $(element).css({'position':'fixed','bottom':'0'});
}
else{
    $(element).css({'position':'relative'});
}
}
     /*document.body.style.height = "1400px";*/
  /*  var myelement = $('#intoView'); // the element to act on if viewable
    $(window).scroll(function(event)
    {
            if(isScrolledIntoView(myelement)) 
            {
                    disable_scroll();

            } else 
            {
                enable_scroll();
            }
    });
    
    $('body').bind('DOMMouseScroll', function(e){
     if(e.originalEvent.detail > 0) { //Down
         $("#chatNowBtn").html("down");
         //disable_scroll();
     }else {
         
         $("#chatNowBtn").html("up");
        enable_scroll();
     }
     //return false;
 });

 //IE, Opera, Safari
 $('body').bind('mousewheel', function(e){
     if(e.originalEvent.wheelDelta < 0) { //Down
         $("#chatNowBtn").html("down");
     }else {
         $("#chatNowBtn").html("up");
         enable_scroll();
     }
 });*/

});