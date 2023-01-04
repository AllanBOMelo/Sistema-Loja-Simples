import java.util.Scanner;

abstract class Screen {
    Scanner scan1 = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);
    Scanner select = new Scanner(System.in);

    private String msg = " ";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
