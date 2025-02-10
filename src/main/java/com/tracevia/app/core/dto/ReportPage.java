package com.tracevia.app.core.dto;

import java.util.List;
import java.util.Map;

/**
 * Represents a single page of a report.
 * Each page contains a list of data rows for that specific page.
 *
 * @param pageNumber The page number (starting from 0) for this report page.
 * @param data A list of data rows for the current page, where each row is represented as a map of column names and their values.
 */
public record ReportPage(int pageNumber, List<Map<String, Object>> data) {}
