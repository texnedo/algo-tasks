package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DinnerParty {
    public static void main(String[] args) {
        DinnerParty party = new DinnerParty();
        String[] friends = {"Bob", "Sally", "Joe", "Silvia"};
        List<List<List<String>>> result = party.generateCombinations(friends, 2, 2);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(String.format("Combination %d: %s", i, result.get(i)));
        }
    }

    List<List<List<String>>> generateCombinations(String[] friends, int tables, int tableCapacity) {
        if (tables <= 0) {
            return Collections.emptyList();
        }
        if (friends == null || friends.length == 0) {
            return new ArrayList<>(tables);
        }
        return generateCombinations(friends, tables, tableCapacity, 0);
    }

    List<List<List<String>>> generateCombinations(String[] friends,
                                                  int tables,
                                                  int tableCapacity,
                                                  int startIndex) {
        if (startIndex == friends.length - 1) {
            List<List<List<String>>> combinations = new ArrayList<>(tables);
            for (int i = 0; i < tables; i++) {
                ArrayList<List<String>> tableCombination = new ArrayList<>(tables);
                for (int j = 0; j < tables; j++) {
                    LinkedList<String> combination = new LinkedList<>();
                    if (j == i) {
                        combination.add(friends[startIndex]);
                    }
                    tableCombination.add(combination);
                }
                combinations.add(tableCombination);
            }
            return combinations;
        }
        List<List<List<String>>> combinations =
                generateCombinations(friends, tables, tableCapacity, startIndex + 1);
        List<List<List<String>>> extCombinations = new LinkedList<>();
        for (List<List<String>> tableCombination : combinations) {
            for (int i = 0; i < tableCombination.size(); i++) {
                List<List<String>> extTableCombination = new ArrayList<>(tableCombination.size());
                for (int j = 0; j < tableCombination.size(); j++) {
                    LinkedList<String> extCombination = new LinkedList<>(tableCombination.get(j));
                    if (j == i) {
                        extCombination.add(friends[startIndex]);
                        if (extCombination.size() > tableCapacity) {
                            extTableCombination = null;
                            break;
                        }
                    }
                    extTableCombination.add(extCombination);
                }
                if (extTableCombination != null) {
                    extCombinations.add(extTableCombination);
                }
            }
        }
        return extCombinations;
    }

    List<List<String>> generateCombinationsPossible(String[] friends) {
        if (friends == null || friends.length == 0) {
            return Collections.emptyList();
        }
        return generateCombinationsPossible(friends, 0);
    }

    List<List<String>> generateCombinationsPossible(String[] friends, int startIndex) {
        if (startIndex == friends.length - 1) {
            return Collections.singletonList(Collections.singletonList(friends[startIndex]));
        }
        List<List<String>> combinations =
                generateCombinationsPossible(friends, startIndex + 1);
        List<List<String>> extCombinations = new LinkedList<>();
        for (List<String> combination : combinations) {
            for (int i = 0; i <= combination.size(); i++) {
                List<String> extCombination = new ArrayList<>(combination.size() + 1);
                extCombination.addAll(combination);
                extCombination.add(i, friends[startIndex]);
                extCombinations.add(extCombination);
            }
        }
        return extCombinations;
    }
}
