import java.util.*;

public class NFAact{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Define the NFA transitions using a Map
        Map<String, Map<Character, Set<String>>> transitions = new HashMap<>();

        // Transition definitions
        transitions.put("q0", new HashMap<>()); // start state
        transitions.put("q1", new HashMap<>()); // after 'a'
        transitions.put("q2", new HashMap<>()); // accept state (contains 'ab')

        // Define q0, q1, q2 transitions (syntax in order)
        transitions.get("q0").put('a', new HashSet<>(Arrays.asList("q0", "q1")));
        transitions.get("q0").put('b', new HashSet<>(Arrays.asList("q0")));

        transitions.get("q1").put('a', new HashSet<>(Arrays.asList("q1"))); 
        transitions.get("q1").put('b', new HashSet<>(Arrays.asList("q2"))); // 'ab' found!

        transitions.get("q2").put('a', new HashSet<>(Arrays.asList("q2")));
        transitions.get("q2").put('b', new HashSet<>(Arrays.asList("q2")));

        // Start
        Set<String> startStates = new HashSet<>(Arrays.asList("q0"));
        Set<String> currentStates = simulateNFA(transitions, startStates, input);

        // Check if any current state is accepting
        if (currentStates.contains("q2")) {
            System.out.println("Accepted");
        } else {
            System.out.println("Not Accepted");
        }

        scanner.close();
    }

    // Function to simulate the NFA
    public static Set<String> simulateNFA(Map<String, Map<Character, Set<String>>> transitions,
                                          Set<String> currentStates, String input) {
        for (char symbol : input.toCharArray()) {
            Set<String> nextStates = new HashSet<>();

            // Explore transitions for all current states
            for (String state : currentStates) {
                Map<Character, Set<String>> stateTransitions = transitions.get(state);
                if (stateTransitions != null && stateTransitions.containsKey(symbol)) {
                    nextStates.addAll(stateTransitions.get(symbol));
                }
            }

            // Move to next states
            currentStates = nextStates;
        }

        return currentStates;
    }
}
