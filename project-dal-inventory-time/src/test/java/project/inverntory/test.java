package project.inverntory;

public class test {

    public static void main(String[] args) {
        String str = "https://apitest.huaxiafinance.com/newPage/protocol/register.html";

        String[] split = str.split("\\.");

        for (String s : split) {
            System.out.println(s);
        }

    }


}
