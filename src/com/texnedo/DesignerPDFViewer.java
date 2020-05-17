package com.texnedo;

public class DesignerPDFViewer {
    static int designerPdfViewer(int[] h, String word) {
        int maxHeight = 0;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            int index = current - 'a';
            int currentHeight = h[index];
            if (currentHeight > maxHeight) {
                maxHeight = currentHeight;
            }
        }
        return maxHeight * word.length();
    }
}
