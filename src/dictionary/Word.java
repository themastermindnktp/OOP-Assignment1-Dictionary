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

    public static String modify(String string)
    {
        String result = string.toLowerCase();
        while (result.indexOf("  ") != -1) result = result.replace("  ", " ");
        if (result.charAt(0) == ' ') result = result.substring(1);
        if (result.charAt(result.length()-1) == ' ') result = result.substring(0, result.length() - 1);
        return result;
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