package com.digilytics.digilyticsProject.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digilytics.digilyticsProject.dto.Response;

/**
 * @author akash
 *
 */
@Service
public interface UserService {

	Response<?> register(MultipartFile file) throws IOException;
}
