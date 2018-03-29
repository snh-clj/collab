var fdg_go = function(graph) {
    /* Use JavaScript to reset the svg! Whee! */
    d3.select("#graph-container>svg")
        .remove();
    d3.select("#graph-container")
        .append("svg")
        .attr("id", "graph_fdg")
        .attr("viewBox", "-150 -60 300 120");

    var svg = d3.select("svg"),
    width = +svg.attr("width"),
    height = +svg.attr("height");

    var color = d3.scaleOrdinal(d3.schemeCategory20);

    var simulation = d3.forceSimulation()
        .force("link", d3.forceLink().id(function(d) { return d.id; }).distance(function(d) { return d.value; }))
        .force("charge", d3.forceManyBody())
        .force("center", d3.forceCenter(width / 2, height / 2));

    var graph_it = function(graph) {
        var link = svg.append("g")
            .attr("class", "links")
            .selectAll("line")
            .data(graph.links)
            .enter().append("line")
            .attr("stroke-width", 1);

        var node = svg.append("g")
            .attr("class", "nodes")
            .selectAll("circle")
            .data(graph.nodes)
            .enter().append("circle")
            .attr("r", 10)
            .attr("fill", function(d) { return color(d.group); })
            .call(d3.drag()
                  .on("start", dragstarted)
                  .on("drag", dragged)
                  .on("end", dragended));

        node.append("title")
            .text(function(d) { return d.id; });

        var label = svg.append("g")
            .attr("class", "nodes")
            .selectAll("text")
            .data(graph.nodes)
            .enter()
            .append("text")
            .text(function(d) { return d.id; })
/*            .attr("x", function(d) { return d.cx; })
            .attr("y", function(d) { return d.cy; }) */
            .attr("text-anchor" , "middle" )
            .attr("font-family", "sans-serif")
            .attr("font-size", "5px")
            .attr("fill", "red");

        simulation
            .nodes(graph.nodes)
            .on("tick", ticked);

        simulation.force("link")
            .links(graph.links);

        function ticked() {
            link
                .attr("x1", function(d) { return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });

            node
                .attr("cx", function(d) { return d.x; })
                .attr("cy", function(d) { return d.y; });

            label
                .attr("x", function(d) { return d.x; })
                .attr("y", function(d) { return d.y - 10; });
        }
    };
    graph_it(graph);

    function dragstarted(d) {
        if (!d3.event.active) simulation.alphaTarget(0.3).restart();
        d.fx = d.x;
        d.fy = d.y;
    }

    function dragged(d) {
        d.fx = d3.event.x;
        d.fy = d3.event.y;
    }

    function dragended(d) {
        if (!d3.event.active) simulation.alphaTarget(0);
        d.fx = null;
        d.fy = null;
    }
};
