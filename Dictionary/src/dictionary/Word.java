/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

public class Word
{
    /*
        Word class is used to contain the information of
        an English word and its Vietnamese definition
    */

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