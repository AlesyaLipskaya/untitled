package main;

import exception.InvalidFileFormatException;
import model.Account;
import parser.FileParser;
import report.TransactionReport;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите операцию:");
        System.out.println("1. Парсинг файлов перевода");
        System.out.println("2. Вывод списка всех переводов из файла-отчета");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                performParsingOperation();
                break;
            case 2:
                performReportOperation();
                break;
            default:
                System.out.println("Некорректный выбор операции.");
        }

        scanner.close();
    }

    private static void performParsingOperation() {
        FileParser fileParser = new FileParser();

        try {
            List<Account> transactions = fileParser.parseFiles("src/main/resources/input", "src/main/resources/archive");

            // Обновление информации в файле с номерами счетов и суммами
            updateAccountInfoFile(transactions);

            System.out.println("Парсинг файлов завершен успешно.");
        } catch (IOException | InvalidFileFormatException e) {
            System.out.println("Ошибка при парсинге файлов: " + e.getMessage());
        }
    }

    private static void performReportOperation() {
        FileParser fileParser = new FileParser();
        TransactionReport transactionReport = new TransactionReport();

        try {
            List<Account> transactions = fileParser.parseFiles("src/main/resources/input", "src/main/resources/archive");

            // Генерация отчета
            transactionReport.generateReport(transactions);

            System.out.println("Отчет сгенерирован успешно.");
        } catch (IOException | InvalidFileFormatException e) {
            System.out.println("Ошибка при генерации отчета: " + e.getMessage());
        }
    }

    private static void updateAccountInfoFile(List<Account> transactions) {
        // Реализуйте логику обновления информации в файле с номерами счетов и суммами
        // Например, можно просто вывести информацию о переводах в консоль
        for (Account transaction : transactions) {
            System.out.println("From: " + transaction.getFromAccount() + ", To: " + transaction.getToAccount() + ", Amount: " + transaction.getTransferAmount());
        }
    }
}