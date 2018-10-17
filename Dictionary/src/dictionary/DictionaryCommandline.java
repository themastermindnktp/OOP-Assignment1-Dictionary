/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline{
    private static final int space1 = 8;
    private static final int space2 = 30;
    private static final String dataFileName = "dictionaries.txt";

    private static final Scanner sc = new Scanner(System.in);

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static void inform(String s) {
        System.out.println(s);
        System.out.println();
    }

    public static String read(String s) {
        System.out.print(s);
        String string = sc.nextLine().toLowerCase();
        System.out.println();
        return string;
    }

    public static void write(String s1, String s2, String s3) {
        for (int i = 0, n = space1 - s1.length(); i < n; i++) System.out.print(" ");
        System.out.print(s1 + "  │  " + s2);
        for (int i = 0, n = space2 - s2.length(); i < n; i++) System.out.print(" ");
        System.out.println("  │  " + s3);
    }

    public static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public static void printArray(ArrayList<Word> arr) {
        write("STT", "Tieng Anh", "Tieng Viet");
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
        for (int i = 0, n = arr.size(); i < n; i++) {
            Word word = arr.get(i);
            write(Integer.toString(i + 1), capitalize(word.getEngWord()), capitalize(word.getDefinition()));
        }
        System.out.println();
    }

    public static boolean confirm(String s) {
        String ans;
        while (true) {
            System.out.print(s + " (Y/N) ");
            ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y") || ans.equals("N") || ans.equals("YES") || ans.equals("NO")) break;
        }
        System.out.println();
        return ans.equals("Y") || ans.equals("YES");
    }

    public static void exitToMainMenu() {
        while (true) {
            System.out.print("Nhap \"exit\" de tro lai man hinh chinh! ");
            String s = sc.nextLine();
            if (s.toUpperCase().equals("EXIT")) return;
        }
    }

    public static void dictionarySearch(){
        while (true) {
            clearScreen();
            String s = read("Nhap tu Tieng Anh ban muon tra cuu: ");
            Word word = Dictionary.find(s);
            if (word != null) inform("\"" + capitalize(s) + "\" co nghia la: " + capitalize(word.getDefinition()));
            else {
                ArrayList<Word> arr = Dictionary.searchByPrefix(s);
                if (arr.isEmpty()) inform("\"" + capitalize(s) + "\" hoan toan khong ton tai trong tu dien!");
                else {
                    inform("\"" + capitalize(s) + "\" chua ton tai trong tu dien!");
                    if (confirm("Ban co muon goi y cac tu co tien to \"" + s + "\" khong?")) {
                        for (int i = 0, n = arr.size() - 1; i < n; i += 2) {
                            System.out.print("  " + capitalize(arr.get(i).getEngWord()));
                            for (int _i = 0, _n = 20 - arr.get(i).getEngWord().length(); _i < _n; _i++) System.out.print(" ");
                            System.out.println(capitalize(arr.get(i + 1).getEngWord()));
                        }
                        if (arr.size() % 2 == 1) System.out.println("  " + capitalize(arr.get(arr.size() - 1).getEngWord()));
                        System.out.println();
                    }
                }
            }
            if (!confirm("Ban co muon tiep tuc tra cuu tu khong?"))  break;
        }
    }

    public static void dictionarySearchByPrefix() {
        while (true) {
            clearScreen();
            String s = read("Nhap vao tien to ban muon tham khao: ");
            ArrayList<Word> arr = Dictionary.searchByPrefix(s);
            if (arr.isEmpty()) inform("Khong ton tai tu Tieng Anh nao co tien to \"" + s + "\"!");
            else printArray(Dictionary.searchByPrefix(s));
            if (!confirm("Ban co muon tiep tuc tham khao tu theo tien to khong?")) break;
        }
    }

    public static void dictionaryInsert() {
        while (true) {
            clearScreen();
            String engWord = read("Nhap vao tu Tieng Anh ban muon them : ");
            String definition = read("Nhap nghia Tieng Viet: ");
            Word word = Dictionary.find(engWord);
            if ((word == null && confirm("Ban co chac chan muon them tu \"" + capitalize(engWord) + "\" vao tu dien khong?")) ||
                (word != null && confirm("\"" + capitalize(engWord) + "\" da nam trong tu dien, ban muon cap nhat lai nghia cua tu do khong?"))) {
                Dictionary.insert(new Word(engWord, definition));
                inform("Them tu moi thanh cong!");
            }
            else inform("Them tu moi khong thanh cong!");
            if (!confirm("Ban co muon tiep tuc them tu moi vao tu dien khong?")) break;
        }
    }

    public static void dictionaryRedefine() {
        while (true) {
            clearScreen();
            String engWord = read("Nhap tu ban muon dinh nghia lai: ");
            String definition = read("Nhap dinh nghia moi: ");
            Word word = Dictionary.find(engWord);
            if (word == null) {
                if (confirm("\"" + capitalize(engWord) + "\" chua ton tai trong tu dien, ban co muon them tu nay khong?"))
                {
                    Dictionary.insert(new Word(engWord, definition));
                    inform("Them tu moi thanh cong!");
                }
                else inform("Dinh nghia lai tu khong thanh cong!");
            }
            else {
                if (confirm("Ban co chac chan muon dinh nghia lai tu \"" + capitalize(engWord) + "\" khong?")) {
                    Dictionary.insert(new Word(engWord, definition));
                    inform("Dinh nghia lai tu thanh cong!");
                }
                else inform("Dinh nghia lai tu khong thanh cong!");
            }
            if (!confirm("Ban co muon tiep tuc chinh sua tu khac khong?")) break;
        }
    }

    public static void dictionaryDelete() {
        while (true) {
            clearScreen();
            String s = read("Nhap tu ban muon xoa : ");
            Word word = Dictionary.find(s);
            if (word == null) inform("\"" + capitalize(s) + "\" chua ton tai trong tu dien!");
            else {
                if (confirm("Ban co chac chan muon xoa tu \"" + capitalize(s) + "\" khong?")) {
                    Dictionary.delete(s);
                    inform("Xoa tu thanh cong!");
                } else inform("Xoa tu khong thanh cong!");
            }
            if (!confirm("Ban co muon tiep tuc xoa tu khong?")) break;
        }
    }

    public static void showAllWords(){
        printArray(Dictionary.all());
        exitToMainMenu();
    }

    public static void dictionaryReloadFromFile() {
        while (true) {
            clearScreen();
            String s = read("Nhap ten file du lieu cho tu dien: ");
            System.out.println("Nhap \"1\" de cap nhat du lieu cho tu dien tu file \"" + s + "\"");
            System.out.println("Nhap \"2\" de tao moi du lieu cho tu dien tu file \"" + s + "\"");
            System.out.println("Nhap \"3\" huy thao tac");
            System.out.println();
            String ans;
            while (true) {
                System.out.print("Lua chon cua ban? (1/2/3) ");
                ans = sc.nextLine();
                if (ans.equals("1") || ans.equals("2") || ans.equals("3")) break;
            }
            System.out.println();
            if (ans.equals("1")) {
                if (confirm("Ban co chac chan muon cap nhat du lieu cho tu dien khong?")) {
                    DictionaryManagement.importFromFile(s);
                    inform("Cap nhat du lieu cho tu dien thanh cong!");
                } else inform("Huy cap nhat du lieu!");
            }
            else if (ans.equals("2")) {
                if (confirm("Ban co chac chan muon tao moi du lieu cho tu dien khong")) {
                    Dictionary.clear();
                    DictionaryManagement.importFromFile(s);
                    inform("Tao moi du lieu cho tu dien thanh cong!");
                } else inform("Huy tao moi du lieu!");
            }
            else inform("Huy thiet lap du lieu!");
            if (!confirm("Ban co muon tiep tuc thiet lap du lieu cho tu dien khong?")) break;
        }
    }

    public static void dictionarySaveToFile() {
        while (true) {
            clearScreen();
            String s = read("Nhap ten file luu tru du lieu cua tu dien: ");
            if (confirm("Ban co chac chan muon luu tru du lieu vao file \"" + s + "\" khong?")) {
                DictionaryManagement.exportToFile(s);
                inform("Luu tru du lieu thanh cong!");
            } else inform("Huy luu tru du lieu");
            if (!confirm("Ban co muon tiep tuc luu tru du lieu cuar tu dien khong?")) break;
        }
    }

    public static void mainMenu() {
        while (true) {
            clearScreen();
            inform("TU DIEN ANH - VIET");
            System.out.println("1/ Tra cuu Anh - Viet");
            System.out.println("2/ Tham khao tu thong qua tien to");
            System.out.println("3/ Them tu moi vao tu dien");
            System.out.println("4/ Chinh sua tu trong tu dien");
            System.out.println("5/ Xoa tu trong tu dien");
            System.out.println("6/ Thiet lap du lieu cho tu dien");
            System.out.println("7/ Luu tru du lieu cua tu dien");
            System.out.println("8/ Thoat");
            System.out.println();
            System.out.print("Lua chon thao tac (1/2/3/4/5/6/7) : ");
            String s = sc.nextLine();
            if (s.equals("1")) dictionarySearch();
            else if (s.equals("2")) dictionarySearchByPrefix();
            else if (s.equals("3")) dictionaryInsert();
            else if (s.equals("4")) dictionaryRedefine();
            else if (s.equals("5")) dictionaryDelete();
            else if (s.equals("6")) dictionaryReloadFromFile();
            else if (s.equals("7")) dictionarySaveToFile();
            else if (s.equals("8")) break;
        }
    }

    public static void main(String[] args) {
        DictionaryManagement.importFromFile(dataFileName);
        mainMenu();
    }
}