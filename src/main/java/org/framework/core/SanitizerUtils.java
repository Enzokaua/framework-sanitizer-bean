package org.framework.core;

import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.framework.core.SanitizerConstants.*;

@Component
class SanitizerUtils {
    private static final Properties PROP = new Properties();

    @PostConstruct
    public static Properties exceptionPropertiesConfiguration() throws IOException {
        try (FileInputStream file = new FileInputStream("./src/main/resources/application.properties")) {
            PROP.load(file);
            return PROP;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public static String exceptionPersonalize(Field field, String... exceptionMessage) {
        return Arrays.stream(exceptionMessage).findFirst().orElse(FIELD_MESSAGE_ERROR_INVALID) + FIELD_MESSAGE_COMPLEMENTATION + field.getName();
    }

    public static void generateReport(List<String> fields) throws IOException {
        try {
            File directory = new File(DOCS_DIRECTORY_VALUE);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Document document = new Document(PageSize.A4);
            File outputFile = new File(DOCS_SANITIZER_VALUE);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            addHeader(document);
            Paragraph title = new Paragraph(TITLE_NAME_REPORT, new Font(Font.HELVETICA, SIZE_FONT_VALUE_HEADER, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);
            Font normalFont = new Font(Font.HELVETICA, SIZE_FONT_VALUE_CONTENT);
            document.add(new Paragraph(PARAGRAPH_TITLE_CONTENT_REPORT, normalFont));

            for (String validation : fields) {
                Paragraph validationItem = new Paragraph("- " + validation, normalFont);
                document.add(validationItem);
            }

            addFooter(writer, document);
            document.close();
        } catch (IOException e) {
            throw new IOException(RUNTIME_ERROR_GENERATE_PDF);
        }
    }

    private static void addHeader(Document document) throws DocumentException, IOException {
        InputStream imageStream = SanitizerUtils.class.getResourceAsStream(PATH_HEADER_SANITIZER_FRAME);
        if (imageStream != null) {
            Image image = Image.getInstance(ImageIO.read(imageStream), null);
            image.scaleToFit(500, 120);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
        }
    }

    private static void addFooter(PdfWriter writer, Document document) throws DocumentException {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(FOOTER_INFO_CONTENT + writer.getPageNumber(), new Font(Font.HELVETICA, FOOTER_FONT_VALUE_CONTENT, Font.ITALIC)), (document.left() + document.right()) / 2, document.bottom() - PADDING_BOTTOM_FOOTER, ROTATE_FOOTER_DEFAULT);
    }
}
