package com.report.jasper_report_generator.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import com.report.jasper_report_generator.model.Report;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class JasperReportService {

    public byte[] generateReport(String reportTemplateLocation) throws JRException {
        JasperReport jasperReport = loadReportTemplate(reportTemplateLocation);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getHardcodedData());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reportDataSource1", dataSource);
        parameters.put("reportDataSource2", dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return exportReportToPdf(jasperPrint);
    }

    private JasperReport loadReportTemplate(String reportTemplateLocation) throws JRException {
        try (InputStream jasperStream = getClass().getResourceAsStream(reportTemplateLocation)) {
            return JasperCompileManager.compileReport(jasperStream);
        } catch (Exception e) {
            throw new JRException("Error loading report template", e);
        }
    }

    private byte[] exportReportToPdf(JasperPrint jasperPrint) throws JRException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new JRException("Error exporting report to PDF", e);
        }
    }

    private List<Report> getHardcodedData() {
        List<Report> reportData = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            Report report = new Report(
                i,  // No: dynamically changing with each iteration
                "GRL" + String.format("%03d", i),  // Group ID: dynamically changing with each iterationa
                "Description " + i,  // Description: dynamically changing with each iteration
                new String[]{"*-All", "M", "NM"}[new java.util.Random().nextInt(3)],  // Medical Indicator: dynamically changing with each iteration
                new String[]{"Y", "N"}[new java.util.Random().nextInt(2)],  // Decline Authority: dynamically changing with each iteration
                new String[]{"Y", "N"}[new java.util.Random().nextInt(2)],  // Regional Referral: dynamically changing with each iteration
                new String[]{"Inactive", "Active"}[new java.util.Random().nextInt(2)],  // Status: dynamically changing with each iteration
                1000000.0 * i,  // maxLifeRisk: dynamically changing with each iteration
                2000000.0 * i,  // maxAddbRisk: dynamically changing with each iteration
                3000000.0 * i,  // maxCiRisk: dynamically changing with each iteration
                4000000.0 * i,  // maxTpdRisk: dynamically changing with each iteration
                30 * i,  // faceRatingBase: dynamically changing with each iteration
                50 * i,  // faceRatingAddb: dynamically changing with each iteration
                60 * i,  // faceRatingCi: dynamically changing with each iteration
                70 * i,  // faceRatingTpd: dynamically changing with each iteration
                80 * i,  // faceRatingWpPb: dynamically changing with each iteration
                90 * i,  // faceRatingHospital: dynamically changing with each iteration
                1.0 * i  // extraRateBase: dynamically changing with each iteration
            );        
            reportData.add(report);
        }
        return reportData;
    }
}
