package alfarezyyd.anathema;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class DataRepository {
  public void dispatchTask(List<String> linkedListOfData) {
    try (Connection connection = Database.hikariDataSource.getConnection()) {
      String sqlQuery = String.format("INSERT INTO domain (%s) VALUES (%s)",
          String.join(",", Data.dataHeaders),
          String.join(",", generateQuestionMarks())
      );
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      for (int i = 0; i < linkedListOfData.size(); i++) {
        preparedStatement.setString(i + 1, linkedListOfData.get(i));
      }
      preparedStatement.execute();
      log.info("Success Insert Data");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public LinkedList<String> generateQuestionMarks() {
    LinkedList<String> linkedListOfString = new LinkedList<>();
    for (int i = 0; i < Data.dataHeaders.size(); i++) {
      linkedListOfString.add("?");
    }
    return linkedListOfString;
  }
}
