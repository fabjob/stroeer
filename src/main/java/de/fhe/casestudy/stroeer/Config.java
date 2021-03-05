package de.fhe.casestudy.stroeer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class Config {

   @Bean
   public DataSource dataSource() {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
            .setScriptEncoding("UTF-8")
            .addScript("classpath:sql/table.sql")
            .addScript("classpath:sql/data.sql")
            .build();
      return db;
   }

   @Bean
   public JdbcTemplate jdbcTemplate(DataSource dataSource) {
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
      return jdbcTemplate;
   }

}