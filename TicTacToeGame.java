import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeGame {
    private static String currentPlayer = "X";
    private static JButton[][] buttons = new JButton[3][3];

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        // 3x3 buton oluştur ve ekle
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].addActionListener(e -> handleMove((JButton) e.getSource()));
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    // Hareketi işle
    private static void handleMove(JButton button) {
        if (!button.getText().isEmpty()) return; // Buton doluysa işlem yapma
        button.setText(currentPlayer);
        if (checkWinner()) {
            JOptionPane.showMessageDialog(null, "Oyuncu " + currentPlayer + " kazandı!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(null, "Oyun berabere!");
            resetBoard();
        }
        currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // Sıradaki oyuncu
    }

    // Kazanan kontrolü
    private static boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer) && 
                buttons[i][1].getText().equals(currentPlayer) && 
                buttons[i][2].getText().equals(currentPlayer)) return true;
            if (buttons[0][i].getText().equals(currentPlayer) && 
                buttons[1][i].getText().equals(currentPlayer) && 
                buttons[2][i].getText().equals(currentPlayer)) return true;
        }
        return buttons[0][0].getText().equals(currentPlayer) && buttons[1][1].getText().equals(currentPlayer) && buttons[2][2].getText().equals(currentPlayer) ||
               buttons[0][2].getText().equals(currentPlayer) && buttons[1][1].getText().equals(currentPlayer) && buttons[2][0].getText().equals(currentPlayer);
    }

    // Tahtayı doldurma kontrolü
    private static boolean isBoardFull() {
        for (JButton[] row : buttons) 
            for (JButton btn : row) 
                if (btn.getText().isEmpty()) return false;
        return true;
    }

    // Tahtayı sıfırla
    private static void resetBoard() {
        for (JButton[] row : buttons) 
            for (JButton btn : row) 
                btn.setText("");
        currentPlayer = "X";
    }
}
