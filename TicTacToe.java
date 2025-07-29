import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {

    private JFrame frame;
    private int width, height;
    private JButton[][] button;
    private char currentPlayer = 'X';

    // constructor 
    public TicTacToe(int width, int height) {
        frame = new JFrame();
        width = this.width;
        height = this.height;
        button = new JButton[3][3];
        /* for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[0].length; col++) {
                button[row][col] = new JButton("");
                frame.add(button[row][col]);
            }
        } */
    }

    public void setUpTicTacToe() {
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(width, height);
        setUpBoard(button);
        frame.setTitle("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setUpBoard(JButton[][] board) {
        for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[0].length; col++) {
                board[row][col] = new JButton("");
                frame.add(board[row][col]);
            }
        }
    }


}