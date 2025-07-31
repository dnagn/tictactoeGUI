import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {

    private JFrame frame;
    private int width, height;
    private JButton[][] button;
    private String currentPlayer = "X";

    // constructor 
    public TicTacToe(int width, int height) {
        frame = new JFrame();
        width = this.width;
        height = this.height;
        button = new JButton[3][3];
    }

    public void setUpTicTacToe() {
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(width, height);
        setUpBoard(button);
        frame.setTitle("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object clickedButoon = e.getSource();
        for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[0].length; col++) {
                if (clickedButoon == button[row][col]) {
                    // check if the clicked button is empty
                    if (!ifEmpty(row, col)) {
                        System.out.println("Try again!");
                    } else {
                        button[row][col].setText(currentPlayer);
                    }
                    // check if there's a winner
                    if(winnerFound(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + "won!");
                        return;
                    }

                    if (currentPlayer.equals("X")) {
                        currentPlayer = "Y";
                    } else {
                        currentPlayer = "X"
;                    }
                }
            }
        }
    }

    private void setUpBoard(JButton[][] button) {
        for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[0].length; col++) {
                button[row][col] = new JButton(" ");
                button[row][col].addActionListener(this);
                button[row][col].setEnabled(true);
                frame.add(button[row][col]);
            }
        }
    }

    private boolean ifEmpty(int row, int col) {
        if (button[row][col].getText().equals(" ")) {
            return true;
        } 
        return false;
    }

    private boolean winnerFound(String player) {
        // check for rows
        for (int row = 0; row < button.length; row++) {
            if (button[row][0].getText().equals(player) && button[row][1].getText().equals(player) && button[row][2].getText().equals(player)) {
                return true;
            }
        }

        // check for collumns
        for (int col = 0; col < button[0].length; col++) {
            if (button[0][col].getText().equals(player) && button[1][col].getText().equals(player) && button[2][col].getText().equals(player)) {
                return true;
            } 
        }

        // check for diagonals
        if (button[0][0].getText().equals(player) && button[1][1].getText().equals(player) && button[2][2].getText().equals(player)) {
            return true;
        }

        if (button[0][2].getText().equals(player) && button[1][1].getText().equals(player) && button[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    } 


}