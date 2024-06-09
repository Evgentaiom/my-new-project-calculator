import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;

    public static void main(String[] args) {

        System.out.println("Введите арифметическое выражение с двумя аргументами затем нажмите Enter ");

        String userInput = scanner.nextLine().trim();
        int operationCount = 0;

        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == '+' || userInput.charAt(i) == '-' ||
                    userInput.charAt(i) == '*' || userInput.charAt(i) == '/') {
                operation = userInput.charAt(i);
                operationCount++;
            }
        }

        if (operationCount != 1) {
            throw new InputMismatchException("Должно быть одно арифметическое действие и два операнда");
        }

        String[] parts = userInput.split("[+\\-*/]");
        if (parts.length != 2) {
            throw new InputMismatchException("Должно быть одно арифметическое действие и два операнда");
        }

        String part1 = parts[0].trim();
        String part2 = parts[1].trim();

        if (isRoman(part1)) {
            number1 = romanToNumber(part1);
        } else {
            number1 = Integer.parseInt(part1);
        }

        if (isRoman(part2)) {
            number2 = romanToNumber(part2);
        } else {
            number2 = Integer.parseInt(part2);
        }

        if (number1 > 10 || number2 > 10) {
            throw new InputMismatchException("Операнды не могут быть больше 10");
        }

        result = calculated(number1, number2, operation);

        if (isRoman(part1) && isRoman(part2)) {
            System.out.println("---Результат для римских цифр----");
            String resultRoman = convertIntToRoman(result);
            System.out.println(part1 + " " + operation + " " + part2 + " = " + resultRoman);
        } else if (isRoman(part1) || isRoman(part2)) {
            throw new InputMismatchException("Оба числа должны быть либо римскими либо арабскими");
        } else {
            System.out.println("--Результат для арабских цифр----");
            System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
        }
    }

    private static String convertIntToRoman(int numArabian) {
        String[] roman =
                {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
                        "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                        "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                        "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                        "XXXIX", "XL",
                        "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                        "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                        "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                        "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
                        "LXXIX", "LXXX",
                        "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                        "LXXXVIII", "LXXXIX", "XC",
                        "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
                };
        if (numArabian <= 0 || numArabian >= roman.length) {
            throw new IllegalArgumentException("Результат вне диапазона римских чисел");
        }
        return roman[numArabian];
    }

    private static int romanToNumber(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new InputMismatchException("Неверный формат римских чисел");
        };
    }

    public static int calculated(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный знак арифметической операции");
        }
        return result;
    }

    private static boolean isRoman(String str) {
        return str.matches("^(?i)(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }
}