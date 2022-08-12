package porttest;

public class ObjectEquals {
    public static void main(String[] args) {
        String str = "12";
        Object o=str;

        String str1=(String)o;
        Integer i = 12;

        System.out.println(o.getClass().equals(int.class));
        System.out.println(o.getClass().equals(Integer.class));

    }

}
