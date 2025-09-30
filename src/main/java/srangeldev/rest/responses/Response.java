package srangeldev.rest.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("Response")
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}
}