package dictionary;

import java.util.ArrayList;

public class Trie
{
    private TrieNode root;
    private int size;
    private int tmpCounter;
    private ArrayList<Word> tmpList;

    public Trie()
    {
        root = new TrieNode();
        size = 0;
        tmpList = new ArrayList<>();
    }

    public int getSize()
    {
        return size;
    }

    public void insert(Word newWord)
    {
        size++;
        TrieNode current = root;
        String string = newWord.getEngWord();

        for(int i = 0, size = string.length(); i < size; ++i)
        {
            int index = string.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) childNodes[index] = new TrieNode();
            current = childNodes[index];
        }

        current.setEndOfWord(true);
        current.setData(newWord);
    }

    public Word find(String string)
    {
        TrieNode current = root;

        for(int i = 0, size = string.length(); i < size; ++i)
        {
            int index = string.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) return null;
            current = childNodes[index];
        }

        if (current.isEndOfWord()) return current.getData();
        return null;
    }

    private void scan(TrieNode current)
    {
        if (tmpCounter == 0) return;
        if (current.isEndOfWord()) tmpList.add(current.getData());

        TrieNode[] childNodes = current.getChildNodes();
        for(int i = 0; i < 256; ++i)
        {
            if (childNodes[i] != null) scan(childNodes[i]);
            if (tmpCounter == 0) return;
        }
    }

    public ArrayList<Word> hasPrefix(String prefix, int number)
    {
        tmpCounter = number;
        tmpList.clear();

        TrieNode current = root;

        for(int i = 0, size = prefix.length(); i < size; ++i)
        {
            int index = prefix.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) return tmpList;
            current = childNodes[index];
        }

        scan(current);
        return tmpList;
    }

    public void delete(String string)
    {
        TrieNode current = root;

        for(int i = 0, size = string.length(); i < size; ++i)
        {
            int index = string.charAt(i);
            TrieNode[] childNodes = current.getChildNodes();
            if (childNodes[index] == null) return;
            current = childNodes[index];
        }

        if (current.isEndOfWord())
        {
            size--;
            current.setEndOfWord(false);
        }
    }

}

