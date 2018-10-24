/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

public class Word
{
    /*
        Word class is used to contain the information of
        an English word, its pronunciation and detail
    */

    private String engWord;         // English word
    private String pronunciation;   // English pronunciation
    private String detail;          // English detail

    public Word(String engWord, String pronunciation, String detail)
    {
        this.engWord        = engWord;
        this.pronunciation  = pronunciation;
        this.detail         = detail;
    }

    public String getEngWord()
    {
        return engWord;
    }

    public void setEngWord(String engWord)
    {
        this.engWord = engWord;
    }

    public String getPronunciation()
    {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation)
    {
        this.pronunciation = pronunciation;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}