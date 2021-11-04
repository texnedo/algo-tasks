package com.texnedo;

public class WordAbbreviation {
    public static void main(String[] args) {
        WordAbbreviation check = new WordAbbreviation();
        System.out.println(check.validWordAbbreviation("internationalization", "i5a11o1"));
        System.out.println(check.validWordAbbreviation("apple", "a2e"));
        System.out.println(check.validWordAbbreviation("s ubstitutio n", "s10n"));
        System.out.println(check.validWordAbbreviation("s ubsti tutio n", "s55n"));
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        StringBuilder number = null;
        int wordIndex = 0;
        for (int i = 0; i < abbr.length(); i++) {
            if (wordIndex == word.length()) {
                return false;
            }
            char abbrCurrent = abbr.charAt(i);
            if (Character.isDigit(abbrCurrent)) {
                if (number == null) {
                    number = new StringBuilder();
                }
                number.append(abbrCurrent);
            } else {
                char wordCurrent;
                if (number != null) {
                    int anyCharCount = Integer.parseInt(number.toString());
                    number = null;
                    int restWordCount = word.length() - wordIndex;
                    if (restWordCount == anyCharCount) {
                        return true;
                    } else if (restWordCount < anyCharCount) {
                        return false;
                    }
                    while (anyCharCount > 0) {
                        if (wordIndex == word.length()) {
                            return false;
                        }
                        wordCurrent = word.charAt(wordIndex);
                        if (wordCurrent != ' ') {
                            anyCharCount--;
                        }
                        wordIndex++;
                    }
                }
                wordCurrent = word.charAt(wordIndex);
                if (wordCurrent != abbrCurrent) {
                    return false;
                }
                wordIndex++;
            }
        }
        if (number != null) {
            char wordCurrent;
            int anyCharCount = Integer.parseInt(number.toString());
            int restWordCount = word.length() - wordIndex;
            if (restWordCount == anyCharCount) {
                return true;
            } else if (restWordCount < anyCharCount) {
                return false;
            }
            while (anyCharCount > 0) {
                if (wordIndex == word.length()) {
                    return false;
                }
                wordCurrent = word.charAt(wordIndex);
                if (wordCurrent != ' ') {
                    anyCharCount--;
                }
                wordIndex++;
            }
        }
        return true;
    }
}
