package com.cisco.excel;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cisco.excel.dao.ContractExcelDao;
import com.cisco.excel.dao.ExcelDao;
import com.cisco.excel.dao.impl.ContractExcelDaoImpl;
import com.cisco.excel.dao.impl.ExcelDaoImpl;
import com.cisco.excel.service.ContractExcelService;
import com.cisco.excel.service.ExcelService;
import com.cisco.excel.service.impl.ContractExcelServiceImpl;
import com.cisco.excel.service.impl.ExcelServiceImpl;
import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
//@PropertySource({ "classpath:persistence-mysql.properties" })
//@PropertySource({ "classpath:ir-oracle.properties" })
@PropertySource({ "query.properties" })
@ComponentScan({ "com.cisco.excel.*" })
public class PersistenceConfig {

    @Autowired
    private Environment env;

    public PersistenceConfig() {
        super();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.cisco.excel.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    
   @Bean
    public LocalSessionFactoryBean irSessionFactory() {
        final LocalSessionFactoryBean irSessionFactory = new LocalSessionFactoryBean();
        irSessionFactory.setDataSource(irRestDataSource());
        irSessionFactory.setPackagesToScan(new String[] { "com.cisco.excel.model" });
        irSessionFactory.setHibernateProperties(irHibernateProperties());

        return irSessionFactory;
    }
    
    @Bean
    public DataSource restDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setDriverClassName(Preconditions.checkNotNull("oracle.jdbc.driver.OracleDriver"));
//        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUrl(Preconditions.checkNotNull("jdbc:oracle:thin:@localhost:1521:orcl"));
//        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setUsername(Preconditions.checkNotNull("AUTO_TOOLS"));
//        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));
        dataSource.setPassword(Preconditions.checkNotNull("Test1ng1"));

        return dataSource;
    }
    
    @Bean
    public DataSource irRestDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("ir.jdbc.driverClassName")));
//        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("ir.jdbc.url")));
//        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("ir.jdbc.user")));
//        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("ir.jdbc.pass")));
        
        dataSource.setDriverClassName(Preconditions.checkNotNull("oracle.jdbc.driver.OracleDriver"));
        dataSource.setUrl(Preconditions.checkNotNull("jdbc:oracle:thin:@//indb-npd-007.cisco.com:1528/CAFI6STG.cisco.com"));
        dataSource.setUsername(Preconditions.checkNotNull("NLS_ADMIN"));
        dataSource.setPassword(Preconditions.checkNotNull("NADTXQ7277"));
        

        return dataSource;
    }


    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
    
    @Bean
    public ExcelDao  excelDAO(){
    	return new ExcelDaoImpl();

    }
    
    @Bean
    public ContractExcelDao  contractExcelDAO(){
    	return new ContractExcelDaoImpl();

    }
    
  /*  @Bean
    public IRExcelDao  irExcelDAO(){
    	return new IRExcelDaoImpl();

    }*/
    
    @Bean 
    public ExcelService excelService(){
    	
    	return new ExcelServiceImpl(); 
    	
    }
    
    @Bean 
    public ContractExcelService contractExcelService(){
    	
    	return new ContractExcelServiceImpl(); 
    	
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
    //    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        hibernateProperties.setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
        hibernateProperties.setProperty("hibernate.jdbc.batch_size", "500");

        hibernateProperties.setProperty("hibernate.show_sql","false");
        // hibernateProperties.setProperty("hibernate.format_sql", "true");
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");

        return hibernateProperties;
    }
    
 
    final Properties irHibernateProperties() {
        final Properties hibernateProperties = new Properties();
     //   hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("ir.hibernate.hbm2ddl.auto"));
//        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("ir.hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        hibernateProperties.setProperty("hibernate.jdbc.batch_size", env.getProperty("ir.hibernate.jdbc.batch_size"));
        hibernateProperties.setProperty("hibernate.jdbc.batch_size", "500");

//        hibernateProperties.setProperty("hibernate.show_sql",env.getProperty("ir.hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.show_sql","false");
        // hibernateProperties.setProperty("hibernate.format_sql", "true");
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");

        return hibernateProperties;
    }
    

}