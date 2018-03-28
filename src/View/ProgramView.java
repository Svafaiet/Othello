package View;

import java.util.Scanner;

public class ProgramView {
    private Scanner scanner = new Scanner(System.in);
    private ProgressView programView = new ProgressView();

    public String[] getUserRequest() {
        String requestText;
        requestText = scanner.nextLine().replaceAll("\\s+", " ");
        requestText = requestText.replaceAll("^ ", "").replaceAll(" $", "");
        System.out.println(requestText);
        return requestText.split(" ");
    }

}
