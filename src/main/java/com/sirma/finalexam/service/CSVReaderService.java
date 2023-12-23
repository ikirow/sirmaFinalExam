package com.sirma.finalexam.service;

import com.sirma.finalexam.model.Employee;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

@Service
public class CSVReaderService implements interfaces.Reader {

    private final String[] patterns = {
            "yyyy/MM/dd", "yyyy-MM-dd", "yyyy.MM.dd", "yyyy/MM/d", "yyyy-MM-d", "yyyy.MM.d",
            "yyyy/M/dd", "yyyy-M-dd", "yyyy.M.dd", "yyyy/M/d", "yyyy-M/d", "yyyy.M.d",
            "yy/MM/dd", "yy-MM-dd", "yy.MM.dd", "yy/MM/d", "yy-MM-d", "yy.MM.d",
            "yy/M/dd", "yy-M-dd", "yy.M.dd", "yy/M/d", "yy-M/d", "yy.M.d",
            "dd/MM/yyyy", "dd-MM-yyyy", "dd.MM.yyyy", "dd/MM/yy", "dd-MM-yy", "dd.MM.yy"
    };

    private final List<String> datePatterns = Arrays.asList(patterns);

    private final String[] dateFormats = datePatterns.toArray(new String[0]);
    public CSVReaderService() {
    }

    @Override
    public List<Employee> read(List<Employee> items, String path) {
        List<Employee> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            boolean isFirstRow = true;
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstRow && looksLikeHeader(line)) {
                    // Skip the first line if it looks like a header
                    isFirstRow = false;
                    continue;
                }

                String[] values = line.split(",");

                // Verify Employee ID and Project ID are integers
                int employeeId = parseInteger(values[0]);
                int projectId = parseInteger(values[1]);

                // Verify DateFrom and DateTo are in the expected format
                LocalDate dateFrom = parseDate(values[2]);
                LocalDate dateTo = parseDateWithNullCheck(values[3]);

                clients.add(new Employee(employeeId, projectId, dateFrom, dateTo));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    private boolean looksLikeHeader(String line) {
        return line.trim().startsWith("EmpID,");
    }

    private String sanitizeValue(String value) {
        return value.trim();
    }

    private LocalDate parseDate(String dateString) {
        try {
            Date parsedDate = DateUtils.parseDate(dateString, dateFormats);
            return parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException ignored) {
            throw new IllegalArgumentException("Unable to parse date: " + dateString);
        }
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer value: " + value);
        }
    }

    private LocalDate parseDateWithNullCheck(String dateString) {
        if ("NULL".equals(dateString.trim())) {
            return LocalDate.now();
        } else {
            return parseDate(dateString.trim());
        }
    }

}
