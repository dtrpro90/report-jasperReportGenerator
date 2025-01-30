# JasperReports Integration with Spring Boot

## Overview
This Spring Boot application provides an API to generate PDF reports using JasperReports (.jrxml templates). The reports are dynamically generated based on the provided template path.

## Prerequisites
- Java 17 (or compatible version)
- Spring Boot 2.x or 3.x
- JasperReports Library (6.21.3)
- Maven or Gradle

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/springboot-jasper.git
   cd springboot-jasper
   ```
2. Ensure you have the required dependencies in `pom.xml` (for Maven users):
   ```xml
   <dependency>
       <groupId>net.sf.jasperreports</groupId>
       <artifactId>jasperreports</artifactId>
       <version>6.21.3</version>
   </dependency>
   ```

## API Usage
### Generate Report
**Endpoint:**
```http
GET /generateReport
```
**Query Parameters:**
- `reportTemplateLocation` (String) - The file path of the .jrxml template (relative to resources folder).

**Example Usage:**
```http
http://localhost:8080/generateReport?reportTemplateLocation=/templates/report/test1.jrxml
http://localhost:8080/generateReport?reportTemplateLocation=/templates/report/test2.jrxml
```

**Response:**
- A dynamically generated PDF file returned as a byte stream.
- Content-Type: `application/pdf`

## Configuration
### Ensure Times New Roman Font Support
JasperReports requires fonts to be correctly configured for PDF export. To resolve font issues:
1. Place `times.ttf` inside `src/main/resources/fonts/`
2. Configure a font extension in `jasperreports.properties`:
   ```properties
   net.sf.jasperreports.export.pdf.font.times=resources/fonts/times.ttf
   net.sf.jasperreports.default.font.name=Times New Roman
   ```
3. Reference the font in `.jrxml` files:
   ```xml
   <textElement>
       <font fontName="Times New Roman" pdfFontName="times.ttf" isPdfEmbedded="true"/>
   </textElement>
   ```

## Running the Application
1. Build and run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```
2. Open your browser and visit:
   ```sh
   http://localhost:8080/generateReport?reportTemplateLocation=/templates/report/test1.jrxml
   ```

## Troubleshooting
### Common Issues
**1. Font Not Found Error:**
   - Ensure `times.ttf` is in the correct directory (`src/main/resources/fonts/`).
   - Verify `jasperreports.properties` is correctly set.

**2. JRException: Could not load report:**
   - Check if the `.jrxml` template exists at the specified location.
   - Ensure the template path is correct relative to `resources/`.

## License
This project is licensed under the MIT License.

## Contact
For support or contributions, reach out via [umardjou@gmail.com](mailto:umardjou@gmail.com).

