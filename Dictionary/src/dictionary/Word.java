package dictionary;

public class Word
{
    private String engWord;     //English word
    private String definition;  //Vietnamese definition of word

    public Word(String engWord, String definition)
    {
        this.engWord    = engWord.toLowerCase();
        this.definition = definition.toLowerCase();
    }

    public String getEngWord()
    {
        return engWord;
    }

    public void setEngWord(String engWord)
    {
        this.engWord = engWord;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

}