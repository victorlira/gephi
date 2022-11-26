package org.gephi.desktop.search;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.gephi.desktop.search.api.SearchController;
import org.gephi.desktop.search.api.SearchListener;
import org.gephi.desktop.search.api.SearchRequest;
import org.gephi.desktop.search.api.SearchResult;
import org.gephi.desktop.search.popup.ActionPopup;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 * @author mathieu.bastian
 */
public class SearchDialog extends javax.swing.JPanel implements SearchListener {

    private final SearchUIModel uiModel;
    private final ButtonGroup categoryGroup;

    public SearchDialog(SearchUIModel uiModel) {
        this.uiModel = uiModel;
        categoryGroup = new ButtonGroup();
        initComponents();
        setup();
    }

    protected void setup() {
        // Tabs
        allCategoriesButton.addActionListener(e -> {
            uiModel.setCategory(null);
            search();
        });
        allCategoriesButton.putClientProperty("JButton.buttonType", "square");
        allCategoriesButton.setSelected(true);
        categoryGroup.add(allCategoriesButton);
        uiModel.getCategories().forEach(category -> {
            JToggleButton toggleButton = new JToggleButton();
            toggleButton.setText(category.getDisplayName());
            toggleButton.setFocusable(false);
            toggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            toggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            toggleButton.putClientProperty("JButton.buttonType", "square");
            categoryToolbar.add(toggleButton);
            categoryGroup.add(toggleButton);
            toggleButton.addActionListener(e -> {
                uiModel.setCategory(category);
                search();
            });
            if (uiModel.category == category) {
                toggleButton.setSelected(true);
            }
        });

        // Results list
        resultsList.setCellRenderer(new ResultRenderer());
        resultsList.addMouseListener(new ActionPopup(resultsList));

        // Search
        searchField.setText(uiModel.query);
        searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            search();
        });

        // Search if query isn't empty
        if (!uiModel.query.isEmpty()) {
            search();
        }

        // Focus
        searchField.requestFocusInWindow();
    }

    protected void search() {
        String query = searchField.getText();
        uiModel.query = query;
        if (query != null && !query.trim().isEmpty()) {
            SearchRequest request = SearchRequest.builder().query(query.trim()).category(uiModel.category).build();
            SearchController searchController = Lookup.getDefault().lookup(SearchController.class);
            searchController.search(request, this);
        } else {
            resultsList.setModel(new ResultsListModel(Collections.emptyList()));
        }
    }

    @Override
    public void started(SearchRequest request) {

    }

    @Override
    public void cancelled() {

    }

    @Override
    public void finished(SearchRequest request, List<SearchResult> results) {
        SwingUtilities.invokeLater(() -> {
            resultsList.setModel(new ResultsListModel(results));
        });
    }

    private static class ResultRenderer implements ListCellRenderer<SearchResult> {

        protected final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        // Icons
        private final ImageIcon nodeIcon = ImageUtilities.loadImageIcon("DesktopSearch/node.svg", false);
        private final ImageIcon edgeIcon = ImageUtilities.loadImageIcon("DesktopSearch/edge.svg", false);

        @Override
        public Component getListCellRendererComponent(JList<? extends SearchResult> list, SearchResult value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            Object val = value.getResult();
            if (val instanceof Node) {
                renderer.setIcon(nodeIcon);
            } else if (val instanceof Edge) {
                renderer.setIcon(edgeIcon);
            }

            return renderer;
        }
    }

    private static class ResultsListModel extends AbstractListModel<SearchResult> {

        private final List<SearchResult> results;

        public ResultsListModel(List<SearchResult> results) {
            this.results = results;
        }

        @Override
        public int getSize() {
            return results.size();
        }

        @Override
        public SearchResult getElementAt(int index) {
            return results.get(index);
        }
    }

    @FunctionalInterface
    public interface SimpleDocumentListener extends DocumentListener {
        void update(DocumentEvent e);

        @Override
        default void insertUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        default void removeUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        default void changedUpdate(DocumentEvent e) {
            update(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        optionToolbar = new javax.swing.JToolBar();
        searchField = new javax.swing.JTextField();
        categoryToolbar = new javax.swing.JToolBar();
        allCategoriesButton = new javax.swing.JToggleButton();
        resultsList = new javax.swing.JList<>();

        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new java.awt.BorderLayout());

        optionToolbar.setRollover(true);
        topPanel.add(optionToolbar, java.awt.BorderLayout.EAST);
        topPanel.add(searchField, java.awt.BorderLayout.SOUTH);

        categoryToolbar.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(allCategoriesButton,
            org.openide.util.NbBundle.getMessage(SearchDialog.class,
                "SearchDialog.allCategoriesButton.text")); // NOI18N
        allCategoriesButton.setFocusable(false);
        allCategoriesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        allCategoriesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        categoryToolbar.add(allCategoriesButton);

        topPanel.add(categoryToolbar, java.awt.BorderLayout.CENTER);

        add(topPanel, java.awt.BorderLayout.NORTH);

        resultsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        add(resultsList, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton allCategoriesButton;
    private javax.swing.JToolBar categoryToolbar;
    private javax.swing.JToolBar optionToolbar;
    private javax.swing.JList<SearchResult> resultsList;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}