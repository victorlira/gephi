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

package org.gephi.ui.project;

import org.gephi.project.api.Project;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.ProjectInformation;
import org.gephi.project.api.ProjectMetaData;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.netbeans.validation.api.ui.swing.ValidationPanel;
import org.openide.util.Lookup;

/**
 * @author Mathieu Bastian
 */
public class ProjectPropertiesEditor extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorTextField;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JTextField keywordsTextField;
    private javax.swing.JLabel labelAuthor;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelFile;
    private javax.swing.JLabel labelKeywords;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form ProjectPropertiesEditor
     */
    public ProjectPropertiesEditor() {
        initComponents();
    }

    public static ValidationPanel createValidationPanel(ProjectPropertiesEditor innerPanel) {
        ValidationPanel validationPanel = new ValidationPanel();
        validationPanel.setInnerComponent(innerPanel);
        ValidationGroup group = validationPanel.getValidationGroup();

        //Make sure components have names
        innerPanel.nameTextField.setName(innerPanel.labelName.getText().replace(":", ""));

        group.add(innerPanel.nameTextField, StringValidators.REQUIRE_NON_EMPTY_STRING);

        return validationPanel;
    }

    public void load(Project project) {
        ProjectInformation info = project.getLookup().lookup(ProjectInformation.class);
        if (info != null) {
            nameTextField.setText(info.getName());
            if (info.getFile() != null) {
                fileLabel.setText(info.getFile().getName());
            }
        }

        ProjectMetaData metaData = project.getLookup().lookup(ProjectMetaData.class);
        if (metaData != null) {
            titleTextField.setText(metaData.getTitle());
            authorTextField.setText(metaData.getAuthor());
            keywordsTextField.setText(metaData.getKeywords());
            descriptionTextArea.setText(metaData.getDescription());
        }
    }

    public void save(Project project) {
        ProjectInformation info = project.getLookup().lookup(ProjectInformation.class);
        if (info != null) {
            if (!nameTextField.getText().isEmpty() && !nameTextField.getText().equals(info.getName())) {
                ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
                pc.renameProject(project, nameTextField.getText());
            }
        }
        ProjectMetaData metaData = project.getLookup().lookup(ProjectMetaData.class);
        if (metaData != null) {
            metaData.setTitle(titleTextField.getText());

            metaData.setAuthor(authorTextField.getText());
            metaData.setKeywords(keywordsTextField.getText());
            metaData.setDescription(descriptionTextArea.getText());
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

        descriptionPanel = new javax.swing.JPanel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        labelDescription = new javax.swing.JLabel();
        labelKeywords = new javax.swing.JLabel();
        keywordsTextField = new javax.swing.JTextField();
        authorTextField = new javax.swing.JTextField();
        labelAuthor = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        fileLabel = new javax.swing.JLabel();
        labelFile = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class,
                "ProjectPropertiesEditor.descriptionPanel.border.title"))); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(3);
        descriptionScrollPane.setViewportView(descriptionTextArea);

        labelDescription.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.labelDescription.text")); // NOI18N

        labelKeywords.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.labelKeywords.text")); // NOI18N

        keywordsTextField.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.keywordsTextField.text")); // NOI18N

        authorTextField.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.authorTextField.text")); // NOI18N

        labelAuthor.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.labelAuthor.text")); // NOI18N

        labelName.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.labelName.text")); // NOI18N

        nameTextField.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.nameTextField.text")); // NOI18N

        fileLabel.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.fileLabel.text")); // NOI18N

        labelFile.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.labelFile.text")); // NOI18N

        labelTitle.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.labelTitle.text")); // NOI18N

        titleTextField.setText(org.openide.util.NbBundle
            .getMessage(ProjectPropertiesEditor.class, "ProjectPropertiesEditor.titleTextField.text")); // NOI18N

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(descriptionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelTitle)
                        .addComponent(labelAuthor)
                        .addComponent(labelFile)
                        .addComponent(labelName)
                        .addComponent(labelKeywords)
                        .addComponent(labelDescription))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(descriptionScrollPane, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addComponent(fileLabel, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addComponent(keywordsTextField, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addComponent(authorTextField, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addComponent(titleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                    .addContainerGap())
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(descriptionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fileLabel)
                        .addComponent(labelFile))
                    .addGap(12, 12, 12)
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelName))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTitle)
                        .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAuthor)
                        .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(keywordsTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelKeywords))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelDescription)
                        .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(148, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
}
