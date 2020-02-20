package vn.dunglq.datasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = {"vn.dunglq.datasource.second.repository"},
	entityManagerFactoryRef = "secondEntityManagerFactory",
	transactionManagerRef = "secondTransactionManager"
)
public class SecondDataSourceConfig {

	@Autowired
	private Environment env;
	
	// Cấu hình second datasource
	@Bean(name = "secondDataSouce")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "secondEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("secondDataSouce") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
        		env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",
                env.getProperty("spring.jpa.show-sql"));
		return builder.dataSource(dataSource)
				.packages("vn.dunglq.datasource.second.domain")
				.persistenceUnit("secondEntityManagerFactory")
				.properties(properties)
				.build();
	}

	@Bean(name = "secondTransactionManager")
	@Autowired
	PlatformTransactionManager secondTransactionManager(@Qualifier("secondEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
