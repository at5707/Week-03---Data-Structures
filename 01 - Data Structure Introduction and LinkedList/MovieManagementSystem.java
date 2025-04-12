import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieManagementSystem {
    private Movie head = null;
    private Movie tail = null;

    // Add movie at beginning
    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    // Add movie at end
    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    // Add movie at specific position (1-based)
    public void addAtPosition(String title, String director, int year, double rating, int position) {
        if (position <= 1 || head == null) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        Movie newMovie = new Movie(title, director, year, rating);
        Movie current = head;
        int count = 1;

        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }

        if (current == null || current.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newMovie.next = current.next;
            newMovie.prev = current;
            current.next.prev = newMovie;
            current.next = newMovie;
        }
    }

    // Remove movie by title
    public void removeByTitle(String title) {
        Movie current = head;

        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Movie removed.");
                return;
            }
            current = current.next;
        }
        System.out.println("Movie not found.");
    }

    // Search by Director
    public void searchByDirector(String director) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                printMovie(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movies found by this director.");
    }

    // Search by Rating
    public void searchByRating(double rating) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                printMovie(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movies found with this rating.");
    }

    // Update rating
    public void updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                System.out.println("Rating updated.");
                return;
            }
            current = current.next;
        }
        System.out.println("Movie not found.");
    }

    // Display all forward
    public void displayForward() {
        Movie current = head;
        if (current == null) {
            System.out.println("No movies in the system.");
            return;
        }
        System.out.println("Movies (Forward):");
        while (current != null) {
            printMovie(current);
            current = current.next;
        }
    }

    // Display all reverse
    public void displayReverse() {
        Movie current = tail;
        if (current == null) {
            System.out.println("No movies in the system.");
            return;
        }
        System.out.println("Movies (Reverse):");
        while (current != null) {
            printMovie(current);
            current = current.prev;
        }
    }

    // Utility
    private void printMovie(Movie m) {
        System.out.println("Title: " + m.title + ", Director: " + m.director + ", Year: " + m.year + ", Rating: " + m.rating);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieManagementSystem mms = new MovieManagementSystem();
        int choice;

        do {
            System.out.println("\n--- Movie Management System ---");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Display Movies Forward");
            System.out.println("8. Display Movies Reverse");
            System.out.println("9. Update Movie Rating");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // flush

            String title, director;
            int year, position;
            double rating;

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Director: ");
                    director = sc.nextLine();
                    System.out.print("Enter Year: ");
                    year = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    rating = sc.nextDouble();
                    mms.addAtBeginning(title, director, year, rating);
                    break;
                case 2:
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Director: ");
                    director = sc.nextLine();
                    System.out.print("Enter Year: ");
                    year = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    rating = sc.nextDouble();
                    mms.addAtEnd(title, director, year, rating);
                    break;
                case 3:
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Director: ");
                    director = sc.nextLine();
                    System.out.print("Enter Year: ");
                    year = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    rating = sc.nextDouble();
                    System.out.print("Enter Position: ");
                    position = sc.nextInt();
                    mms.addAtPosition(title, director, year, rating, position);
                    break;
                case 4:
                    System.out.print("Enter Title to Remove: ");
                    title = sc.nextLine();
                    mms.removeByTitle(title);
                    break;
                case 5:
                    System.out.print("Enter Director: ");
                    director = sc.nextLine();
                    mms.searchByDirector(director);
                    break;
                case 6:
                    System.out.print("Enter Rating: ");
                    rating = sc.nextDouble();
                    mms.searchByRating(rating);
                    break;
                case 7:
                    mms.displayForward();
                    break;
                case 8:
                    mms.displayReverse();
                    break;
                case 9:
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter New Rating: ");
                    rating = sc.nextDouble();
                    mms.updateRating(title, rating);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 10);
        sc.close();
    }
}
