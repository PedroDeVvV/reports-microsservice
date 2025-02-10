package com.tracevia.app.core.dto;

import java.util.List;

/**
 * Represents the response for a report containing multiple pages of data.
 * Each page contains a subset of data rows, with each row represented as a map
 * where the key is the column name and the value is the corresponding data value.
 *
 * @param pages A list of pages, where each page contains a set of data rows.
 *              Each page is represented by a {@link ReportPage} object.
 * @param totalRecords The total number of records in the dataset (before pagination).
 * @param pageSize The number of records per page.
 * @param totalPages The total number of pages available based on the total number of records and page size.
 */
public record ReportResponse(List<ReportPage> pages, int totalRecords, int pageSize, int totalPages) {}

