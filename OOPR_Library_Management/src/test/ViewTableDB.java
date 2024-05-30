package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.MySQLConnection;

public class ViewTableDB {
    private Connection connection;

    public ViewTableDB() {
        this.connection = MySQLConnection.conn;
    }

    public List<String> getAllTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            tableNames.add(rs.getString("TABLE_NAME"));
        }
        return tableNames;
    }

    public List<String> getColumnNames(String tableName) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getColumns(null, null, tableName, "%");
        while (rs.next()) {
            columnNames.add(rs.getString("COLUMN_NAME"));
        }
        return columnNames;
    }

    public List<List<Object>> getTableData(String tableName) throws SQLException {
        List<List<Object>> data = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    if (value instanceof Boolean) {
                        row.add((Boolean) value ? 1 : 0);
                    } else {
                        row.add(value);
                    }
                }
                data.add(row);
            }
        }
        return data;
    }

    public void addRow(String tableName, List<Object> values) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
        for (int i = 0; i < values.size(); i++) {
            query.append("?");
            if (i < values.size() - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                Object value = values.get(i);
                if (value instanceof Integer) {
                    if ((Integer) value == 1) {
                        pstmt.setBoolean(i + 1, true);
                    } else if ((Integer) value == 0) {
                        pstmt.setBoolean(i + 1, false);
                    } else {
                        pstmt.setObject(i + 1, value);
                    }
                } else {
                    pstmt.setObject(i + 1, value);
                }
            }
            pstmt.executeUpdate();
        }
    }

    public void updateRow(String tableName, List<Object> values, String primaryKey, Object primaryKeyValue) throws SQLException {
        List<String> columnNames = getColumnNames(tableName);
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (int i = 1; i < columnNames.size(); i++) { // Start from 1 to skip the primary key column
            query.append(columnNames.get(i)).append(" = ?, ");
        }
        query.setLength(query.length() - 2); // Remove last comma and space
        query.append(" WHERE ").append(primaryKey).append(" = ?");

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                Object value = values.get(i);
                if (value instanceof Integer) {
                    if ((Integer) value == 1) {
                        pstmt.setBoolean(i + 1, true);
                    } else if ((Integer) value == 0) {
                        pstmt.setBoolean(i + 1, false);
                    } else {
                        pstmt.setObject(i + 1, value);
                    }
                } else {
                    pstmt.setObject(i + 1, value);
                }
            }
            pstmt.setObject(values.size() + 1, primaryKeyValue);
            pstmt.executeUpdate();
        }
    }


    public void deleteRow(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, primaryKeyValue);
            pstmt.executeUpdate();
        }
    }
}

