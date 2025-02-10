package com.tracevia.app.core.service;

import com.tracevia.app.core.dto.ReportPage;
import com.tracevia.app.core.dto.ReportRequest;
import com.tracevia.app.core.dto.ReportResponse;
import com.tracevia.app.core.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class responsible for handling report generation.
 * It interacts with the ReportRepository to execute dynamic queries and generate reports.
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Generates a report based on the provided request.
     *
     * @param request The report request containing parameters for the report generation.
     * @return A ReportResponse containing the generated report data.
     */
    public ReportResponse generateReport(ReportRequest request) {

        try {

            // Execute the dynamic query
            List<Map<String, Object>> result = reportRepository.executeDynamicQuery(request);

            // Total records returned by the query
            int totalRecords = result.size();

            // Default page size
            int pageSize = 50;

            // Calculation of the number of pages based on page size
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // Loop to generate data for all pages
            List<ReportPage> pages = new ArrayList<>();
            
            for (int page = 0; page < totalPages; page++) {
                int fromIndex = page * pageSize;
                int toIndex = Math.min(fromIndex + pageSize, totalRecords);

                // Extracts the sublist corresponding to the current page
                List<Map<String, Object>> pageData = result.subList(fromIndex, toIndex);

                // Adds the page with its data and metadata to the list of pages
                pages.add(new ReportPage(page, pageData));
            }

            // Returns the response with all pages and global metadata
            return new ReportResponse(pages, totalRecords, pageSize, totalPages);

        } catch (Exception e) {

            logger.error("Error generating report:", e);

            // Returns an empty response
            return new ReportResponse(Collections.emptyList(), 0, 50, 0);
        }
    }
}