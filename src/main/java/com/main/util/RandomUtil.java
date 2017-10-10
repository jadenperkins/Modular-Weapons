package com.main.util;

import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 5/14/2015.
 */
public class RandomUtil {
    public static <T> T random(List<T> list, Random r) {
        return list.get(randomIndex(list, r));
    }
    public static <T> int randomIndex(List<T> list, Random r) {
        return r.nextInt(list.size());
    }
}