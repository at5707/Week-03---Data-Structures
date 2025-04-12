import java.util.Scanner;
class Student{
    int rollno;
    String name;
    int age;
    char grade;
    Student next;
    public Student(int rollno,String name,int age,char grade){
        this.rollno=rollno;
        this.name=name;
        this.age=age;
        this.grade=grade;
        this.next=null;
    }
}
public class StudentLinkedList{
    private Student head=null;
    public void addAtBeginning(int rollno,String name,int age,char grade){
        Student newStudent=new Student(rollno,name,age,grade);
        newStudent.next=head;
        head=newStudent;
    }
    public void addAtEnd(int rollno,String name,int age,char grade){
        Student newStudent=new Student(rollno,name,age,grade);
        if(head==null){
            head=newStudent;
            return;
        }
        Student temp=head;
        while(temp.next!=null)
            temp=temp.next;
        temp.next=newStudent;
    }
    public void addAtPosition(int position,int rollno,String name,int age,char grade){
        if(position<1){
            System.out.println("Invalid position");
            return;
        }
        if(position==1){
            addAtBeginning(rollno,name,age,grade);
            return;
        }
        Student newStudent=new Student(rollno,name,age,grade);
        Student temp=head;
        for(int i=1;i<position-1&&temp!=null;i++)
            temp=temp.next;
        if(temp==null){
            System.out.println("Position out of range");
            return;
        }
        newStudent.next=temp.next;
        temp.next=newStudent;
    }
    public void deleteByRollNo(int rollno){
        Student temp=head,prev=null;
        while (temp!=null&&temp.rollno!=rollno){
            prev=temp;
            temp=temp.next;
        }
        if (temp==null){
            System.out.println("Student not found");
            return;
        }
        if (prev==null){
            head=temp.next;
        }else{
            prev.next=temp.next;
        }
        System.out.println("Student deleted successfully");
    }
    public void searchByRollNo(int rollno){
        Student temp=head;
        while (temp!=null){
            if (temp.rollno==rollno){
                System.out.println("Student found:");
                System.out.println("Roll No: "+temp.rollno);
                System.out.println("Name: "+temp.name);
                System.out.println("Age: "+temp.age);
                System.out.println("Grade: "+temp.grade);
                return;
            }
            temp=temp.next;
        }
        System.out.println("Student not found");
    }
    public void updateGrade(int rollno,char newGrade){
        Student temp=head;
        while (temp!=null){
            if (temp.rollno==rollno) {
                temp.grade=newGrade;
                System.out.println("Grade updated successfully");
                return;
            }
            temp=temp.next;
        }
        System.out.println("Student not found");
    }
    public void displayAll(){
        if (head==null){
            System.out.println("No student records");
            return;
        }
        System.out.println("Student Records:");
        Student temp=head;
        while (temp!=null){
            System.out.println("Roll No: "+temp.rollno);
            System.out.println("Name: "+temp.name);
            System.out.println("Age: "+temp.age);
            System.out.println("Grade: "+temp.grade);
            System.out.println("-----");
            temp=temp.next;
        }
    }
    public static void main(String[] args) {
        StudentLinkedList list=new StudentLinkedList();
        Scanner sc=new Scanner(System.in);
        int choice,rollno,age,pos;
        String name;
        char grade;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Position");
            System.out.println("4. Delete Student by Roll No");
            System.out.println("5. Search Student by Roll No");
            System.out.println("6. Update Grade by Roll No");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.println("Enter choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Roll No,Name,Age,Grade: ");
                    rollno=sc.nextInt();
                    name=sc.next();
                    age=sc.nextInt();
                    grade=sc.next().charAt(0);
                    list.addAtBeginning(rollno, name, age, grade);
                    break;
                case 2:
                    System.out.println("Enter Roll No,Name,Age,Grade: ");
                    rollno=sc.nextInt();
                    name=sc.next();
                    age=sc.nextInt();
                    grade=sc.next().charAt(0);
                    list.addAtEnd(rollno, name, age, grade);
                    break;
                case 3:
                    System.out.println("Enter Position,Roll No,Name,Age,Grade: ");
                    pos=sc.nextInt();
                    rollno=sc.nextInt();
                    name=sc.next();
                    age=sc.nextInt();
                    grade=sc.next().charAt(0);
                    list.addAtPosition(pos, rollno, name, age, grade);
                    break;
                case 4:
                    System.out.println("Enter Roll No to delete: ");
                    rollno=sc.nextInt();
                    list.deleteByRollNo(rollno);
                    break;
                case 5:
                    System.out.println("Enter Roll No to search: ");
                    rollno=sc.nextInt();
                    list.searchByRollNo(rollno);
                    break;
                case 6:
                    System.out.println("Enter Roll No and new Grade: ");
                    rollno=sc.nextInt();
                    grade=sc.next().charAt(0);
                    list.updateGrade(rollno, grade);
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice!=8);
        sc.close();
    }
}