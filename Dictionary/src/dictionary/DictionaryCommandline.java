package dictionary;


import java.util.ArrayList;

public class DictionaryCommandline{
    public static void write(String s1, String s2, String s3){
        System.out.print(s1);
        for (int i = 0; i < 10 - s1.length(); i++) System.out.print(" ");
        System.out.print("| " + s2);
        for (int i = 0; i < 30 - s2.length(); i++) System.out.print(" ");
        System.out.println("| " + s3);
    }
    public static void showAllWords(){
        write("No", "English", "Vietnamese");
        int Size = Dictionary.getSize();
        ArrayList<Word> words = Dictionary.all();
        for (int i = 0; i < Size; i++) write(Integer.toString(i + 1), words.get(i).getEngWord(), words.get(i).getDefinition());
    }

    public static void dictionaryBasic(){
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced(){
        DictionaryManagement.insertFromFile();
        //showAllWords();
        DictionaryManagement.dictionaryLookup();
    }

    public static void main(String[] args) {
        dictionaryAdvanced();

        //dictionaryBasic();
    }
}