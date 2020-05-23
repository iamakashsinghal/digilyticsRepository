package com.digilytics.digilyticsProject.serviceimpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digilytics.digilyticsProject.dto.ErrorResponse;
import com.digilytics.digilyticsProject.dto.Response;
import com.digilytics.digilyticsProject.entity.Role;
import com.digilytics.digilyticsProject.entity.User;
import com.digilytics.digilyticsProject.repository.RoleRepository;
import com.digilytics.digilyticsProject.repository.UserRepository;
import com.digilytics.digilyticsProject.service.UserService;

/**
 * @author akash
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Response<?> register(MultipartFile file) throws IOException {
		Response response = new Response();
		long no_of_rows_parsed = 0;
		long no_of_rows_failed = 0;
		try (Reader reader = new InputStreamReader(file.getInputStream());
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
			for (CSVRecord csvRecord : csvParser) {
				String email = csvRecord.get(0);
				String name = csvRecord.get(1);
				String roles = csvRecord.get(2);
				User user = new User();
				Role roleName = roleRepository.findByRoleName(roles);
				//User userCheckEmail = userRepository.findAllByEmailId(email);
				//User userCheckErrorEmail = userRepository.findAllByEmailId(email);
				if (!email.equalsIgnoreCase("Email") && !name.equalsIgnoreCase("Name")
						&& !roles.equalsIgnoreCase("Roles")) {
					user.setName(name);
					ErrorResponse errorResponse = validateEmail(email, roles);
					if (errorResponse.isValidationFlage()) {
						user.setErrorEmail(errorResponse.getEmail());
						user.setErrorRoll(errorResponse.getRole());
						user.setError(errorResponse.getError());
						user.setErrorFlag(true);
						no_of_rows_failed++;

					} else {
						no_of_rows_parsed++;
					}
					user.setEmailId(email);
					user.setRole(roleName);
					userRepository.save(user);
					LOGGER.info("User Record Inserted..");

				}
				response.setNo_of_rows_parsed(no_of_rows_parsed);
				response.setNo_of_rows_failed(no_of_rows_failed);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			LOGGER.error("User Record Insert data failed. " + e.getMessage());
		}
		return response;
	}

	public ErrorResponse validateEmail(String email, String role) {
		ErrorResponse errorResponse = new ErrorResponse();
		Role roleName = roleRepository.findByRoleName(role);
		if (roleName.getRoleName().equals("SU") || roleName.getRoleName().equals("ADMIN")
				|| roleName.getRoleName().equals("USER")) {
			boolean checkEmail = isValid(email);
			if (!checkEmail) {
				String[] parts = email.split("@");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				String part4 = part1 + "@" + part2 + part3;
				errorResponse.setEmail(part4);
				errorResponse.setRole(roleName.getRoleName());
				errorResponse.setError("Invalid Email#Invalid Role " + role);
				errorResponse.setValidationFlage(true);
			}
		} else {
			errorResponse.setValidationFlage(false);
		}
		return errorResponse;

	}

	static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}
