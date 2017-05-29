package org.wscale.mountains;

/*
 * Copyright 2017 Valentin Durst (www.wscale.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.service.MountainService;

// We want the rule that there should be no main files, this is the only exception.
// That's why we turn checkstyle off here and keep the rule.

//CHECKSTYLE:OFF
@SpringBootApplication
@ComponentScan("org.wscale") // TODO I dont know what to think about this.
public class MsTemplateProjectApplication {

	@Autowired
	private MountainService mountainService;

	public static void main(final String[] args) {
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
	public CommandLineRunner commandLineRunner(final ApplicationContext apx) {
		return args -> {

			final MountainEntity montblanc = new MountainEntity(
					"Mont Blanc", 4200, 1786,
					new String[]{"Jacques Balmat", "Michel-Gabriel Paccard"}, LocalDate.now());
			montblanc.setMountainId(0L);
			this.mountainService.addMountain(montblanc);

			final MountainEntity mounteverest = new MountainEntity(
					"Mount Everest", 8848, 1953,
					new String[]{"Edmund Hillary", "Tenzing Norgay"}, LocalDate.now());
			mounteverest.setMountainId(0L);
			this.mountainService.addMountain(mounteverest);

			final MountainEntity lhotse = new MountainEntity(
					"Lhotse", 8516, 1956,
					new String[]{"Ernst Reiss", "Fritz Luchsinger"}, LocalDate.now());
			lhotse.setMountainId(0L);
			this.mountainService.addMountain(lhotse);

			final MountainEntity chopolu = new MountainEntity(
					"Cho Polu", 6735, 1984,
					new String[]{"Nil Bohigas"}, LocalDate.now());
			chopolu.setMountainId(0L);
			this.mountainService.addMountain(chopolu);
		};
	}
}
//CHECKSTYLE:ON