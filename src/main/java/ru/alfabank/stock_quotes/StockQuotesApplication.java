package ru.alfabank.stock_quotes;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockQuotesApplication {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

}
