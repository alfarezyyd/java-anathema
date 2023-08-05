package alfarezyyd.anathema;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ProviderDataPublisher {
  private final ExecutorService executorService = Executors.newFixedThreadPool(100);
  private final SubmissionPublisher<List<String>> submissionPublisher = new SubmissionPublisher<>();
  private final DelegatorDataSubscriber delegatorDataSubscriber;
  private final Phaser phaserSynchronizer = new Phaser();

  public ProviderDataPublisher(DelegatorDataSubscriber delegatorDataSubscriber) {
    this.delegatorDataSubscriber = delegatorDataSubscriber;
  }

  public void registerSubscriber() {
    submissionPublisher.subscribe(delegatorDataSubscriber);
  }

  public void dispatchData() {
    executorService.execute(() -> {
      try (CSVParser csvRecords = Data.readCSVData()) {
        for (var csvRecord : csvRecords) {
          phaserSynchronizer.register();
          LinkedList<String> linkedListOfData = new LinkedList<>();
          for (int i = 0; i < csvRecord.size(); i++) {
            linkedListOfData.add(csvRecord.get(i));
          }
          log.info(Thread.currentThread().getName() + " Send Data to Subscriber");
          submissionPublisher.submit(linkedListOfData);
          phaserSynchronizer.arriveAndDeregister();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void waitAllTask(Instant millisNow) {
    executorService.execute(() -> {
      Instant millisThen = Instant.now();
      phaserSynchronizer.awaitAdvance(0);
      System.out.println("All Task Finished");
      System.out.println("Time Elapsed : " + Duration.between(millisNow, millisThen));
      executorService.shutdown();
    });
  }
}
