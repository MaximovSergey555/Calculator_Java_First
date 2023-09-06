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
    public static void main(String[] args){
        System.out.println(calc());
    }

    public static String calc() {
        Scanner scanner = new Scanner(System.in);
        String inputResult = scanner.nextLine();
        if (inputResult.split(" ").length != 3) return ("Ошибка ввода");
        testRoman(inputResult);

        if (firstNumberIsArabian && secondNumberIsArabian) {
            return startCalcArab(inputResult);
        } else if (firstNumberIsArabian || secondNumberIsArabian) {
            return "Неудовлетворяет условию";
        } else {
            return startCalcRoman(inputResult);
        }
    }

    static String startCalcRoman(String input) {
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
                    return "Нет отрицательных числе в Римской системе";

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
                return "Неизвестный знак";
        }
        if (result == 0) return "В Римской системе нет нуля";
        return (romanTypeNumbers[result - 1]);
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


    static String startCalcArab(String input) {
        String[] tokkens = input.split(" ");
        try {
            int a = Integer.parseInt(tokkens[0]);
            int b = Integer.parseInt(tokkens[2]);
            if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
                return "Переменные больше 10 или меньше 1";
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
                        return "Неизвестный знак";
                }
                return Integer.toString(result);
            }
        } catch (Exception e) {
            return "Ошибка при вводе данных";
        }
    }
}