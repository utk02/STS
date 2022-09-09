package com.te.springsecurity;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.springsecurity.entity.Admin;
import com.te.springsecurity.entity.AppUser;
import com.te.springsecurity.entity.Employee;
import com.te.springsecurity.entity.Mentor;
import com.te.springsecurity.entity.Role;
import com.te.springsecurity.repository.AdminRepository;
import com.te.springsecurity.repository.AppUserRepository;
import com.te.springsecurity.repository.EmployeeRepository;
import com.te.springsecurity.repository.MentorRepository;
import com.te.springsecurity.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class LearnSpringSecurityApplication {
	private final RoleRepository roleRepository;
	private final AdminRepository adminRepository;
	private final EmployeeRepository employeeRepository;
	private final MentorRepository mentorRepository;
	private final AppUserRepository appUserRepository;

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveInDb() {

		return a -> {
			Optional<AppUser> findById = appUserRepository.findById("ADM01");
			if (!findById.isPresent()) {
				Role adminRole = new Role();
				adminRole.setRoleName("ROLE_ADMIN");
				Role mentorRole = new Role();
				mentorRole.setRoleName("ROLE_MENTOR");
				Role employeeRole = new Role();
				employeeRole.setRoleName("ROLE_EMPLOYEE");
				roleRepository.save(adminRole);
				roleRepository.save(mentorRole);
				roleRepository.save(employeeRole);

				Admin admin = new Admin();
				admin.setAdminId("ADM01");
				admin.setAdminName("Admin001");
				admin.setAdminEmail("admin001@ty.com");
				adminRepository.save(admin);
				AppUser appUser1 = new AppUser();
				appUser1.setUsername(admin.getAdminId());
				appUser1.setPassword(passwordEncoder.encode("qwerty"));
				appUser1.setRoles(Arrays.asList(adminRole));
				appUserRepository.save(appUser1);

				Mentor mentor = new Mentor();
				mentor.setMentorId("MENT01");
				mentor.setMentorName("Mentor001");
				mentor.setMentorEmail("mentor001@ty.com");
				mentorRepository.save(mentor);
				AppUser appUser2 = new AppUser();
				appUser2.setUsername(mentor.getMentorId());
				appUser2.setPassword(passwordEncoder.encode("qwerty"));
				appUser2.setRoles(Arrays.asList(mentorRole));
				appUserRepository.save(appUser2);

				Employee employee = new Employee();
				employee.setEmployeeId("EMP01");
				employee.setEmployeeName("Employee001");
				employee.setEmployeeEmail("employee001@ty.com");
				employeeRepository.save(employee);
				AppUser appUser3 = new AppUser();
				appUser3.setUsername(employee.getEmployeeId());
				appUser3.setPassword(passwordEncoder.encode("qwerty"));
				appUser3.setRoles(Arrays.asList(employeeRole));
				appUserRepository.save(appUser3);
			}
		};
	}

}
