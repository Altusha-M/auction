package com.stc21.boot.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@SpringBootApplication
public class AuctionApplication {



	public static void main(String[] args) {
		SpringApplication.run(AuctionApplication.class, args);
	}

}
