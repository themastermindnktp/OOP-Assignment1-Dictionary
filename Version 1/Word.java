public class Word{
    private String engWord;     //English word
    private String vieWord;     //Vietnamese definition of word

    /* Contructors, getters and setters */
    public Word(String engWord, String vieWord){
        this.engWord    = new String(engWord);
        this.vieWord    = new String(vieWord);
    }

    public Word(Word another){
        this(another.engWord, another.vieWord);
    }

    public String getEngWord(){
        return new String(engWord);
    }

    public void setEngWord(String engWord){
        this.engWord = new String(engWord);
    }

    public String getVieWord(){
        return new String(vieWord);
    }

    public void setVieWord(String vieWord){
        this.vieWord = new String(vieWord);
    }

}