package com.bank.customer;

import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/DownloadTransactionsServlet")
public class DownloadTransactionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account_no") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accountNo = (String) session.getAttribute("account_no");

        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactions;
        try {
            transactions = transactionDAO.getTransactions(accountNo);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Transaction Details", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            PdfPCell c1 = new PdfPCell(new Phrase("Date"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Type"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Amount"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Balance After Transaction"));
            table.addCell(c1);

            table.setHeaderRows(1);

            for (Transaction transaction : transactions) {
                table.addCell(transaction.getTransactionDate().toString());
                table.addCell(transaction.getType());
                table.addCell(String.valueOf(transaction.getAmount()));
                table.addCell(String.valueOf(transaction.getBalanceAfterTransaction()));
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}