/*
API 
E-Coach eTest API JS file
version 1.0.0
*/

//-------------------------------------
var GlobalTempHTML = {};
var GlobalTempFormOptions;
var GlobalcallbackFunctions = [];
//-------------------------------------

window.onload = function (e) {
    if (api_ecoach_DB_get('GlobalOptions')) {
        var GlobalOptions = api_ecoach_DB_get('GlobalOptions'); //get current global options
        //------------------------------------------------------
        GlobalOptions.signin_callback = 'api_etest_authenticate_handler';
        GlobalOptions.signup_callback = 'api_etest_authenticate_handler';
        GlobalOptions.page_renderer = 'api_etest_page_change';
        GlobalOptions.options_load_callback = 'ecoach_etest_app_start';
        //------------------------------------------------------
        api_ecoach_DB_store('GlobalOptions', GlobalOptions, 'session'); //update global options		
        ecoach_etest_app_getoptions();
    } else {
        $(body).html('Could not load. Retrying...');
        setTimeout(function () {
            window.location.reload(true);
        }, 5000);
    }
}

function ecoach_etest_app_getoptions() {
    api_set_global_options(app_options);
    //===============================================
    //var html = api_ecoach_splash_page();
    $(api_ecoach_DB_get('GlobalOptions').targetElement).html(html);
    //===============================================

    var options = {
        API_key: app_options.API_key
    };
    var callbackFunction = 'api_handle_global_options';
    var domain = GlobalDomain;
    var keyparam = "etestgetappoptions=1";
    api_ecoach_generic_request(options, callbackFunction, domain, keyparam);
}
/*function ecoach_etest_app_start() {
    var signin_from = api_ecoach_signin_form();
    $(api_ecoach_DB_get('GlobalOptions').targetElement).html(signin_from);
    if (api_ecoach_DB_get('isauthenticated', 'local') == true) {

        if (typeof api_ecoach_DB_get('testsession') !== 'undefined') {
            //start test timer
            api_etest_starttimer();
            //show questions
            var QID = api_ecoach_DB_get('testsession').QID;
            var current_question = api_ecoach_DB_get('testsession').current_question;
            api_etest_showquestion(QID, current_question);
            if (api_ecoach_DB_get('testsession').pausestatus == true) {
                api_etest_pausetest();
            }

            setTimeout(function () {
                api_etest_PageResizer();
            }, 200);

        } else {
            api_ecoach_page_change('home-page');
        }

    } else {
        api_etest_PreparePage();
    }
} */

function api_etest_testinsession() {
    var status = false;
    if (typeof api_ecoach_DB_get('testsession') !== 'undefined' && api_ecoach_DB_get('testsession').status == 'started') {
        status = true;
    }
    return status;
}

function api_etest_testinreview() {
    var status = false;
    if (typeof api_ecoach_DB_get('testsession') !== 'undefined' && api_ecoach_DB_get('testsession').status == 'completed') {
        status = true;
    }
    return status;
}

function api_etest_pausetest() {
    var d = new Date();
    var testsession = api_ecoach_DB_get('testsession');
    //Paused (unpause)
    if (testsession.pausestatus == true) {
        testsession.pausestatus = false;
        testsession.pauseduration += d.getTime() - testsession.pausestarttime;
        //---------------------------------------
        $('#main-div-middle').html(GlobalTempHTML.maindivmiddle);
        $('#main-div-bottom').html(GlobalTempHTML.maindivbottom);
        //---------------------------------------
        //Not Paused (pause)
    } else {
        testsession.pausestatus = true;
        testsession.pausestarttime = d.getTime();
        //---------------------------------------
        GlobalTempHTML.maindivmiddle = $('#main-div-middle').html();
        GlobalTempHTML.maindivbottom = $('#main-div-bottom').html();

        //prompt
        var html = '';
        html += '<p></p>';
        html += '<p><span class="testname"><b><i>Paused</i></b></span></p>';
        $('#main-div-middle').html(html);

        var html = '';
        html += '<div id="ec-buttons">';
        html += '<div class="ec-button-click" onclick="api_etest_pausetest()">Resume</div>';
        html += '</div>';
        $('#main-div-bottom').html(html);
        //---------------------------------------
    }

    api_ecoach_DB_store('testsession', testsession);
    api_etest_starttimer();
    api_etest_PreparePage();
}

function api_etest_quittest(mode) {
    //Yes
    if (mode == 1) {
        api_etest_endtestsession();
        api_ecoach_page_change('login.html');

        //Quit?
    } else if (mode == 0) {
        GlobalTempHTML.maindivmiddle = $('#main-div-middle').html();
        GlobalTempHTML.maindivbottom = $('#main-div-bottom').html();

        //prompt	
        var html = '';
        html += '<p></p>';
        html += '<p><span class="testname"><b><i>Are you sure you want to quit?</i></b></span></p>';
        $('#main-div-middle').html(html);

        var html = '';
        html += '<div id="ec-buttons">';
        html += '<div class="ec-button-click" onclick="api_etest_quittest(-1)">No</div>'; // No
        html += '<div class="ec-button-click" onclick="api_etest_quittest(1)" >Yes</div>'; // Yes
        html += '</div>';
        $('#main-div-bottom').html(html);
        //No
    } else {
        $('#main-div-middle').html(GlobalTempHTML.maindivmiddle);
        $('#main-div-bottom').html(GlobalTempHTML.maindivbottom);
    }

    api_etest_PreparePage();
}

function api_etest_endtestsession() {
    api_ecoach_DB_store('testsession', undefined);
    /*
	//Online
	if(navigator.onLine == true){
		//...
	}
	*/
}

