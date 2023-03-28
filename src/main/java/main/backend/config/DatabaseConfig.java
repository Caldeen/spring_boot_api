package main.backend.config;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@PropertySource("classpath:persistence.properties")
@Configuration
public class DatabaseConfig {
    @Autowired
    private Environment environment;
    @Bean
    public DataSource dataSource() {
        Logger.getAnonymousLogger().info(environment.toString());
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getRequiredProperty("url"));
        dataSource.setDriverClassName(environment.getRequiredProperty("driverClassName"));
        dataSource.setUsername(environment.getRequiredProperty("username"));
        dataSource.setPassword(environment.getRequiredProperty("password"));
        return dataSource;
    }
}