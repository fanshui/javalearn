import java.util.Stack;

public class Jn {

    public static void main(String[] args) {
        System.out.println(Switch(235,15));
        System.out.println(Integer.toString(235,15));
    }

    public static String Switch(int num, int dest) {
        StringBuilder sb = new StringBuilder();
        String a = "0123456789ABCDE";
        Stack s = new Stack();
        while (num != 0) {
            s.push(a.charAt(num % dest));
            num /= dest;
        }
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }



}
