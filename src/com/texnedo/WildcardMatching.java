package com.texnedo;

import java.util.HashMap;

public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching matching = new WildcardMatching();
        System.out.println(matching.isMatch("aa", "a"));
        System.out.println(matching.isMatch("adceb", "*a*b"));
    }

    public boolean isMatch(String s, String p) {
        return isMatchInternal(s, p, new HashMap<>());
    }

    private boolean isMatchInternal(String s, String p, HashMap<Integer, Boolean> existing) {
        if (s == null || p == null) {
            return false;
        }
        if ("*".equals(p)) {
            return true;
        }
        if (s.equals(p)) {
            return true;
        }
        if (s.length() != 0 && p.length() == 0) {
            return false;
        }
        String matchKey = s + "|" + p;
        Boolean hasAlreadyMatched = existing.get(matchKey.hashCode());
        if (hasAlreadyMatched != null) {
            return hasAlreadyMatched;
        }
        if (s.length() > 0 && (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0))) {
            boolean matchResult = isMatchInternal(s.substring(1), p.substring(1), existing);
            existing.put(matchKey.hashCode(), matchResult);
            return matchResult;
        }
        if (p.charAt(0) == '*') {
            boolean matchEmpty = isMatchInternal(s, p.substring(1), existing);
            existing.put(matchKey.hashCode(), matchEmpty);
            if (matchEmpty) {
                return true;
            }
            boolean matchFull = s.length() > 0 && isMatchInternal(s.substring(1), p, existing);
            existing.put(matchKey.hashCode(), matchFull);
            return matchFull;
        }
        return false;
    }
}
