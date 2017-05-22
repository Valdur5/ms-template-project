package de.pandigo;

import de.pandigo.controller.CountryController;
import de.pandigo.domain.CountryEntity;
import de.pandigo.domain.MountainEntity;
import de.pandigo.dto.Country;
import de.pandigo.dto.Mountain;
import de.pandigo.services.CountryService;
import de.pandigo.services.MountainService;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

// We want the rule that there should be no main files, this is the only exception.
// That's why we turn checkstyle off here and keep the rule.

//CHECKSTYLE:OFF
@SpringBootApplication
@EnableJSONDoc
public class MsTemplateProjectApplication {

	@Autowired
	private MountainService mountainService;

	@Autowired
	private CountryService countryService;

	public static void main(String[] args) {
		SpringApplication.run(MsTemplateProjectApplication.class, args);
	}

	/**
	 * This method is just for test purpose! Because our H2 database is cleared every restart we need to add some data
	 * so you don't have to enter test data every time you restart the application. This can be removed as soon as you
	 * connect a persistent database to this application.
	 * @param apx - Application context.
	 * @return - CommandLineRunner.
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext apx) {
		return args -> {
			CountryEntity nepal = new CountryEntity("Nepal", 26424000, LocalDate.now());
			//nepal = countryService.addCountry(nepal);
			CountryEntity tibet = new CountryEntity("Tibet", 5240504, LocalDate.now());
			//tibet = countryService.addCountry(tibet);
			CountryEntity france = new CountryEntity("France", 66910000, LocalDate.now());
			//france = countryService.addCountry(france);
			CountryEntity italy = new CountryEntity("Italy", 60599000, LocalDate.now());
			//italy = countryService.addCountry(italy);


			final MountainEntity montblanc = new MountainEntity(
					"Mont Blanc", 4200, Arrays.asList(france, italy),1786,
					new String[]{"Jacques Balmat", "Michel-Gabriel Paccard"}, LocalDate.now());
			mountainService.addMountain(montblanc);

			final MountainEntity mounteverest = new MountainEntity(
					"Mount Everest", 8848, Arrays.asList(nepal, tibet), 1953,
					new String[]{"Edmund Hillary", "Tenzing Norgay"}, LocalDate.now());
			mountainService.addMountain(mounteverest);

			/*final MountainEntity lhotse = new MountainEntity(
					"Lhotse", 8516, Arrays.asList(nepal, tibet), 1956,
					new String[]{"Ernst Reiss", "Fritz Luchsinger"}, LocalDate.now());
			mountainService.addMountain(lhotse);

			final MountainEntity chopolu = new MountainEntity(
					"Cho Polu", 6735, Arrays.asList(nepal), 1984,
					new String[]{"Nil Bohigas"}, LocalDate.now());
			mountainService.addMountain(chopolu);*/
		};
	}
}
//CHECKSTYLE:ON