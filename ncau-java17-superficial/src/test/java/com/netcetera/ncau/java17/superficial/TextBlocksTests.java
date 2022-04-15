package com.netcetera.ncau.java17.superficial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class TextBlocksTests {

  private static final String CREATE_SEQUENCE = """
                                                CREATE SEQUENCE demo_squence 
                                                NO CYCLE 
                                                """;
  
  private static final String SELECT_SEQUENCE_VALUES = """
                                                       WITH RECURSIVE t(n, level_num) AS (
                                                         SELECT 1
                                                           UNION ALL
                                                         SELECT n + 1
                                                           FROM t
                                                          WHERE n < ?)
                                                       SELECT NEXT VALUE FOR demo_squence
                                                         FROM t
                                                       """;

  @Test
  void databaseAccess() throws SQLException {
    try (var connection = DriverManager.getConnection("jdbc:h2:mem:")) {
      try (var statement = connection.createStatement()) {
        statement.execute(CREATE_SEQUENCE);
      }
      List<Integer> sequenceValues = new ArrayList<>(10); 
      try (PreparedStatement selectSequenceValues = connection.prepareStatement(SELECT_SEQUENCE_VALUES)) {
        selectSequenceValues.setInt(1, 10);
        try (ResultSet resultSet = selectSequenceValues.executeQuery()) {
          while (resultSet.next()) {
            sequenceValues.add(resultSet.getInt(1));
          }
        }
      }
      assertEquals(IntStream.rangeClosed(1, 10).boxed().toList(), sequenceValues);
    }
  }

}
