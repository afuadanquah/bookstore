import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.util.Properties;

@Component
public class CSV {

    @Autowired
    private Environment env;

            int batchSize = 20;

            String csvFilePath = "Reviews-simple.csv";

             String jdbcURL = env.getProperty("spring.datasource.url");
            String username = env.getProperty("spring.datasource.username");
             String password = env.getProperty("spring.datasource.password");

             Connection connection = null;

    try {

                connection = DriverManager.getConnection(jdbcURL, username, password);

                String sql = "INSERT INTO review (course_name, student_name, timestamp, rating, comment) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);

                BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
                String lineText = null;

                int count = 0;

                lineReader.readLine(); // skip header line

                while ((lineText = lineReader.readLine()) != null) {
                    String[] data = lineText.split(",");
                    String courseName = data[0];
                    String studentName = data[1];
                    String timestamp = data[2];
                    String rating = data[3];
                    String comment = data.length == 5 ? data[4] : "";

                    statement.setString(1, courseName);
                    statement.setString(2, studentName);

                    statement.setTimestamp(3, sqlTimestamp);

                    Float fRating = Float.parseFloat(rating);
                    statement.setFloat(4, fRating);

                    statement.setString(5, comment);

                    statement.addBatch();

                    if (count % batchSize == 0) {
                        statement.executeBatch();
                    }
                }

                lineReader.close();

                // execute the remaining queries
                statement.executeBatch();

            } catch (IOException ex) {
                System.err.println(ex);
            } catch (SQLException ex) {
                ex.printStackTrace();

                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

