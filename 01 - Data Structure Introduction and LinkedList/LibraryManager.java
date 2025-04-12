import java.util.Scanner;
class Book{
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    Book prev, next;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.prev = null;
        this.next = null;
    }
}
public class LibraryManager {
    private Book head=null;
    private Book tail=null;
    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable){
        Book newBook=new Book(title, author, genre, bookId, isAvailable);
        if (head==null) {
            head=tail=newBook;
        }else{
            newBook.next=head;
            head.prev=newBook;
            head=newBook;
        }
    }
    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable){
        Book newBook=new Book(title, author, genre, bookId, isAvailable);
        if (tail==null) {
            head=tail=newBook;
        } else {
            tail.next=newBook;
            newBook.prev=tail;
            tail=newBook;
        }
    }
    public void addAtPosition(int position,String title, String author, String genre, int bookId, boolean isAvailable){
        if (position<=1||head==null) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        Book temp=head;
        int index=1;
        while (temp!=null&&index<position-1) {
            temp=temp.next;
            index++;
        }
        if (temp==null||temp.next==null) {
            addAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook=new Book(title, author, genre, bookId, isAvailable);
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
    }
    public void removeById(int bookId) {
        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book ID not found.");
            return;
        }

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        System.out.println("Book ID " + bookId + " removed.");
    }
    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No book found with title: " + title);
    }
    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No books found by author: " + author);
    }
    public void updateAvailability(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }
    public void displayForward() {
        if (head == null) {
            System.out.println("No books in library.");
            return;
        }

        System.out.println("Library Books (Forward):");
        Book temp = head;
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }
    public void displayReverse() {
        if (tail == null) {
            System.out.println("No books in library.");
            return;
        }

        System.out.println("Library Books (Reverse):");
        Book temp = tail;
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }
    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total books: " + count);
    }
    private void displayBook(Book b) {
        System.out.println("ID: " + b.bookId + ", Title: " + b.title + ", Author: " + b.author + ", Genre: " + b.genre + ", Available: " + (b.isAvailable ? "Yes" : "No"));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManager lib = new LibraryManager();

        int choice;
        do {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search by Title");
            System.out.println("6. Search by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display All Books (Forward)");
            System.out.println("9. Display All Books (Reverse)");
            System.out.println("10. Count Total Books");
            System.out.println("11. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();
            String title, author, genre;
            int bookId, pos;
            boolean avail;

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: "); title = sc.nextLine();
                    System.out.print("Enter Author: "); author = sc.nextLine();
                    System.out.print("Enter Genre: "); genre = sc.nextLine();
                    System.out.print("Enter Book ID: "); bookId = sc.nextInt();
                    System.out.print("Is Available (true/false): "); avail = sc.nextBoolean();
                    lib.addAtBeginning(title, author, genre, bookId, avail);
                    break;

                case 2:
                    System.out.print("Enter Title: "); title = sc.nextLine();
                    System.out.print("Enter Author: "); author = sc.nextLine();
                    System.out.print("Enter Genre: "); genre = sc.nextLine();
                    System.out.print("Enter Book ID: "); bookId = sc.nextInt();
                    System.out.print("Is Available (true/false): "); avail = sc.nextBoolean();
                    lib.addAtEnd(title, author, genre, bookId, avail);
                    break;

                case 3:
                    System.out.print("Enter Position: "); pos = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Title: "); title = sc.nextLine();
                    System.out.print("Enter Author: "); author = sc.nextLine();
                    System.out.print("Enter Genre: "); genre = sc.nextLine();
                    System.out.print("Enter Book ID: "); bookId = sc.nextInt();
                    System.out.print("Is Available (true/false): "); avail = sc.nextBoolean();
                    lib.addAtPosition(pos, title, author, genre, bookId, avail);
                    break;

                case 4:
                    System.out.print("Enter Book ID to remove: "); bookId = sc.nextInt();
                    lib.removeById(bookId);
                    break;

                case 5:
                    System.out.print("Enter Book Title to search: "); title = sc.nextLine();
                    lib.searchByTitle(title);
                    break;

                case 6:
                    System.out.print("Enter Author to search: "); author = sc.nextLine();
                    lib.searchByAuthor(author);
                    break;

                case 7:
                    System.out.print("Enter Book ID: "); bookId = sc.nextInt();
                    System.out.print("Is Available (true/false): "); avail = sc.nextBoolean();
                    lib.updateAvailability(bookId, avail);
                    break;

                case 8:
                    lib.displayForward();
                    break;

                case 9:
                    lib.displayReverse();
                    break;

                case 10:
                    lib.countBooks();
                    break;

                case 11:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 11);

        sc.close();
    }
}
