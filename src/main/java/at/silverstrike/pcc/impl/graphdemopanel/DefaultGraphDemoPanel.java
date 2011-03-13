/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.graphdemopanel;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.svggen.SVGGraphics2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import eu.livotov.tpt.TPTApplication;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;

class DefaultGraphDemoPanel extends Panel implements GraphDemoPanel {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGraphDemoPanel.class);

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final VerticalLayout layout = new VerticalLayout();
        final Embedded image = createSampleGraph();
        layout.addComponent(image);
        layout.setSizeFull();

        this.addComponent(layout);
    }

    private Embedded createSampleGraph() {
        Embedded imageComponent = null;
        
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            Element svgelem = document.createElement("svg");
            document.appendChild(svgelem);

            SVGGraphics2D graphic2d = new SVGGraphics2D(document);

            final Graph<String, String> graph = createGraph();
            final VisualizationImageServer<String, String> server =
                    createServer(graph);

            server.printAll(graphic2d);

            Element el = graphic2d.getRoot();
            el.setAttributeNS(null, "viewBox", "0 0 350 350");
            el.setAttributeNS(null, "style", "width:100%;height:100%;");

            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            Writer out = new OutputStreamWriter(bout, "UTF-8");
            graphic2d.stream(el, out);

            
            final JungResource source = new JungResource(bout);
            
            TPTApplication.getCurrentApplication().addResource(source);
            
            imageComponent = new Embedded("", source);

            imageComponent.setWidth(350, UNITS_PIXELS);
            imageComponent.setHeight(350, UNITS_PIXELS);
            imageComponent.setMimeType("image/svg+xml");
            addComponent(imageComponent);
        } catch (Exception exception) {
            LOGGER.error("", exception);
        }
        return imageComponent;
    }

    private VisualizationImageServer<String, String> createServer(
            Graph<String, String> graph) {
        Layout<String, String> layout = new FRLayout<String, String>(
                graph);
        layout.setSize(new Dimension(300, 300));
        VisualizationImageServer<String, String> vv =
                new VisualizationImageServer<String, String>(
                        layout, new Dimension(350, 350));
        return vv;
    }

    private Graph<String, String> createGraph() {
        final Graph<String, String> graph =
                new DirectedSparseMultigraph<String, String>();
        final String vertex1 = "IE";
        final String vertex2 = "P1";
        final String vertex3 = "P2";
        final String vertex4 = "P3";
        final String vertex5 = "FE";

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);

        graph.addEdge("1", vertex1, vertex2, EdgeType.DIRECTED);
        graph.addEdge("2", vertex2, vertex3, EdgeType.DIRECTED);
        graph.addEdge("3", vertex3, vertex5, EdgeType.DIRECTED);
        graph.addEdge("4", vertex1, vertex4, EdgeType.DIRECTED);
        graph.addEdge("5", vertex4, vertex5, EdgeType.DIRECTED);
        return graph;
    }

}
