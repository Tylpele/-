package Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


abstract class Currency{
    String name;
    Map<String, Double> rates;
    public Currency(String name){
        this.name =name;
        rates = new HashMap<>();
    }

    //добавление курса валюты для конвертирования
    public void addRate(String targetCurrency, double rate){
        rates.put(targetCurrency, rate);
    }

    public double convertTo(String targetCurrency, double amount){
        return  amount * rates.get(targetCurrency);
    }
    public  String getName(){
        return name;
    }
}

class Ruble extends Currency{
    public Ruble(){
        super("Российский рубль"); //вызов конструктора родительского класса с параметром
        addRate("Доллар США", 0.010345);
        addRate("Евро", 0.009541);
        addRate("Китайский юань", 0.07407);
        addRate("Японская йена", 1.57 );
    }
}

class Dollar extends Currency{
    public Dollar(){
        super("Доллар США");
        addRate("Российский рубль", 96.67);
        addRate("Евро", 0.92629);
        addRate("Китайский юань",  7.12);
        addRate("Японская йена", 152.3 );
    }
}

class Euro extends Currency{
    public Euro(){
        super("Евро");
        addRate("Российский рубль", 104.81);
        addRate("Доллар США", 1.08);
        addRate("Китайский юань",  7.69);
        addRate("Японская йена", 164.42 );
    }
}

class Yuan extends Currency{
    public Yuan(){
        super("Китайский юань");
        addRate("Российский рубль", 13.5);
        addRate("Доллар США", 0.140461);
        addRate("Евро",  0.130108);
        addRate("Японская йена", 21.39 );
    }
}

class Yen extends Currency{
    public Yen(){
        super("Японская йена");
        addRate("Российский рубль", 0.635666);
        addRate("Доллар США", 0.006566);
        addRate("Евро",  0.006082);
        addRate("Китайский юань", 0.046744 );
    }
}
public class Main {
    public static void main(String[] args) {
        Currency[] currencies = { //массив с классами валют
                new Ruble(),
                new Dollar(),
                new Euro(),
                new Yuan(),
                new Yen()
        };
        System.out.println("Выберите входную валюту:");
        for (int i = 0; i < currencies.length; i++) {
            System.out.println((i + 1) + "." + currencies[i].getName());
        }

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt() - 1;
        System.out.println("Введите сумму");
        double money = sc.nextDouble();

        Currency selectedCurrency = currencies[choice]; //получение выбранной валюты из массива

        for (Currency currency : currencies) { //цикл по валютам для конвертации
            if (!currency.getName().equals(selectedCurrency.getName())) { //чтобы не конвертировалось в ту же валюту
                double convertedAmount = selectedCurrency.convertTo(currency.getName(), money); //конвертация из выбранной валюты в другую по ее имени
                System.out.println("В " + currency.getName() + ": " + convertedAmount);
            }
        }
    }
}
