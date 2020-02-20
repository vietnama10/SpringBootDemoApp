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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = {"vn.dunglq.datasource.primary.repository"},
	entityManagerFactoryRef = "primaryEntityManagerFactory",
	transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {
	@Autowired
    private Environment env;

	// Cấu hình primary datasource
	@Bean(name = "primaryDataSouce")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("primaryDataSouce") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
        		env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",
                env.getProperty("spring.jpa.show-sql"));
        
		return builder.dataSource(dataSource)
				.packages("vn.dunglq.datasource.primary.domain")
				.persistenceUnit("primaryEntityManagerFactory")
				.properties(properties)
				.build();
	}

	@Bean(name = "primaryTransactionManager")
	@Autowired
	@Primary
	PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
