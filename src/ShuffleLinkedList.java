import edu.princeton.cs.algs4.StdRandom;

public class ShuffleLinkedList
{
    public static int count = 0;

    public static Node shuffle(Node head)
    {
        System.out.println(count++);
        if (head.next == null) return head;

        Node left = null;
        Node right = null;

        Node leftNext = null;
        Node rightNext = null;
        Node next = head;

        int i = 0;
        while (next != null)
        {
            if (i % 2 == 0)
            {
                if (left == null)
                {
                    left = next;
                    leftNext = left;
                }
                else
                {
                    leftNext.next = next;
                    leftNext = leftNext.next;
                }
            }
            else
            {
                if (right == null)
                {
                    right = next;
                    rightNext = right;
                }
                else
                {
                    rightNext.next = next;
                    rightNext = rightNext.next;
                }
            }
            i++;
            next = next.next;
        }

        leftNext.next = null;
        rightNext.next = null;

        left = shuffle(left);
        right = shuffle(right);

        // merge
        i = StdRandom.uniform(2);
        if (i == 0)
        { head = left; left = left.next; }
        else
        { head = right; right = right.next; }

        next = head;
        while (true)
        {
            i = StdRandom.uniform(2);
            if (left == null && right == null) break;
            else if (left == null) { next.next = right; right = right.next; }
            else if (right == null) { next.next = left; left = left.next; }
            else if (i == 0) { next.next = left; left = left.next; }
            else { next.next = right; right = right.next; }

            next = next.next;
        }
        return head;
    }

    public static void main(String[] args)
    {
        Node<Integer> head = null;
        Node<Integer> next = null;
        for (int i = 0; i < 160; i++) {
            Node<Integer> n = new Node();
            n.value = i;
            if (head == null) {
                head = n;
                next = head;
            }
            else
            {
                next.next = n;
                next = next.next;
            }


        }
        head = ShuffleLinkedList.shuffle(head);

//        next = head;
//        while (next != null)
//        {
//            System.out.println(next.value);
//            next = next.next;
//        }
    }
}
