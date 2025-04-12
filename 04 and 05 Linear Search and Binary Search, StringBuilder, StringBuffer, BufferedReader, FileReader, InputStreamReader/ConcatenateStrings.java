public class ConcatenateStrings {
    public static String concatenate(String[] strings){
        StringBuffer sb=new StringBuffer();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] words={"Java","is","fun","!"};
        String result=concatenate(words);
        System.out.println("Concatenated String: "+result);
    }
}