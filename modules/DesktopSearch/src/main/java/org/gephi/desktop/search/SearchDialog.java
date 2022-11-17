package org.gephi.desktop.search;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.gephi.desktop.search.api.SearchController;
import org.gephi.desktop.search.api.SearchListener;
import org.gephi.desktop.search.api.SearchRequest;
import org.gephi.desktop.search.api.SearchResult;
import org.openide.util.Lookup;

/**
 * @author mathieu.bastian
 */
public class SearchDialog extends javax.swing.JPanel implements SearchListener {

    /**
     * Creates new form SearchDialog
     */
    public SearchDialog() {
        initComponents();

        setup();
    }

    protected void setup() {
        // Results list
        resultsList.setCellRenderer(new ResultRenderer());

        // Search
        searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            search();
        });

        // Focus
        searchField.requestFocusInWindow();
    }

    protected void search() {
        String query = searchField.getText();
        if (query != null && !query.trim().isEmpty()) {
            SearchRequest request = SearchRequest.builder().query(query.trim()).build();
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

        @Override
        public Component getListCellRendererComponent(JList<? extends SearchResult> list, SearchResult value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

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

        allCategoriesButton.setSelected(true);
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
