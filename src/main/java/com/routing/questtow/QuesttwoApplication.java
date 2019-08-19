package com.routing.questtow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@SpringBootApplication
@Controller
public class QuesttwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesttwoApplication.class, args);
	}


	@RequestMapping("/myDoctor/{number}")
	@ResponseBody
	public Doctor myDoctor( @PathVariable int number,  @RequestParam(required = false ) Optional<String> details) {
		String name = "";
		int nbEp = 0 ;
		int ageOfStart = 0 ;

		if (number == 9 ){
			name = "Christopher Eccleston";
			nbEp = 13;
			ageOfStart = 41;
		}
		if (number == 10 ){
			name = "David Tennant";
			nbEp = 47;
			ageOfStart = 34;
		}
		if (number == 11 ){
			name = "Matt Smith";
			nbEp = 44;
			ageOfStart = 27;
		}
		if (number == 12 ){
			name = "Peter Capaldi";
			nbEp = 40;
			ageOfStart = 55;
		}
		if (number == 13 ){
			name = "Jodie Whittaker";
			nbEp = 11;
			ageOfStart = 35;
		}
		if (number > 13 ) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error 404 ");
		}
		if (number < 9) {

			throw new ResponseStatusException(HttpStatus.TEMPORARY_REDIRECT, "Erreur 303");
		}
		if (details.isPresent()){
			return new ExtendDoctor(number, name, nbEp, ageOfStart);

		} else {
			return new Doctor(number, name);
		}

	}


	class Doctor {

		private String name;
		private int number;

		public Doctor(int number, String name) {
			this.number = number;
			this.name = name;
		}

		public int getNumber() {
			return number;
		}

		public String getName() {
			return name;
		}
	}

	class ExtendDoctor extends Doctor {

		private int numbersOfEpisode;
		private int startAt;


		public ExtendDoctor(int number, String name, int numbersOfEpisode, int startAt) {
			super(number,name );
			this.numbersOfEpisode = numbersOfEpisode;
			this.startAt = startAt;
		}

		public int getNumbersOfEpisode() {
			return numbersOfEpisode;
		}

		public int getStartAt() {
			return startAt;
		}
	}

}
