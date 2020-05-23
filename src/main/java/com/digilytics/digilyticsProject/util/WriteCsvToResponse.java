package com.digilytics.digilyticsProject.util;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.digilytics.digilyticsProject.entity.User;
/**
 * @author akash
 *
 */
public class WriteCsvToResponse {

	public static void writeObjectToCSV(PrintWriter writer, List<User> users) {
		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("Email", "Name", "Role", "Errors"));) {
				for (User user : users) {
					List<String> data = Arrays.asList(user.getErrorEmail(), user.getName(), user.getErrorRoll(),
							user.getError());
					csvPrinter.printRecord(data);

				}
				csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

}