function api_etest_authenticate_handler(responseData) {
    var html;
    //success
    if (responseData.status == 'success') {
        html = '<p><span class="color-gr">' + responseData.msg + '</span></p>';
        $('#main-div-middle').html(html);
        setTimeout(function () {
            api_ecoach_DB_store('profile', responseData.profile, 'local');
            api_ecoach_DB_store('isauthenticated', true, 'local');

            var profile = api_ecoach_DB_get('profile', 'local');
            html = '';
            html += '<p><span><i>Hello Welcome back, <b>' + profile.fname + ' ' + profile.lname + '</b></i></span></p>';
            html += '<p>' + api_loaderANIMATION(2, 'Loading request') + '</p>';
            $('#main-div-middle').html(html);

            setTimeout(function () {
                api_ecoach_page_change('home-page');
            }, 2000);

        }, 1000);
        //error
    } else {
        html = '<p><span class="color-r">' + responseData.msg + '</span></p>';
        $('#main-div-middle').html(GlobalTempHTML.maindivmiddle);
        $('#status-area').html(html);
    }
}

function api_etest_page_change(page) {
    var html;
    var profile = api_ecoach_DB_get('profile', 'local');
    if (page == 'home-page') {
        //-----------------------
        api_etest_endtestsession();
        //-----------------------
        api_etest_gettestlist();
        //-----------------------
        html = '<span><i>Welcome <b>' + profile.fname + ' ' + profile.lname + '</b></i></span>';
        $('#main-div-top-inner').html(html);
        html = '<div class="ec-mid-page">';
        //-----------------------
        if (api_ecoach_DB_get('GlobalOptions').show_profile == 1) {
            html += ' <div class="ec-button-span" onclick="api_ecoach_page_change(\'profile-page\')">My&nbsp;Profile</div>';
        }
        //-----------------------
        if (api_ecoach_DB_get('GlobalOptions').app_type == 'instant_test') {
            html += ' <div class="ec-button-span" onclick="api_ecoach_page_change(\'start-test\')">Start Test</div>';
        }
        //-----------------------
        if (api_ecoach_DB_get('GlobalOptions').app_type == 'custom_app') {
            html += ' <div class="ec-button-span" onclick="api_ecoach_page_change(\'new-test\')">New&nbsp;Test</div>';
        }
        //-----------------------
        if (api_ecoach_DB_get('GlobalOptions').show_history == 1) {
            html += ' <div class="ec-button-span">Test&nbsp;History*</div>';
        }
        //-----------------------
        if (api_ecoach_DB_get('GlobalOptions').show_analytics == 1) {
            html += ' <div class="ec-button-span">Test&nbsp;Analytics*</div>';
        }
        //-----------------------
        if (api_ecoach_DB_get('profile', 'local').id == api_ecoach_DB_get('GlobalOptions').app_author) {
            html += ' <div class="ec-button-span">Customize&nbsp;App*</div>';
        }
        //-----------------------
        html += '</div>';
        $('#main-div-middle').html(html);
        html = '';
        $('#main-div-bottom').html(html);
    } else if (page == 'about-page') {
        //-----------------------
        api_etest_endtestsession();
        //-----------------------
        html = '<span><b>About</b></i></span>';
        $('#main-div-top-inner').html(html);
        html = '<div class="ec-mid-page lft">';
        html += '<div class="app-description">';
        html += api_ecoach_DB_get('GlobalOptions').app_description;
        html += '</div>';
        html += '</div>';
        $('#main-div-middle').html(html);
        html = '';
        $('#main-div-bottom').html(html);
    } else if (page == 'profile-page') {
        //-----------------------
        api_etest_endtestsession();
        //-----------------------
        html = '<span><b>My Profile</b></span>';
        $('#main-div-top-inner').html(html);
        html = '<div class="ec-mid-page">';
        html += ' <div class="ec-box shadow-on">';
        html += ' <table class="ec-table">';
        html += '  <tr><td style="width:25%">first name</td><td>' + profile.fname + '</td></tr>';
        html += '  <tr><td style="width:25%">last name</td><td>' + profile.lname + '</td></tr>';
        html += '  <tr><td style="width:25%">email</td><td>' + profile.email + '</td></tr>';
        html += '  <tr><td style="width:25%">gender</td><td>' + profile.gender + '</td></tr>';
        html += '  <tr><td style="width:25%">birthday</td><td>' + profile.dob_mm + ' ' + profile.dob_dd + ',' + profile.dob_yy + '</td></tr>';
        html += '  <tr><td style="width:25%">phone</td><td>' + profile.tel + '</td></tr>';
        html += '  <tr><td style="width:25%">location</td><td>' + profile.location + '</td></tr>';
        html += '  <tr><td style="width:25%">country</td><td>' + profile.country + '</td></tr>';
        html += '  <tr><td style="width:25%">institution</td><td>' + profile.sch + '</td></tr>';
        html += '  <tr><td style="width:25%">level</td><td>' + profile.level + '</td></tr>';
        html += '  <tr><td style="width:25%">username</td><td>' + profile.user + '</td></tr>';
        html += '  <tr><td style="width:25%">role</td><td>' + profile.role + '</td></tr>';
        html += ' </table>';
        html += ' </div>';
        html += '</div>';
        $('#main-div-middle').html(html);

        html = '<div id="ec-buttons">';
        html += '<div class="ec-button-click" onclick="api_ecoach_page_change(\'home-page\')">Main&nbsp;Menu</div>';
        html += '</div>';
        $('#main-div-bottom').html(html);
    } else if (page == 'start-test') {

        if (api_ecoach_DB_get('GlobalOptions').app_type == 'instant_test') {
            var currenttest = {
                test_ids: api_ecoach_DB_get('GlobalOptions').test_ids,
                test_type: api_ecoach_DB_get('GlobalOptions').test_type
            }
            api_ecoach_DB_store('currenttest', currenttest);
        }

        var html = '<b>Start Test</b>';
        $('#main-div-top-inner').html(html);

        var html = '';
        html += '<p></p>';
        html += '<p>' + api_loaderANIMATION(4, 'Preparing test') + '</p>';
        $('#main-div-middle').html(html);

        //load questions
        api_etest_getquestions();
    } else if (page == 'new-test') {

        var html = '';
        html += '<p></p>';
        html += '<p>' + api_loaderANIMATION(4, 'Preparing content') + '</p>';
        $('#main-div-middle').html(html);

        //show test list
        api_etest_showtestlist();

    } else if (page == 'test-results') {

        if (api_ecoach_DB_get('GlobalOptions').instant_results == 1) {
            var html = '';
            html += '<span class="ec-infotext">Review Test</span>';
            $('#header-center').html(html);



            var html = '<span>' + api_ecoach_DB_get('testsession').coursename.split(' ').join('&nbsp;') + '&nbsp;:&nbsp;<i><b>' + api_ecoach_DB_get('testsession').testname.split(' ').join('&nbsp;') + '</b></i></span>';
            $('#main-div-top-inner').html(html);

            var time_left = api_ecoach_DB_get('testsession').duration;
            var mins = Math.floor(time_left / (1000 * 60));
            var secs = Math.floor((time_left - mins * 60 * 1000) / (1000));

            var html = '';
            html += '<div id="etest-results">';
            //------------------------------
            html += ' <div class="etest-result shadow-on">';
            html += '  Summary';
            html += '  <div class="etest-result-entry">';
            html += '  <table id="etest-result-table" class="ec-table">';
            html += '   <tr><th>Test variable</th><th>Value</th></tr>';
            html += '   <tbody>';
            html += '   <tr><td>Test Score</td><td>' + api_ecoach_DB_get('testsession').testresult.correct + ' of ' + api_ecoach_DB_get('testQIDs').length + '</td></tr>';
            html += '   <tr><td>Time spent</td><td>' + mins + ' mins ' + secs + ' secs</td></tr>';
            html += '   </tbody>';
            html += '  </table>';
            html += '   <div id="etest-result-chart"></div>';
            html += '  </div>';
            html += ' </div>';
            //------------------------------			
            html += ' <div class="etest-result shadow-on">';
            html += '  Quick Feedback';
            html += '  <div class="etest-result-entry">';

            var qnum = 1;
            var testresponses = api_ecoach_DB_get('testsession').testresponses;
            for (QID in testresponses) {
                var choice = testresponses[QID].answer_choice;
                var fbclass = 'wrong';
                //unattempted
                if (choice == -1) {
                    fbclass = 'unattempted';
                    //correct
                } else if (testresponses[QID].score == 1) {
                    fbclass = 'correct';
                    //wrong
                } else {
                    fbclass = 'wrong';
                }
                html += '   <div class="etest-qfeedback ' + fbclass + '" onclick="api_etest_showquestion(' + QID + ',' + (qnum - 1) + ')">' + qnum + '</div>';
                qnum++;
            }
            html += '  </div>';
            html += ' </div>';
            //------------------------------

            html += '</div>';
            $('#main-div-middle').html(html);

            var reviewsolutions = 'api_etest_reviewsolutions()';
            var returnhome = 'api_ecoach_page_change(\'home-page\')';

            var html = '';
            html += '<div id="ec-buttons">';
            //Review Solutions
            if (api_ecoach_DB_get('GlobalOptions').instant_solutions == 1) {
                html += '<div class="ec-button-click" onclick="' + reviewsolutions + '">Review&nbsp;Solutions</div>';
            }
            //Return Home
            html += '<div class="ec-button-click" onclick="' + returnhome + '" >Home&nbsp;Menu</div>';
            html += '</div>';

            $('#main-div-bottom').html(html);

            api_etest_PreparePage();
            drawResultsChart();
            drawResultsChart();

        } else {
            var html = '';
            html += '<br/>';
            html += '<h1> Thanks for successfully completing the test</h1>';
            $('#main-div-middle').html(html);

            setTimeout(function () {
                api_ecoach_page_change('home-page');
            }, 3000);

        }

    } else {

    }
    api_etest_PreparePage();
}

