package project.troops;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

//This Model class handles the exportation of troopers into a formatted pdf.
public class DataPDFExporter {
    private List<Player> player;

    public DataPDFExporter(List<Player> player) {
        this.player = player;
    }

    //This method deals with writing the header of the pdf document and table header labels
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Task completion", font));
        table.addCell(cell);

    }
    //This method populates the trooper information to the pdf table.
    private void writeTableData(PdfPTable table) {
        for (Player player : this.player) {
            table.addCell(String.valueOf(player.getNum()));
            table.addCell(player.getName());
            table.addCell(player.getPercentage() + "%");

        }
    }
    //This method builds the pdf by calling the writeTableHeader and writeTableData then completes the formatting.
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        //creating and adding the page title
        Paragraph p = new Paragraph("Troop report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        //formatting the table column widths and spacings
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 8.5f, 1.5f});
        table.setSpacingBefore(10);
        //Adding the table header and data
        writeTableHeader(table);
        writeTableData(table);
        //adding the table to the document
        document.add(table);
        //Finalising the document
        document.close();

    }
}
