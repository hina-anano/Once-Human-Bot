package me.hina.utils;

import java.text.DecimalFormat;

public class MathUtils {

    public static String formatNumber(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        return formatter.format(number);
    }
}
