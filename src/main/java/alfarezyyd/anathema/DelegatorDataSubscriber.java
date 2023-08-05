package alfarezyyd.anathema;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Flow;

@Slf4j
public class DelegatorDataSubscriber implements Flow.Subscriber<List<String>> {
  private Flow.Subscription subscription;
  private final DataRepository dataRepository;

  public DelegatorDataSubscriber(DataRepository dataRepository) {
    this.dataRepository = dataRepository;
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    this.subscription = subscription;
    this.subscription.request(1);
  }

  @Override
  public void onNext(List<String> item) {
    dataRepository.dispatchTask(item);
    log.info("Dispatch Task to Class DataRepository");
    this.subscription.request(1);

  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @Override
  public void onComplete() {
    System.out.println("All Data Have Inserted");
  }
}
