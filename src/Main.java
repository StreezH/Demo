import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        char mainOperation;                                 // необходимая операция

        Scanner userInput = new Scanner(System.in);
        System.out.println("\nВведите выражение:");         // запрашиваем исходные данные в заданном заданием формате

        String mainInput = userInput.nextLine();
        String[] arr = mainInput.split("\"");         // считываем данные массивом с помощью Array
                                                            // так же удаляем кавычки
        String firstValue = arr[1];                         // первое значение - строка (оно же первый набор слов), в нулевом значении массива - пусто
        String secondValue = arr[2];                        // второе значение операция и вторая строка(тут будет только цифра, слово/слова "уйдут" в третье значение массива)
        String operation = arr[2].trim();                   // из второго значения "достаем" нужную операцию с помощью .trim+.charAt(0)
        mainOperation = operation.charAt(0);                // конвертировал значение операции из "строки" в "символ" для личного удобства, не принципиально
        if (firstValue.length()>10)  {
            throw new IOException("Ошибка! Не более 10 символов в значении"); }

        switch (mainOperation) {
            // использую функцию multiplication для умножения
            case '*': System.out.println ( "\""+multiplication (firstValue, secondValue)+"\"" );
                      break;
            // аналогично для деления
            case '/': System.out.println ( "\""+division (firstValue, secondValue)+"\"" );
                      break;
            // сложение выполняется простым сложением слов, второе слово берем из массива(arr3) + проверка на 10 и более символов
            case '+': if (arr[3].length() < 11) System.out.println("\""+firstValue + arr[3]+"\"");
                          else throw new IOException("Ошибка! Не более 10 символов в значении");
                      break;
            //вычитание аналогично, только из "тела" первого слова удаляем второе слово + проверка на 10+ символов
            case '-': if (arr[3].length() < 11) System.out.println("\""+firstValue.replaceAll(arr[3], "")+"\"");
                          else throw new IOException("Ошибка! Не более 10 символов в значении");
                      break;
            default:
                throw new IllegalStateException("Только знаки арифметических операций: " + mainOperation);
        }

    }
    // функция для умножения, "вытаскиваем" вторую цифру из secondvalue, проверяем её на 0<x<11, используем метод .repeat(x)
    public static String multiplication (String firstValue, String secondValue) throws IOException {
        int multiplier = Integer.parseInt(secondValue.replace("*", " ").trim());
        if ((multiplier > 10) || (multiplier <= 0)) {
            throw new IOException("Ошибка! Введенные числа должны быть от 1 до 10."); }
        String answer = firstValue.repeat(multiplier);
        if (answer.length() > 40) {
            answer = answer.substring(0,39)+"..."; }
        return answer;
    }
    // функция для деления, так же "вытаскиваем" вторую цифру из secondvalue, первая цифра = количество букв в первом слове,
    // делим их друг на друга и получившееся число используем в методе .substring в первом слове
    public static String division (String firstValue, String secondValue) throws IOException {
        int firstValueLength = firstValue.length();
        int divider = Integer.parseInt(secondValue.replace("/", " ").trim());
        if ((divider > 10) || (divider <= 0)) {
            throw new IOException("Ошибка! Введенные числа должны быть от 1 до 10."); }
        int answerNum = firstValueLength/divider;
        String answer = firstValue.substring(0,answerNum);
        return answer;
    }
}