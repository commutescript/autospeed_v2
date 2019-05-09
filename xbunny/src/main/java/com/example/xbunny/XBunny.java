package com.example.xbunny;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David.Yi
 * @Describe
 * @create 2019/5/9
 */
public class XBunny {

    HashMap<String, Integer> counts = new HashMap<>();
    HashMap<String, Boolean> loadApis = new HashMap<>();
    Map<String, Long> sStartTime = new HashMap<>();
    Map<String, Long> sEndTime = new HashMap<>();

    private static class BunnyHolder {
        private static XBunny instance = new XBunny();
    }

    public static XBunny getInstance() {
        return BunnyHolder.instance;
    }

    public void onColdStartBegin() {
        sStartTime.put("coldStart", System.nanoTime());
    }

    public void onColdStartEnd() {
        sEndTime.put("coldStart", System.nanoTime());

    }

    public void onPageDrawBegin(String pageName) {
        sStartTime.put(pageName, System.nanoTime());
    }

    public void onPageDrawEnd(String pageName) {
        storeCount(pageName);
        if (getCountByName(pageName) == 1 && isApiLoaded(pageName)) {
            sEndTime.put(pageName, System.nanoTime());
            // tag main page.
            if (pageName.contains("Main")) {
                onColdStartEnd();
            }
        }

        // TODO storage and report
        report();
    }

    public void storeCount(String pageName) {
        counts.put(pageName, counts.get(pageName) == null ? 1 : counts.get(pageName) + 1);
    }

    public int getCountByName(String pageName) {
        return counts.get(pageName) == null ? 0 : counts.get(pageName);

    }

    public String getColdStartTime() {
        long start = sStartTime.get("coldStart");
        long end = sEndTime.get("coldStart");
        DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
        return "cold start " + "spend: " + decimalFormat.format(Float.valueOf(end - start) / 1000000) + " ms";
    }

    public String getPageRenderTime(String pageName) {
        long start = sStartTime.get(pageName);
        long end = sEndTime.get(pageName);
        System.nanoTime();
        DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
        return "page '" + pageName + "'render spend: " + decimalFormat.format(Float.valueOf(end - start) / 1000000) + " ms";
    }


    public void onApiLoadBegin() {

    }

    public void onApiLoadEnd(String page) {
        loadApis.put(page, true);
    }

    public boolean isApiLoaded(String pageId) {

        return loadApis.get(pageId) != null;

    }

    private void report() {

    }
}
