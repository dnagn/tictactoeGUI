import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {

    private JFrame frame;
    private int width, height;
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';

    // constructor 
    public TicTacToe(int width, int height) {
        frame = new JFrame();
        width = this.width;
        height = this.height;
    }

    public void setUpTicTacToe() {
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(width, height);
        frame.setTitle("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}