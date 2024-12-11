package org.interaction.interactionbackend;

import org.interaction.interactionbackend.util.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class InteractionBackendApplication {

	public static void main(String[] args) {
		// your local .env file path
		try {EnvLoader.loadEnv(".env");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(InteractionBackendApplication.class, args);
	}

}
