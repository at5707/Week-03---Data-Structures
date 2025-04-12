import java.util.LinkedList;
public class MyHashMap {
    static class Entry {
        int key;
        int value;
        Entry(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    private final int SIZE=1000;
    private LinkedList<Entry>[] buckets;
    public MyHashMap(){
        buckets=new LinkedList[SIZE];
    }
    private int getIndex(int key){
        return Integer.hashCode(key)%SIZE;
    }
    public void put(int key,int value){
        int index=getIndex(key);
        if (buckets[index]==null) {
            buckets[index]=new LinkedList<>();
        }
        for (Entry entry : buckets[index]) {
            if (entry.key==key) {
                entry.value=value;
                return;
            }
        }
        buckets[index].add(new Entry(key, value));
    }
    public Integer get(int key){
        int index=getIndex(key);
        LinkedList<Entry> bucket=buckets[index];
        if (bucket!=null) {
            for (Entry entry : bucket) {
                if (entry.key==key) {
                    return entry.value;
                }
            }
        }
        return null;
    }
    public void remove(int key){
        int index=getIndex(key);
        LinkedList<Entry> bucket=buckets[index];
        if (bucket!=null) {
            bucket.removeIf(entry->entry.key==key);
        }
    }
    public static void main(String[] args) {
        MyHashMap map=new MyHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        System.out.println("Get key 2: "+map.get(2));
        map.put(2, 25);
        System.out.println("Updated key 2: "+map.get(2));
        map.remove(2);
        System.out.println("After removing key 2: "+map.get(2));
    }
}