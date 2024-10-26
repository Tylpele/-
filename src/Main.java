import java.util.*;

class Word{
    private final String value;
    private StringBuilder mask;
    public  Word(String value){
        this.value=value.toUpperCase();
        this.mask = createMask();
    }
    private StringBuilder createMask(){
        StringBuilder mask = new StringBuilder();
        for(int i = 0; i< value.length(); i++) {
            mask.append('*');
        }
        return mask;
    }
    public String getValue(){
        return value;
    }
    public  StringBuilder getMask(){
        return mask;
    }
    public void updateMask(char letter){
        for(int i = 0; i<value.length(); i++){
            if(value.charAt(i)==letter){
                mask.setCharAt(i, letter);
            }
        }
    }
    public boolean isGuessed(){
        return !mask.toString().contains("*");
    }
}

class Category{
    private String name;
    private List<String> words;

    public Category(String name, List<String> words){
        this.name = name;
        this.words= words;
    }
    public String getName(){
        return name;
    }
    public String getRandomWord(){
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}

class Game{
    private Word word;
    private int XP;
    private final String[] pictures;
    public Game(){
        this.XP = 7;
        this.pictures = new String[]{"Осталось 0 жизней:\n" +
                "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " / \\  |\n" +
                "      |\n" +
                "=========",
                "Осталась 1 жизнь:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "=========",
                "Осталось 2 жизни:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========",
                "Осталось 3 жизни:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "Осталось 4 жизни:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "Осталось 5 жизней:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "Осталось 6 жизней:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "Осталось 7 жизней:\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "========="
        };
    }

    public void chooseCategory(List<Category> categories){
        System.out.println("Выберите категорию:");
        for(int i = 0; i<categories.size(); i++){
            System.out.println((i+1)+". "+categories.get(i).getName());
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt()-1;
        if(choice>=0 && choice < categories.size()){
            this.word = new Word(categories.get(choice).getRandomWord());
        }
        else {
            System.out.println("Неверный выбор, выбирается случайное слово.");
            this.word = new Word(categories.get(new Random().nextInt(categories.size())).getRandomWord());
        }
    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        while (XP > 0 && !word.isGuessed()){
            System.out.println(pictures[XP]);
            System.out.println(word.getMask());
            System.out.println("Введите букву:");
            String letter = sc.nextLine().toUpperCase();

            if(letter.length() != 1){
                System.out.println("Вы ввели не один символ, попробуйте еще раз");
                continue;
            }

            if(word.getValue().contains(letter)){
                word.updateMask(letter.charAt(0));
            }
            else {
                XP--;
                System.out.println("Неправильно! Минус жизнь");
            }

            if (XP > 0) {
                System.out.println("Вы выиграли!");
            } else {
                System.out.println(pictures[XP]);
                System.out.println("Вы проиграли!");
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Программирование", Arrays.asList("ПРОГРАММИРОВАНИЕ", "АЛГОРИТМ", "ПРОГРАММА")));
        categories.add(new Category("Наука", Arrays.asList("ФИЗИКА", "ХИМИЯ", "БИОЛОГИЯ")));
        categories.add(new Category("Развлечения", Arrays.asList("ФИЛЬМ", "ИГРА", "КНИГА")));

        Game game = new Game();
        game.chooseCategory(categories);
        game.start();
    }
}





