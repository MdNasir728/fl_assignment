
import java.util.HashMap;

class LRUCache {

    class Node {

        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int cap;
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    HashMap<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node newnode) {
        Node temp = head.next;

        newnode.next = temp;
        newnode.prev = head;

        head.next = newnode;
        temp.prev = newnode;
    }

    private void deleteNode(Node delnode) {
        Node prevv = delnode.prev;
        Node nextt = delnode.next;

        prevv.next = nextt;
        nextt.prev = prevv;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node resNode = map.get(key);
            int ans = resNode.value;

            map.remove(key);
            deleteNode(resNode);
            addNode(resNode);

            map.put(key, head.next);
            return ans;
        }
        return -1;
    }

    public void put(int key, int valueue) {
        if (map.containsKey(key)) {
            Node curr = map.get(key);
            map.remove(key);
            deleteNode(curr);
        }

        if (map.size() == cap) {
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        addNode(new Node(key, valueue));
        map.put(key, head.next);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head.next;
        while (current != tail) {
            sb.append("[").append(current.key).append(":").append(current.value).append("] ");
            current = current.next;
        }
        return sb.toString();
    }
}

public class LRU_cache_main {

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.get(1);
        lru.put(3, 3);
        lru.get(2);
        lru.put(4, 4);
        lru.get(1);
        lru.get(3);
        lru.get(4);
    }
}
