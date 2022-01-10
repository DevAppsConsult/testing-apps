/* charting functions by Timothy Kotin using D3 library */

function AZAdrawBarChart(data, targetElement, chart_options){

  $(targetElement).html("");

  var targetHeight = $(targetElement).height();
  var targetWidth = $(targetElement).width();
     
  var margin = {top: 60, right: 20, bottom: 60, left: 60},
      width = targetWidth - margin.left - margin.right,
      height = targetHeight - margin.top - margin.bottom;

  var x = d3.scale.ordinal()
      .rangeRoundBands([0, width], .1);

  var y = d3.scale.linear()
      .range([height, 0]);

  var xAxis = d3.svg.axis()
      .scale(x)
      .orient("bottom");

  var yAxis = d3.svg.axis()
      .scale(y)
      .orient("left");

  var chart = d3.select(targetElement)
    .append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
    .append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
  chart.append("text")
      .attr("transform", "rotate(0)")
      .attr("y", - Math.floor((margin.top)/2))
      .attr("x", Math.floor((width)/2 ))
      .attr("dy", ".71em")
      .style("text-anchor", "middle")
      .style("font-size", "1.3em")
      .style("font-weight", "bold")
      .text(chart_options.title);
  //---------------------

  x.domain(data.map(function(d) { return d[chart_options.dataname]; }));
  y.domain([0, d3.max(data, function(d) { return d[chart_options.datavalue]; })]);

  chart.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)
    .append("text")
      .attr("transform", "rotate(0)")
      .attr("y", Math.floor(margin.bottom/2 ))
      .attr("x", Math.floor((width)/2 ))
      .attr("dy", ".71em")
      .style("text-anchor", "middle")
      .style("font-size", "1.1em")
      .text(chart_options.xlabel);

  chart.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", - Math.floor(margin.left/2 + 15 ))
      .attr("x", - Math.floor((height)/2 ))
      .attr("dy", ".71em")
      .style("text-anchor", "middle")
      .style("font-size", "1.1em")
      .text(chart_options.ylabel);


  chart.selectAll(".bar")
    .data(data)
  .enter().append("rect")
    .attr("class", "bar")
    .attr("x", function(d) { return x(d[chart_options.dataname]); })
    .attr("y", function(d) { return y(d[chart_options.datavalue]); })
    .attr("height", function(d) { return height - y(d[chart_options.datavalue]); })
    .attr("width", x.rangeBand());
}



/*
function AZAdrawBubbleChart(data, targetElement, chart_options){

  console.log(data);

  $(targetElement).html("");

  var targetHeight = $(targetElement).height();
  var targetWidth = $(targetElement).width();
     
  var margin = {top: 60, right: 20, bottom: 60, left: 60},
      width = targetWidth - margin.left - margin.right,
      height = targetHeight - margin.top - margin.bottom;

  var x = d3.scale.ordinal()
      .rangeRoundBands([0, width], .1);

  var y = d3.scale.linear()
      .range([height, 0]);

  var xAxis = d3.svg.axis()
      .scale(x)
      .orient("bottom");

  var yAxis = d3.svg.axis()
      .scale(y)
      .orient("left");

  var chart = d3.select(targetElement)
    .append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
    .append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
  chart.append("text")
      .attr("transform", "rotate(0)")
      .attr("y", - Math.floor((margin.top)/2))
      .attr("x", Math.floor((width)/2 ))
      .attr("dy", ".71em")
      .style("text-anchor", "middle")
      .style("font-size", "1.3em")
      .style("font-weight", "bold")
      .text(chart_options.title);
  //---------------------

  x.domain(data.map(function(d) { return d[chart_options.dataname]; }));
  y.domain([0, d3.max(data, function(d) { return d[chart_options.datavalue]; })]);

  chart.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)
    .append("text")
      .attr("transform", "rotate(0)")
      .attr("y", Math.floor(margin.bottom/2 ))
      .attr("x", Math.floor((width)/2 ))
      .attr("dy", ".71em")
      .style("text-anchor", "middle")
      .style("font-size", "1.1em")
      .text(chart_options.xlabel);

  chart.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", - Math.floor(margin.left/2 + 15 ))
      .attr("x", - Math.floor((height)/2 ))
      .attr("dy", ".71em")
      .style("text-anchor", "middle")
      .style("font-size", "1.1em")
      .text(chart_options.ylabel);


  chart.selectAll(".bar")
    .data(data)
  .enter().append("rect")
    .attr("class", "bar")
    .attr("x", function(d) { return x(d[chart_options.dataname]); })
    .attr("y", function(d) { return y(d[chart_options.datavalue]); })
    .attr("height", function(d) { return height - y(d[chart_options.datavalue]); })
    .attr("width", x.rangeBand());
}

*/


/* Derived from PIE chart D3 code on:  http://blog.stephenboak.com/2011/08/07/easy-as-a-pie.html */

function AZAdrawPieChart(data, targetElement, chart_options){
    PieChartupdate(data, targetElement, chart_options);
}

