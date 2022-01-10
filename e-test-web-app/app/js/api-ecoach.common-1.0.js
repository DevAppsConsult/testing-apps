/*
API 
E-Coach Common API JS file
version 1.0.0
*/
//-------------------------------------
var GlobalTempHTML = {};
var GlobalTempFormOptions;
//-------------------------------------
var GlobalDomain = GlobalRoot + "main.php";
var DefaultOptions = {
	'API_key':'',
	//-----------------
	'targetElementID':'',
	'signin_callback':'api_ecoach_authenticate_handler',
	'signup_callback':'api_ecoach_authenticate_handler',
	'page_renderer':'api_ecoach_page_change',
	'options_load_callback':function(){	
		//default: do nothing
	},
	//-----------------
	'custom_js':'',
	'custom_css':'',
	'targetElement':'body'
};

api_ecoach_DB_store('GlobalOptions', DefaultOptions ,'session');		//store global options

//-------------------------------------
//Set Global App Options
function api_set_global_options(app_options){
	var GlobalOptions = api_ecoach_DB_get('GlobalOptions');				//get current global options
	//------------------------------------------------------
	for(v in app_options){ GlobalOptions[v] = app_options[v]; }
	if(GlobalOptions.targetElementID != ''){ GlobalOptions.targetElement = '#'+GlobalOptions.targetElementID;	}
	//------------------------------------------------------
	api_ecoach_DB_store('GlobalOptions', GlobalOptions ,'session');		//update global options
}
 
//Handle Globap App Options response
function api_handle_global_options(responseData){
	if(responseData.status == 'success'){
		api_set_global_options(responseData.app_options); 					//update global options
		window[api_ecoach_DB_get('GlobalOptions').options_load_callback]();
	}else{
		console.log('Error: Could not Load App Options');
	}
	
}
//-------------------------------------
//-------------------------------------
// -- Execution --
//-------------------------------------
//SIGN IN
//options = {"user":"x", "pass":"x"};
function api_ecoach_signin(options, callbackFunction){
	var domain = GlobalDomain;
	var keyparam = "ecoachsignin=1";
	api_ecoach_generic_request(options, callbackFunction, domain, keyparam);
}
//SIGN UP
//options = {"fname":"x","lname":"x","email":"x","phone":"x","country":"x","sch":"x","user2":"x","pass1":"x","pass2":"x", "ref":"x"};
function api_ecoach_signup(options, callbackFunction){
	var domain = GlobalDomain;
	var keyparam = "ecoachsignup=1";
	api_ecoach_generic_request(options, callbackFunction, domain, keyparam);
}
//LOGOUT
function api_ecoach_logout(){
	api_ecoach_DB_clear('local');
	api_ecoach_DB_clear('session');
	window.location.reload(true);
}
//REFRESH
function api_ecoach_reload(){
	window.location.reload(true);
}

