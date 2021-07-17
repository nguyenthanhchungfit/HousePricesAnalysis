package chotot.crawler.entities;

import chotot.crawler.utils.JsonUtils;
import chotot.crawler.utils.MapRegionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

/**
 * @author chungnt
 */
public class ApartmentInfo {
	private long apartId;
	private String subject;
	private String body;
	private int categoryId;
	private String categoryName;
	private int areaId;
	private String areaName;
	private int regionId;
	private String regionName;
	private int subRegionId;
	private String subRegionName;
	private int wardId;
	private String wardName;
	private int streetId;
	private String streetNumber;
	private String address;
	private double longitude;
	private double latitude;
	private long price;
	private int numRooms;
	private int numToilets;
	private int ereaSquare;
	private String phone;

	private static final long RANGE_PRICE = 500000000; // 500.000.000

	public static ApartmentInfo parseInfo(Map<String, Object> mapData) {
		ApartmentInfo apartmentInfo = new ApartmentInfo();
		apartmentInfo.apartId = (Integer) mapData.getOrDefault("list_id", "0");
		apartmentInfo.subject = (String) mapData.getOrDefault("subject", "");
		apartmentInfo.body = (String) mapData.getOrDefault("body", "");
		apartmentInfo.categoryId = (Integer) mapData.getOrDefault("category", "0");
		apartmentInfo.categoryName = (String) mapData.getOrDefault("category_name", "");
		apartmentInfo.areaId = (Integer) mapData.getOrDefault("area", "0");
		apartmentInfo.areaName = (String) mapData.getOrDefault("area_name", "");
		apartmentInfo.regionId = (Integer) mapData.getOrDefault("region", "0");
		apartmentInfo.regionName = (String) mapData.getOrDefault("region_name", "");
		apartmentInfo.wardId = (Integer) mapData.getOrDefault("ward", "0");
		apartmentInfo.wardName = (String) mapData.getOrDefault("ward_name", "");
		apartmentInfo.streetId = (Integer) mapData.getOrDefault("street_id", "0");
		apartmentInfo.streetNumber = (String) mapData.getOrDefault("street_number", "");
		apartmentInfo.address = (String) mapData.getOrDefault("address", "");
		apartmentInfo.longitude = (Double) mapData.getOrDefault("longitude", "0");
		apartmentInfo.latitude = (Double) mapData.getOrDefault("latitude", "0");
		apartmentInfo.price = (Long) mapData.getOrDefault("price", "0");
		apartmentInfo.numRooms = (Integer) mapData.getOrDefault("rooms", "0");
		apartmentInfo.numToilets = (Integer) mapData.getOrDefault("toilets", "0");
		apartmentInfo.ereaSquare = (Integer) mapData.getOrDefault("size", "0");
		apartmentInfo.phone = (String) mapData.getOrDefault("phone", "");

		// modify
		if (apartmentInfo.price < RANGE_PRICE) { // it is price per m2
			apartmentInfo.price = apartmentInfo.price * apartmentInfo.ereaSquare;
		}
		return apartmentInfo;
	}

	public static ApartmentInfo parseInfoV2(Map<String, Object> mapData, String district) {
		ApartmentInfo apartmentInfo = new ApartmentInfo();
		apartmentInfo.apartId = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("list_id", "0")), 0);
		apartmentInfo.subject = String.valueOf(mapData.getOrDefault("subject", ""));
		apartmentInfo.body = String.valueOf(mapData.getOrDefault("body", ""));
		apartmentInfo.categoryId = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("category", "0")), 0);
		apartmentInfo.categoryName = String.valueOf(mapData.getOrDefault("category_name", ""));
		apartmentInfo.areaId = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("area", "0")), 0);
		apartmentInfo.areaName = String.valueOf(mapData.getOrDefault("area_name", ""));
		apartmentInfo.regionId = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("region", "0")), 0);
		apartmentInfo.regionName = String.valueOf(mapData.getOrDefault("region_name", ""));
		apartmentInfo.wardId = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("ward", "0")), 0);
		apartmentInfo.wardName = String.valueOf(mapData.getOrDefault("ward_name", ""));
		apartmentInfo.streetId = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("street_id", "0")), 0);
		apartmentInfo.streetNumber = String.valueOf(mapData.getOrDefault("street_number", ""));
		apartmentInfo.address = String.valueOf(mapData.getOrDefault("address", ""));
		apartmentInfo.longitude = NumberUtils.toDouble(String.valueOf(mapData.getOrDefault("longitude", "0")), 0);
		apartmentInfo.latitude = NumberUtils.toDouble(String.valueOf(mapData.getOrDefault("latitude", "0")), 0);
		apartmentInfo.price = NumberUtils.toLong(String.valueOf(mapData.getOrDefault("price", "0")));
//		System.out.println("price: " + apartmentInfo.price + ", str: " + mapData.getOrDefault("price", "price_string"));
		apartmentInfo.numRooms = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("rooms", "0")), 0);
		apartmentInfo.numToilets = NumberUtils.toInt(String.valueOf(mapData.getOrDefault("toilets", "0")), 0);
		apartmentInfo.ereaSquare = (int) NumberUtils.toDouble(String.valueOf(mapData.getOrDefault("size", "0")), 0);
		apartmentInfo.phone = String.valueOf(mapData.getOrDefault("phone", ""));

		// modify
		if (apartmentInfo.price < RANGE_PRICE) { // it is price per m2
			apartmentInfo.price = apartmentInfo.price * apartmentInfo.ereaSquare;
		}
		int subRegionId = MapRegionUtils.INSTANCE.getSubRegionIdByDistrict(district);
		if (subRegionId > 0) {
			String subRegionName = MapRegionUtils.INSTANCE.getSubRegionNameById(subRegionId);
			apartmentInfo.subRegionId = subRegionId;
			apartmentInfo.subRegionName = subRegionName;
		}
		return apartmentInfo;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public int getAreaId() {
		return areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public int getRegionId() {
		return regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public int getWardId() {
		return wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public int getStreetId() {
		return streetId;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getAddress() {
		return address;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public long getPrice() {
		return price;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public int getNumToilets() {
		return numToilets;
	}

	public int getEreaSquare() {
		return ereaSquare;
	}

	public String getPhone() {
		return phone;
	}

	public long getApartId() {
		return apartId;
	}

	public int getSubRegionId() {
		return subRegionId;
	}

	public String getSubRegionName() {
		return subRegionName;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
