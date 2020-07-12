package demo.util;

import java.util.List;

import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestSpecModel {
	private String baseUri;
	private ContentType contentType;
	private List<QueryParam> queryParamList;
	
	
}

@Getter
@Setter
@AllArgsConstructor
@Builder
class QueryParam {
	private String key;
	private String value;
}
