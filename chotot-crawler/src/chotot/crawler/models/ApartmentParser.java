package chotot.crawler.models;

import chotot.crawler.entities.ApartmentInfo;
import chotot.crawler.utils.JsonUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * @author chungnt
 */
public class ApartmentParser {

    public static ApartmentParser INSTANCE = new ApartmentParser();

    public ApartmentInfo crawlApartInfo(String url) {
        ApartmentInfo apartmentInfo = null;
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0")
                    .followRedirects(true).
                            ignoreContentType(true).get();
            String jsonData = doc.getElementById("__NEXT_DATA__").data().toString();
            Map<String, Object> mapData = JsonUtils.getMap(jsonData);
            // props  >> initialState >> adView >> adInfo >> ad
            if (mapData.containsKey("props")) {
                Map<String, Object> mapProps = (Map<String, Object>) mapData.get("props");
                if (mapProps.containsKey("initialState")) {
                    Map<String, Object> mapInitialState = (Map<String, Object>) mapProps.get("initialState");
                    if (mapInitialState.containsKey("adView")) {
                        Map<String, Object> mapAdView = (Map<String, Object>) mapInitialState.get("adView");
                        if (mapAdView.containsKey("adInfo")) {
                            Map<String, Object> mapAdInfo = (Map<String, Object>) mapAdView.get("adInfo");
                            if (mapAdInfo.containsKey("ad")) {
                                Map<String, Object> mapAd = (Map<String, Object>) mapAdInfo.get("ad");
                                apartmentInfo = ApartmentInfo.parseInfo(mapAd);
                            }
                        }
                    }
                }
            }
//            System.out.println("mapData: " + mapData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apartmentInfo;
    }
}
