import java.util.Scanner;

public class Main {
    static String digitsArabRom[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String bigRom[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

    public static void main(String[] args) throws Exception {
        Scanner sc= new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(calc(s));
    }

    public static String calc(String input) throws Exception {
//        String ad[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
//        String rd[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        String s = input.replace(" ", "");
        String ab[] = s.split("[\\-\\+\\*\\/]");
        if (ab.length < 2)
            throw new Exception("Неверный или отсутствует оператор.");

        if (ab.length > 2)
            throw new Exception("Более одной операции");

        int a = indexOfOperand(ab[0]);
        int b = indexOfOperand(ab[1]);

        if ((11 - a) * (10 - b) < 0) {
            throw new Exception("Недопустимые числа");
        }

        String op = s.substring(ab[0].length(), ab[0].length() + 1);

        int adu = a;
        if (a > 10) {
            a -= 10;
            b -= 10;
        }

        int c = 0;
        if (op.equals("+"))
            c = a + b;
        if (op.equals("-"))
            c = a - b;
        if (op.equals("*"))
            c = a * b;
        if (op.equals("/"))
            c = a / b;
        if (adu < 11)   //  arabic
            return input+" = "+Integer.toString(c);
        if (c < 1)
            throw new Exception("Число меньше единицы");
        int c10 = c / 10;
        c = c % 10;
        return input+" = "+bigRom[c10] + digitsArabRom[c + 9];

    }

    static int indexOfOperand(String opAB) throws Exception {
        for (int i = 0; i < 20; i++) {
            if (opAB.equals(digitsArabRom[i])) {
                return i + 1;
            }
        }
        throw new Exception("Неверный операнд.");
    }
}
