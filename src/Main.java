import java.io.IOException;
import java.util.Scanner;

class Main {
    private static boolean firstNumberIsArabian = true;
    private static boolean secondNumberIsArabian = true;
    private final static String[] romanNumbers =
            new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private final static String[] romanTypeNumbers = new String[]{
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
            "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String inputResult = scanner.nextLine();
        System.out.println(calc(inputResult));
    }


    public static String calc(String input) throws Exception {

        if (input.split(" ").length != 3) throw new Exception("т.к. формат математической операции " +
                "не удовлетворяет заданию" +
                " - два операнда и один оператор (+, -, /, *)");
        testRoman(input);

        if (firstNumberIsArabian && secondNumberIsArabian) {
            return startCalcArab(input);
        } else if (firstNumberIsArabian || secondNumberIsArabian) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        } else {
            return startCalcRoman(input);
        }
    }

    static void testRoman(String input) {
        String[] tokkens = input.split(" ");
        for (int i = 0; i < romanNumbers.length; i++) {
            if (tokkens[0].equals(romanNumbers[i])) {
                firstNumberIsArabian = false;
            }
        }
        for (int j = 0; j < romanNumbers.length; j++) {
            if (tokkens[2].equals(romanNumbers[j])) {
                secondNumberIsArabian = false;
            }
        }

    }

    static String startCalcRoman(String input) throws Exception {
        String[] tokkens = input.split(" ");
        int a = 0;
        int b = 0;
        for (int i = 0; i < romanNumbers.length; i++) {
            if (tokkens[0].equals(romanNumbers[i])) {
                a = i + 1;
            }
        }
        for (int j = 0; j < romanNumbers.length; j++) {
            if (tokkens[2].equals(romanNumbers[j])) {
                b = j + 1;
            }
        }
        String operator = tokkens[1];
        int result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                if (a < b) {
                    throw new Exception("Нет отрицательных числе в Римской системе");

                } else {
                    result = a - b;
                }
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Неизвестный знак");
        }
        if (result == 0) throw new Exception("В Римской системе нет нуля");
        return (romanTypeNumbers[result - 1]);
    }

    static String startCalcArab(String input) throws Exception {
        String[] tokkens = input.split(" ");
        try {
            int a = Integer.parseInt(tokkens[0]);
            int b = Integer.parseInt(tokkens[2]);
            if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
                throw new IOException("Переменные больше 10 или меньше 1");
            } else {
                String operator = tokkens[1];
                int result = 0;
                switch (operator) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                    default:
                        throw new IOException("Неизвестный оператор");
                }
                return Integer.toString(result);
            }
        } catch (RuntimeException e){
           throw new Exception("Неизвестное значение одного из чисел");
        }

    }
}