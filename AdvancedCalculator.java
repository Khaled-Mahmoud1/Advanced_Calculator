import java.util.Scanner;

public class AdvancedCalculator {

    private static final Scanner SC = new Scanner(System.in);
    private static final String LINE_SEPARATOR = "----------------------------------------";
    private static final String BLOCK_SEPARATOR = "========================================";
    private static final int MAX_GUESS_ATTEMPTS = 7;
    private static final int MAX_FACTORIAL_N = 20;


    public static void main(String[] args) {
        boolean running = true;
        System.out.println(BLOCK_SEPARATOR);
        System.out.println("=== Welcome to Advanced Calculator ===");
        System.out.println(BLOCK_SEPARATOR);

        while (running) {
            printMenu();
            int choice = readInt("Choose an option (1-9): ");

            System.out.println(LINE_SEPARATOR);

            switch (choice) {
                case 1 -> performBasicArithmetic();
                case 2 -> performAverage();
                case 3 -> performSqrt();
                case 4 -> performPower();
                case 5 -> performFactorial();
                case 6 -> performTemperatureConversion();
                case 7 -> performCurrencyConversion();
                case 8 -> performNumberGuessingGame();
                case 9 -> {
                    if (confirmExit()) {
                        running = false;
                    }
                }
                default -> System.out.println("Invalid option. Please choose a number between 1 and 9.");
            }

            System.out.println(BLOCK_SEPARATOR);
        }

        System.out.println("Program terminated. Goodbye!");
        SC.close();
    }

    // ================== Menu ==================
    private static void printMenu() {
        System.out.println();
        System.out.println("=== Main Menu ===");
        System.out.println("1. Basic arithmetic (add, subtract, multiply, divide)");
        System.out.println("2. Average of numbers");
        System.out.println("3. Square root");
        System.out.println("4. Power (x^y)");
        System.out.println("5. Factorial (integer)");
        System.out.println("6. Temperature conversion (C <-> F <-> K)");
        System.out.println("7. Currency conversion (custom rate)");
        System.out.println("8. Number guessing game");
        System.out.println("9. Exit");
    }

