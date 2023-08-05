package alfarezyyd.anathema;

import java.time.Instant;

public class Main {
  public static void main(String[] args) {
    System.out.println("The data used is just an example, if you want to download full data, the link is here: https://blog.majestic.com/development/majestic-million-csv-daily");
    Instant millisNow = Instant.now();
    DataRepository dataRepository = new DataRepository();
    DelegatorDataSubscriber delegatorDataSubscriber = new DelegatorDataSubscriber(dataRepository);
    ProviderDataPublisher providerDataPublisher = new ProviderDataPublisher(delegatorDataSubscriber);
    providerDataPublisher.registerSubscriber();
    providerDataPublisher.dispatchData();
    providerDataPublisher.waitAllTask(millisNow);
  }
}

