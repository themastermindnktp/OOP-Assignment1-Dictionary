/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    /*
        DictionaryManagement class has the mission to import and export
        the data of the dictionary between the program and data source
    */

    // Import the data from commandline
    public static void importFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print("So luong tu muon nhap: ");            // Enter the number of input word
        int numberOfWord = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numberOfWord; i++) {
            System.out.print("Nhap tu Tieng Anh: ");            // Enter the English word
            String engWord = sc.nextLine().toLowerCase();
            System.out.print("Nhap dinh nghia Tieng Viet: ");   // Enter the Vietnamese definition
            String definition = sc.nextLine().toLowerCase();
            Dictionary.insert(new Word(engWord, definition));
        }
    }

    // Import the data from file
    public static void importFromFile(String inputFileName) {
        try {
            File f = new File(inputFileName);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().toLowerCase().split("\t");
                Dictionary.insert(new Word(line[0], line[1]));
            }
            sc.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Export the data to file
    public static void exportToFile(String outputFileName) {
        try {
            File f = new File(outputFileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            ArrayList<Word> arr = Dictionary.all();
            for (int i = 0, n = Dictionary.size(); i < n; i++) {
                Word word = arr.get(i);
                bw.write(word.getEngWord() + "\t" + word.getDefinition());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}