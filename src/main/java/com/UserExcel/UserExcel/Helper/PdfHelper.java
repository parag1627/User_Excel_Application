package com.UserExcel.UserExcel.Helper;

import com.UserExcel.UserExcel.Model.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfHelper {
    public static byte[] generatePdf(List<User> users) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        for (User user : users) {
            document.add(new Paragraph("Id: " + user.getId()));
            document.add(new Paragraph("Name: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Phone: " + user.getPhone()));
            document.add(new Paragraph("\n"));
        }

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}