    // ================== Input Utilities ==================
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a numeric value.");
            }
        }
    }

    // ================== Exit Confirmation ==================
    private static boolean confirmExit() {
        while (true) {
            String ans = readLine("Are you sure you want to exit? (y/n): ").toLowerCase();
            if (ans.equals("y") || ans.equals("yes")) {
                return true;
            } else if (ans.equals("n") || ans.equals("no")) {
                System.out.println("Exit cancelled. Returning to main menu.");
                return false;
            } else {
                System.out.println("Please answer with 'y' or 'n'.");
            }
        }
    }

    // ================== 1) Basic Arithmetic ==================
    private static void performBasicArithmetic() {
        System.out.println("--- Basic Arithmetic ---");
        while (true) {
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("0. Back to main menu");

            int op = readInt("Choose operation (0-4): ");
            if (op == 0) {
                System.out.println("Returning to main menu...");
                return;
            }

            if (op < 1 || op > 4) {
                System.out.println("Invalid operation. Please choose from 0 to 4.");
                System.out.println(LINE_SEPARATOR);
                continue;
            }

            double a = readDouble("Enter first number: ");
            double b = readDouble("Enter second number: ");

            switch (op) {
                case 1 -> System.out.println("Result: " + add(a, b));
                case 2 -> System.out.println("Result: " + subtract(a, b));
                case 3 -> System.out.println("Result: " + multiply(a, b));
                case 4 -> {

                    while (b == 0.0) {
                        System.out.println("Cannot divide by zero. Please enter a non-zero second number.");
                        b = readDouble("New second number: ");
                    }
                    System.out.println("Result: " + divide(a, b));
                }
            }

            System.out.println(LINE_SEPARATOR);
        }
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        return a / b;
    }

    // ================== 2) Average ==================
    private static void performAverage() {
        System.out.println("--- Average ---");
        int n = readInt("How many numbers? (>=1, 0 to cancel): ");

        if (n == 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        if (n < 0) {
            System.out.println("Count must be a positive integer.");
            return;
        }

        double sum = 0;
        for (int i = 1; i <= n; i++) {
            double val = readDouble("Number " + i + ": ");
            sum += val;
        }
        double avg = sum / n;
        System.out.println("Average: " + avg);
    }

    // ================== 3) Square Root ==================
    private static void performSqrt() {
        System.out.println("--- Square root ---");
        while (true) {
            double x = readDouble("Enter a non-negative number (or -1 to cancel): ");
            if (x == -1) {
                System.out.println("Operation cancelled.");
                return;
            }
            if (x < 0) {
                System.out.println("Cannot compute square root of a negative number.");
                System.out.println("Please enter a value >= 0 or -1 to cancel.");
            } else {
                System.out.println("sqrt(" + x + ") = " + Math.sqrt(x));
                return;
            }
        }
    }

    // ================== 4) Power ==================
    private static void performPower() {
        System.out.println("--- Power x^y ---");
        double base = readDouble("Base x: ");
        double exp = readDouble("Exponent y: ");
        System.out.println(base + " ^ " + exp + " = " + Math.pow(base, exp));
    }

    // ================== 5) Factorial ==================
    private static void performFactorial() {
        System.out.println("--- Factorial ---");
        int n = readInt("Enter non-negative integer (0 to 20, or -1 to cancel): ");

        if (n == -1) {
            System.out.println("Operation cancelled.");
            return;
        }

        if (n < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
            return;
        }

        if (n > MAX_FACTORIAL_N) {
            System.out.println(n + " is too large. Please enter a number <= " + MAX_FACTORIAL_N + ".");
            return;
        }

        long fact = factorial(n);
        System.out.println(n + "! = " + fact);
    }

    private static long factorial(int n) {
        if (n <= 1) return 1;
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    // ================== 6) Temperature Conversion ==================
    private static void performTemperatureConversion() {
        System.out.println("--- Temperature Conversion ---");

        while (true) {
            System.out.println("1. C -> F");
            System.out.println("2. F -> C");
            System.out.println("3. C -> K");
            System.out.println("4. K -> C");
            System.out.println("5. F -> K");
            System.out.println("6. K -> F");
            System.out.println("0. Back to main menu");

            int op = readInt("Choose option (0-6): ");
            if (op == 0) {
                System.out.println("Returning to main menu...");
                return;
            }

            if (op < 1 || op > 6) {
                System.out.println("Invalid option. Please choose from 0 to 6.");
                System.out.println(LINE_SEPARATOR);
                continue;
            }

            double val = readDouble("Enter temperature: ");
            switch (op) {
                case 1 -> System.out.println(val + "°C = " + cToF(val) + "°F");
                case 2 -> System.out.println(val + "°F = " + fToC(val) + "°C");
                case 3 -> System.out.println(val + "°C = " + cToK(val) + " K");
                case 4 -> System.out.println(val + " K = " + kToC(val) + "°C");
                case 5 -> System.out.println(val + "°F = " + fToK(val) + " K");
                case 6 -> System.out.println(val + " K = " + kToF(val) + "°F");
            }

            System.out.println(LINE_SEPARATOR);
        }
    }

    private static double cToF(double c) {
        return (c * 9.0 / 5.0) + 32.0;
    }

    private static double fToC(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    private static double cToK(double c) {
        return c + 273.15;
    }

    private static double kToC(double k) {
        return k - 273.15;
    }

    private static double fToK(double f) {
        return cToK(fToC(f));
    }

    private static double kToF(double k) {
        return cToF(kToC(k));
    }

    // ================== 7) Currency Conversion ==================
    private static void performCurrencyConversion() {
        System.out.println("--- Currency Conversion ---");
        System.out.println("You will provide the conversion rate.");
        System.out.println("Example: if 1 USD = 50 EGP, then rate = 50 (target per 1 source).");

        double rate = readDouble("Enter conversion rate (target per 1 source): ");
        if (rate <= 0) {
            System.out.println("Rate must be positive.");
            return;
        }

        double amount = readDouble("Enter amount in source currency: ");
        double converted = amount * rate;

        System.out.println(amount + " (source) = " + converted + " (target)");
        System.out.println("To convert back from target to source, divide by the rate.");
    }

    // ================== 8) Guessing Game ==================
    private static void performNumberGuessingGame() {
        System.out.println("--- Number Guessing Game ---");

        int max = readInt("Choose maximum number (>= 5, 0 to cancel): ");
        if (max == 0) {
            System.out.println("Game cancelled.");
            return;
        }
        if (max < 5) {
            System.out.println("Maximum should be at least 5 for a meaningful game.");
            return;
        }

        int secret = 1 + (int) (Math.random() * max);
        boolean won = false;

        System.out.println("I have chosen a number between 1 and " + max + ".");
        System.out.println("You have " + MAX_GUESS_ATTEMPTS + " attempts. Good luck!");

        for (int attempt = 1; attempt <= MAX_GUESS_ATTEMPTS; attempt++) {
            System.out.println("Attempt " + attempt + " of " + MAX_GUESS_ATTEMPTS);
            int guess = readInt("Your guess: ");

            if (guess == secret) {
                System.out.println("Correct! You guessed the number in " + attempt + " attempts.");
                won = true;
                break;
            } else if (guess < secret) {
                System.out.println("Hint: Higher.");
            } else {
                System.out.println("Hint: Lower.");
            }

            System.out.println(LINE_SEPARATOR);
        }

        if (!won) {
            System.out.println("You ran out of attempts. The correct number was: " + secret);
        }
    }
}
