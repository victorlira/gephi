/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

Copyright 2011 Gephi Consortium. All rights reserved.

The contents of this file are subject to the terms of either the GNU
General Public License Version 3 only ("GPL") or the Common
Development and Distribution License("CDDL") (collectively, the
"License"). You may not use this file except in compliance with the
License. You can obtain a copy of the License at
http://gephi.org/about/legal/license-notice/
or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
specific language governing permissions and limitations under the
License.  When distributing the software, include this License Header
Notice in each file and include the License files at
/cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
License Header, with the fields enclosed by brackets [] replaced by
your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"

If you wish your version of this file to be governed by only the CDDL
or only the GPL Version 3, indicate your decision by adding
"[Contributor] elects to include this software in this distribution
under the [CDDL or GPL Version 3] license." If you do not indicate a
single choice of license, a recipient has the option to distribute
your version of this file under either the CDDL, the GPL Version 3 or
to extend the choice of license to its licensees as provided above.
However, if you add GPL Version 3 code and therefore, elected the GPL
Version 3 license, then the option applies only if the new code is
made subject to such option by the copyright holder.

Contributor(s):

Portions Copyrighted 2011 Gephi Consortium.
 */

package org.gephi.layout.plugin;

import junit.framework.TestCase;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.io.importer.GraphImporter;
import org.gephi.layout.plugin.noverlap.NoverlapLayout;
import org.gephi.layout.plugin.noverlap.NoverlapLayoutBuilder;
import org.gephi.layout.plugin.random.Random;
import org.gephi.layout.plugin.random.RandomLayout;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Mathieu Jacomy
 */
public class NoverlapTest extends TestCase {

    @Test
    public void test2nodesNoverlap() {

        GraphModel graphModel = GraphImporter.importGraph(DummyTest.class, "2nodes.gexf");

        // TODO: remove the two monitoring things below

        // Monitor node sizes
        Graph graph = graphModel.getGraphVisible();
        for (Node n : graph.getNodes()) {
            System.out.println("Node "+n.getId()+": Size "+ n.size());
        }

        // Monitor distance
        Node[] nodes = graph.getNodes().toArray();
        Node n1 = nodes[0];
        Node n2 = nodes[1];
        double d = Math.sqrt(Math.pow(n2.x()-n1.x(), 2) + Math.pow(n2.y()-n1.y(), 2));
        System.out.println("Distance: "+d);

        NoverlapLayoutBuilder layoutBuilder = new NoverlapLayoutBuilder();
        NoverlapLayout layout = new NoverlapLayout(layoutBuilder);
        layout.setGraphModel(graphModel);

        layout.initAlgo();
        layout.resetPropertiesValues();
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            layout.goAlgo();
            if (layout.isConverged()) {
                break;
            }
        }
        if (layout.isConverged()) {
            System.out.println("Noverlap has prevented all overlaps.");
        }
        layout.endAlgo();

    }

    @Test
    public void test10KnodesNoverlap() {

        GraphModel graphModel = GraphImporter.importGraph(DummyTest.class, "10K_randomlayout.gexf");

        NoverlapLayoutBuilder layoutBuilder = new NoverlapLayoutBuilder();
        NoverlapLayout layout = new NoverlapLayout(layoutBuilder);
        layout.setGraphModel(graphModel);

        layout.initAlgo();
        layout.resetPropertiesValues();
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            layout.goAlgo();
            if (layout.isConverged()) {
                break;
            }
        }
        if (layout.isConverged()) {
            System.out.println("Noverlap has prevented all overlaps.");
        }
        layout.endAlgo();

    }

    @Test
    public void test1MnodesNoverlap() {
        GraphModel graphModel = GraphGenerator.generateNullUndirectedGraph(1000000);
        System.out.println(graphModel.getGraph().getNodeCount() + " nodes; "+graphModel.getGraph().getEdgeCount()+" edges.");

        // Apply random layout
        RandomLayout randomLayout = new RandomLayout(new Random(), 10);
        randomLayout.setGraphModel(graphModel);
        randomLayout.initAlgo();
        randomLayout.goAlgo();
        randomLayout.endAlgo();

        // Noverlap
        NoverlapLayoutBuilder layoutBuilder = new NoverlapLayoutBuilder();
        NoverlapLayout layout = new NoverlapLayout(layoutBuilder);
        layout.setGraphModel(graphModel);

        layout.initAlgo();
        layout.resetPropertiesValues();
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            layout.goAlgo();
            if (layout.isConverged()) {
                break;
            }
        }
        if (layout.isConverged()) {
            System.out.println("Noverlap has prevented all overlaps.");
        }
        layout.endAlgo();

    }
}