//-------------------------------------
// Splash
//-------------------------------------
/*function api_ecoach_splash_page(){
	var html = '';
	html+='<div id="body-div">';
	html+='	<div id="splash-div">';
	html+='		<span class="Centerer"></span>';
	html+='		<div class="Centered">';
	html+='			<div id="logo-div" class="splash-logo"></div>';
	html+='			<div id="loading-div">Loading application...</div>';
	html+='		</div>';
	html+='	</div>';
	html+='</div>';
	return html;
}


//-------------------------------------
// Forms
//-------------------------------------
/*function api_ecoach_signin_form(){
	var html = '';
	html+='<div id="menu">';
	//-------------------
	html+=' <div class="menu-item first app-logo">';
	html+='  <span class="app-logo"></span><span></span>';
	html+=' </div>';
	//-------------------
	html+=' <div class="menu-item" onclick="api_ecoach_page_change(\'/main.html\')">';
	html+='  <span class="menu-icon home-icon"></span><span>Home</span>';
	html+=' </div>';
	//-------------------
	html+=' <div class="menu-item" onclick="api_ecoach_page_change(\'/help.html\')">';
	html+='  <span class="menu-icon info-icon"></span><span>About</span>';
	html+=' </div>';
	//-------------------
	// TODO: delete
	
	html+=' <div class="menu-item">';
	html+='  <span class="menu-icon help-icon"></span><span>Help*</span>';
	html+=' </div>';
	//-------------------
	html+=' <div class="menu-item">';
	html+='  <span class="menu-icon search-icon"></span><span>Search*</span>';
	html+=' </div>';
	
	//-------------------
	html+=' <div class="menu-item" onclick="api_ecoach_reload()">';
	html+='  <span class="menu-icon reload-icon"></span><span>Reload</span>';
	html+=' </div>';
	//-------------------
	html+=' <div class="menu-item last" onclick="api_ecoach_logout()">';
	html+='  <span class="menu-icon logout-icon"></span><span>Logout</span>';
	html+=' </div>';
	//-------------------
	html+='</div>';
	html+='<div id="body" align="center">';
	html+=' <div id="header-div">';
	html+='  <div id="header-left"></div>';
	html+='  <div id="header-center">'+api_ecoach_DB_get('GlobalOptions').app_name.split(' ').join('&nbsp;')+'</div>';
	html+='  <div id="header-right" title="Menu" onclick="api_menu_toggle()"></div>';
	html+=' </div>';
	html+=' <div id="main-div">';
	html+='  <div class="line-top"></div>';
	html+='  <div id="main-div-inner">';
	//------------------------
	html+='   <div id="main-div-top">';
	html+='    <div id="main-div-top-inner">';
	html+='    Welcome';
	html+='    </div>';
	html+='   </div>';
	html+='   <div id="main-div-middle">';
	//------------------------
	html+='    <div id="authenticate-div" class="ec-box shadow-on">';
	//------------------------
	html+='    <div id="signin-page">';
	html+='     <div><h2>Existing User</h2></div>';
	html+='     <table style="width:250px">';
	html+='      <tr><td style="width:40%">username</td><td><input id="user" class="ec-field" name="user" type="text" placeholder="username"></td></tr>';
	html+='      <tr><td style="width:40%">password</td><td><input id="pass" class="ec-field" name="pass" type="password" placeholder="password"></td></tr>';
	html+='      <tr><td></td><td class="rgt"><button value="Sign-in" type="submit" onclick="ecoach_signin_submit()">Sign in</button></td></tr>';
	html+='     </table>';
	html+='     <div><h2>New User</h2></div>';
	html+='     <table style="width:250px">';
	html+='      <tr><td style="width:40%">&nbsp;</td><td class="rgt"><button value="Sign-in" type="submit" onclick="api_ecoach_page_change(\'login.html\')">Sign up</button></td></tr>';
	html+='     </table>';
	html+='    </div>';
	//------------------------
	html+='    <div id="signup-page">';
	html+='     <div><h2>Existing User</h2></div>';
	html+='     <table style="width:250px">';
	html+='      <tr><td></td><td class="rgt"><button value="Sign-in" type="submit" onclick="api_ecoach_page_change(\'login.html\')">Sign in</button></td></tr>';
	html+='     </table>';
	html+='     <div><h2>New User</h2></div>';
	html+='     <table style="width:250px">';
	html+='      <tr><td style="width:40%">first name</td><td><input id="fname" class="ec-field" name="fname" type="text" placeholder="first name"></td></tr>';
	html+='      <tr><td style="width:40%">last name</td><td><input id="lname" class="ec-field" name="lname" type="text" placeholder="last name"></td></tr>';
	html+='      <tr><td style="width:40%">email</td><td><input id="email" class="ec-field" name="email" type="text" placeholder="email"></td></tr>';
	html+='      <tr><td style="width:40%">phone</td><td><input id="phone" class="ec-field" name="phone" type="text" placeholder="phone"></td></tr>';
	html+='      <tr><td style="width:40%">country</td><td><input id="country" class="ec-field" name="country" type="text" placeholder="country"></td></tr>';
	html+='      <tr><td style="width:40%">school</td><td><input id="sch" class="ec-field" name="sch" type="text" placeholder="school"></td></tr>';
	html+='      <tr><td style="width:40%">username</td><td><input id="user2" class="ec-field" name="user2" type="text" placeholder="username"></td></tr>';
	html+='      <tr><td style="width:40%">password</td><td><input id="pass1" class="ec-field" name="pass1" type="password" placeholder="password"></td></tr>';
	html+='      <tr><td style="width:40%">password</td><td><input id="pass2" class="ec-field" name="pass2" type="password" placeholder="password (repeat)"></td></tr>';
	html+='      <tr><td style="width:40%">referrer</td><td><input id="ref" class="ec-field" name="ref" type="text" placeholder="referrer (OPTIONAL)"></td></tr>';
	html+='      <tr><td style="width:40%">&nbsp;</td><td class="rgt"><button value="Sign-in" type="submit" onclick="ecoach_signup_submit()">Sign up</button></td></tr>';
	html+='     </table>';
	html+='    </div>';
	//------------------------
	html+='    </div>';
	//------------------------
	html+='    <div id="status-area"></div>';
	//------------------------
	html+='   </div>';
	html+='   <div id="main-div-bottom">';
	html+='   </div>';
	
	html+='  </div>';
	html+=' </div>';
	html+=' <div id="footer-div">';
	html+='  <div class="line-top"></div>';
	html+='  <div id="footer-div-inner">Powered by E-Coach Solutions &copy; 2014</div>';
	html+=' </div>';
	html+='</div>';
	return html;
}
*/
//-------------------------------------
// Form Handlers
//-------------------------------------
function ecoach_signin_submit(){
	var options = {};
	options.user = $('#user').val();
	options.pass = $('#pass').val();
	//--------------------
	GlobalTempFormOptions = options;
	alert('login details ' + JSON.stringify(GlobalTempFormOptions));
	//--------------------
	$('#status-area').html('');
	GlobalTempHTML.maindivmiddle = $('#main-div-middle').html();
	$('#main-div-middle').html('<p>' + api_loaderANIMATION(4,'Signing in. Please wait') + '</p>');
	//--------------------
	api_ecoach_signin(options, api_ecoach_DB_get('GlobalOptions').signin_callback);
}

