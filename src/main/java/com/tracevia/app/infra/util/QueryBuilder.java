package com.tracevia.app.infra.util;

import com.tracevia.app.core.dto.FilterRequest;
import com.tracevia.app.core.dto.JoinRequest;
import com.tracevia.app.core.dto.ReportRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * A utility class for dynamically building SQL queries based on various request parameters.
 * This class provides methods to construct SELECT queries, including optional clauses
 * such as JOINs, WHERE filters, GROUP BY, and ORDER BY.
 * <p>
 * The queries are constructed using data from the {@link ReportRequest} object,
 * allowing for flexible and customizable query generation.
 */
public class QueryBuilder {

    private static final Logger logger = LoggerFactory.getLogger(QueryBuilder.class);

    /**
     * Builds a dynamic SQL query based on the provided request details.
     * The query includes selected columns, joins, filters (with optional date range),
     * group by, and order by clauses based on the data in the {@link ReportRequest}.
     *
     * @param request The {@link ReportRequest} object containing the details for the query construction.
     * @return A string representing the SQL query.
     */
    public static String buildQuery(ReportRequest request) {

        try {

            StringBuilder query = new StringBuilder("SELECT ");
            query.append(String.join(", ", request.getColumns())); // Add the columns
            query.append(" FROM ").append(request.getTableName()); // Add the main table
            System.out.println("REQUEST: " + request.getTableName());
            // Add joins, if any
            if (request.getJoins() != null && !request.getJoins().isEmpty()) {
                for (JoinRequest join : request.getJoins()) {
                    query.append(" ")
                            .append(join.getType()) // Join type (INNER, LEFT, RIGHT)
                            .append(" JOIN ")
                            .append(join.getTable()) // Join table
                            .append(" ON ")
                            .append(join.getOnCondition()); // Join condition
                }
            }

            // Add filters (WHERE)
            List<String> conditions = new ArrayList<>();
            StringBuilder dateCondition = new StringBuilder();

            if (request.getFilters() != null && !request.getFilters().isEmpty()) {
                query.append(" WHERE ");

                for (FilterRequest filter : request.getFilters()) {
                    if ("startDate".equalsIgnoreCase(filter.getKey())) {
                        // Store the BETWEEN condition in a temporary variable
                        dateCondition = new StringBuilder(filter.getColumn() + " " + filter.getOperator() + " :" + filter.getKey());
                    } else if ("endDate".equalsIgnoreCase(filter.getKey())) {
                        // Add the final part of BETWEEN
                        dateCondition.append(" ").append(filter.getOperator()).append(" :").append(filter.getKey());
                        conditions.add(String.valueOf(dateCondition)); // Add the complete condition
                        dateCondition = new StringBuilder(); // Clear StringBuilder
                    } else {
                        // Add other filters as normal
                        conditions.add(filter.getColumn() + " " + filter.getOperator() + " :" + filter.getKey());
                    }
                }

                query.append(String.join(" AND ", conditions));
            }

            // Add GROUP BY if it exists
            if (request.getGroupBy() != null && !request.getGroupBy().isEmpty()) {
                query.append(" GROUP BY ").append(request.getGroupBy());
            }

            // Add ORDER BY, if it exists
            if (request.getOrderBy() != null && !request.getOrderBy().isEmpty()) {
                query.append(" ORDER BY ").append(request.getOrderBy());
            }

            return query.toString();

        } catch (Exception e) {

            logger.error("Failed to build query for request: {}", request, e);

            // Return an empty value
            return "";
        }
    }

    /**
     * Maps the result set from a query to a list of maps, where each map represents a row.
     * The keys of the map are the column names, and the values are the corresponding values from the row.
     * The insertion order of the columns is preserved in the map.
     *
     * @param columns The list of column names to be used as keys in the map.
     * @param results The list of rows from the query result, where each row is an array of objects.
     * @return A list of maps where each map contains the column-value pairs for a row.
     */
    public static List<Map<String, Object>> mapResults(List<String> columns, List<Object[]> results) {

        try {

            List<Map<String, Object>> mappedResults = new ArrayList<>();

            for (Object[] row : results) {
                Map<String, Object> rowMap = new LinkedHashMap<>(); // Preserves the insertion order
                for (int i = 0; i < columns.size(); i++) {
                    rowMap.put(columns.get(i), row[i]); // Maps each column to its value
                }
                mappedResults.add(rowMap);
            }

            return mappedResults;

        } catch (Exception e) {

            logger.error("Failed to map results for columns: {}", columns, e);

            // Return an empty list
            return Collections.emptyList();
        }
    }
}