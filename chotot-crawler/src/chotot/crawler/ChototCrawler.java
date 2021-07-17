/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chotot.crawler;

import chotot.crawler.entities.ApartmentInfo;
import chotot.crawler.models.ApartmentParser;
import chotot.crawler.models.ChoTotCrawlerModel;
import chotot.crawler.utils.ExcelUtils;
import chotot.crawler.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;

/**
 * @author chungnt
 */
public class ChototCrawler {

	private static final Logger _logger = LogManager.getLogger(ChototCrawler.class);


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		List<String> listDistrict = Arrays.asList("quan-1", "quan-2", "quan-3", "quan-4", "quan-5", "quan-6",
				"quan-7", "quan-8", "quan-9", "quan-10", "quan-11", "quan-12", "quan-binh-tan",
				"quan-binh-thanh", "quan-go-vap", "quan-phu-nhuan", "quan-tan-binh", "quan-tan-phu",
				"quan-thu-duc", "huyen-binh-chanh", "huyen-can-gio", "huyen-hoc-mon", "huyen-nha-be", "huyen-cu-chi");

		long sleepTime = 2000;
		int nItems = 100;
//		List<ApartmentInfo> listApartments = INSTANCE._parseListApartmentIds(url, district);
		List<ApartmentInfo> allApartments = new ArrayList<>();
		for (String district : listDistrict) {
			List<ApartmentInfo> listApartments = ChoTotCrawlerModel.INSTANCE.crawlByDistrict(district, sleepTime, nItems);
			if (listApartments != null && !listApartments.isEmpty()) {
				allApartments.addAll(listApartments);
			}
		}

//		System.out.println("result: " + JsonUtils.toJsonString(listApartments));
		String fileName = "chungcu_tphcm_data.xlsx";
		String sheetName = "data";
		List<String> headersFile = Arrays.asList("apartId", "subject", "areaId", "areaName", "subRegionId", "subRegionName",
				"price", "numRooms", "numToilets", "ereaSquare", "categoryId", "categoryName",
				"phone", "longitude", "latitude", "streetId", "streetNumber", "address", "wardId", "wardName", "regionId", "regionName");
		// "body"
		ExcelUtils.INSTANCE.writeFileExcel(fileName, sheetName, headersFile, allApartments);
	}

}


