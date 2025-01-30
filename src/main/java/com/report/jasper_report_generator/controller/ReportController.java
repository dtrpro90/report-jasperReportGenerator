package com.report.jasper_report_generator.controller;

import com.report.jasper_report_generator.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportController {

    private final JasperReportService jasperReportService;

    @Autowired
    public ReportController(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    /**
     * Generates a PDF report using a specified JasperReports template (.jrxml).
     *
     * This API accepts the report template path as a query parameter and returns the generated PDF.
     *
     * Example usage:
     *   - http://localhost:8080/generateReport?reportTemplateLocation=/templates/report/test1.jrxml
     *   - http://localhost:8080/generateReport?reportTemplateLocation=/templates/report/test2.jrxml
     *
     * @param reportTemplateLocation The file path of the .jrxml template (relative to the resources folder).
     * @return ResponseEntity containing the PDF report as a byte array.
     * @throws JRException If an error occurs during report generation.
     */
    @GetMapping("/generateReport")
    public ResponseEntity<byte[]> generateReportByName(@RequestParam String reportTemplateLocation) throws JRException {
        // Call the service to generate the report based on the provided template location
        byte[] reportContent = jasperReportService.generateReport(reportTemplateLocation);

        // Set response headers to ensure the file is returned as a PDF
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        // Return the generated report as a response
        return new ResponseEntity<>(reportContent, headers, HttpStatus.OK);
    }  
}
