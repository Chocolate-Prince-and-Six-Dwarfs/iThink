package com.choco.ithink.tool;

public class Tool {
    public static String delS(String s)
    {
        String replacement = "大家都是我爸爸";
        return s.replaceAll("(?i)<script[^>]*>(?:.*?)</script[^>]*>", replacement).replaceAll("(?i)&lt;[.]*script[^>]*&gt;", replacement);
    }
}
