import java.util.Scanner;

public class DFAact {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter a binary string: ");
        String input = scanner.nextLine();

        // Check if the string is accepted by DFA
        if (isAccepted(input)) {
            System.out.println("Accepted");
        } else {
            System.out.println("Not accepted");
        }

        scanner.close();
    }

    // DFA main method to check acceptance
    public static boolean isAccepted(String str) {
        String state = "q0"; // start state

        for (char ch : str.toCharArray()) {
            // Language; Input validation = only '0' or '1' allowed
            if (ch != '0' && ch != '1') {
                System.out.println("Invalid input! Only 0 and 1 are allowed.");
                return false; 
            }

            // State transitions
            switch (state) {
                case "q0":
                    if (ch == '0') state = "q1";
                    else if (ch == '1') state = "q0";
                    break;

                case "q1":
                    if (ch == '0') state = "q1";
                    else if (ch == '1') state = "q2";
                    break;

                case "q2":
                    if (ch == '0') state = "q1";
                    else if (ch == '1') state = "q0";
                    break;
            }
        }

        // Language : Accepting state is q2 (string ends with 01)
        return state.equals("q2");
    }
}
