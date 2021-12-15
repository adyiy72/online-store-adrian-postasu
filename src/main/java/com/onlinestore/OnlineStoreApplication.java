package com.onlinestore;

import com.onlinestore.entities.Role;
import com.onlinestore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {

	private RoleService roleService;

	@Autowired
	public OnlineStoreApplication(RoleService roleService) {
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		roles.add("CUSTOMER");

		roles.stream().forEach(el -> {
			Role newRole = new Role();
			newRole.setName(el);
			roleService.save(newRole);
		});
	}
}