//GET TEST LIST
function api_etest_gettestlist() {
    var options = {
        "API_key": api_ecoach_DB_get('GlobalOptions').API_key,
            "userID": api_ecoach_DB_get('profile', 'local').id
    };
    var callbackFunction = 'api_etest_storetestlist';
    var domain = GlobalDomain;
    var keyparam = "etestgettestlist=1";
    api_ecoach_generic_request(options, callbackFunction, domain, keyparam);
}

//STORE TEST LIST
function api_etest_storetestlist(responseData) {
    var testlist = responseData.testlist;
    var testtree = {};
    for (var i = 0; i < testlist.length; i++) {
        var packagename = testlist[i].packagename
        var new_package = {
            package_id: testlist[i].package_id,
            packagename: testlist[i].packagename,
            package_code: testlist[i].package_code,
            courses: {}
        };
        var is_new = (typeof testtree[packagename] === 'undefined') ? true : false;
        testtree[packagename] = (is_new) ? new_package : testtree[packagename];
        //----------------
        var coursename = testlist[i].coursename;
        var new_course = {
            course_id: testlist[i].course_id,
            courseID: testlist[i].courseID,
            coursename: testlist[i].coursename,
            coursedesc: testlist[i].coursedesc,
            types: {}
        };
        var is_new = (typeof testtree[packagename].courses[coursename] === 'undefined') ? true : false;
        testtree[packagename].courses[coursename] = (is_new) ? new_course : testtree[packagename].courses[coursename];
        //-------------
        var type = testlist[i].type;
        var test = {
            testlist_index: i
        };
        var is_new = (typeof testtree[packagename].courses[coursename].types[type] === 'undefined') ? true : false;
        testtree[packagename].courses[coursename].types[type] = (is_new) ? [] : testtree[packagename].courses[coursename].types[type];
        testtree[packagename].courses[coursename].types[type].push(test);

    }

    api_ecoach_DB_store('testlist', responseData.testlist, 'local');
    api_ecoach_DB_store('testtree', testtree, 'local');
    //api_ecoach_DB_store('testliststored', true);

    if (GlobalcallbackFunctions.length > 0) {
        var callback = GlobalcallbackFunctions.pop();
        callback();
    }
}

