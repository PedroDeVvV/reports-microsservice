package com.tracevia.app.core.dto;

/**
 * Represents a request for a database join in a dynamic query.
 * Contains the details necessary to define how tables should be joined
 * based on the specified join type, table, alias, and join condition.
 * This class is used when constructing a dynamic query to fetch data
 * from multiple related tables.
 *
 * @param type The type of join (e.g., INNER, LEFT, RIGHT).
 * @param table The name of the table to join with.
 * @param alias An alias for the table in the join (optional).
 * @param onCondition The condition to specify how the tables should be joined.
 */
public class JoinRequest {

    private String type;
    private String table;
    private String alias;
    private String onCondition;

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getTable() {
        return table;
    }

    public void setTable(String table) { this.table = table; }

    public String getAlias() { return alias; }

    public void setAlias(String alias) { this.alias = alias; }

    public String getOnCondition() { return onCondition; }

    public void setOnCondition(String onCondition) { this.onCondition = onCondition; }

}
