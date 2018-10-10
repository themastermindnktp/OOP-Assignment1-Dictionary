package dictionary;

public class TrieNode
{
    private Word data;
    private boolean endOfWord;
    private TrieNode[] childNodes;

    public TrieNode()
    {
        childNodes = new TrieNode[256];
        data = null;
    }

    public Word getData()
    {
        return data;
    }

    public void setData(Word word)
    {
        data = word;
    }

    public boolean isEndOfWord()
    {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord)
    {
        this.endOfWord = endOfWord;
    }

    public TrieNode[] getChildNodes()
    {
        return childNodes;
    }

}