//SHOW TEST LIST
function api_etest_showtestlist() {
    if (typeof api_ecoach_DB_get('testtree', 'local') !== 'undefined') {
        api_etest_packagesHTML();
    } else {
        var callback = api_etest_showtestlist;
        GlobalcallbackFunctions.push(callback);
        api_etest_gettestlist();
    }
}

//SHOW PACKAGES HTML
function api_etest_packagesHTML() {
    var html = 'Available Packages : <b>Select One</b>'.split(' ').join('&nbsp;');
    $('#main-div-top-inner').html(html);

    var testtree = api_ecoach_DB_get('testtree', 'local');
    var html = '<div class="ec-mid-page">';
    for (var packagename in testtree) {
        html += '<div class="ec-button-span shadow" onclick="api_etest_coursesHTML(\'' + packagename + '\')">' + packagename.split(' ').join('&nbsp;') + '</div>';
    }
    html += '</div>';
    $('#main-div-middle').html(html);

    html = '<div id="ec-buttons">';
    html += '<div class="ec-button-click" onclick="api_ecoach_page_change(\'home-page\')">Main&nbsp;Menu</div>';
    html += '</div>';
    $('#main-div-bottom').html(html);

    api_etest_PreparePage();
}
//SHOW COURSES HTML
function api_etest_coursesHTML(packagename) {
    var currenttest = (typeof api_ecoach_DB_get('currenttest') == 'undefined') ? {} : api_ecoach_DB_get('currenttest');
    currenttest.packagename = packagename;
    currenttest.test_ids = '[]';
    api_ecoach_DB_store('currenttest', currenttest);

    var html = packagename + ' : <b>Select Course</b>'.split(' ').join('&nbsp;');
    $('#main-div-top-inner').html(html);

    var testtree = api_ecoach_DB_get('testtree', 'local');
    var html = '<div class="ec-mid-page">';
    for (var coursename in testtree[packagename].courses) {
        html += '<div class="hmenu-item shadow" onclick="api_etest_testtypesHTML(\'' + coursename + '\')">';
        html += ' <div class="hmenu-item-opt"><span class="ec-checkbox">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>';
        html += ' <div class="hmenu-item-text">';
        html += '  <p>' + coursename + '</p>';
        html += ' </div>';
        html += ' </div>';
    }
    html += '</div>';
    $('#main-div-middle').html(html);

    html = '<div id="ec-buttons">';
    html += '<div class="ec-button-click" onclick="api_etest_packagesHTML()">Go&nbsp;Back</div>';
    html += '</div>';
    $('#main-div-bottom').html(html);
    api_etest_PreparePage();
}
//SHOW TEST TYPES HTML
function api_etest_testtypesHTML(coursename) {
    var currenttest = api_ecoach_DB_get('currenttest');
    var packagename = currenttest.packagename;
    currenttest.coursename = coursename;
    api_ecoach_DB_store('currenttest', currenttest);

    var html = coursename + ' : <b>Select Test Type</b>'.split(' ').join('&nbsp;');
    $('#main-div-top-inner').html(html);

    var testtree = api_ecoach_DB_get('testtree', 'local');
    var html = '<div class="ec-mid-page">';

    for (var type in testtree[packagename].courses[coursename].types) {
        html += '<div class="hmenu-item shadow " onclick="api_etest_testlistHTML(\'' + type + '\')">';
        html += ' <div class="hmenu-item-opt"><span class="ec-checkbox">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>';
        html += ' <div class="hmenu-item-text">';
        html += '  <p>' + type + '</p>';
        html += ' </div>';
        html += '</div>';
    }

    html += '</div>';
    $('#main-div-middle').html(html);

    html = '<div id="ec-buttons">';
    html += '<div class="ec-button-click" onclick="api_etest_coursesHTML(\'' + packagename + '\')">Go&nbsp;Back</div>';
    html += '</div>';
    $('#main-div-bottom').html(html);

    api_etest_PreparePage();
}
//SHOW TEST LIST HTML
function api_etest_testlistHTML(type) {
    var currenttest = api_ecoach_DB_get('currenttest');
    var packagename = currenttest.packagename;
    var coursename = currenttest.coursename;
    currenttest.test_type = type;
    api_ecoach_DB_store('currenttest', currenttest);

    var html = coursename + ' &raquo; ' + type + ' : <b>Select Tests</b>'.split(' ').join('&nbsp;');
    $('#main-div-top-inner').html(html);

    var testlist = api_ecoach_DB_get('testlist', 'local');
    var testtree = api_ecoach_DB_get('testtree', 'local');

    var html = '<div class="ec-mid-page">';
    for (var i = 0; i < testtree[packagename].courses[coursename].types[type].length; i++) {
        var testlist_index = testtree[packagename].courses[coursename].types[type][i].testlist_index;
        var test = testlist[testlist_index];
        var status = (test.nquestions > 0) ? '' : 'inactive';
        var onclick = (test.nquestions > 0) ? 'onclick="api_etest_addtest(' + test.test_id + ')"' : '';
        var test_name = (test.type == 'EXAM') ? test.test_name : toProperCase(test.test_name);
        html += '<div id="test' + test.test_id + '" class="hmenu-item shadow ' + status + '" ' + onclick + '>';
        html += ' <div class="hmenu-item-opt"><span class="ec-checkbox">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>';
        html += ' <div class="hmenu-item-text">';
        html += '  <p>' + test_name + '</p>';
        html += ' </div>';
        html += '</div>';
    }
    html += '</div>';
    $('#main-div-middle').html(html);

    html = '<div id="ec-buttons">';
    html += '<div class="ec-button-click" onclick="api_etest_testtypesHTML(\'' + coursename + '\')">Go&nbsp;Back</div>';
    html += '<div class="ec-button-click" onclick="api_ecoach_page_change(\'start-test\')">Start&nbsp;Test</div>';
    html += '</div>';
    $('#main-div-bottom').html(html);

    api_etest_PreparePage();
}

