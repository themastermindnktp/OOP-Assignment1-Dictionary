public class DictionaryCommandline{
    // Write a string in a particular space
    public static void write(String string, int space){
        System.out.print(string);
        for(int i = 0, time = space - string.length(); i < time; i++) System.out.print(" ");
    }

    // Display all the words in the Dictionary to the screen
    public static void showAllWords(){
        int space1 = 8;
        int space2 = 30;
        int space3 = 50;
        write("No", space1);            System.out.print("| ");
        write("English", space2);       System.out.print("| ");
        write("Vietnamese", space3);    System.out.println();
        for(int i = 0, time = Dictionary.vocabulary.size(); i < time; i++){
            write(new Integer(i + 1).toString(), space1);               System.out.print("| ");
            write(Dictionary.vocabulary.get(i).getEngWord(), space2);   System.out.print("| ");
            write(Dictionary.vocabulary.get(i).getVieWord(), space3);   System.out.println();
        }
    }

    // Receive and display words
    public static void dictionaryBasic(){
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    //  Testing section
    public static void main(String[] args) {
        DictionaryCommandline.dictionaryBasic();
    }

}