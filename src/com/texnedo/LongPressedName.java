package com.texnedo;

public class LongPressedName {
    public static void main(String[] args) {
        LongPressedName name = new LongPressedName();
        System.out.println(name.isLongPressedName2("alex", "aaleex"));
        System.out.println(name.isLongPressedName2("saeed", "ssaaedd"));
        System.out.println(name.isLongPressedName2("saeed", "ssaaeeeedd"));
        System.out.println(name.isLongPressedName2("leelee", "lleeelee"));
        System.out.println(name.isLongPressedName2("alex", "aaleexz"));
        System.out.println(name.isLongPressedName2("alexb", "aaleex"));
    }

    public boolean isLongPressedName(String name, String typed) {
        int indexName = 0;
        int indexTyped = 0;
        while (indexName < name.length() && indexTyped < typed.length()) {
            char original = name.charAt(indexName);
            char real = typed.charAt(indexTyped);
            if (original == real) {
                indexName++;
                indexTyped++;
            } else if (indexName > 0) {
                char previousOriginal = name.charAt(indexName - 1);
                if (previousOriginal != real) {
                    return false;
                }
                indexTyped++;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isLongPressedName2(String name, String typed) {
        int indexName = 0;
        int indexTyped = 0;
        while (indexName < name.length() && indexTyped < typed.length()) {
            char original = name.charAt(indexName);
            int countSameOriginal = 0;
            while (indexName < name.length()) {
                char originalSame = name.charAt(indexName);
                if (originalSame != original) {
                    break;
                }
                countSameOriginal++;
                indexName++;
            }
            char real = typed.charAt(indexTyped);
            int countSameReal = 0;
            while (indexTyped < typed.length()) {
                char realSame = typed.charAt(indexTyped);
                if (realSame != real) {
                    break;
                }
                countSameReal++;
                indexTyped++;
            }
            if (original != real || countSameOriginal > countSameReal) {
                return false;
            }
        }
        return indexName == name.length() && indexTyped == typed.length();
    }
}
