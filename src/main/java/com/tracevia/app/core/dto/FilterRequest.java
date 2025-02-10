package com.tracevia.app.core.dto;

/**
 * Represents a filter request used to add filtering conditions in a dynamic query.
 * Contains the necessary details to apply conditions on specific columns of a table
 * based on a specified operator and filter value.
 * This class is used when constructing a dynamic query to apply filtering criteria.
 *
 * @param column The column name to filter on.
 * @param operator The operator to be used in the filter (e.g., '=', '>', '<', 'LIKE').
 * @param key The key representing the filter (e.g., a field name used for parameterized queries).
 * @param value The value to compare against in the filter condition.
 */
public class FilterRequest {

    private String key;
    private String column;
    private String operator;
    private String value;

    // Getters and Setters
    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    public String getColumn() { return column; }

    public void setColumn(String column) { this.column = column; }

    public String getOperator() { return operator; }

    public void setOperator(String operator) { this.operator = operator; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

}
