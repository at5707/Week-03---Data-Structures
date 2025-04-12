import java.util.Stack;

import javax.management.RuntimeErrorException;
public class QueueUsingStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public QueueUsingStacks(){
        stack1=new Stack<>();
        stack2=new Stack<>();
    }
    public void enqueue(int x){
        stack1.push(x);
    }
    public int dequeue(){
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stack2.isEmpty()) {
            transfer();
        }
        return stack2.pop();
    }
    public int peek(){
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stack2.isEmpty()) {
            transfer();
        }
        return stack2.peek();
    }
    public boolean isEmpty(){
        return stack1.isEmpty()&&stack2.isEmpty();
    }
    private void transfer(){
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    public static void main(String[] args){
        QueueUsingStacks q=new QueueUsingStacks();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println(q.dequeue());
        q.enqueue(40);
        System.out.println(q.dequeue());
        System.out.println(q.peek());
        System.out.println(q.isEmpty());
    }
}
