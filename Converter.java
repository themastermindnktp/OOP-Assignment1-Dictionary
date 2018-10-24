import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Converter
{
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("edict.sql")), "UTF8"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("edict.txt")), "UTF8"));

            for (int i = 0; i < 39; ++i) br.readLine();

            String s;

            int cnt = 39;
            while ((s = br.readLine()).length() > 1) {
                if (s.charAt(0) != '(') continue;
                s = s.substring(1, s.length()-2);
                int p = s.indexOf("', '");
                String engWord = s.substring(s.indexOf('\'')+1, p);
                if (engWord.indexOf("=") != -1) continue;
                while (engWord.indexOf('(') != -1)
                {
                    engWord = engWord.replaceFirst(engWord.substring(engWord.indexOf('('), engWord.indexOf(')') + 1), "");
                    engWord = engWord.replaceFirst("\\(", "");
                    engWord = engWord.replaceFirst("\\)", "");
                }
                while (engWord.indexOf('[') != -1)
                {
                    engWord = engWord.replaceFirst(engWord.substring(engWord.indexOf('['), engWord.indexOf(']') + 1), "");
                    engWord = engWord.replaceFirst("\\[", "");
                    engWord = engWord.replaceFirst("\\]", "");
                }
                s = s.replaceFirst(s.substring(0, p+4), "");
                p = s.indexOf('/');
                if (s.charAt(p+1) == '>' || s.charAt(p+1) == 'Q' || s.charAt(p+1) == ' ') continue;
                s = s.replaceFirst(s.substring(0, p), "");
                p = s.indexOf('<');
                String pronunciation = s.substring(0, p);
                String detail;
                if (s.indexOf("</Q></N>") != -1)
                    detail = s.substring(s.indexOf("<br />") + 6, s.indexOf("</Q></N>"));
                else
                    detail = s.substring(s.indexOf("<br />") + 6, s.length() - 4);
                if (detail.indexOf("@") != -1) detail = detail.replace(detail.substring(detail.indexOf("@"), detail.length()),"");
                bw.write(engWord + "\n");
                bw.write(pronunciation + "\n");
                bw.write(detail + "\n");
                cnt++;
                System.out.println("Loaded " + cnt + " words");
            }

            System.out.println("Finished Loading");
            br.close();
            bw.close();
        }       
        catch (UnsupportedEncodingException exception) {
            System.out.println(exception.getMessage());
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
