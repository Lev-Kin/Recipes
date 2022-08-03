import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        int count = 0;
        for (Map.Entry<String, String> entryMap1 : map1.entrySet()) {
            for (Map.Entry<String, String> entryMap2 : map2.entrySet()) {
                if (entryMap1.getKey().contains(entryMap2.getKey()) &&
                        entryMap1.getValue().contains(entryMap2.getValue())) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
