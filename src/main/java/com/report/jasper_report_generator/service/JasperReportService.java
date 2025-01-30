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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JasperReportService {

    public byte[] generateReport(String reportTemplateLocation) throws JRException {
        JasperReport jasperReport = loadReportTemplate(reportTemplateLocation);
        List<Report> reportData = new ArrayList<>();    
        reportData.add(new Report(1, "ANTONIA", "GRL" + String.format("%03d", 1), "Description ", new String[]{"*-All", "M", "NM"}[new java.util.Random().nextInt(3)], new String[]{"Y", "N"}[new java.util.Random().nextInt(2)], new String[]{"Y", "N"}[new java.util.Random().nextInt(2)], new String[]{"Inactive", "Active"}[new java.util.Random().nextInt(2)],1000000.0, 2000000.0, 3000000.0, 4000000.0, 30, 50, 60, 70, 80, 90, 1.0));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("REPORT_DATASET_1", new JRBeanCollectionDataSource(getStaticData()));
        parameters.put("REPORT_DATASET_2", new JRBeanCollectionDataSource(getStaticData()));
        parameters.put("REPORT_DATASET_3", new JRBeanCollectionDataSource(getCASRNBID0131SecondPageData()));
        parameters.put("CREATED_BY", "Umar");
        parameters.put("FORMATTED_CURRENT_DATE", new SimpleDateFormat("dd MMM yyyy").format(new Date()));
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

    private List<Report> getStaticData() {
        List<Report> reportData = new ArrayList<>();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma"};;
        for (int i = 0; i <= 29; i++) {
            Report report = new Report(
                i+1, 
                names[new java.util.Random().nextInt(names.length)],  
                "GRL" + String.format("%03d", i),  
                "Description " + i,  
                new String[]{"*-All", "M", "NM"}[new java.util.Random().nextInt(3)],  
                new String[]{"Y", "N"}[new java.util.Random().nextInt(2)], 
                new String[]{"Y", "N"}[new java.util.Random().nextInt(2)], 
                new String[]{"Inactive", "Active"}[new java.util.Random().nextInt(2)], 
                1000000.0 * i,
                2000000.0 * i, 
                3000000.0 * i, 
                4000000.0 * i, 
                30 * i, 
                50 * i, 
                60 * i, 
                70 * i,
                80 * i, 
                90 * i, 
                1.0 * i 
            );        
            reportData.add(report);
        }
        return reportData;
    }

    private List<Report> getCASRNBID0131SecondPageData() {
        List<Report> reportData = new ArrayList<>();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma"};;
        for (int i = 0; i <= 64; i++) {
            Report report = new Report(
                i+1, 
                names[new java.util.Random().nextInt(names.length)],  
                "GRL" + String.format("%03d", i),  
                "Description " + i,  
                new String[]{"*-All", "M", "NM"}[new java.util.Random().nextInt(3)],  
                new String[]{"Y", "N"}[new java.util.Random().nextInt(2)], 
                new String[]{"Y", "N"}[new java.util.Random().nextInt(2)], 
                new String[]{"Inactive", "Active"}[new java.util.Random().nextInt(2)], 
                1000000.0 * i,
                2000000.0 * i, 
                3000000.0 * i, 
                4000000.0 * i, 
                30 * i, 
                50 * i, 
                60 * i, 
                70 * i,
                80 * i, 
                90 * i, 
                1.0 * i 
            );        
            reportData.add(report);
        }
        return reportData;
    }
}