function ecoach_signup_submit(){
	var options = {};
	options.fname = $('#fname').val();
	options.lname = $('#lname').val();
	options.email = $('#email').val();
	options.phone = $('#phone').val();
	options.country = $('#country').val();
	options.sch = $('#sch').val();
	options.ref = $('#ref').val();
	options.user2 = $('#user2').val();
	options.pass1 = $('#pass1').val();
	options.pass2 = $('#pass2').val();
	//--------------------
	GlobalTempFormOptions = options;
	alert('signup details ' + JSON.stringify(GlobalTempFormOptions));
	//--------------------
	$('#status-area').html('');
	GlobalTempHTML.maindivmiddle = $('#main-div-middle').html();
	$('#main-div-middle').html('<p>' + api_loaderANIMATION(4,'Signing up. Please wait') + '</p>');
	//--------------------
	api_ecoach_signup(options, api_ecoach_DB_get('GlobalOptions').signin_callback);
}

function api_ecoach_authenticate_handler(responseData){
	var html;
	//success
	if(responseData.status == 'success'){
		html = '<p><span class="color-gr">' + responseData.msg + '</span></p>';
		$('#main-div-middle').html(html);
		setTimeout(function(){
			html = '<p><span>Hello Welcome back to E-Coach Solutions,<i>' + responseData.profile.fname + '</i></span></p>';			
			$('#main-div-middle').html(html);
		}, 2000);
	//error
	}else{
		html = '<p><span class="color-r">' + responseData.msg + '</span></p>';
		$('#main-div-middle').html(GlobalTempHTML.maindivmiddle);
		$('#status-area').html(html);
	}
}

//-------------------------------------
function api_ecoach_generic_request(options, callbackFunction, domain, keyparam){
	var requestData = api_serialize_options(options);
	var url = domain+"?"+keyparam+"&"+requestData+"&callback=?";
	jQuery.getJSON(url, 
	function(responseData) {	window[callbackFunction](responseData);	});
}
function api_serialize_options(options){
	var requestData = "";
	for(var v in options){ 	requestData+=v+"="+options[v]+"&";	}
	return requestData;
}
//-------------------------------------
function api_loaderANIMATION(type ,text){
	var html = '';
	//two-row bar
	if(type == 1){
		html+= (text =='')? '': '<h2 class="testname"><i>'+text+'...</i></h2>';
		html+= ' <img class="loadingIMG1" src="'+GlobalRoot+'img/misc/loader1.gif" />';
	//one-row circle
	}else if(type == 2){
		html+= '<div align="center">';
		html+= '<table class="loader-animation-table" ><tbody><tr>';
		html+= '<td><img class="loadingIMG2"  src="'+GlobalRoot+'img/misc/loader2.gif"/></td>';
		html+= (text =='')? '<td></td>': '<td><span class="testname"><b><i>&nbsp;&nbsp;'+text+'...</i></b></span></td>';
		html+= '</tr></tbody></table>';
		html+= '</div>';
	//one-row circle (dots)	
	}else if(type == 3){
		html+= '<div align="center">';
		html+= '<table class="loader-animation-table"><tbody><tr>';
		html+= '<td><img class="loadingIMG3" src="'+GlobalRoot+'img/misc/loader3.gif"/></td>';
		html+= (text =='')? '<td></td>': '<td><span class="testname"><b><i>&nbsp;&nbsp;'+text+'...</i></b></span></td>';
		html+= '</tr></tbody></table>';
		html+= '</div>';
	//one-row circle (dots II)	
	}else if(type == 4){
		html+= '<div align="center">';
		html+= '<table class="loader-animation-table"><tbody><tr>';
		html+= '<td><img class="loadingIMG4" src="./images/ajax-loader.gif"/></td>';
		html+= (text =='')? '<td></td>': '<td><span class="testname"><b><i>&nbsp;&nbsp;'+text+'...</i></b></span></td>';
		html+= '</tr></tbody></table>';
		html+= '</div>';
	//just text
	}else{
		html+=(text =='')? '': '<h2 class="testname"><i>'+text+'...</i></h2>';
	}
	return html;
}

