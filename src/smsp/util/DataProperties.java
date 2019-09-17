package smsp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataProperties {
	
	public static String getValueFromProperties(String nameFile, String key) {
		
		Properties prop = new Properties();
		InputStream input = null;
		String valueProperties=null;
		try {
			input = new FileInputStream(nameFile);
			prop.load(input);
			valueProperties = prop.getProperty(key);
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return valueProperties;
	}
	

}
