package hash;

import java.util.LinkedList;

public class ChainHashingTable {
    private static final int SIZE = 100;
    private LinkedList<HashEntry>[] table;

    public ChainHashingTable() {
        table = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void put(String key, String value) {
        int index = hash(key);
        LinkedList<HashEntry> entries = table[index];
        for (HashEntry entry : entries) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        entries.add(new HashEntry(key, value));
    }

    public void remove(String key) {
        int index = hash(key);
        LinkedList<HashEntry> entries = table[index];
        for (HashEntry entry : entries) {
            if (entry.getKey().equals(key)) {
                entries.remove(entry);
                return;
            }
        }
    }

    public String get(String key) {
        int index = hash(key);
        LinkedList<HashEntry> entries = table[index];
        for (HashEntry entry : entries) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int hash(String key){
        return key.hashCode();
    }

    public static void main(String[] args) {
        ChainHashingTable hashingTable = new ChainHashingTable();

        hashingTable.put("1", "10");
        hashingTable.put("2", "20");
        hashingTable.put("3", "30");
        hashingTable.put("3", "50");

        System.out.println(hashingTable.get("3"));
    }
}
