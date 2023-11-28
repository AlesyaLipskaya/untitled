package report;
import model.Account;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionReport {
    public void generateReport(List<Account> transactions) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = "report_" + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(reportFileName)) {
            for (Account transaction : transactions) {
                // Запись деталей операции в файл отчета
                writer.write("От: " + transaction.getFromAccount() + "\n");
                writer.write("Кому: " + transaction.getToAccount() + "\n");
                writer.write("Сумма: " + transaction.getTransferAmount() + "\n");
                writer.write("Статус: Обработано\n");
                writer.write("Дата: " + new Date() + "\n");
                writer.write("\n");
            }
        }
    }
}