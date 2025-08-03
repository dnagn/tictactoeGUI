import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {

    private JFrame frame;
    private JPanel panel, wrapper;
    private int width, height;
    private JButton[][] boardButtons;
    private String currentPlayer = "X";

    // constructor 
    public TicTacToe() {
        frame = new JFrame();
        width = 400;
        height = 400;
        panel = new JPanel();
        wrapper = new JPanel();
        boardButtons = new JButton[3][3];
    }

    public void setUpTicTacToe() {
        frame.setTitle("Tic Tac Toe");
        frame.setSize(width, height);
        panel.setBackground(new Color(178, 123, 206));
        panel.setLayout(new GridLayout(3, 3, 8, 8));
        panel.setPreferredSize(new Dimension(300, 300));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        setUpBoard();
        wrapper.setLayout(new GridBagLayout());
        wrapper.setBackground(new Color(178, 123, 206)); // center its content
        wrapper.add(panel); // add the game board panel to center
        frame.getContentPane().add(wrapper);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object clickedButoon = e.getSource();
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[0].length; col++) {
                if (clickedButoon == boardButtons[row][col]) {
                    // check if the clicked button is empty beofore placing the letter
                    if (!ifEmpty(row, col)) {
                        System.out.println("Try again!");
                    } else {
                        boardButtons[row][col].setText(currentPlayer);
                        boardButtons[row][col].setForeground(
                            currentPlayer.equals("X") ? new Color(245, 114, 114) : new Color(51, 153, 255)
                            );
                    }

                    // check for a winner or a draw
                    if(winnerFound(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + "won!");
                        disableButtons();
                        return;
                    } else if(ifDraw()) {
                        disableButtons();
                        return;
                    }
                    currentPlayer = currentPlayer.equals("X") ? "Y" : "X";
                }
            }
        }
    }

    private void setUpBoard() {
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[0].length; col++) {
                boardButtons[row][col] = new JButton(" ");
                boardButtons[row][col].setFont(new Font("Comic Sans MS", Font.BOLD, 34));
                boardButtons[row][col].setBackground(new Color(240, 248, 255));
                boardButtons[row][col].addActionListener(this);
                boardButtons[row][col].setEnabled(true);
                boardButtons[row][col].setBorderPainted(false);
                boardButtons[row][col].setContentAreaFilled(true); 
                boardButtons[row][col].setOpaque(true);         
                panel.add(boardButtons[row][col]);
            }
        }
    }

    private void disableButtons() {
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[0].length; col++) {
                boardButtons[row][col].setEnabled(false);
            }
        }
    }

    private boolean ifEmpty(int row, int col) {
        if (boardButtons[row][col].getText().equals(" ")) {
            return true;
        } 
        return false;
    }

    private boolean winnerFound(String player) {
        // check for rows
        for (int row = 0; row < boardButtons.length; row++) {
            if (boardButtons[row][0].getText().equals(player) && boardButtons[row][1].getText().equals(player) && boardButtons[row][2].getText().equals(player)) {
                return true;
            }
        }

        // check for columns
        for (int col = 0; col < boardButtons[0].length; col++) {
            if (boardButtons[0][col].getText().equals(player) && boardButtons[1][col].getText().equals(player) && boardButtons[2][col].getText().equals(player)) {
                return true;
            } 
        }

        // check for diagonals
        if (boardButtons[0][0].getText().equals(player) && boardButtons[1][1].getText().equals(player) && boardButtons[2][2].getText().equals(player)) {
            return true;
        }

        if (boardButtons[0][2].getText().equals(player) && boardButtons[1][1].getText().equals(player) && boardButtons[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    } 

    private boolean ifDraw() {
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[0].length; col++) {
                if (boardButtons[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


}