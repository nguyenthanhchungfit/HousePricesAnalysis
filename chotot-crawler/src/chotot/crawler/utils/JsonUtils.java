/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chotot.crawler.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author chungnt
 */
public class JsonUtils {

//	private static final Logger logger = ZLogger.getLogger(JsonUtils.class);
	private static final ObjectMapper objMapper = new ObjectMapper();
	private static final JSONParser PARSER = new JSONParser();

	public static String toJsonString(Object obj) {
		String rs = "";
		try {
			if (objMapper != null) {
				rs = objMapper.writeValueAsString(obj);
			}
		} catch (Exception e) {
//			logger.error("Exception toJsonString: " + e.getMessage());
		}

		return rs;
	}

	public static <T> T fromJsonString(String jsString, Class<T> clazz) {
		try {
			return (T) objMapper.readValue(jsString, clazz);
		} catch (IOException e) {
			return null;
		}
	}

	public static <K, V> Map<K, V> getMapObject(TypeReference<Map<K, V>> type, String json) {
		try {
			return objMapper.readValue(json, type);
		} catch (Exception e) {
//			logger.error(e.getMessage());
			return null;
		}
	}

	public static Map<String, Object> getMap(String json) {
		try {
			return objMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
//			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T getObject(TypeReference<T> type, String json) {
		try {
			return objMapper.readValue(json, type);
		} catch (Exception e) {
//			logger.error("getObject: " + json, e);
			return null;
		}
	}

//	public static byte[] serialize(Object obj) {
//		if (obj == null) {
//			return null;
//		}
//
//		try {
//			ObjectMapper mapper = JacksonHelper1.getInstance(false);
//			return mapper.writeValueAsBytes(obj);
//		} catch (Exception e) {
//			logger.error("Error serialize json", e);
//			return null;
//		}
//	}

//	public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
//		if (bytes == null) {
//			return null;
//		}
//
//		try {
//			ObjectMapper mapper = JacksonHelper1.getInstance(false);
//			return mapper.readValue(bytes, clazz);
//		} catch (Exception e) {
//			logger.error("Error deserialize json", e);
//			return null;
//		}
//	}

	public static JSONObject parse(String jsonString) {
		try {
			return (JSONObject) PARSER.parse(jsonString);
		} catch (ParseException ex) {
//			logger.error(ex.getMessage(), ex);
		}
		return null;
	}
}
