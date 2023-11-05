package pro.sky.telegram_chat_zooShelter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@OpenAPIDefinition
@EnableScheduling
public class TelegramChatZooShelterApplication {

	public static void main(String[] args) {

		SpringApplication.run(TelegramChatZooShelterApplication.class, args);
	}

}
