package edu.sjsu.seekers.starbucks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile("unit-test")
public class UnitTestDatabaseConfig {
    @Bean
    public DataSource starbucksDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/db/sql/dbCreate.sql")
                .addScript("/db/sql/insert.sql")
                .build();
    }
}
