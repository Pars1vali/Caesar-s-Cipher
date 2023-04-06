import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import java.util.*;

public class maket {it
    public static Map<Character, Integer> charOffset = new HashMap<Character, Integer>(); // Здесь хранятся сиволы и их номер в алфавите чтобы ым могли делать смещение


    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Welcome to WACH.org");
        Thread.sleep(2500);
        System.out.println("This is what we are able to do:");
        Thread.sleep(1000);
        System.out.println("1. Encrypt Text");
        Thread.sleep(1000);
        System.out.println("2. Decrypt the text using the specified key");
        Thread.sleep(1000);
        System.out.println("3. View existing encrypted texts");
        Thread.sleep(1000);
        System.out.print("Choose the number you need -> ");
        Scanner scanner = new Scanner(System.in);// Пользователь выбирает что он хочет сделать
        int number = scanner.nextInt();
        Thread.sleep(1000);
        switch (number){// В зависмости от выбранного варинта пользователя вызваем нужный метод
            case 1: Encrypt(); break;
            case 2: break;
            case 3: break;
            default: System.out.println("Are you sure, bro? Check the number..."); //Если ошибся

        }
        String alphabet = "абвгдеёжхийклмнопрстуфхцчшщъыьэюя"; // Создает базу символов чтобы полсе делать смещение по ним
        char[] alphabetArray = alphabet.toCharArray();
        for (int i = 1; i < 34; i++) {
            charOffset.put(alphabetArray[i - 1], i);
        }
    }

    static void Encrypt() throws InterruptedException { //Метод который шифрует текст
        Thread.sleep(1000);
        System.out.println("Hey, you want to encrypt text, lets go");
        Thread.sleep(1000);
        System.out.print("Write the text, which you need encrypt -> \n");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int offset = (int) (Math.random()*33); // Смещение символов
        String text_encrypt = caesar_eccrypt(text,offset); // Шифрованный текст
        Thread.sleep(1000);
        System.out.println("This is your encrypt text -> \n");
        System.out.println(text_encrypt);

        System.out.println("This is your key - encrypt, don't forget and don't show it to different people -> " + offset);
    }
    static String caesar_eccrypt(String text, int offset){ // Именно он обеспечивает широфку заданого текста по смещению
        String[] words = text.split("\\s"); // Массив слов заданого текста
        String textEncrypt = "";
        for (int i = 0; i < words.length; i++) { // для каждого слова
            char[] wordToChar = words[i].toCharArray(); //Массив символов каждого слова
            for (int j = 0; j < wordToChar.length; j++) { // для каждого символа
                int order = 0;




                if(order>33){
                    order=order-33;
                }
                Collection<Character> collection= charOffset.keySet();
                for (Character key : collection) {
                    int number = charOffset.get(key);
                    if (order==number) {
                        wordToChar[j]= key;
                    }
                }
            }
            System.out.println(words[i]);
            textEncrypt = textEncrypt.concat(words[i] + " ");
        }


        return textEncrypt;
    }

}