function api_etest_addtest(test_id) {
    var currenttest = api_ecoach_DB_get('currenttest');
    var test_ids = jQuery.parseJSON(currenttest.test_ids);

    var test_index = $.inArray(test_id, test_ids);
    //present (remove)
    if (test_index > -1) {
        test_ids.splice(test_index, 1);
        $('#test' + test_id).removeClass('selected');

        //absent add
    } else {
        test_ids.push(test_id);
        $('#test' + test_id).addClass('selected');
    }

    currenttest.test_ids = JSON.stringify(test_ids);
    api_ecoach_DB_store('currenttest', currenttest);

}

//GET TEST QUESTIONS
function api_etest_getquestions() {
    var options = {
        "API_key": api_ecoach_DB_get('GlobalOptions').API_key,
            "test_ids": api_ecoach_DB_get('currenttest').test_ids,
            "test_type": api_ecoach_DB_get('currenttest').test_type,
            "userID": api_ecoach_DB_get('profile', 'local').id
    };
    var callbackFunction = 'api_etest_preparetest';
    var domain = GlobalDomain;
    var keyparam = "etestgetquestions=1";
    api_ecoach_generic_request(options, callbackFunction, domain, keyparam);

}

//PREPARE TEST
function api_etest_preparetest(responseData) {
    var QIDs = [];
    var testresponses = {};
    var testsession = {};

    for (QID in responseData.resources.questions) {
        QIDs.push(QID);
        testresponses[QID] = {
            'question_id': QID,
                'answer_choice': -1,
                'answer_id': 0,
                'time_taken': 0 //milliseconds
        };
    }

    //randomize questions
    QIDs = (api_ecoach_DB_get('GlobalOptions').randomize == 1) ? shuffleArray(QIDs) : QIDs;
    api_ecoach_DB_store('testQIDs', QIDs);
    api_ecoach_DB_store('testresponses', testresponses);
    api_ecoach_DB_store('testresources', responseData.resources);

    //log start times
    var d = new Date();
    testsession.starttime = d.getTime();
    testsession.startquestiontime = d.getTime();
    testsession.status = 'started';
    testsession.pauseduration = 0;
    testsession.pausestatus = false;

    testsession.coursename = api_ecoach_DB_get('testresources').coursename;
    testsession.testname = api_ecoach_DB_get('testresources').testname;
    testsession.testtype = api_ecoach_DB_get('testresources').testtype;
    api_ecoach_DB_store('testsession', testsession);

    //start test timer
    api_etest_starttimer();

    //show questions
    api_etest_showquestion(0, 0);
}

//START TIMER
function api_etest_starttimer() {

    if (api_ecoach_DB_get('GlobalOptions').timed == 1) {
        var num_questions = api_ecoach_DB_get('testQIDs').length;
        var time_per_question = api_ecoach_DB_get('GlobalOptions').time_per_question;

        var d = new Date();
        var time_left = (api_ecoach_DB_get('testsession').starttime + api_ecoach_DB_get('testsession').pauseduration + num_questions * time_per_question * 1000) - d.getTime();
        var mins = Math.floor(time_left / (1000 * 60));
        var secs = Math.floor((time_left - mins * 60 * 1000) / (1000));

        var html = '';
        var warn = (time_left < 5 * 60 * 1000) ? 'warning' : ''; //less than 5 mins warming 

        if (time_left > 0 && api_ecoach_DB_get('testsession').status == 'started') {
            html += '<span class="ec-timer ' + warn + '">' + padNumber(mins, "00") + ':' + padNumber(secs, "00") + ' s</span>';
            html += '<span class="ec-infotext"> time left</span>';
            $('#header-center').html(html);

            setTimeout(function () {
                if (typeof api_ecoach_DB_get('testsession') !== 'undefined' && api_ecoach_DB_get('testsession').pausestatus == false) {
                    api_etest_starttimer();
                }
            }, 1000);

            //timedout
        } else {
            html += '<span class="ec-timer ' + warn + '">' + padNumber(0, "00") + ':' + padNumber(0, "00") + ' s</span>';
            html += '<span class="ec-infotext"> timed out</span>';
            $('#header-center').html(html);

            setTimeout(function () {
                api_etest_completetest();
            }, 0000);
        }

    }
}

