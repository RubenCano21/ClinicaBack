package uagrm.bo.workflow.servicio;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ExcelReportService {

    public byte[] generarReporteExcel() {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Reporte");
            Row headerRow = sheet.createRow(0);

            // Crear encabezados
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nombre");

            // Crear filas de datos
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(1);
            dataRow.createCell(1).setCellValue("Producto 1");

            workbook.write(out);  // Escribir el contenido del Excel
            return out.toByteArray();  // Retornar el Excel como byte array

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
