var count; // time in seconds 
var event;
var minutes;
var seconds;
var timeStr;
var minutesStr;
var secondsStr;
var the_end = 0;
var counting_down = 0;

function countdown() {
  if((count > 0) && (counting_down)) {
    minutes = Math.floor(count / 60);
    seconds = count % 60;
    minutesStr = minutes.toString();
    secondsStr = seconds.toString();
    secondsStr = (secondsStr.length < 2 ? '0' : '') + secondsStr;
    minutesStr = (minutesStr.length < 2 ? '0' : '') + minutesStr;
    timeStr = minutesStr + ' min : ' + secondsStr + ' s';
    document.getElementById('timer').innerHTML = timeStr;
    event = setTimeout('countdown()', 1000);
    count--;
  }else{
   if(!count){
      document.getElementById('timer').innerHTML ="00 min: 00 s";
      setTimeout('eTestOutTimed()',2000);
   }
   clearTimeout(event);
  }
}

function startCountdown(startcount) {
  count = startcount;
  if (!counting_down) {
    counting_down = 1;
    countdown();
  }
}

function pauseCountdown(){
   if(count){
      clearTimeout(event);
      if (counting_down) {
         counting_down = 0;
      }
   }
}

function stopCountdown() {
  clearTimeout(event); 
  count = 0;
  document.getElementById('timer').innerHTML ="00 min: 00 s";
}

function eTestPauseTest(){
   pauseCountdown();
   //---------------
   var d = new Date();
   testsessionOBJECT.pausestarttime = d.getTime();  //track pause starttime
   //---------------
   
   var html = '<h2 class="instruct">eTest : <span class="ec1">Test Session Paused</span></h2>';
   html+='<div class="hlo"></div><div class="hlo"></div>';
   html+='<div id="popout-content">';
   html+='<div align="left"><h2 class="testname">Your current Test Session has been paused.</h2><h2 class="testname">To continue, Resume Test.</h2></div>';
   html+='</div>'
   html+='<div class="hlo"></div><div class="hlo"></div>';
   html+='<div class="rgt">';
   html+=' <button class="ec-button ec-blankBTN" onclick="eTestResumeTest()">Resume Test</button>';
   html+=' <button class="ec-button ec-blankBTN" onclick="eTestEndTest()">Quit Test</button>';
   html+='</div>';
   ecoachPopoutShow(html);
}

function eTestResumeTest(){
   //---------------
   var d = new Date();
   testsessionOBJECT.pauseduration += d.getTime()  - testsessionOBJECT.pausestarttime;  //track pause duration
   //---------------
   startCountdown(count);
   ecoachPopoutHide();
   the_end = 0;
}

function eTestEndTest(){
   if(the_end == 0){
      pauseCountdown();
      var html = '<h2 class="instruct"><span class="ec1">eTest : </span> End Test Session?</h2>';
      html+='<div class="hlo"></div><div class="hlo"></div>';
      html+='<div id="popout-content">';
      html+='<div align="left"><h2 class="testname">Are you sure you want to quit?</h2><h2 class="testname"><span class="r">You will not receive a score for this test!</span></h2></div>';
      html+='</div>'
      html+='<div class="hlo"></div><div class="hlo"></div>';
      html+='<div class="rgt">';
      html+=' <button class="ec-button ec-blankBTN" onclick="eTestResumeTest()">Resume Test</button>';
      html+=' <button class="ec-button ec-redBTN" onclick="eTestEndTest()">Quit Test</button>';
      html+='</div>';
      ecoachPopoutShow(html);
      the_end++;
   }else{
      stopCountdown();
      QuitToNewTest();
   }
}

function eTestOutTimed(){
   answerSubmit(20,200); // process test
}