function api_ecoach_page_change(page){
	var html = api_ecoach_DB_get('GlobalOptions').app_name;
	$('#header-center').html(html);
	
	if(page == 'signup-page'){
		$('#signin-page').hide();
		$('#signup-page').show()
	}else if(page == 'signin-page'){
		$('#signin-page').show();
		$('#signup-page').hide()
	}else{
		if(api_ecoach_DB_get('GlobalOptions').page_renderer != 'api_ecoach_page_change'){
			window[api_ecoach_DB_get('GlobalOptions').page_renderer](page);	
		}
	}
}

function api_ecoach_DB_store(name, data, type){
	//type: 'local'[persistent] or 'session' [temporary]
	var status = true;
	var dbOBJ = {};
	if(typeof(Storage)!=="undefined"){
		if(type == 'local'){
			dbOBJ = (typeof localStorage.ecoach != 'undefined')? jQuery.parseJSON( localStorage.ecoach ) : dbOBJ;
			dbOBJ[name] = data;
			localStorage.ecoach = JSON.stringify(dbOBJ);
		}else{
			dbOBJ = (typeof sessionStorage.ecoach != 'undefined')? jQuery.parseJSON( sessionStorage.ecoach ) : dbOBJ;
			dbOBJ[name] = data;
			sessionStorage.ecoach = JSON.stringify(dbOBJ);
		}
  	}else{
		status = false;
  	}
	return status;
}

function api_ecoach_DB_get(name, type){
	//type: 'local'[persistent] or 'session' [temporary]
	var dbOBJ = {};
	var data;
	if(typeof(Storage)!=="undefined"){
		if(type == 'local'){	
			dbOBJ = (typeof localStorage.ecoach != 'undefined')? jQuery.parseJSON( localStorage.ecoach ) : dbOBJ;
			if(typeof dbOBJ[name] != 'undefined'){
				return dbOBJ[name];
			}
		}else{	
			dbOBJ = (typeof sessionStorage.ecoach != 'undefined')? jQuery.parseJSON( sessionStorage.ecoach ) : dbOBJ;
			if(typeof dbOBJ[name] != 'undefined'){
				return dbOBJ[name];
			}
		}
  	}else{
		//alert('Sorry! No Web Storage support..');
  	}
}

function api_ecoach_DB_clear(type){
	//type: 'local'[persistent] or 'session' [temporary]
	if(type == 'local'){
		localStorage.removeItem('ecoach');
	}else {		
		sessionStorage.removeItem('ecoach');
	}
}

function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;
}

//------------------------------------
function padNumber(number, template){
	var str = "" + number;
	var pad = template;
	return pad.substring(0, pad.length - str.length) + str;
}

function api_css_fitToWidth(parentElement, element, items_per_row){
	console.log('parentElement: '+parentElement+' element:'+element+' items_per_row: '+items_per_row);
	var parentWIDTH = $(parentElement).width();
	var elementPADW = api_css_getPADW(element)+4;
	var elementWIDTH = Math.floor((parentWIDTH - elementPADW*items_per_row)/items_per_row);
	$(element).width(elementWIDTH);
	$(element).css({'float':'left'});
}

function api_css_fitToHeight(parentElement, element, items_per_column){
	var parentHEIGHT = $(parentElement).height();
	var elementPADH = api_css_getPADH(element);	
	var elementHEIGHT = Math.floor((parentHEIGHT - elementPADH*items_per_column)/items_per_column);
	$(element).height(elementHEIGHT);
	$(element).css({'max-height': elementHEIGHT.toString()+'px'});
}

function api_css_getPADW(element){
	var pad = parseInt($(element).css('padding-left'))
			+ parseInt($(element).css('padding-right'))
			+ parseInt($(element).css('border-left-width'))
			+ parseInt($(element).css('border-right-width'))
			+ parseInt($(element).css('margin-left'))
			+ parseInt($(element).css('margin-right'));
	return pad;
}

function api_css_getPADH(element){
	var pad = parseInt($(element).css('padding-top'))
			+ parseInt($(element).css('padding-bottom'))
			+ parseInt($(element).css('border-top-width'))
			+ parseInt($(element).css('border-bottom-width'))
			+ parseInt($(element).css('margin-top'))
			+ parseInt($(element).css('margin-bottom'));
	return pad;
}

//==============================================================================
function toProperCase(str){
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

function api_TEST(responseData){
	//alert(responseData.status);
	
	
	//--------------------
	$('#status-area').html('');
	//--------------------
	$('#status-area').html(responseData.status + ' : ' + responseData.msg);
	//alert(responseData.status + ' : ' + responseData.msg);
}

function api_ecoach_debug( debugString){
	$('#header-center').html(debugString);
}

