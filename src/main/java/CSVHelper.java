import model.Books;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Books> csvToBooks(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Books> books= new ArrayList<Books>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Books book = new Books(
                        Long.parseLong(csvRecord.get("bookID")),
                        csvRecord.get("title"),
                        csvRecord.get("author"),
                        csvRecord.get("publicationYear"),
                        Integer.parseInt(csvRecord.get("quantity")),
                        Double.parseDouble(csvRecord.get("originalPrice")),
                        Double.parseDouble(csvRecord.get("sellingPrice")),
                        Double.parseDouble(csvRecord.get("salesTaxPrice")));

                books.add(book);
            }

            return books;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
