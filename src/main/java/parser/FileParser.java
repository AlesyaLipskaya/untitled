package parser;
import exception.InvalidFileFormatException;
import model.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public List<Account> parseFiles(String inputDirectory, String archiveDirectory) throws IOException, InvalidFileFormatException {
        List<Account> transactions = new ArrayList<>();

        File inputDir = new File(inputDirectory);
        File[] files = inputDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] parts = line.split("\\s+");

                            if (parts.length == 3) {
                                Account account = new Account(parts[0], parts[1], Integer.parseInt(parts[2]));
                                transactions.add(account);
                            } else {
                                throw new InvalidFileFormatException("Неверный формат файла в " + file.getName());
                            }
                        }
                    }

                    // Переместить обработанный файл в архивный каталог
                    Path sourcePath = file.toPath();
                    Path destinationPath = Path.of(archiveDirectory, file.getName());
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        return transactions;
    }
}