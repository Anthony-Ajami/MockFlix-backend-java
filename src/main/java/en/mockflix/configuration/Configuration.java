package en.mockflix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Named;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class Configuration {

    @Bean
    @Named("postgresDataSource")
    public org.springframework.jdbc.datasource.DriverManagerDataSource postgresDataSource() {
        org.springframework.jdbc.datasource.DriverManagerDataSource postgresDataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        postgresDataSource.setDriverClassName("org.postgresql.Driver");
        postgresDataSource.setUrl("jdbc:postgresql://localhost:5433/postgres");
        postgresDataSource.setUsername("postgres");
        postgresDataSource.setPassword("password");
        return postgresDataSource;
    }

    @Bean
    @Named("postgresSessionFactory")
    public org.springframework.orm.hibernate5.LocalSessionFactoryBean postgresSessionFactory() {
        org.springframework.orm.hibernate5.LocalSessionFactoryBean postgresSessionFactory = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
        postgresSessionFactory.setDataSource(postgresDataSource());
        postgresSessionFactory.setPackagesToScan("en/mockflix/entities");
        Properties properties = new Properties();
        properties.setProperty("hibernate.properties", "org.postgresql.Driver");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql","true");
        postgresSessionFactory.setHibernateProperties(properties);
        return postgresSessionFactory;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