//SHOW QUESTION
function api_etest_showquestion(previous_QID, current_question) {
    var d = new Date();
    var testQIDs = api_ecoach_DB_get('testQIDs');
    var testresources = api_ecoach_DB_get('testresources');
    var testresponses = api_ecoach_DB_get('testresponses');
    var testsession = api_ecoach_DB_get('testsession');
    var coursename = testresources.coursename;
    var testname = testresources.testname;
    var num_questions = api_ecoach_DB_get('testQIDs').length;
    var show_feedback = (api_ecoach_DB_get('GlobalOptions').instant_solutions == 1) ? true : false;
    //------------------------------------
    if (current_question < 0) {
        return;
    }
    if (current_question >= num_questions) {
        api_etest_completetest();
        return;
    }
    //------------------------------------
    var QID = testQIDs[current_question];
    var question = testresources.questions[QID];
    //---------------------------------------------------------------
    testsession.current_question = current_question;
    testsession.QID = QID;
    api_ecoach_DB_store('testsession', testsession);
    //-----------------------------------
    //capture time (of previous question)
    if (previous_QID !== 0) {
        api_etest_storetime(previous_QID);
    }
    //---------------------------------------------------------------
    var html = '<span>' + coursename.split(' ').join('&nbsp;') + '&nbsp;:&nbsp;<i><b>' + testname.split(' ').join('&nbsp;') + '</b></i></span>';
    $('#main-div-top-inner').html(html);
    html = '<div id="etest-test-div" >';
    html = ' <div id="etest-question" class="shadow">';

    html += '  <div id="etest-qinstruction" >';
    html += '  <span class="bolder">Question ' + (current_question + 1).toString() + ' of  ' + num_questions.toString() + ' :</span> ' + question.instructions;
    html += '  </div>';
    html += '  <div id="etest-qtext" >' + question.text + '</div>';
    html += '  <div id="etest-qresource" >' + question.resource + '</div>';
    html += ' </div>';

    var solution = '';
    for (var i = 0; i < question.answers.length; i++) {
        if (question.answers[i].value == 1) {
            solution = question.answers[i].solution;
        }
    }

    if (api_ecoach_DB_get('testsession').status == 'completed' && show_feedback && solution != '') {
        //-----------------
        html += ' <div id="etest-qsolution" ><span class="bolder">Solution</span><br/>';
        html += solution;
        html += ' </div>';
        //-----------------
    }

    html += ' <div id="etest-qoptions" >';
    var choices = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    for (var i = 0; i < question.answers.length; i++) {
        var response = testresponses[QID];
        var feedback = (response.answer_choice == -1) ? '' : 'unselected';
        feedback = (response.answer_choice == i) ? 'selected' : feedback;


        if (api_ecoach_DB_get('testsession').status == 'completed' && show_feedback) {
            //unselected
            feedback = 'unselected';
            //selected option
            if (testresponses[QID].answer_choice == i) {
                feedback = 'wrong';
            }
            //correct
            if (question.answers[i].value == 1) {
                feedback = 'correct';
            }
        }

        var onclick = 'api_etest_storeresponse(' + question.qid + ',' + question.answers[i].id + ',' + i + ')';
        html += '  <div class="etest-qoption shadow ' + feedback + '" onclick="' + onclick + '">';
        html += '   <div class="etest-qoption-opt">' + choices[i] + '</div>';
        html += '   <div class="etest-qoption-text">' + question.answers[i].text + '</div>';
        html += '  </div>';
    }

    html += ' </div>';
    html += '</div>';

    $('#main-div-middle').html(html);

    var html = '';
    html += '<div id="etest-qprogress">';
    for (var i = 0; i < testQIDs.length; i++) {
        var iQID = testQIDs[i];
        var response = testresponses[iQID];
        var onhover = 'title="Question ' + (i + 1).toString() + '"';
        var gotoquestion = '';

        //Back
        if (api_ecoach_DB_get('GlobalOptions').back_navigation == 1) {
            gotoquestion = 'onclick="api_etest_showquestion(' + QID.toString() + ',' + i.toString() + ')"';
        }

        //current
        if (iQID == QID) {
            html += '<span class="ec-progress current" ' + onhover + ' ' + gotoquestion + '></span>';
            //unattempted
        } else if (response.answer_choice == -1) {
            html += '<span class="ec-progress unattempted" ' + onhover + ' ' + gotoquestion + '></span>';
            //attempted
        } else {
            html += '<span class="ec-progress attempted" ' + onhover + ' ' + gotoquestion + '></span>';
        }
    }
    html += '</div>';

    var onback = 'api_etest_showquestion(' + QID.toString() + ',' + (current_question - 1).toString() + ')';
    var onnext = 'api_etest_showquestion(' + QID.toString() + ',' + (current_question + 1).toString() + ')';
    var onpause = 'api_etest_pausetest()';
    var onquit = 'api_etest_quittest(0)';
    var oncomplete = 'api_etest_completetest()';

    html += '<div id="ec-buttons">';
    //Back
    if (api_ecoach_DB_get('GlobalOptions').back_navigation == 1 && current_question > 0) {
        html += '<div class="ec-button-click" onclick="' + onback + '">&laquo;&nbsp;Back</div>';
    }
    //Pause
    if (api_ecoach_DB_get('GlobalOptions').pause == 1 && api_etest_testinsession()) {
        html += '<div class="ec-button-click" onclick="' + onpause + '" >Pause</div>';
    }
    //Quit
    if (api_ecoach_DB_get('GlobalOptions').quit == 1) {
        html += '<div class="ec-button-click" onclick="' + onquit + '" >Quit</div>';
    }
    //Next / Complete
    if (current_question < num_questions - 1) {
        html += '<div class="ec-button-click" onclick="' + onnext + '">Next&nbsp;&raquo;</div>';
        if (api_ecoach_DB_get('testsession').status == 'completed') {
            html += '<div class="ec-button-click" onclick="' + oncomplete + '">Results</div>';
        }

    } else {
        html += '<div class="ec-button-click" onclick="' + oncomplete + '">Complete</div>';
    }
    html += '</div>';

    $('#main-div-bottom').html(html);
    //---------------------------------------------------------------
    api_etest_PreparePage();
}

function api_etest_storetime(previous_QID) {

    if (api_etest_testinsession()) {
        var d = new Date();
        var testresponses = api_ecoach_DB_get('testresponses');
        var testsession = api_ecoach_DB_get('testsession');
        //------------------
        //store time of previous question
        testresponses[previous_QID].time_taken += d.getTime() - testsession.startquestiontime;
        //------------------
        //start recording time of current question
        testsession.startquestiontime = d.getTime();

        //update
        api_ecoach_DB_store('testsession', testsession);
        api_ecoach_DB_store('testresponses', testresponses);
    }
}

function api_etest_storeresponse(QID, answer_id, answer_choice) {

    if (api_etest_testinsession()) {
        var testresponses = api_ecoach_DB_get('testresponses');
        //------------------
        testresponses[QID].question_id = QID;
        testresponses[QID].answer_id = answer_id;
        testresponses[QID].answer_choice = answer_choice;
        //------------------	
        api_ecoach_DB_store('testresponses', testresponses);
    }
}

