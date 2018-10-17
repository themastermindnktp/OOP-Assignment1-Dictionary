/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

import java.util.ArrayList;

public class Trie
{
    /*
        Trie class represents the same name data structure - Trie, which is also
        called Digital Tree, Radix Tree or Prefix Tree. It is a kind of search
        tree, used to store a dynamic set where keys are strings. A Trie can be
        seen as a tree-shaped Deterministic acyclic finite automaton.

        In this code, trie is used to save Word objects, whose key strings are
        equal to their engWord field.

        More details about how it should be implement can be seen on this link:
        <https://en.wikipedia.org/wiki/Trie>
    */

    private TrieNode root;              // The root node of the trie
    private int size;                   // The number of words contained in the trie

    private ArrayList<Word> tmpList;    // Temporary list used in recursion processes

    public Trie()
    {
        root = new TrieNode();
        size = 0;
        tmpList = new ArrayList<>();
    }

    public TrieNode getRoot()
    {
        return root;
    }

    public void setRoot(TrieNode root)
    {
        this.root = root;
    }

    public int getSize()
    {
        return size;
    }

    // Find and return the word associated with a given key in the trie
    // If the key has not been existing in trie, the method will return null pointer
    public Word find(String key)
    {
        TrieNode current = root;

        for(int i = 0, l = key.length(); i < l; ++i)
        {
            int index = key.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) return null;
            current = childNodes[index];
        }

        return current.getData();
    }

    // Add a new word to trie
    // If its engWord field - the key string has already existed, the associated data will be replaced
    public void insert(Word newWord)
    {
        String key = newWord.getEngWord();

        TrieNode current = root;

        for(int i = 0, l = key.length(); i < l; ++i)
        {
            int index = key.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) childNodes[index] = new TrieNode();
            current = childNodes[index];
        }

        if (current.getData() == null) size++;
        current.setData(newWord);
    }

    // Remove the data which is associated with a given key
    public void delete(String key)
    {
        TrieNode current = root;

        for(int i = 0, l = key.length(); i < l; ++i)
        {
            int index = key.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) return;
            current = childNodes[index];
        }

        if (current.getData() != null)
        {
            size--;
            current.setData(null);
        }
    }

    // Visit a node (DFS algorithm) and add all its data into the temporary list if possible
    // The set of data will be listed in alphabetical order of key strings
    private void scan(TrieNode current)
    {
        if (current.getData() != null) tmpList.add(current.getData());

        TrieNode[] childNodes = current.getChildNodes();
        for(int i = 0; i < 256; ++i)
            if (childNodes[i] != null) scan(childNodes[i]);
    }

    // Return an array of all the data associated with keys which has the same prefix as a given string
    public ArrayList<Word> hasPrefix(String prefix)
    {
        tmpList.clear();

        TrieNode current = root;

        for(int i = 0, l = prefix.length(); i < l; ++i)
        {
            int index = prefix.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) return tmpList;
            current = childNodes[index];
        }

        scan(current);
        return tmpList;
    }

    // Remake a new trie with no content
    public void clear()
    {
        root = new TrieNode();
        size = 0;
    }

}

