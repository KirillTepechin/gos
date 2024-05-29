package hash;

public class DoubleHashingTable {
    private static final int SIZE = 100;
    private HashEntry[] table;

    public DoubleHashingTable() {
        table = new HashEntry[SIZE];
    }

    public void put(String key, String value) {
        int hash1 = hashFunction1(key);
        int hash2 = hashFunction2(key);
        int index = hash1 % SIZE;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + hash2) % SIZE;
        }

        table[index] = new HashEntry(key, value);
    }

    public void remove(String key) {
        int hash1 = hashFunction1(key);
        int hash2 = hashFunction2(key);
        int index = hash1 % SIZE;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + hash2) % SIZE;
        }

        if (table[index] != null) {
            table[index] = null;
        }
    }

    public String get(String key) {
        int hash1 = hashFunction1(key);
        int hash2 = hashFunction2(key);
        int index = hash1 % SIZE;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + hash2) % SIZE;
        }

        if (table[index] != null) {
            return table[index].getValue();
        }

        return null;
    }

    private int hashFunction1(String key) {
        return key.hashCode();
    }

    private int hashFunction2(String key) {
        return 5 - (key.hashCode() % 5);
    }


    public static void main(String[] args) {
        DoubleHashingTable hashingTable = new DoubleHashingTable();

        hashingTable.put("1", "10");
        hashingTable.put("2", "20");
        hashingTable.put("3", "30");
        hashingTable.put("3", "50");
        hashingTable.remove("3");

        System.out.println(hashingTable.get("3"));
    }
}
