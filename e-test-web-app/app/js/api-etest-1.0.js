var GlobalRoot = "http://api.ecoachsolutions.com/";
//var GlobalRoot = "https://api.ecoachsolutions.com/";
//var GlobalRoot = "http://localhost:1234/ecoachnew/WEBSITE/api/";
//--------------------------------------------------------------
//JQuery
if ((typeof jQuery == 'undefined') || (typeof $ == 'undefined')) {
    ecoach_load_script(GlobalRoot + 'js/jquery-1.9.1.js', 'text/javascript');
}
//Add Math Support
app_options.math_js = 'http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML';

var ecoach_scripts = [ //E-Coach Common API JS
{
    'url': app_options.custom_css,
    'type': 'text/css'
}, //APP-specific CSS
{
    'url': 'css/api-ecoach.etest-1.0.css',
    'type': 'text/css'
}, //E-Coach eTest API CSS
{
    'url': 'css/api-ecoach.common-1.0.css',
    'type': 'text/css'
} //E-Coach Common API CSS
];

//Load resources
ecoach_load_resources();
//--------------------------------------------------------------
function ecoach_load_resources() {
    var current = ecoach_scripts.pop();
    var status = (current.url == '') ? true : ecoach_load_script(current.url, current.type);
    //console.log(status);
    if (status == true) {
        if (ecoach_scripts.length != 0) {
            ecoach_load_resources();
        }
    } else {
        ecoach_scripts.push(current);
        ecoach_load_resources();
    }
}

function ecoach_load_script(source_url, type) {
    var dr;
    if (type == 'text/javascript') {
        dr = document.createElement('script');
        dr.type = type;
        dr.src = source_url;
    } else if (type == 'text/css') {
        dr = document.createElement('link');
        dr.rel = 'stylesheet';
        dr.type = type;
        dr.href = source_url;
    } else {

    }
    //--------------
    (function (dr) {
        document.getElementsByTagName('head')[0].appendChild(dr);
    })(dr);
    //--------------
    return true;
}