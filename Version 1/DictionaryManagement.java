import java.util.Scanner;

public class DictionaryManagement{
    // Receive several English words and their Vietnamese definitions from the Commandline
    public static void insertFromCommandline(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of new words: ");
        int numberOfNewWords = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        
        for(int i = 1; i <= numberOfNewWords; i++){
            System.out.print("Enter English word number " + i + ": " );
            String engWord = scanner.nextLine();
            System.out.print("Enter Vietnamese definition of " + (char) 34 + engWord + (char) 34 + ": ");
            String vieWord = scanner.nextLine();
            System.out.println();
            Dictionary.vocabulary.add(new Word(engWord, vieWord));
        }
        
        scanner.close();
    }

}