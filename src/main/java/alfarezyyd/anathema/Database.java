package alfarezyyd.anathema;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Database {
  public final static HikariDataSource hikariDataSource = new HikariDataSource(getHikariConfig());

  private static HikariConfig getHikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("");

    hikariConfig.setMinimumIdle(5);
    hikariConfig.setMaximumPoolSize(50);
    return hikariConfig;
  }
}
