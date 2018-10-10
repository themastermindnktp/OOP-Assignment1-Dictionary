package dictionary;

import java.util.ArrayList;

public class Dictionary
{
    private final static int numberOfSuggestion = 10;
    private static Trie vocabulary = new Trie();

    public static int getSize()
    {
        return vocabulary.getSize();
    }

    public static void insertNewWord(Word newWord)
    {
        vocabulary.insert(newWord);
    }

    public static Word searchWord(String engWord)
    {
        return vocabulary.find(engWord);
    }

    public static ArrayList<Word> all()
    {
        return vocabulary.hasPrefix("", vocabulary.getSize());
    }

    public static ArrayList<Word> suggestion(String prefix)
    {
        return vocabulary.hasPrefix(prefix, numberOfSuggestion);
    }

}