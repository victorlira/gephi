/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gephi.layout.plugin.forceAtlas2.force;

import org.gephi.graph.api.Node;
import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2;
import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2LayoutData;

/**
 *
 * @author totetmatt
 */
public class NormalGravity extends AFA2Force implements IGravity {

    public NormalGravity(ForceAtlas2.ForceAtlas2Params params) {
        super(params);
    }

    @Override
    public void accept(Node n, Double g) {
         ForceAtlas2LayoutData nLayout = n.getLayoutData();

            // Get the distance
            double xDist = n.x();
            double yDist = n.y();
            double distance = (float) Math.sqrt(xDist * xDist + yDist * yDist);

            if (distance > 0) {
                // NB: factor = force / distance
                double factor = params.scalingRatio * nLayout.mass * g / distance;

                nLayout.dx -= xDist * factor;
                nLayout.dy -= yDist * factor;
            } 
    }
    
}
