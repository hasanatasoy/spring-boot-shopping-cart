package com.hasanatasoy.shoppingcart;

import com.hasanatasoy.shoppingcart.domain.user.role.RoleName;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRole;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

	@Bean
	public CommandLineRunner firstRun(UserRoleRepository userRoleRepository){
		return args -> {
			UserRole client = new UserRole(RoleName.Client);
			UserRole admin = new UserRole(RoleName.Admin);
			UserRole helperEmployee = new UserRole(RoleName.HELPEREMPLOYEE);
			userRoleRepository.save(client);
			userRoleRepository.save(admin);
			userRoleRepository.save(helperEmployee);
		};
	}
}
