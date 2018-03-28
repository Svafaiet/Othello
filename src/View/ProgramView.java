package View;

import java.util.Scanner;

public class ProgramView {
    private Scanner scanner = new Scanner(System.in);
    private String requestText;

    public String[] getUserRequest() {
        requestText = scanner.nextLine().replaceAll("\\s+", " ");
        requestText = requestText.replaceAll("^ ", "").replaceAll(" $", "");
        System.out.println(requestText);
        return requestText.split(" ");
    }

}
