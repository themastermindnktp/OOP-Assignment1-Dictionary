/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

import java.util.ArrayList;

public class Dictionary
{
    /*
        Dictionary class arranges and control all the data of the program,
        provides methods to access, extract and change every information
        of the dictionary.
    */

    private static Trie vocabulary = new Trie(); // The set of words, saved and managed by a trie

    // Provide the number of words in the dictionary
    public static int size()
    {
        return vocabulary.getSize();
    }

    // Find and return the information a given English word in the dictionary
    // If the English has not been existing in dictionary, the method will return null pointer
    public static Word find(String key)
    {
        return vocabulary.find(key);
    }

    // Add the new word into the dictionary
    // If the English word has already existed, its definition will be replaced
    public static void insert(Word newWord)
    {
        vocabulary.insert(newWord);
    }

    // Remove the an English word and its definition from the dictionary if it exists
    public static void delete(String key)
    {
        vocabulary.delete(key);
    }

    // Provide a list of words which have the same prefix as the a given string
    public static ArrayList<Word> searchByPrefix(String prefix)
    {
        return vocabulary.hasPrefix(prefix);
    }

    // Provide a list of all words in the dictionary
    public static ArrayList<Word> all()
    {
        return vocabulary.hasPrefix(""); // Get all words by method hasPrefix() with the prefix is the empty string
    }

    // Erase the data of all words in the dictionary
    public static void clear()
    {
        vocabulary.clear();
    }

}