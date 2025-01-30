package com.report.jasper_report_generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {
    private int no;
    private String userId;
    private String groupId;
    private String groupDescription;
    private String medInd;
    private String declineAuthority;
    private String referToRegional;
    private String status;
    private double maxLifeRisk;
    private double maxAddbRisk;
    private double maxCiRisk;
    private double maxTpdRisk;
    private int faceRatingBase;
    private int faceRatingAddb;
    private int faceRatingCi;
    private int faceRatingTpd;
    private int faceRatingWpPb;
    private int faceRatingHospital;
    private double extraRateBase;   
}