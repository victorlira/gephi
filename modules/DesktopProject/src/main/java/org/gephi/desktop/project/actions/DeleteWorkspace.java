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

package org.gephi.desktop.project.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import org.gephi.desktop.project.ProjectControllerUIImpl;
import org.gephi.project.api.Workspace;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@ActionID(id = "org.gephi.desktop.project.actions.DeleteWorkspace", category = "Workspace")
@ActionRegistration(displayName = "#CTL_DeleteWorkspace", lazy = false)
@ActionReference(path = "Menu/Workspace", position = 2600, separatorAfter = 2605)
public final class DeleteWorkspace extends AbstractAction {

    DeleteWorkspace() {
        super(NbBundle.getMessage(DeleteWorkspace.class, "CTL_DeleteWorkspace"),
            ImageUtilities.loadImageIcon("DesktopProject/deleteWorkspace.png", false));
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (isEnabled()) {
            String message =
                NbBundle.getMessage(DeleteWorkspace.class, "WorkspacePanel_closeWorkspace_Question");
            String title = NbBundle.getMessage(DeleteWorkspace.class, "WorkspacePanel_closeWorkspace_Title");
            NotifyDescriptor dd = new NotifyDescriptor(message, title,
                NotifyDescriptor.YES_NO_OPTION,
                NotifyDescriptor.QUESTION_MESSAGE, null, null);
            Object retType = DialogDisplayer.getDefault().notify(dd);
            if (retType == NotifyDescriptor.YES_OPTION) {

                ProjectControllerUIImpl cui = Lookup.getDefault().lookup(ProjectControllerUIImpl.class);
                if (ev.getSource() != null && ev.getSource() instanceof Workspace) {
                    Workspace workspace = (Workspace) ev.getSource();
                    cui.deleteWorkspace(workspace);
                } else {
                    cui.deleteWorkspace();
                }
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return Lookup.getDefault().lookup(ProjectControllerUIImpl.class).canDeleteWorkspace();
    }
}
