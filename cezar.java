import java.util.*;

import static java.lang.System.exit;


public class cezar {

    public static Map<Character, Integer> registrySymbols = new HashMap<Character, Integer>();
    public static ArrayList<String> bookEnTx = new ArrayList<String>();
    public static ArrayList<String> bookDsTx = new ArrayList<String>();
    public static Map<String,String> riddles = new HashMap<String,String>();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("����� ���������� � WACH.org");
        Thread.sleep(1500);
        System.out.println("��� ��, ��� �� ����� ");
        Thread.sleep(1000);
        mainClone();
    }

    public static void mainClone() throws InterruptedException {
        //���� ���������
        String alphabet = "��������������������������������"; // ������� ���� �������� ����� ����� ������ �������� �� ���
        char[] alphabetChar = alphabet.toCharArray();

        for (int i = 0; i < alphabetChar.length; i++) {
            registrySymbols.put(alphabetChar[i], i + 1);
        }
        riddles.put("����� ���� �� ��� ���� ?","���������");
        riddles.put("�� ���, � ����, �� �����, � ��������","��������");
        riddles.put("�� ����, �� ��������, � ����� ��� �� ����������.","@");
        riddles.put("��� ������ ������� � �������?","����������");
        riddles.put("������, �������� ���� �� ������.","������");
        riddles.put("��������, �� �� �����. ����������, �� �� �������.","���������");
        riddles.put("��� ����� ����?","���������");
        System.out.println("1. ����������� �����");
        Thread.sleep(1000);
        System.out.println("2. ������������ �����");
        Thread.sleep(1000);
        System.out.println("3. ���������� ��� ������, ������� ���������");
        Thread.sleep(1000);
        System.out.println("4. �����");
        Thread.sleep(1000);
        System.out.print("�������� ����� �������� ������ �� ����� ������� -> ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                Encrypt();
                System.out.println("����");
                mainClone();
                break;
            case 2:
                Decryption();
                System.out.println("����");
                mainClone();
                break;
            case 3:
                SeeBook();
                System.out.println("����");
                mainClone();
                break;
            case 4:
                exit(0);
                break;
        }
    }

    public static void Encrypt(){// ��������� ����� � ��������� �� ��������
        System.out.println("������� �����");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        bookEnTx.add(text);
        System.out.println("�� ������ ���� ������ ����, ��� �������� ��������� ?");
        System.out.println("1. ������ ��������������, 2. �������� �������� ����");
        System.out.print("-> ");
        int variant = scanner.nextInt();
        int offset = 0;
        if(variant==1){
            System.out.print("������� ���� -> ");
            offset=scanner.nextInt();
        } else if(variant==2){
            offset = (int) (Math.random()*33);
            System.out.println("��� ���� " + offset);
        } else{
            System.out.println("�� ������ ������, ���������.");
        }
        String textEncrypted = textSplit(text,offset);
        System.out.println("������������� ����� -> " + textEncrypted);


    }

    public static String textSplit(String text, int offset){ //��������� ����� �� �����
        String wordArray[] = text.split("\\s");
        String textCh ="";

            for (int i = 0; i < wordArray.length; i++) {
            wordArray[i]= charSplit(wordArray[i],offset);
            textCh=textCh  +wordArray[i] + " ";
            }

        return textCh;
    }

    public static String charSplit(String word, int offset){ //��������� ����� �� �������
        char[] charArray = word.toCharArray();
        String wordCh ="";
            for (int i = 0; i < charArray.length; i++) {
            charArray[i]=changeChar(charArray[i], offset);
            wordCh=wordCh+charArray[i];
            }

        return wordCh;

    }

    public static char changeChar(char symbol, int offset){ // ������ �������� ��������
        int orderNumber = orderChar(Character.toLowerCase(symbol)) + offset;
            if(orderNumber>33){
                orderNumber%=33;
            }else if(orderNumber<0){
                orderNumber+=33;
            }
        symbol=getKeyFromValue(orderNumber);

        return symbol;
    }


    public static int orderChar(char symbol){ //���� ����� ������ � ��������
        int order = registrySymbols.get(symbol);

        return order;
    }

    public static char getKeyFromValue(int orderNumber){//��������� ���� �� ��������, ������ �� ������ ��������� �������
        char symbol = 'n';
        for (Character ch : registrySymbols.keySet()) {
            if (registrySymbols.get(ch).equals(orderNumber)) {
                symbol=ch;
            }
        }

        return symbol;
    }

    public static void Decryption(){//��������� ����� � ���������� �� �����������
        Scanner scanner = new Scanner(System.in);
        System.out.print("�����, ������ ����� ������������ -> ");
        String text = scanner.nextLine();
        bookDsTx.add(text);
        System.out.print("���� ����������� -> ");
        int  offset = -(scanner.nextInt());
        String textDc = textSplit(text,offset);
        System.out.println("�������������� ����� -> ");
        System.out.println(textDc);


    }
    public static void SeeBook() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("�� ������ ���������� ��� ������, ������� ���������");
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(1000);
        System.out.println("����� ��� ������ �������� �� ������� (����� ���� �����) ");
        Thread.sleep(1000);
        System.out.println("�� ��� ������ �������� ����� �� 1 �� 7");
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
                System.out.println("������� -  " + k );
                answer=v;
                break;
            }
            i++;
        }
        System.out.print("��� ����� - ");
        Scanner scanner1 = new Scanner(System.in);
        String otvet = scanner1.nextLine();
        if (!(otvet.equals(answer))){
            System.out.println("�������, ");
            Thread.sleep(1000);
            System.out.print("���� ����");
            Thread.sleep(1000);
            exit(0);
        }
            Thread.sleep(1000);
            System.out.println("�����");
            Thread.sleep(1000);
            System.out.println("������ 1, ���� ������ ���������� ������, ������� ���������, 2, ������� �����������,3 - ����� � ����");
            int vr = scanner1.nextInt();
            switch (vr){
                case 1: System.out.println("������, ������� ���������");
                    for (int j = 0; j < bookEnTx.size(); j++) {
                        System.out.println(bookEnTx.get(j));
                    }
                    mainClone();
                    break;
                case 2:System.out.println("������, ������� �����������");
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

