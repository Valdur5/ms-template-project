package de.pandigo.mstemplateproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// We want the rule that there should be no main files, this is the only exception.
// That's why we turn checkstyle off here and keep the rule.

//CHECKSTYLE:OFF
@SpringBootApplication
public class MsTemplateProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTemplateProjectApplication.class, args);
	}
}
//CHECKSTYLE:ON