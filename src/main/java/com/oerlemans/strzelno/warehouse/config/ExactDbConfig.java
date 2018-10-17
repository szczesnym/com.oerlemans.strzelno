package com.oerlemans.strzelno.warehouse.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.oerlemans.strzelno.warehouse.repository.exact",
        entityManagerFactoryRef = "exactEntityManager",
        transactionManagerRef = "exactTransactionManager"
)
public class ExactDbConfig {
    @Autowired
    private Environment env;

/*    @Bean(name = "warehouseDatasource")
    public DataSource userDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("warehouse.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("warehouse.datasource.url"));
        dataSource.setUsername(env.getProperty("warehouse.datasource.username"));
        dataSource.setPassword(env.getProperty("warehouse.datasource.password"));
        return dataSource;
    }
*/
    @Primary
    @Bean(name = "exactDatasource")
    public DataSource exactDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "exactTransactionManager")
    public PlatformTransactionManager exactTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(exactEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "exactEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean exactEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(exactDataSource());
        em.setPackagesToScan(new String[] { "com.oerlemans.strzelno.warehouse.domain.exact" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2010Dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }


}
