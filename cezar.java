import java.util.*;

import static java.lang.System.exit;


public class cezar {

    public static Map<Character, Integer> registrySymbols = new HashMap<Character, Integer>();
    public static ArrayList<String> bookEnTx = new ArrayList<String>();
    public static ArrayList<String> bookDsTx = new ArrayList<String>();
    public static Map<String,String> riddles = new HashMap<String,String>();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("Добро пожаловать в WACH.org");
        Thread.sleep(1500);
        System.out.println("Это то, что мы можем ");
        Thread.sleep(1000);
        mainClone();
    }

    public static void mainClone() throws InterruptedException {
        //меню программы
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; // Создает базу символов чтобы полсе делать смещение по ним
        char[] alphabetChar = alphabet.toCharArray();

        for (int i = 0; i < alphabetChar.length; i++) {
            registrySymbols.put(alphabetChar[i], i + 1);
        }
        riddles.put("Какой конь не ест овса ?","шахматный");
        riddles.put("Не лед, а тает, не лодка, а уплывает","зарплата");
        riddles.put("Не лает, не кусается, и точно так же называется.","@");
        riddles.put("Что нельзя сделать в космосе?","повеситься");
        riddles.put("Остров, выдающий себя за одежду.","ямайка");
        riddles.put("Стреляет, но не ружье. Скручивает, но не милиция.","радикулит");
        riddles.put("Кто ходит сидя?","шахматист");
        System.out.println("1. Зашифровать текст");
        Thread.sleep(1000);
        System.out.println("2. Расшифровать текст");
        Thread.sleep(1000);
        System.out.println("3. Посмотреть все тексты, который вводились");
        Thread.sleep(1000);
        System.out.println("4. Выход");
        Thread.sleep(1000);
        System.out.print("Напишите номер действия котрые вы хоите сделать -> ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                Encrypt();
                System.out.println("Меню");
                mainClone();
                break;
            case 2:
                Decryption();
                System.out.println("Меню");
                mainClone();
                break;
            case 3:
                SeeBook();
                System.out.println("Меню");
                mainClone();
                break;
            case 4:
                exit(0);
                break;
        }
    }

    public static void Encrypt(){// считывает текст и отпрвляет на шифровку
        System.out.println("Введите текст");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        bookEnTx.add(text);
        System.out.println("Вы хотите сами ввести ключ, или получить случайный ?");
        System.out.println("1. Ввести самостоятельно, 2. Получить случаный ключ");
        System.out.print("-> ");
        int variant = scanner.nextInt();
        int offset = 0;
        if(variant==1){
            System.out.print("Введите ключ -> ");
            offset=scanner.nextInt();
        } else if(variant==2){
            offset = (int) (Math.random()*33);
            System.out.println("Ваш ключ " + offset);
        } else{
            System.out.println("Ты ошибся входом, братанчик.");
        }
        String textEncrypted = textSplit(text,offset);
        System.out.println("Зашифрованный текст -> " + textEncrypted);


    }

    public static String textSplit(String text, int offset){ //разбивает текст на слова
        String wordArray[] = text.split("\\s");
        String textCh ="";

            for (int i = 0; i < wordArray.length; i++) {
            wordArray[i]= charSplit(wordArray[i],offset);
            textCh=textCh  +wordArray[i] + " ";
            }

        return textCh;
    }

    public static String charSplit(String word, int offset){ //разбивает слова на символы
        char[] charArray = word.toCharArray();
        String wordCh ="";
            for (int i = 0; i < charArray.length; i++) {
            charArray[i]=changeChar(charArray[i], offset);
            wordCh=wordCh+charArray[i];
            }

        return wordCh;

    }

    public static char changeChar(char symbol, int offset){ // делает смещение символов
        int orderNumber = orderChar(Character.toLowerCase(symbol)) + offset;
            if(orderNumber>33){
                orderNumber%=33;
            }else if(orderNumber<0){
                orderNumber+=33;
            }
        symbol=getKeyFromValue(orderNumber);

        return symbol;
    }


    public static int orderChar(char symbol){ //ищет номер симвла в алфавите
        int order = registrySymbols.get(symbol);

        return order;
    }

    public static char getKeyFromValue(int orderNumber){//возращает ключ по значению, символ на котрый сместился порядок
        char symbol = 'n';
        for (Character ch : registrySymbols.keySet()) {
            if (registrySymbols.get(ch).equals(orderNumber)) {
                symbol=ch;
            }
        }

        return symbol;
    }

    public static void Decryption(){//считывает текст и отправляет на расшифровку
        Scanner scanner = new Scanner(System.in);
        System.out.print("Текст, котрый нужно расшифровать -> ");
        String text = scanner.nextLine();
        bookDsTx.add(text);
        System.out.print("Ключ расшифровки -> ");
        int  offset = -(scanner.nextInt());
        String textDc = textSplit(text,offset);
        System.out.println("Расшифрованный текст -> ");
        System.out.println(textDc);


    }
    public static void SeeBook() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Вы хотите посмотреть все тексты, которые вводились");
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(1000);
        System.out.println("Тогда для начала ответьте на загадку (Ответ одно слово) ");
        Thread.sleep(1000);
        System.out.println("Но для начала назовите цирфу от 1 до 7");
        Thread.sleep(1000);
        int number = scanner.nextInt();
        int i = 1;
        if (number>7){
            number%=7;
        }
        String answer = " ";

        for (Map.Entry entry : riddles.entrySet()) {

            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if(i==number){
                System.out.println("Загадка -  " + k );
                answer=v;
                break;
            }
            i++;
        }
        System.out.print("Ваш ответ - ");
        Scanner scanner1 = new Scanner(System.in);
        String otvet = scanner1.nextLine();
        if (!(otvet.equals(answer))){
            System.out.println("Неверно, ");
            Thread.sleep(1000);
            System.out.print("пока пока");
            Thread.sleep(1000);
            exit(0);
        }
            Thread.sleep(1000);
            System.out.println("Верно");
            Thread.sleep(1000);
            System.out.println("Введие 1, если хотите посмотреть тексты, которые шифровали, 2, которые дешифровали,3 - Выход в меню");
            int vr = scanner1.nextInt();
            switch (vr){
                case 1: System.out.println("Тексты, которые шифровали");
                    for (int j = 0; j < bookEnTx.size(); j++) {
                        System.out.println(bookEnTx.get(j));
                    }
                    mainClone();
                    break;
                case 2:System.out.println("Тексты, которые дешифровали");
                    for (int j = 0; j < bookDsTx.size(); j++) {
                        System.out.println(bookDsTx.get(j));
                    }
                    mainClone();
                    break;
                case 3:mainClone();
                    break;
            }



    }


}

