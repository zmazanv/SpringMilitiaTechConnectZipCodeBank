package bank.connect.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class TechConnectZipCodeBankApplication {

	public static Logger logger = Logger.getGlobal();

	public static void main(String[] args) {
		SpringApplication.run(TechConnectZipCodeBankApplication.class, args);
	}
}