function api_etest_completetest() {

    if (api_etest_testinsession()) {
        //store current question time
        var previous_QID = api_ecoach_DB_get('testsession').QID;
        api_etest_storetime(previous_QID);

        //store testsession time and mark as completed
        var d = new Date();
        var testsession = api_ecoach_DB_get('testsession');
        testsession.stoptime = d.getTime();
        testsession.duration = (testsession.stoptime - testsession.starttime - testsession.pauseduration); //milliseconds
        testsession.status = 'completed';
        api_ecoach_DB_store('testsession', testsession);

        //grade testsession
        api_etest_gradetest();

        //send test responses to server
        api_etest_sendtestresponses();

    } else {
        //display results (and feedback)
        api_ecoach_page_change('test-results');
    }
}

function api_etest_sendtestresponses() {
    var html = '';
    html += '<p></p>';
    html += '<p>' + api_loaderANIMATION(3, 'Preparing results') + '</p>';
    $('#main-div-middle').html(html);
    //-----------------	
    var d = new Date();
    var testsession = api_ecoach_DB_get('testsession');
    testsession.date_time = d.getFullYear() + '-' + padNumber(d.getMonth() + 1, "00") + '-' + padNumber(d.getDate(), "00");
    testsession.date_time += ' ' + padNumber(d.getHours(), "00") + ':' + padNumber(d.getMinutes(), "00") + ':' + padNumber(d.getSeconds(), "00");
    testsession.datetime = d.getFullYear() + padNumber(d.getMonth() + 1, "00") + padNumber(d.getDate(), "00");
    testsession.datetime += padNumber(d.getHours(), "00") + padNumber(d.getMinutes(), "00") + padNumber(d.getSeconds(), "00");
    api_ecoach_DB_store('testsession', testsession);

    var options = {
        API_key: app_options.API_key,
        userid: api_ecoach_DB_get('profile', 'local').id,
        testsession: JSON.stringify(testsession)
    };
    var callbackFunction = 'show_results';
    var domain = GlobalDomain;
    var keyparam = "etesttestresponses=1";
    api_ecoach_generic_request(options, callbackFunction, domain, keyparam);
}

function show_results() {
    api_ecoach_page_change('test-results');
}

function api_etest_reviewsolutions() {
    var testsession = api_ecoach_DB_get('testsession');
    var current_question = 0;
    
    var QID = api_ecoach_DB_get('testQIDs')[0];
    //-----------------------------------
    testsession.current_question = current_question;
    testsession.QID = QID;
    api_ecoach_DB_store('testsession', testsession);
    //-----------------------------------
    api_etest_showquestion(QID, current_question);
}

function api_etest_gradetest() {
    var testresources = api_ecoach_DB_get('testresources');
    var testresponses = api_ecoach_DB_get('testresponses');
    var testsession = api_ecoach_DB_get('testsession');

    var correct = 0;
    var wrong = 0;
    var unattempted = 0;

    for (QID in testresponses) {
        var choice = testresponses[QID].answer_choice;
        //unattempted
        if (choice == -1) {
            unattempted++;
            testresponses[QID].score = 0;
            //correct
        } else if (testresources.questions[QID].answers[choice].value == 1) {
            correct++;
            testresponses[QID].score = 1;
            //wrong
        } else {
            wrong++;
            testresponses[QID].score = 0;
        }
    }

    testsession.testresult = {
        correct: correct,
        wrong: wrong,
        unattempted: unattempted
    };

    testsession.testresponses = testresponses;
    api_ecoach_DB_store('testsession', testsession);
}

function api_etest_PreparePage() {
    /* Detect Window Resize*/
    $(window).resize(function () {
        api_etest_PageResizer();
        drawResultsChart();
    });

    if (api_etest_testinsession()) {
        $('.etest-qoption').click(function () {
            $('.etest-qoption').addClass('unselected');
            $('.etest-qoption').removeClass('selected');
            $(this).removeClass('unselected');
            $(this).addClass('selected');
        });
    }

    $('#menu').hide();

    //Typeset MathJax content
    if (typeof MathJax === "undefined") {

    } else {
        MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
    }

    api_etest_PageResizer();
}

function drawResultsChart() {

    if (api_etest_testinreview()) {
        var data = [{
            'field': 'correct',
            'value': api_ecoach_DB_get('testsession').testresult.correct
        }, {
            'field': 'unattempted',
            'value': api_ecoach_DB_get('testsession').testresult.unattempted
        }, {
            'field': 'wrong',
            'value': api_ecoach_DB_get('testsession').testresult.wrong
        }];

        var chart_options = {
            title: "Test Results",
            dataname: "field",
            datavalue: "value",
            total_units: "questions"
        };

        AZAdrawPieChart(data, '#etest-result-chart', chart_options);
    }

}

function api_menu_toggle() {
    if (api_ecoach_DB_get('isauthenticated', 'local') == true) {
        $('#menu').toggle();
    }
}

