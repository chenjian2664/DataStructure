package set;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by chenjian
 * 2018/7/28 17:15
 */
public class FileOperation {
    //读取文件名称为filename中的内容，将其所有的词语放进words中
    public static boolean readFile(String filename, ArrayList<String> words) {
        if (null == filename || null == words) {
            System.out.println("filename is null or words is null");
            return false;
        }
        Scanner scanner = null;

        File file = new File(filename);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            scanner = new Scanner(new BufferedInputStream(fis), "UTF8");
            scanner.useLocale(Locale.ENGLISH);
        } else {
            return false;
        }

        //只做demo展示
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();
            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    ++i;
                }
            }
        }
        return true;
    }
    //寻找字符串s,从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); ++i) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }

        }
        return s.length();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("F:\\DataStructure\\DataStructureLearn\\src\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        BSTSet<String> set1 = new BSTSet<>();
        for (String word : word1) {
            set1.add(word);
        }
        System.out.println("Total different words: " + set1.getSize());

    }
}
