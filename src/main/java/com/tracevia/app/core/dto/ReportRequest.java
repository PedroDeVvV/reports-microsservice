package com.tracevia.app.core.dto;

import java.util.List;

/**
 * Represents a request to generate a report, including the necessary parameters
 * such as the table name, columns to retrieve, filters, sorting, and joining information.
 * This class is used to define the dynamic query that will be executed to fetch the data for the report.
 *
 * @param tableName The name of the table from which data will be fetched.
 * @param columns A list of columns to be included in the report.
 * @param filters A list of filters to apply to the query (optional).
 * @param joins A list of joins to include in the query (optional).
 * @param groupBy Columns by which the result should be grouped (optional).
 * @param orderBy The columns by which the result should be ordered (optional).
 */
public class ReportRequest {

    private String tableName;
    private List<String> columns;
    private List<JoinRequest> joins;
    private String groupBy;
    private String orderBy;
    private List<FilterRequest> filters;

    // Getters and Setters

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) { this.tableName = tableName; }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) { this.columns = columns; }

    public List<JoinRequest> getJoins() {
        return joins;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) { this.groupBy = groupBy; }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }

    public List<FilterRequest> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterRequest> filters) { this.filters = filters; }

}
