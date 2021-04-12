package com.texnedo;

import junit.framework.TestCase;
import org.junit.Assert;

public class ShiftingLettersTest extends TestCase {
    public void testShiftLetter() {
        Assert.assertEquals('b', ShiftingLetters.shiftLetter('a', 1));
        Assert.assertEquals('b', ShiftingLetters.shiftLetter('a', 26));
        Assert.assertEquals('b', ShiftingLetters.shiftLetter('z', 2));
    }
}