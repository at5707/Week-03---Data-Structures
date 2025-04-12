public class SentenceSearch {
    public static String findSentenceWithWord(String[] sentences,String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }
    public static void main(String[] args) {
        String[] sentences={
            "The quick brown fox jumps over the lazy dog.",
            "Java is a popular programming language.",
            "Artificial Intelligence is the future.",
            "Practice makes perfect."
        };
        String wordToFind="Java";
        String result=findSentenceWithWord(sentences, wordToFind);
        System.out.println("Result: "+result);
    }
}