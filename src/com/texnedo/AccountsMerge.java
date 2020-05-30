package com.texnedo;

import java.util.*;

/**
 * https://leetcode.com/problems/accounts-merge/
 * This task also could be solved with Union Fold algorithm.
 * */
public class AccountsMerge {
    public static void main(String[] args) {
        AccountsMerge merge = new AccountsMerge();
        String[][] data = {
                {"John", "johnsmith@mail.com", "john00@mail.com"},
                {"John", "johnnybravo@mail.com"},
                {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                {"Mary", "mary@mail.com"}};
        List<List<String>> input = new ArrayList<>(data.length);
        for (String[] item : data) {
            input.add(Arrays.asList(item));
        }
        System.out.println(merge.accountsMerge(input));
    }

    static class Account {
        boolean visited;
        final String name;
        final TreeSet<String> emails = new TreeSet<>();
        final HashSet<Account> connections = new HashSet<>();

        Account(String name) {
            this.name = name;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        final List<Account> accountList = buildAccounts(accounts);
        final HashMap<String, List<Account>> emailMap = new HashMap<>();
        for (Account account : accountList) {
            for (String email : account.emails) {
                emailMap.computeIfAbsent(email, k -> new LinkedList<>()).add(account);
            }
        }
        for (List<Account> group : emailMap.values()) {
            final ListIterator<Account> iterator = group.listIterator();
            if (!iterator.hasNext()) {
                throw new IllegalArgumentException();
            }
            final Account center = iterator.next();
            while (iterator.hasNext()) {
                final Account toMerge = iterator.next();
                center.connections.add(toMerge);
                toMerge.connections.add(center);
            }
        }
        final List<Account> mergedAccounts = new LinkedList<>();
        for (Account account : accountList) {
            if (!account.visited) {
                mergedAccounts.add(account);
                account.visited = true;
                merge(account);
            }
        }
        final List<List<String>> result = new ArrayList<>(mergedAccounts.size());
        for (Account account : mergedAccounts) {
            final ArrayList<String> content = new ArrayList<>(account.emails.size() + 1);
            content.add(account.name);
            content.addAll(account.emails);
            result.add(content);
        }
        return result;
    }

    private List<Account> buildAccounts(List<List<String>> accounts) {
        final List<Account> accountList = new ArrayList<>(accounts.size());
        for (List<String> account : accounts) {
            final ListIterator<String> iterator = account.listIterator();
            if (!iterator.hasNext()) {
                throw new IllegalArgumentException();
            }
            final String name = iterator.next();
            final Account user = new Account(name);
            while (iterator.hasNext()) {
                user.emails.add(iterator.next());
            }
            accountList.add(user);
        }
        return accountList;
    }

    private void merge(Account account) {
        final List<Account> level = new LinkedList<>(account.connections);
        while (!level.isEmpty()) {
            final List<Account> nextLevel = new LinkedList<>();
            for (Account child : level) {
                if (!child.visited) {
                    child.visited = true;
                    nextLevel.addAll(child.connections);
                    account.emails.addAll(child.emails);
                }
            }
            level.clear();
            level.addAll(nextLevel);
            nextLevel.clear();
        }
    }
}
