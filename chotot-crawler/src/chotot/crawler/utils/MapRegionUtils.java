package chotot.crawler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chungnt
 */
public class MapRegionUtils {
	private static final Map<String, Integer> mapDistrictToSubRegion = new HashMap<>();
	private static final Map<Integer, String> mapSubRegion = new HashMap<>();

	public static final MapRegionUtils INSTANCE = new MapRegionUtils();

	static {
		mapSubRegion.put(1, "Khu Đông");
		mapSubRegion.put(2, "Khu Tây");
		mapSubRegion.put(3, "Khu Nam");
		mapSubRegion.put(4, "Khu Bắc");
		mapSubRegion.put(5, "Khu Trung Tâm");
		mapSubRegion.put(6, "Khu Khác");
		mapDistrictToSubRegion.put("quan-2", 1);
		mapDistrictToSubRegion.put("quan-9", 1);
		mapDistrictToSubRegion.put("quan-thu-duc", 1);
		mapDistrictToSubRegion.put("quan-binh-tan", 2);
		mapDistrictToSubRegion.put("huyen-binh-chanh", 2);
		mapDistrictToSubRegion.put("quan-7", 3);
		mapDistrictToSubRegion.put("huyen-nha-be", 3);
		mapDistrictToSubRegion.put("huyen-hoc-mon", 4);
		mapDistrictToSubRegion.put("quan-12", 4);
		mapDistrictToSubRegion.put("quan-1", 5);
		mapDistrictToSubRegion.put("quan-3", 5);
		mapDistrictToSubRegion.put("quan-4", 5);
		mapDistrictToSubRegion.put("quan-5", 5);
		mapDistrictToSubRegion.put("quan-6", 5);
		mapDistrictToSubRegion.put("quan-8", 5);
		mapDistrictToSubRegion.put("quan-10", 5);
		mapDistrictToSubRegion.put("quan-11", 5);
		mapDistrictToSubRegion.put("quan-binh-thanh", 5);
		mapDistrictToSubRegion.put("quan-go-vap", 5);
		mapDistrictToSubRegion.put("quan-phu-nhuan", 5);
		mapDistrictToSubRegion.put("quan-tan-binh", 5);
		mapDistrictToSubRegion.put("quan-tan-phu", 5);
		mapDistrictToSubRegion.put("huyen-can-gio", 6);
		mapDistrictToSubRegion.put("huyen-cu-chi", 6);
	}

	public int getSubRegionIdByDistrict(String district) {
		Integer subRegionId = mapDistrictToSubRegion.get(district);
		if (subRegionId != null) {
			return subRegionId;
		} else {
			return 0;
		}
	}

	public String getSubRegionNameById(int subRegionId) {
		String subRegionName = mapSubRegion.get(subRegionId);
		if (subRegionName != null) {
			return subRegionName;
		} else {
			return "";
		}
	}

}
