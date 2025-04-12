import java.util.HashSet;
public class RemoveDuplicates {
    public static String removeDuplicateCharacters(String input){
        StringBuilder result=new StringBuilder();
        HashSet<Character> seen=new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                result.append(c);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String input="programming";
        String output=removeDuplicateCharacters(input);
        System.out.println("Original: "+input);
        System.out.println("Without duplicates: "+output);
    }
}