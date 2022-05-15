package com.wp.project.beautysalon;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.model.User;


public class AppPDFExporter {


    private Appointment appointment;

    public AppPDFExporter(Appointment appointment) {
        this.appointment = appointment;
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.DARK_GRAY);

        cell.setPhrase(new Phrase("Appointment ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Time", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Services", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Client", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

            table.addCell(String.valueOf(appointment.getAppointmentId()));
            table.addCell(String.valueOf(appointment.getTermin().getStartTime()));
            table.addCell(appointment.getTotalPrice().toString());
            table.addCell(appointment.getSalonServices().stream().map(a->a.getServiceName()).collect(Collectors.toList()).toString());
            table.addCell(appointment.getClient().getName());

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.DARK_GRAY);

        Paragraph p = new Paragraph("Reservation Details", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 1.8f, 3.0f, 3.0f, 3.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}