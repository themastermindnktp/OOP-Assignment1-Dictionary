/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

import java.io.*;
import java.util.ArrayList;

/* COMMANDLINE
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
*/

public class DictionaryManagement {
    /*
        DictionaryManagement class has the mission to import and export
        the data of the dictionary between the program and data source
    */
    public static String dataFileName = "dictionary.txt";

    public static String inputConvert(String string)
    {
        String result = string;
        result = result.replace("<br />", "\n");
        result = result.replace("=", "\t");
        return result;
    }

    public static String outputConvert(String string)
    {
        String result = string;
        result = result.replace("\n", "<br />");
        result = result.replace("\t", "=");
        return result;
    }

    public static void importData()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(dataFileName)), "UTF8"));

            String engWord;
            String pronunciation;
            String detail;

            while ((engWord = reader.readLine()) != null)
            {
                pronunciation = reader.readLine();
                detail = inputConvert(reader.readLine());
                Dictionary.insert(new Word(engWord, pronunciation, detail));
            }

            reader.close();
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public static void exportData()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dataFileName)), "UTF8"));

            ArrayList<Word> words = Dictionary.all();

            for(Word word : words)
            {
                writer.write(word.getEngWord());
                writer.newLine();
                writer.write(word.getPronunciation());
                writer.newLine();
                writer.write(outputConvert(word.getDetail()));
                writer.newLine();
            }

        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /* COMMANDLINE VERSION
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
    */
}