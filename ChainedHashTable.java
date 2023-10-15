/*
 * ChainedHashTable.java
 * 
 * Modifications and additions by:
 *     name: Tan Aydemir
 *     email: tanaydemir1@gmail.com
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Constructor ***/
    public ChainedHashTable(int size)
    {
        if (size<0)
        {
            throw new IllegalArgumentException();
        }
        table = new Node[size];

    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        int i = h1(key);
        Node trav = table[i];
        boolean check = true;
        while(trav!= null)
        {
            if (trav.key.equals(key))
            {
                trav.values.insert(value);
                check = false;
            }
            trav = trav.next;
        }
        if (check == true)
        {
            Node addtoFirst = new Node(key, value);
            addtoFirst.next = table[i];
            table[i] = addtoFirst;
            numKeys++;
            return true;
        }
        return false;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        if(key == null)
        {
            throw new IllegalArgumentException();
        }
        int j = h1(key);
        Node trav = table[j];
        
        while(trav != null)
        {
            if (trav.key.equals(key))
            {
                return trav.values;
            }
            trav = trav.next;
        }
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key);
        Node trav = table[i];
        Node prev = null;
        while(trav != null){
            if (trav.key.equals(key)){
                LLQueue<Object> stored = trav.values;
                if (prev == null){
                    table[i] = null;
                    prev = trav.next;
                }
                else{
                    prev = trav.next;
                    trav = null;
                }
                numKeys--;
                return stored;
            }
            prev = trav;
            trav = trav.next;
        }
        return null;
    }
    
    
/**
 * Returns the total number of keys in the hash table.
 *
 * @return The number of keys in the hash table.
 */
public int getNumKeys() {
    return numKeys;
}

/**
 * Calculates and returns the load factor of the hash table.
 *
 * @return The load factor, which is the ratio of the number of keys to the table size.
 */
public double load() {
    return (double) getNumKeys() / table.length;
}

/**
 * Retrieves all the keys from the hash table and returns them as an array.
 *
 * @return An array containing all the keys in the hash table.
 */
public Object[] getAllKeys() {
    Object[] allValues = new Object[numKeys];
    int counter = 0;
    for (int i = 0; i < table.length; i++) {
        Node travel = table[i];
        while (travel != null) {
            allValues[counter] = travel.key;
            travel = travel.next;
            counter++;
        }
    }
    return allValues;
}

/**
 * Resizes the hash table to the specified size and rehashes the existing elements.
 *
 * @param size The new size of the hash table.
 */
public void resize(int size) {
    Node[] t2 = table;
    Node[] t3 = new Node[size];
    table = t3;

    numKeys = 0;
    for (int i = 0; i < t2.length; i++) {
        Node trav = t2[i];
        while (trav != null) {
            this.insert(trav.key, trav.values);
            trav = trav.next;
        }
    }
}

    
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. 
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        ChainedHashTable table = new ChainedHashTable(5);
        table.insert("howdy", 15);
        table.insert("goodbye", 10);
        System.out.println(table.insert("apple", 5));
        System.out.println(table);

        ChainedHashTable table2 = new ChainedHashTable(5);
        table2.insert("howdy", 15);
        table2.insert("goodbye", 10);
        table2.insert("apple", 5);
        System.out.println(table2.getNumKeys());
        table2.insert("howdy", 25);     // insert a duplicate
        System.out.println(table2.getNumKeys());


        ChainedHashTable table3 = new ChainedHashTable(5);
        table3.insert("howdy", 15);
        table3.insert("goodbye", 10);
        table3.insert("apple", 5);
        System.out.println(table3.load());
        table3.insert("pear", 6);
        System.out.println(table3.load());


        ChainedHashTable table4 = new ChainedHashTable(5);
        table4.insert("howdy", 15);
        table4.insert("goodbye", 10);
        table4.insert("apple", 5);
        table4.insert("howdy", 25);    // insert a duplicate
        Object[] keys = table4.getAllKeys();
        System.out.println(Arrays.toString(keys));

        ChainedHashTable table5 = new ChainedHashTable(5);
        table5.insert("howdy", 15);
        table5.insert("goodbye", 10);
        table5.insert("apple", 5);
        System.out.println(table5);
        table5.resize(7);
        System.out.println(table5);
    
    }
}
