package dictionary;

import java.io.FileNotFoundException;
import java.io.File;
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
            Dictionary.insertNewWord(new Word(engWord.toLowerCase(), definition.toLowerCase()));
        }
    }

    public static void insertFromFile(){

        try {
            File file   = new File("dictionaries.txt");
            Scanner sc  = new Scanner(file);
            int i = 0;
            while (sc.hasNextLine()){
                String[] line = sc.nextLine().split("\t");
                Dictionary.insertNewWord(new Word(line[0].toLowerCase(), line[1].toLowerCase()));
//                System.out.println(i++);
            }
            sc.close();
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }



}