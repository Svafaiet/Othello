package View;

public class ProgressView {

    public void showProgress(String gameName, String[] players) {
        StringBuilder progressView = new StringBuilder(gameName);
        for (String player : players) {
            progressView.append(" ").append(player);
        }
        System.out.println(progressView.toString());
    }
}
