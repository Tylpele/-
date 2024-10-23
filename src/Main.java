import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String word = choiceWord();
        System.out.println("Слово было выбрано:");
        System.out.println(word);
        System.out.println("Маска:");
        StringBuilder mask = createMaskWord(word);
        StartGuessing(word, mask);
    }
    private static String choiceWord(){
        Random random = new Random();
        final ArrayList<String> ListWords = new ArrayList<>(Arrays.asList("Программирование", "Алгоритм", "Программа", "вторжение", "гладильщица", "фарада")); //список угадываемых слов с инициализацией
        return ListWords.get(random.nextInt(ListWords.size())); //случайный элемент из списка
    }
    private static StringBuilder createMaskWord(String word){
        StringBuilder mask = new StringBuilder();
        for(int i =0; i<word.length(); i++){
            mask.append('*');
        }
        return mask;
    }

    public static void StartGuessing(String word, StringBuilder mask){
        Scanner sc = new Scanner(System.in);
        int XP = 7;
        while (XP!=0 && mask.toString().contains("*")){
            System.out.print("Жизней осталось: ");
            System.out.println(XP);
            System.out.println(mask);
            System.out.println("Введите букву");
            String letter = sc.nextLine();
            if (word.contains(letter)) {
                System.out.println("Ну типа вывод изменненного слова");
                ChangeMask(word, letter, mask);
            }
            else {
                XP--;
                System.out.println("Неправильно! Минус жизнь");
            }
        }

    }

    private static void ChangeMask(String word, String letter, StringBuilder mask) {
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i)==letter.charAt(0)) {
                mask.setCharAt(i, letter.charAt(0));
            }
        }
    }
}





