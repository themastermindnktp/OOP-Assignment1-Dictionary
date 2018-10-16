package dictionary;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement{
    public static void insertFromCommandline(){
        String engWord, definition;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao so luong tu: ");
        int numberOfWord = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < numberOfWord; i++){
            System.out.print("Nhap tu Tieng Anh: ");
            engWord = sc.nextLine();
            System.out.print("Nhap giai thich tieng Viet: ");
            definition = sc.nextLine();
            Dictionary.insertNewWord(new Word(engWord, definition));
        }
    }

    public static void insertFromFile(){

        try {
            File file   = new File("dictionaries.txt");
            Scanner sc  = new Scanner(file);
            while (sc.hasNextLine()){
                Dictionary.insertNewWord(new Word(sc.next(), sc.next() + sc.nextLine()));
            }
            sc.close();
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }

    public static void dictionaryLookup(){
        Scanner sc = new Scanner(System.in);
        String string;
        ArrayList<Word> arrl;
        int tmp;
        while(true){
            System.out.print("Nhap tu can tra cuu: ");
            string = sc.nextLine();
            Word word = Dictionary.searchWord(string);
            if (word != null) System.out.println(word.getDefinition());
            else {
                arrl = Dictionary.suggestion(string);
                tmp = arrl.size();
                if (tmp == 0) System.out.println("Khong co tu tim kiem phu hop!");
                else {
                    System.out.println("Khong tim thay, co the ban dang tim kiem nhung tu nay");
                    for(int i = 0; i < tmp; i++) System.out.println(arrl.get(i).getEngWord());
                }
            }
        }
    }

}