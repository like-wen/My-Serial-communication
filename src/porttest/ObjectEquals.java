package porttest;

public class ObjectEquals {

    public static void main(String[] args) {
        String str = "12";
        Integer i = 12;

        System.out.println(str.getClass().equals(int.class));
        System.out.println(i.getClass().equals(Integer.class));

    }

}
