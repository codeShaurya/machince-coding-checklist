# machince-coding-checklist

1. Writing in file
2. Formatting string in simple java
3. Reading from a file inm java
4. forEAch Stream, interate wihtotu strem
5. intin set, and list
6. config readers
7. Enum ordinal
8. search in a set by class names

### 9. Sorting a list of Object
```
List<Account> topAccounts = new ArrayList<>(accounts.values());
topAccounts.sort((a, b) -> Double.compare(b.getBalance(), a.getBalance()));
```


### 10. Sum  in a stream
```
int withdrawalSum = (int) trxList.stream()
                        .filter(trx -> trx.getType() == Trx.Type.WITHDRAWAL)
                        .mapToDouble(Trx::getAmount)
                        .sum();
```

### 11. Sort a hashmap in reverse Order
```
Map<Integer, AccountWithTrx> topAccounts = new HashMap<>();
Map<Integer, AccountWithTrx> topAccountSorted = new java.util.TreeMap<>(java.util.Collections.reverseOrder());
```

### 12. Retrun Top N entries from the map
```
// Return only the top N entries (up to 3) as a new map
        return topAccounts.entrySet().stream()
                .limit(N)
                .collect(java.util.LinkedHashMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        java.util.LinkedHashMap::putAll
                );
```

