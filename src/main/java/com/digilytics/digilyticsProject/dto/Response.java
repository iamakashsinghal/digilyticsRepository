package com.digilytics.digilyticsProject.dto;
/**
 * @author akash
 *
 */
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public class Response<T> {
	private long no_of_rows_parsed;
	private long no_of_rows_failed;

	public static ResponseEntity successResponse(Object entity) {
		return ResponseEntity.ok(entity);

	}

	public static ResponseEntity errorResponse(String errorMessage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", errorMessage);
		map.put("code", "417");
		return ResponseEntity.ok(map);

	}

	public Response(long no_of_rows_parsed, long no_of_rows_failed) {
		super();
		this.no_of_rows_parsed = no_of_rows_parsed;
		this.no_of_rows_failed = no_of_rows_failed;
	}

	public Response() {

	}

	public long getNo_of_rows_parsed() {
		return no_of_rows_parsed;
	}

	public void setNo_of_rows_parsed(long no_of_rows_parsed) {
		this.no_of_rows_parsed = no_of_rows_parsed;
	}

	public long getNo_of_rows_failed() {
		return no_of_rows_failed;
	}

	public void setNo_of_rows_failed(long no_of_rows_failed) {
		this.no_of_rows_failed = no_of_rows_failed;
	}

}
