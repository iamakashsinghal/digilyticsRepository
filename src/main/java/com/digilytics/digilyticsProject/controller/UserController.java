package com.digilytics.digilyticsProject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digilytics.digilyticsProject.dto.Response;
import com.digilytics.digilyticsProject.entity.User;
import com.digilytics.digilyticsProject.repository.UserRepository;
import com.digilytics.digilyticsProject.service.UserService;
import com.digilytics.digilyticsProject.util.WriteCsvToResponse;

/**
 * @author akash
 *
 */
@RestController
public class UserController {

	private static final Log LOGGER = LogFactory.getLog(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		Response<?> response = null;
		try {
			response = userService.register(file);
			return Response.successResponse(response);
		} catch (NullPointerException e) {
			LOGGER.error("Save User Details Error Null Details:-" + e.getMessage());
			return Response.errorResponse(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Save User Details Error:-" + e.getMessage());
			return Response.errorResponse(e.getMessage());
		}
	}
	
	
	 @GetMapping("/download")
	  public void downloadCSV(HttpServletResponse response) throws IOException{
			response.addHeader("Content-Type", "application/csv");
			response.addHeader("Content-Disposition", "attachment; filename=errors.csv");
			response.setCharacterEncoding("UTF-8");
	    List<User> users = (List<User>) userRepository.findByErrorFlag(true); 
	    WriteCsvToResponse.writeObjectToCSV(response.getWriter(), users);
	    LOGGER.error("Download User Details Error:-" + response.getWriter());
	  }

}