function api_etest_PageResizer() {
    //----------------
    var deviceWIDTH = $(window).width(); //pixels
    var deviceHEIGHT = $(window).height(); //pixels
    var LandscapeVIEW = (deviceHEIGHT > deviceWIDTH) ? false : true;
    //----------------
    var headerdivHEIGHT = $('#header-div').height();
    var headerdivWIDTH = $('#header-div').width();
    var footerdivHEIGHT = $('#footer-div').height();
    var footerdivWIDTH = $('#footer-div').width();
    var headercenterWIDTH = headerdivWIDTH - $('#header-left').width() - $('#header-right').width() - 1;
    $('#header-center').width(headercenterWIDTH);
    $('#header-center').css({
        'max-width': headercenterWIDTH.toString() + 'px'
    });
    //----------------
    var maindivHEIGHT = deviceHEIGHT - headerdivHEIGHT - footerdivHEIGHT;
    $('#main-div').height(maindivHEIGHT);
    //----------------
    var maindivinnerWIDTH = $('#main-div-inner').width();
    var maindivinnerPAD = parseInt($('#main-div-inner').css('padding-left')) + parseInt($('#main-div-inner').css('padding-right'));
    var maindivinnerHEIGHT = maindivHEIGHT - $('.line-top').height() - parseInt($('#main-div-inner').css('padding-top')) - parseInt($('#main-div-inner').css('padding-bottom'));
    $('#main-div-inner').height(maindivinnerHEIGHT);
    //----------------
    var maindivtopHEIGHT = $('#main-div-top').height();
    var maindivbottomHEIGHT = $('#main-div-bottom').height();
    var maindivmiddleHEIGHT = maindivinnerHEIGHT - maindivtopHEIGHT - maindivbottomHEIGHT;
    $('#main-div-middle').height(maindivmiddleHEIGHT);
    $('#main-div-middle').css({
        'max-height': maindivmiddleHEIGHT.toString() + 'px'
    });
    //----------------
    $('#main-div-top-inner').width(maindivinnerWIDTH);
    $('#main-div-top-inner').css({
        'max-width': maindivinnerWIDTH.toString() + 'px'
    });
    //----------------
    $('#menu').height(maindivHEIGHT);
    $('#menu').css({
        'top': headerdivHEIGHT.toString() + 'px'
    });
    //----------------
    if ($('.ec-mid-page').length != 0) {
        var ecmidpageWIDTH = maindivinnerWIDTH - maindivinnerPAD;
        $('.ec-mid-page').width(ecmidpageWIDTH);
        //----------------
        var ecbuttonspanMINWIDTH = 100;
        if ($('.ec-button-span').length != 0) {
            var ecbuttonspanPAD = api_css_getPADW('.ec-button-span');
            var ecbuttonsN = Math.floor(ecmidpageWIDTH / (ecbuttonspanMINWIDTH + ecbuttonspanPAD));
            ecbuttonsN = (ecbuttonsN > $('.ec-button-span').length) ? $('.ec-button-span').length : ecbuttonsN;
            var ecbuttonspanWIDTH = Math.floor((ecmidpageWIDTH - ecbuttonsN * ecbuttonspanPAD) / ecbuttonsN);
            $('.ec-button-span').width(ecbuttonspanWIDTH);
            //----------------
            var ecbuttonspanPAD_TB = (ecbuttonsN == 1) ? 15 : 30;
            $('.ec-button-span').css({
                'padding-top': ecbuttonspanPAD_TB.toString() + 'px'
            });
            $('.ec-button-span').css({
                'padding-bottom': ecbuttonspanPAD_TB.toString() + 'px'
            });
            //----------------
        }
    }
    //----------------
    if ($('.etest-qoption').length != 0) {
        var etestoptionWIDTH = $('.etest-qoption').width();
        var etestoptionOPTWIDTH = $('.etest-qoption-opt').width();
        var etestoptionOPTPAD = api_css_getPADW('.etest-qoption-opt');
        var etestoptionTXTPAD = api_css_getPADW('.etest-qoption-text');
        var etestoptionTXTWIDTH = etestoptionWIDTH - etestoptionOPTWIDTH - etestoptionOPTPAD - etestoptionTXTPAD;
        $('.etest-qoption-text').width(etestoptionTXTWIDTH);
    }
    //----------------
    if ($('.hmenu-item').length != 0) {
        var etestoptionWIDTH = $('.hmenu-item').width();
        var etestoptionOPTWIDTH = $('.hmenu-item-opt').width();
        var etestoptionOPTPAD = api_css_getPADW('.hmenu-item-opt');
        var etestoptionTXTPAD = api_css_getPADW('.hmenu-item-text');
        var etestoptionTXTWIDTH = etestoptionWIDTH - etestoptionOPTWIDTH - etestoptionOPTPAD - etestoptionTXTPAD;
        $('.hmenu-item-text').width(etestoptionTXTWIDTH);
    }
    //----------------
    if ($('#etest-qprogress').length != 0) {
        var etestqprogressWIDTH = $('#etest-qprogress').width();
        var num_questions = api_ecoach_DB_get('testQIDs').length;
        $('.ec-progress').width(Math.floor(etestqprogressWIDTH / num_questions));
    }
    //----------------
    var ecbuttonsMAXWIDTH = $('.ec-button-click').length * 250;
    $('#ec-buttons').css({
        'max-width': ecbuttonsMAXWIDTH.toString() + 'px'
    });

    api_css_fitToWidth('#ec-buttons', '.ec-button-click', $('.ec-button-click').length);
    api_css_fitToHeight('#main-div-middle', '#etest-results', 1);

    if (LandscapeVIEW) {
        api_css_fitToWidth('#etest-results', '.etest-result', 2);
        api_css_fitToHeight('#etest-results', '.etest-result', 1);
    } else {
        api_css_fitToWidth('#etest-results', '.etest-result', 1);
        api_css_fitToHeight('#etest-results', '.etest-result', 1);
        //$('.etest-result').css({'height': 'auto'});
    }
    //----------------
    api_css_fitToWidth('.etest-result', '#etest-result-chart', 1);
    $('#etest-result-chart').height($('.etest-result').height() - api_css_getPADH('.etest-result') - $('#etest-result-table').height());
    //----------------
    var qfeedbackMINWIDTH = 70;
    var qfeedbackPAD = api_css_getPADW('.etest-qfeedback');
    var qfeedbackN = Math.floor($('.etest-result').width() / (qfeedbackMINWIDTH + qfeedbackPAD));
    qfeedbackN = (qfeedbackN > $('.etest-qfeedback').length) ? $('.etest-qfeedback').length : qfeedbackN;
    api_css_fitToWidth('.etest-result', '.etest-qfeedback', qfeedbackN);
    //----------------

}