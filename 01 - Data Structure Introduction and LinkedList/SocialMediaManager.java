import java.util.*;

class UserNode {
    int userId;
    String name;
    int age;
    ArrayList<Integer> friendIds;
    UserNode next;

    public UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

public class SocialMediaManager {
    private UserNode head = null;
    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }
    private UserNode findUser(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }
    public void addFriend(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("User(s) not found.");
            return;
        }

        if (!user1.friendIds.contains(userId2))
            user1.friendIds.add(userId2);
        if (!user2.friendIds.contains(userId1))
            user2.friendIds.add(userId1);
        
        System.out.println("Friend connection added.");
    }
    public void removeFriend(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("User(s) not found.");
            return;
        }

        user1.friendIds.remove(Integer.valueOf(userId2));
        user2.friendIds.remove(Integer.valueOf(userId1));

        System.out.println("Friend connection removed.");
    }
    public void displayFriends(int userId) {
        UserNode user = findUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Friends of " + user.name + ":");
        for (int fid : user.friendIds) {
            UserNode friend = findUser(fid);
            if (friend != null) {
                System.out.println("ID: " + friend.userId + ", Name: " + friend.name);
            }
        }
    }
    public void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("User(s) not found.");
            return;
        }

        System.out.println("Mutual Friends:");
        for (int fid : user1.friendIds) {
            if (user2.friendIds.contains(fid)) {
                UserNode friend = findUser(fid);
                if (friend != null) {
                    System.out.println("ID: " + friend.userId + ", Name: " + friend.name);
                }
            }
        }
    }
    public void searchById(int userId) {
        UserNode user = findUser(userId);
        if (user != null) {
            System.out.println("User Found: " + user.name + ", Age: " + user.age);
        } else {
            System.out.println("User not found.");
        }
    }
    public void searchByName(String name) {
        UserNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("Found: ID: " + temp.userId + ", Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) System.out.println("No user with that name.");
    }
    public void countFriends() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friend(s).");
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialMediaManager sm = new SocialMediaManager();
        int choice;

        do {
            System.out.println("\n--- Social Media Manager ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Display Friends");
            System.out.println("5. Find Mutual Friends");
            System.out.println("6. Search User by ID");
            System.out.println("7. Search User by Name");
            System.out.println("8. Count Friends for Each User");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            int id1, id2, age;
            String name;

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    id1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    sm.addUser(id1, name, age);
                    break;
                case 2:
                    System.out.print("Enter Your User ID: ");
                    id1 = sc.nextInt();
                    System.out.print("Enter Friend's User ID: ");
                    id2 = sc.nextInt();
                    sm.addFriend(id1, id2);
                    break;
                case 3:
                    System.out.print("Enter Your User ID: ");
                    id1 = sc.nextInt();
                    System.out.print("Enter Friend's User ID to Remove: ");
                    id2 = sc.nextInt();
                    sm.removeFriend(id1, id2);
                    break;
                case 4:
                    System.out.print("Enter User ID to Display Friends: ");
                    id1 = sc.nextInt();
                    sm.displayFriends(id1);
                    break;
                case 5:
                    System.out.print("Enter First User ID: ");
                    id1 = sc.nextInt();
                    System.out.print("Enter Second User ID: ");
                    id2 = sc.nextInt();
                    sm.findMutualFriends(id1, id2);
                    break;
                case 6:
                    System.out.print("Enter User ID: ");
                    id1 = sc.nextInt();
                    sm.searchById(id1);
                    break;
                case 7:
                    System.out.print("Enter User Name: ");
                    name = sc.nextLine();
                    sm.searchByName(name);
                    break;
                case 8:
                    sm.countFriends();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
        sc.close();
    }
}