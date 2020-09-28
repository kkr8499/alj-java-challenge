package jp.co.axa.apidemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableCaching
public class ApiDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(EmployeeRepository repo){
		long id = 1000;
		return (args) -> {
			// save a couple of data
			repo.save(new Employee( id ,"Michal Simpson", 450000, "IT"));
			repo.save(new Employee(id+ 1,"Jamila Micehlle", 320000, "HR"));
			repo.save(new Employee(id+ 1,"Tony Kal", 520000, "HR"));
			repo.save(new Employee(id+ 1,"Dhoni Singh", 580000, "IT"));


			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Employee emp : repo.findAll()) {
				log.info(emp.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Employee emp = repo.findById(1L).get();
			log.info("Employee found with findOne(1L):");
			log.info("--------------------------------");
			log.info(emp.toString());
			log.info("");
		};
	}

}
