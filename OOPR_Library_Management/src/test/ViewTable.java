package test;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewTable extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ViewTableDB genericModel;
    private JComboBox<String> tableSelector;
    private JPanel inputPanel;
    private List<String> columnNames;
    private List<JTextField> inputFields;

    public ViewTable() {
        genericModel = new ViewTableDB();

        setTitle("Generic Table Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));
        add(inputPanel, BorderLayout.SOUTH);

        tableSelector = new JComboBox<>();
        tableSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTable((String) tableSelector.getSelectedItem());
            }
        });
        add(tableSelector, BorderLayout.NORTH);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRow();
                repaint();
                revalidate();
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRow();
                repaint();
                revalidate();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRow();
                repaint();
                revalidate();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadTableNames();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    fillInputFields();
                }
            }
        });
    }

    private void loadTableNames() {
        try {
            List<String> tableNames = genericModel.getAllTableNames();
            for (String tableName : tableNames) {
                tableSelector.addItem(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading table names", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTable(String tableName) {
        try {
            columnNames = genericModel.getColumnNames(tableName);
            tableModel.setColumnIdentifiers(columnNames.toArray());
            List<List<Object>> data = genericModel.getTableData(tableName);
            tableModel.setRowCount(0);
            for (List<Object> row : data) {
                tableModel.addRow(row.toArray());
            }
            createInputFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createInputFields() {
        inputPanel.removeAll();
        inputFields = new ArrayList<>();
        for (String columnName : columnNames) {
            inputPanel.add(new JLabel(columnName + ":"));
            JTextField textField = new JTextField();
            inputFields.add(textField);
            inputPanel.add(textField);
        }
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void fillInputFields() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            for (int i = 0; i < inputFields.size(); i++) {
                Object value = table.getValueAt(selectedRow, i);
                if (value != null) {
                    inputFields.get(i).setText(value.toString());
                } else {
                    inputFields.get(i).setText("");
                }
            }
        }
    }

    private void addRow() {
        try {
            List<Object> values = new ArrayList<>();
            for (JTextField field : inputFields) {
                values.add(parseFieldValue(field.getText()));
            }
            genericModel.addRow((String) tableSelector.getSelectedItem(), values);
            loadTable((String) tableSelector.getSelectedItem());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to update", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            List<Object> values = new ArrayList<>();
            for (int i = 1; i < inputFields.size(); i++) { // Start from 1 to skip the primary key field
                values.add(parseFieldValue(inputFields.get(i).getText()));
            }
            String tableName = (String) tableSelector.getSelectedItem();
            String primaryKey = columnNames.get(0); // Assuming first column is primary key
            Object primaryKeyValue = tableModel.getValueAt(selectedRow, 0);
            genericModel.updateRow(tableName, values, primaryKey, primaryKeyValue);
            loadTable(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String tableName = (String) tableSelector.getSelectedItem();
            String primaryKey = columnNames.get(0); // Assuming first column is primary key
            Object primaryKeyValue = tableModel.getValueAt(selectedRow, 0);
            genericModel.deleteRow(tableName, primaryKey, primaryKeyValue);
            loadTable(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Object parseFieldValue(String text) {
        // Here we assume that all numbers are integers. Modify this as needed for other types.
        if (text.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return text; // If not a number, return as string
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewTable().setVisible(true);
            }
        });
    }
}


