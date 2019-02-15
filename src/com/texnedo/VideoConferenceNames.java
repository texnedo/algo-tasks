package com.texnedo;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class VideoConferenceNames {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<String> names = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());
        List<String> res = VideoConferenceNames.solve(names);
        bufferedWriter.write(
                res.stream()
                        .collect(joining("\n"))
                        + "\n"
        );
        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<String> solve(List<String> names) {
        HashMap<String, Integer> dict = new HashMap<>();
        List<String> result = new ArrayList<>(names.size());
        for (String name : names) {
            String alias = null;
            for (int i = 1; i <= name.length(); i++) {
                String token = name.substring(0, i);
                if (dict.putIfAbsent(token, 1) == null && alias == null) {
                    alias = token;
                }
            }
            if (alias == null) {
                Integer count = dict.get(name);
                if (count == null) {
                    throw new IllegalArgumentException();
                }
                alias = name + " " + Integer.toString(++count);
                dict.put(name, count);
            }
            result.add(alias);
        }
        return result;
    }
}
