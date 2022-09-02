/*
Copyright 2008-2011 Gephi
Authors : Mathieu Bastian
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

package org.gephi.desktop.timeline;

import javax.swing.SpinnerNumberModel;
import org.gephi.timeline.api.TimelineController;
import org.gephi.timeline.api.TimelineModel;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 * @author Mathieu Bastian
 */
public class PlaySettingsDialog extends javax.swing.JPanel {

    private TimelineModel model;
    private TimelineController controller;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox backwardCheckbox;
    private javax.swing.JSpinner delaySpinner;
    private org.jdesktop.swingx.JXHeader headerTitle;
    private javax.swing.JLabel labelDelay;
    private javax.swing.JLabel labelMode;
    private javax.swing.JLabel labelMs;
    private javax.swing.JLabel labelPerc;
    private javax.swing.JLabel labelSpeed;
    private javax.swing.JLabel labelStepSize;
    private javax.swing.ButtonGroup modeButtonGroup;
    private javax.swing.JRadioButton oneBoundRadio;
    private javax.swing.JSpinner stepSizeSpinner;
    private javax.swing.JRadioButton twoBoundsRadio;
    // End of variables declaration//GEN-END:variables

    public PlaySettingsDialog() {
        initComponents();
    }

    public void setup(TimelineModel model) {
        this.model = model;
        this.controller = Lookup.getDefault().lookup(TimelineController.class);

        int delay = model.getPlayDelay();
        SpinnerNumberModel delayModel = new SpinnerNumberModel(delay, 10, Integer.MAX_VALUE, 50);
        delaySpinner.setModel(delayModel);

        double step = model.getPlayStep() * 100;
        SpinnerNumberModel stepModel = new SpinnerNumberModel(Math.abs(step), 0.00001, 100, 1);
        stepSizeSpinner.setModel(stepModel);

        if (model.getPlayMode().equals(TimelineModel.PlayMode.ONE_BOUND)) {
            oneBoundRadio.setSelected(true);
        } else {
            twoBoundsRadio.setSelected(true);
        }

        if (step < 0) {
            backwardCheckbox.setSelected(true);
        }
    }

    public void unsetup() {
        double step = (Double) stepSizeSpinner.getValue() / 100.0;
        step = backwardCheckbox.isSelected() ? -step : step;
        controller.setPlayStep(step);

        int delay = (Integer) delaySpinner.getValue();
        controller.setPlaySpeed(delay);

        if (oneBoundRadio.isSelected()) {
            controller.setPlayMode(TimelineModel.PlayMode.ONE_BOUND);
        } else {
            controller.setPlayMode(TimelineModel.PlayMode.TWO_BOUNDS);
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modeButtonGroup = new javax.swing.ButtonGroup();
        headerTitle = new org.jdesktop.swingx.JXHeader();
        labelSpeed = new javax.swing.JLabel();
        delaySpinner = new javax.swing.JSpinner();
        labelDelay = new javax.swing.JLabel();
        labelMs = new javax.swing.JLabel();
        labelStepSize = new javax.swing.JLabel();
        stepSizeSpinner = new javax.swing.JSpinner();
        labelPerc = new javax.swing.JLabel();
        labelMode = new javax.swing.JLabel();
        oneBoundRadio = new javax.swing.JRadioButton();
        twoBoundsRadio = new javax.swing.JRadioButton();
        backwardCheckbox = new javax.swing.JCheckBox();

        headerTitle.setDescription(
            NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.headerTitle.description")); // NOI18N
        headerTitle.setIcon(ImageUtilities.loadImageIcon("org/gephi/desktop/timeline/resources/enabled.png", false)); // NOI18N
        headerTitle.setTitle(
            NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.headerTitle.title")); // NOI18N

        labelSpeed.setFont(labelSpeed.getFont().deriveFont(labelSpeed.getFont().getStyle() | java.awt.Font.BOLD));
        labelSpeed
            .setText(NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.labelSpeed.text")); // NOI18N

        labelDelay
            .setText(NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.labelDelay.text")); // NOI18N

        labelMs.setText(NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.labelMs.text")); // NOI18N

        labelStepSize.setText(
            NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.labelStepSize.text")); // NOI18N

        labelPerc
            .setText(NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.labelPerc.text")); // NOI18N

        labelMode.setFont(labelMode.getFont().deriveFont(labelMode.getFont().getStyle() | java.awt.Font.BOLD));
        labelMode
            .setText(NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.labelMode.text")); // NOI18N

        modeButtonGroup.add(oneBoundRadio);
        oneBoundRadio.setText(
            NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.oneBoundRadio.text")); // NOI18N

        modeButtonGroup.add(twoBoundsRadio);
        twoBoundsRadio.setText(
            NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.twoBoundsRadio.text")); // NOI18N

        backwardCheckbox.setText(
            NbBundle.getMessage(TimelineTopComponent.class, "PlaySettingsDialog.backwardCheckbox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(headerTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelMode)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(backwardCheckbox)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(oneBoundRadio)
                                    .addGap(18, 18, 18)
                                    .addComponent(twoBoundsRadio))))
                        .addComponent(labelSpeed)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelDelay)
                                .addComponent(labelStepSize))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(stepSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelMs)
                                .addComponent(labelPerc))))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(headerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelSpeed)
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMs, javax.swing.GroupLayout.Alignment.TRAILING,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelStepSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stepSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(labelMode)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oneBoundRadio)
                        .addComponent(twoBoundsRadio))
                    .addGap(18, 18, 18)
                    .addComponent(backwardCheckbox)
                    .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
}
