package com.tracevia.app.core.repository;

import com.tracevia.app.core.dto.FilterRequest;
import com.tracevia.app.core.dto.ReportRequest;
import com.tracevia.app.infra.util.QueryBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Repository class responsible for executing dynamic queries to generate reports.
 * This class interacts with the database using JPA's EntityManager to execute SQL queries
 * based on the report request parameters.
 */
@Repository
public class ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(ReportRepository.class);

    /**
     * Executes a dynamic SQL query based on the given report request.
     * The query is built dynamically, and filters are applied based on the request parameters.
     *
     * @param request The report request containing query parameters and filters.
     * @return A list of maps containing the query result, with column names as keys and their corresponding values.
     *         Returns an empty list if no results are found or if no columns are provided.
     * @throws RuntimeException if an error occurs while executing the query.
     */
    public List<Map<String, Object>> executeDynamicQuery(ReportRequest request) {

        try {

            // Generates the dynamic query
            String query = QueryBuilder.buildQuery(request);
            System.out.println("Aqui: " + query);
            Query nativeQuery = entityManager.createNativeQuery(query);

            // Define the query parameters from the filters
            if (request.getFilters() != null) {
                for (FilterRequest filter : request.getFilters()) {
                    if (filter.getKey() != null && filter.getValue() != null) {
                        nativeQuery.setParameter(filter.getKey(), filter.getValue());
                    }
                }
            }

            // Execute the query and map the results
            @SuppressWarnings("unchecked")
            List<Object[]> results = nativeQuery.getResultList();

            if (results.isEmpty() || request.getColumns() == null || request.getColumns().isEmpty()) {
                return Collections.emptyList();
            }

            return QueryBuilder.mapResults(request.getColumns(), results);

        } catch (Exception e) {

            logger.error("Failed to execute dynamic query:", e);

            // Returns an empty list
            return Collections.emptyList();
        }
    }
}
