package myproject;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyCustomEndpoint implements Endpoint<List<String>> {
	
	private static final Logger logger = LoggerFactory.getLogger(MyCustomEndpoint.class);
	
	@Autowired
	Environment environment;

	public String getId() {
		return "myCustom";
	}

	public List<String> invoke() {
		List<String> customList = new ArrayList<String>();
		try {
			customList.add("User : Akhilesh");
			customList.add("Server IP Address : " + InetAddress.getLocalHost().getHostAddress());
			customList.add("Server OS : " + System.getProperty("os.name").toLowerCase());
			customList.add("Current Time: "+new Date());	        
		} catch (Exception e) {
			logger.error("Error :", e);
		}
		return customList;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isSensitive() {
		return false;
	}

}