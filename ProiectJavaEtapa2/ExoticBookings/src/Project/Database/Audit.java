package Project.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    private static final String CSV_FILE_PATH = "C:\\Users\\VLAD\\Desktop\\PAO Lab\\ProiectJavaEtapa2\\ExoticBookings\\src\\Project\\Database\\audit.csv";

    public static void logAction(String actionName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String timestamp = getCurrentTimestamp();
            String logEntry = actionName + ", " + timestamp;
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to audit file: " + e.getMessage());
        }
    }

    private static String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}

