package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {

        try (InputStream resource = cl.getResourceAsStream("files/QaGuruFiles.zip");
             ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvcontent = reader.readAll();
                    assertThat(csvcontent.get(3)[1])
                            .contains("71");
                } else if (entry.getName().contains(".pdf")) {
                    PDF pdfcontent = new PDF(zis);
                    assertThat(pdfcontent.text).contains("текст для урока");

                } else if (entry.getName().contains(".xlsx")) {
                    XLS xlscontent = new XLS(zis);
                    assertThat(xlscontent.excel
                            .getSheetAt(0).getRow(2).getCell(0).getStringCellValue())
                            .contains("Корзинка");
                }
            }
        }
    }

}
