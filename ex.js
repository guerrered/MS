document.getElementById('home').onclick = function(event){
  window.location.href = "/main"
}

var min;
var sec;
document.getElementById('timeSub').onclick = function(event){
  var inputElements = document.getElementById("getTime");
  var timeString = inputElements[0];
  var intTime = timeString.split(":");
  min = intTime[0];
  sec = intTime[1];
  takeTime(min, sec);
}

function takeTime(min, sec){
  var curMin = min;
  var curSec = sec;
  while(curMin > 0){
    while(curSec > 0){
          document.getElementById("timer").innerHTML = curMin + ":" + curSec;
          curSec--;
    }
    curSec = 60;
    curMin--;
  }
}
