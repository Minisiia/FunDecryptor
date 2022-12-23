package decryptor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String s = "Головну ялинку міста Харків відкрили на станції метро \"Університет\", відійшовши від традиції встановлювати її на майдані Свободи. " +
                "Тривалий час вона вважалась найвищою в Україні, а зараз сягає всього 5,5 метрів. Поряд розмістили будиночок Святого Миколая.\n" +
                "Ялинка прикрашена іграшками та гірляндами, які не споживають багато електроенергії. " +
                "Також відомо, що станції метро \"Історичний музей\", \"Південний вокзал\" та \"Наукова\" прикрасили до Нового року.\n";
        String str = "";
        // запис у змінну словника прийменників
        try (FileReader fr = new FileReader("Vocabulary.txt")) {
            int temp;
            for (; ; ) {
                temp = fr.read();
                if (temp == -1) break;
                str += (char) temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // розділення по ,
        String[] vocabulary = str.split(",");
        // видалення зайвих пробілів та додавання пробілів на початку та на кінці
        for (int i = 0; i < vocabulary.length; i++) {
            vocabulary[i] = vocabulary[i].trim();
            vocabulary[i] = " " + vocabulary[i] + " ";
        }
        s = " " + s;
        boolean flag = false;
        ArrayList<String> al = new ArrayList<>();
        // пошук прийменників у тексті та заміна на Java
        for (int i = 0; i < vocabulary.length; i++) {
            Pattern p = Pattern.compile(vocabulary[i], Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
            Matcher m = p.matcher(s);
            flag = m.find();
            s = m.replaceAll(" Java ");
            if (flag) al.add(vocabulary[i]);
        }
        s = s.trim();
        System.out.println(s);
        System.out.println();
        System.out.println("Поміняли такі слова:");
        for (String temp : al) System.out.println(temp);

    }
}
