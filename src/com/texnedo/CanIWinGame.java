package com.texnedo;

import java.util.Arrays;
import java.util.HashMap;

public class CanIWinGame {
    public static void main(String[] args) {
        CanIWinGame game = new CanIWinGame();
        System.out.println(game.canFirstWin(10, 11));
        System.out.println(game.canFirstWin(10, 0));
        System.out.println(game.canFirstWin(10, 1));
        System.out.println(game.canFirstWin(10, 40));
        System.out.println(game.canFirstWin(4, 6));
    }

    public boolean canFirstWin(int maxChoosableInteger, int desiredTotal) {
        boolean[] usedIntegers = new boolean[maxChoosableInteger];
        HashMap<Integer, Boolean> cache = new HashMap<>();
        return canFirstWinInternal(usedIntegers, desiredTotal, 0, cache);
    }

    public boolean canFirstWinInternal(boolean[] usedIntegers,
                                        int desiredTotal,
                                        int total,
                                        HashMap<Integer, Boolean> cache) {
        int userIntegersHash = Arrays.hashCode(usedIntegers);
        Boolean cachedValue =  cache.get(userIntegersHash);
        if (cachedValue != null) {
            return cachedValue;
        }
        for (int i = 0; i < usedIntegers.length; i++) {
            if (!usedIntegers[i]) {
                int current = total + i + 1;
                if (current >= desiredTotal) {
                    cache.put(userIntegersHash, true);
                    return true;
                }
                usedIntegers[i] = true;
                boolean result = canFirstWinInternal(usedIntegers, desiredTotal, current, cache);
                usedIntegers[i] = false;
                if (!result) {
                    cache.put(userIntegersHash, true);
                    return true;
                }
            }
        }
        cache.put(userIntegersHash, false);
        return false;
    }
}
