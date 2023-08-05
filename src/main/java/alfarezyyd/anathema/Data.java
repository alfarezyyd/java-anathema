package alfarezyyd.anathema;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class Data {
  public final static List<String> dataHeaders = List.of(
      "GlobalRank",
      "TldRank",
      "Domain",
      "TLD",
      "RefSubNets",
      "RefIPs",
      "IDN_Domain",
      "IDN_TLD",
      "PrevGlobalRank",
      "PrevTldRank",
      "PrevRefSubNets",
      "PrevRefIPs"
  );

  public static CSVParser readCSVData() {
    log.info("Read CSV Data...");
    Path dataPath = Path.of("src/main/resources/majestic_million.csv");
    try {
      log.info("Return CSV Parser");
      BufferedReader bufferedReader = Files.newBufferedReader(dataPath);
      return new CSVParser(bufferedReader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
