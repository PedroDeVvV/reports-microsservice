package com.tracevia.app.core.controller;

import com.tracevia.app.core.dto.ReportRequest;
import com.tracevia.app.core.dto.ReportResponse;
import com.tracevia.app.core.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for handling requests related to report generation.
 * Provides an endpoint to generate custom reports based on the provided request.
 *
 * @apiNote This controller exposes the API for generating custom reports.
 */
@RestController
@RequestMapping("/api/reports")
@Tag(name = "Report Service API")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Endpoint to generate a custom report based on the provided request.
     *
     * @param request The request containing the necessary data for report generation.
     * @return A ResponseEntity containing the generated report or an error message.
     */
    @PostMapping("/generate")
    @Operation(description = "Generate custom report from request")
    public ResponseEntity<?> generateReport(@RequestBody ReportRequest request) {

        try {

            ReportResponse report = reportService.generateReport(request);
            return ResponseEntity.ok(report);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}