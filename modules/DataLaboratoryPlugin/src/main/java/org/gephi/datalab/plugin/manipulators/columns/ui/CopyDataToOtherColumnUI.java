/*
 Copyright 2008-2010 Gephi
 Authors : Eduardo Ramos <eduramiba@gmail.com>
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

package org.gephi.datalab.plugin.manipulators.columns.ui;

import java.util.ArrayList;
import javax.swing.JPanel;
import org.gephi.datalab.api.AttributeColumnsController;
import org.gephi.datalab.plugin.manipulators.columns.CopyDataToOtherColumn;
import org.gephi.datalab.spi.DialogControls;
import org.gephi.datalab.spi.columns.AttributeColumnsManipulator;
import org.gephi.datalab.spi.columns.AttributeColumnsManipulatorUI;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Table;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 * UI for CopyDataToOtherColumn AttributeColumnsManipulator
 *
 * @author Eduardo Ramos
 */
public class CopyDataToOtherColumnUI extends javax.swing.JPanel implements AttributeColumnsManipulatorUI {

    CopyDataToOtherColumn manipulator;
    Column[] columns;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox columnsComboBox;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel sourceColumnLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form CopyDataToOtherColumnUI
     */
    public CopyDataToOtherColumnUI() {
        initComponents();
    }

    @Override
    public void setup(AttributeColumnsManipulator m, GraphModel graphModel, Table table, Column column,
                      DialogControls dialogControls) {
        this.manipulator = (CopyDataToOtherColumn) m;

        sourceColumnLabel.setText(NbBundle
            .getMessage(CopyDataToOtherColumnUI.class, "CopyDataToOtherColumnUI.sourceColumnLabel.text",
                column.getTitle()));

        AttributeColumnsController ac = Lookup.getDefault().lookup(AttributeColumnsController.class);

        ArrayList<Column> availableColumns = new ArrayList<>();

        for (Column c : table) {
            if (ac.canChangeColumnData(c) && c != column) {
                availableColumns.add(c);
                columnsComboBox.addItem(c.getTitle());
            }
        }

        columns = availableColumns.toArray(new Column[0]);
    }

    @Override
    public void unSetup() {
        if (columnsComboBox.getSelectedIndex() != -1) {
            manipulator.setTargetColumn(columns[columnsComboBox.getSelectedIndex()]);
        } else {
            manipulator.setTargetColumn(null);
        }
    }

    @Override
    public String getDisplayName() {
        return manipulator.getName();
    }

    @Override
    public JPanel getSettingsPanel() {
        return this;
    }

    @Override
    public boolean isModal() {
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descriptionLabel = new javax.swing.JLabel();
        columnsComboBox = new javax.swing.JComboBox();
        sourceColumnLabel = new javax.swing.JLabel();

        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionLabel.setText(org.openide.util.NbBundle
            .getMessage(CopyDataToOtherColumnUI.class, "CopyDataToOtherColumnUI.descriptionLabel.text")); // NOI18N

        sourceColumnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sourceColumnLabel.setText(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(columnsComboBox, 0, 192, Short.MAX_VALUE)
                        .addComponent(sourceColumnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sourceColumnLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(columnsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
}
