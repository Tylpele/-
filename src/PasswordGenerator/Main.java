package PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class CharacterSet {
    private List<String> charaterSets;
    private int passwordLength;
    public CharacterSet(int passwordLength){
        this.charaterSets = new ArrayList<>();
        this.passwordLength = passwordLength;
        initializeCharterSets();
    }
    private void initializeCharterSets(){
        charaterSets.add("abcdefghijklmnopqrstuvwxyz");
        charaterSets.add("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        charaterSets.add("0123456789");
        charaterSets.add("!@#$%&*()_+-=[]|,./?><");
    }

    public List<String> getCharaterSets(){
        return charaterSets;
    }
    public int getPasswordLength(){
        return passwordLength;
    }
}

class PasswordGenerator{
    private CharacterSet passwordChars;
    private Random random;

    public PasswordGenerator(CharacterSet passwordChars){
        this.passwordChars = passwordChars;
        this.random = new Random();
    }
    public String generatePassword(){
        StringBuilder password = new StringBuilder();
        //генерируем первые 4 символа так, чтобы они точно были из разных категорий (потом пароль перемешается)
        for(int i = 0; i<passwordChars.getCharaterSets().size(); i++){
            char randomChar = passwordChars.getCharaterSets().get(i).charAt(random.nextInt(passwordChars.getCharaterSets().size())); //достается случайный символ из каждой категории по порядку
            password.append(randomChar);
        }

        int newLen = passwordChars.getPasswordLength()-passwordChars.getCharaterSets().size();

        //догенерируются оставшиеся символы пароля
        for(int i =0; i<newLen; i++){
            String randomSet = passwordChars.getCharaterSets().get(random.nextInt(passwordChars.getCharaterSets().size())); //случайный выбор категории символа
            char randomChar = randomSet.charAt(random.nextInt(randomSet.length())); //выбор самого символа из категории
            password.append(randomChar);
        }
        return shuffleString(password.toString()); //перемешивание пароля (чтобы первые 4 символа затерялись)

    }
    private String shuffleString(String input){
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите длину пароля (от 8 до 12 символов)");
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        while (len<8 || len>12){
            System.out.println("Неправильная длинна пароля");
            len = sc.nextInt();
        }
        CharacterSet passwordChars = new CharacterSet(len);
        PasswordGenerator passwordGenerator = new PasswordGenerator(passwordChars);
        String password = passwordGenerator.generatePassword();
        System.out.println("Пароль: "+ password);
    }
}
