import java.util.Scanner;
class Item{
    int itemId;
    String itemName;
    int quantity;
    double price;
    Item next;
    Item(int itemId,String itemName,int quantity,double price){
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}
public class InventoryManagement {
    private Item head=null;
    public void addAtBeginning(int id,String name,int qty,double price){
        Item newItem=new Item(id, name, qty, price);
        newItem.next=head;
        head=newItem;
        System.out.println("Itema added at beginning");
    }
    public void addAtEnd(int id,String name,int qty,double price){
        Item newItem=new Item(id, name, qty, price);
        if (head==null){
            head=newItem;
        }else{
            Item temp=head;
            while (temp.next!=null) {
                temp=temp.next;
            }
            temp.next=newItem;
        }
        System.out.println("Item added at end");
    }
    public void addAtPosition(int position,int id,String name,int qty,double price){
        if (position<=1||head==null) {
            addAtBeginning(id, name, qty, price);
            return;
        }
        Item newItem=new Item(id, name, qty, price);
        Item temp=head;
        int count=1;
        while (count<position-1&&temp.next!=null) {
            temp=temp.next;
            count++;
        }
        newItem.next=temp.next;
        temp.next=newItem;
        System.out.println("Item added at position "+position);
    }
    public void removeById(int id){
        if (head==null) {
            System.out.println("Inventory is empty");
            return;
        }
        if (head.itemId==id) {
            head=head.next;
            System.out.println("Item removed");
            return;
        }
        Item temp=head,prev=null;
        while (temp!=null&&temp.itemId!=id) {
            prev=temp;
            temp=temp.next;
        }
        if (temp==null) {
            System.out.println("Item ID not found");
            return;
        }
        prev.next=temp.next;
        System.out.println("Item removed");
    }
    public void updateQuantity(int id,int newQty){
        Item temp=head;
        while (temp!=null) {
            if (temp.itemId==id) {
                temp.quantity=newQty;
                System.out.println("Quantity updated");
                return;
            }
            temp=temp.next;
        }
        System.out.println("Item ID not found");
    }
    public void searchById(int id){
        Item temp=head;
        while (temp!=null) {
            if (temp.itemId==id) {
                displayItem(temp);
                return;
            }
            temp=temp.next;
        }
        System.out.println("Item ID not found");
    }
    public void searchByName(String name){
        Item temp=head;
        boolean found=false;
        while (temp!=null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                displayItem(temp);
                found=true;
            }
            temp=temp.next;
        }
        if (!found) {
            System.out.println("No item found with name: "+name);
        }
    }
    public void calculateTotalValue(){
        double total=0;
        Item temp=head;
        while (temp!=null) {
            total+=temp.quantity*temp.price;
            temp=temp.next;
        }
        System.out.printf("Total inventory value: Rs%.2f\n",total);
    }
    public void displayAll(){
        if (head==null) {
            System.out.println("No items in inventory");
            return;
        }
        System.out.println("\n---Inventory List---");
        Item temp=head;
        while (temp!=null) {
            displayItem(temp);
            temp=temp.next;
        }
    }
    private void displayItem(Item item){
        System.out.println("Item ID: "+item.itemId);
        System.out.println("Item Name: "+item.itemName);
        System.out.println("Quantity: "+item.quantity);
        System.out.println("Price: Rs "+item.price);
        System.out.println("-------");
    }
    public void sortInventory(String by,boolean ascending){
        head=mergeSort(head,by,ascending);
        System.out.println("Inventory sorted by "+by+" in "+(ascending?"ascending":"descending")+" order");
    }
    private Item mergeSort(Item head,String by,boolean asc){
        if (head==null||head.next==null) {
            return head;
        }
        Item middle=getMiddle(head);
        Item nextOfMiddle=middle.next;
        middle.next=null;
        Item left=mergeSort(head, by, asc);
        Item right=mergeSort(nextOfMiddle, by, asc);
        return merge(left,right,by,asc);
    }
    private Item merge(Item left,Item right,String by,boolean asc){
        if (left==null) {
            return right;
        }
        if (right==null) {
            return left;
        }
        Item result;
        boolean compare;
        if (by.equalsIgnoreCase("name")) {
            compare=asc?left.itemName.compareToIgnoreCase(right.itemName)<=0:
                        left.itemName.compareToIgnoreCase(right.itemName)>0;
        }else{
            compare=asc?left.price<=right.price:left.price>right.price;
        }
        if (compare) {
            result=left;
            result.next=merge(left.next, right, by, asc);
        } else {
            result=right;
            result.next=merge(left, right.next, by, asc);
        }
        return result;
    }
    private Item getMiddle(Item head){
        if (head==null) {
            return head;
        }
        Item slow=head,fast=head.next;
        while (fast!=null&&fast.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        InventoryManagement inventory = new InventoryManagement();
        int choice;
        do {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by ID");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display All Items");
            System.out.println("9. Calculate Total Inventory Value");
            System.out.println("10. Sort Inventory");
            System.out.println("11. Exit");
            System.out.print("Choose: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            int id, qty, pos;
            String name;
            double price;

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Item ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    if (choice == 1) inventory.addAtBeginning(id, name, qty, price);
                    else if (choice == 2) inventory.addAtEnd(id, name, qty, price);
                    else {
                        System.out.print("Position: ");
                        pos = sc.nextInt();
                        inventory.addAtPosition(pos, id, name, qty, price);
                    }
                    break;

                case 4:
                    System.out.print("Item ID to remove: ");
                    id = sc.nextInt();
                    inventory.removeById(id);
                    break;

                case 5:
                    System.out.print("Item ID to update: ");
                    id = sc.nextInt();
                    System.out.print("New Quantity: ");
                    qty = sc.nextInt();
                    inventory.updateQuantity(id, qty);
                    break;

                case 6:
                    System.out.print("Item ID to search: ");
                    id = sc.nextInt();
                    inventory.searchById(id);
                    break;

                case 7:
                    System.out.print("Item Name to search: ");
                    name = sc.nextLine();
                    inventory.searchByName(name);
                    break;

                case 8:
                    inventory.displayAll();
                    break;

                case 9:
                    inventory.calculateTotalValue();
                    break;

                case 10:
                    System.out.print("Sort by (name/price): ");
                    String by = sc.nextLine();
                    System.out.print("Ascending? (true/false): ");
                    boolean asc = sc.nextBoolean();
                    inventory.sortInventory(by, asc);
                    break;

                case 11:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 11);

        sc.close();
    }
}
