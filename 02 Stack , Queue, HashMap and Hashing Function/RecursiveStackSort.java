import java.util.Stack;
public class RecursiveStackSort {
    public static void sortStack(Stack<Integer> stack){
        if (!stack.isEmpty()) {
            int top=stack.pop();
            sortStack(stack);
            insertInSortedOrder(stack,top);
        }
    }
    private static void insertInSortedOrder(Stack<Integer> stack,int element){
        if (stack.isEmpty()||element>stack.peek()) {
            stack.push(element);
        }else{
            int top=stack.pop();
            insertInSortedOrder(stack, element);
            stack.push(top);
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        stack.push(30);
        stack.push(-5);
        stack.push(18);
        stack.push(14);
        stack.push(-3);
        System.out.println("Original stack: "+stack);
        sortStack(stack);
        System.out.println("Sorted stack: "+stack);
    }
}