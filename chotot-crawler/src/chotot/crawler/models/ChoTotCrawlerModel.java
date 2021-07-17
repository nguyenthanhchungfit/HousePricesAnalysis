package chotot.crawler.models;

import chotot.crawler.entities.ApartmentInfo;
import chotot.crawler.utils.ExcelUtils;
import chotot.crawler.utils.JsonUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author chungnt
 */
public class ChoTotCrawlerModel {

	private static String URL_PATTERN = "https://nha.chotot.com/tp-ho-chi-minh/%s/mua-ban-can-ho-chung-cu";
	private static String URL_PATTERN_LIST = URL_PATTERN + "?sp=0&page=%d";

	private static final long MIN_PRICE = 1000000000; // 1.000.000.000 // 1 bilion

	public static final ChoTotCrawlerModel INSTANCE = new ChoTotCrawlerModel();

	public List<ApartmentInfo> crawlByDistrict(String district, long sleepTime, int maxItem) {
		System.out.println("******* crawlDataDistrict: " + district + " ******************\n");
		int curPage = 1;
		List<ApartmentInfo> allApartments = new ArrayList<>();
		boolean hasMore = true;
		while (hasMore) {
			String urlCrawl = String.format(URL_PATTERN_LIST, district, curPage);
			System.out.println("    >>> Crawl: " + urlCrawl);
			List<ApartmentInfo> listApartment = _parseListApartmentIds(urlCrawl, district);
			if (!listApartment.isEmpty()) {
				allApartments.addAll(listApartment);
				curPage++;
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				hasMore = false;
			}
			if (allApartments.size() > maxItem) { // only crawl nItem
				break;
			}

		}
		System.out.println("******* crawlDataDistrict: " + district + ", result: " + allApartments.size() + " ******************\n\n");
		return allApartments;
	}

	private List<ApartmentInfo> _parseListApartmentIds(String urlPage, String district) {
		List<ApartmentInfo> listApartments = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(urlPage).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0")
					.followRedirects(true).
							ignoreContentType(true).get();
			String jsonData = doc.getElementById("__NEXT_DATA__").data().toString();
			Map<String, Object> mapData = JsonUtils.getMap(jsonData);
			if (mapData.containsKey("props")) {
				Map<String, Object> mapProps = (Map<String, Object>) mapData.get("props");
				if (mapProps.containsKey("initialState")) {
					Map<String, Object> mapInitialState = (Map<String, Object>) mapProps.get("initialState");
					if (mapInitialState.containsKey("adlisting")) {
						Map<String, Object> mapAdListing = (Map<String, Object>) mapInitialState.get("adlisting");
						if (mapAdListing.containsKey("data")) {
							Map<String, Object> mapAdData = (Map<String, Object>) mapAdListing.get("data");
							if (mapAdData.containsKey("ads")) {
								List<Map<String, Object>> listData = (List<Map<String, Object>>) mapAdData.get("ads");
								for (Map<String, Object> mapDataApart : listData) {
									ApartmentInfo apartmentInfo = ApartmentInfo.parseInfoV2(mapDataApart, district);
									if (apartmentInfo != null && apartmentInfo.getPrice() > MIN_PRICE) {
										listApartments.add(apartmentInfo);
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listApartments;
	}

	public static void main(String[] args) {
		String url = "https://nha.chotot.com/tp-ho-chi-minh/quan-7/mua-ban-can-ho-chung-cu?sp=0&page=1";
		String district = "quan-7";
		long sleepTime = 2000;
		int nItems = 100;
//		List<ApartmentInfo> listApartments = INSTANCE._parseListApartmentIds(url, district);
		List<ApartmentInfo> listApartments = INSTANCE.crawlByDistrict(district, sleepTime, nItems);
		System.out.println("result: " + JsonUtils.toJsonString(listApartments));
		String fileName = "data_test.xlsx";
		String sheetName = "data";
		List<String> headersFile = Arrays.asList("apartId", "subject", "areaId", "areaName", "subRegionId", "subRegionName",
				"price", "numRooms", "numToilets", "ereaSquare", "categoryId", "categoryName",
				"phone", "longitude", "latitude", "streetId", "streetNumber", "address", "wardId", "wardName", "regionId", "regionName");
		// "body"
		ExcelUtils.INSTANCE.writeFileExcel(fileName, sheetName, headersFile, listApartments);
	}
}
