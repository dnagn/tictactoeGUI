import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {

    private JFrame frame;
    private JPanel panel, middlePanel, topPanel;
    private int width, height;
    private JButton[][] boardButtons;
    private JButton restartButton;
    private String currentPlayer = "X";
    private JLabel turnLabel;

    // constructor 
    public TicTacToe() {
        frame = new JFrame();
        width = 600;
        height = 600;
        panel = new JPanel();
        middlePanel = new JPanel();
        topPanel = new JPanel();
        turnLabel = new JLabel();
        boardButtons = new JButton[3][3];
        restartButton = new JButton("Restart Game");
        restartButton.setPreferredSize(new Dimension(110, 60));
    }

    public void setUpTicTacToe() {
        Container contentPane = frame.getContentPane();
        frame.setTitle("Tic Tac Toe");
        frame.setSize(width, height);

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setPreferredSize(new Dimension(width, 80));
        topPanel.setBackground(new Color(178, 123, 206));
        turnLabel.setText(currentPlayer + "'s turn");
        topPanel.add(turnLabel);
        topPanel.add(restartButton);
        contentPane.add(topPanel, BorderLayout.PAGE_START);


        panel.setBackground(new Color(178, 123, 206));
        panel.setLayout(new GridLayout(3, 3, 8, 8));
        panel.setPreferredSize(new Dimension(300, 300));
        contentPane.add(panel, BorderLayout.CENTER);

        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(new Color(178, 123, 206)); // center its content
        middlePanel.add(panel); // add the game board panel to center
        contentPane.add(middlePanel);

        setUpBoard();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object clickedButton = e.getSource();
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[0].length; col++) {
                if (clickedButton == boardButtons[row][col]) {
                    // check if the clicked button is empty beofore placing the letter
                    if (!isBoardEmpty(row, col)) {
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
                        turnLabel.setVisible(false);
                        restartButton.setVisible(true);
                        disableButtons();
                    } else if(ifDraw()) {
                        restartButton.setVisible(true);
                        turnLabel.setVisible(false);
                        disableButtons();
                    }
                    currentPlayer = currentPlayer.equals("X") ? "Y" : "X"; 
                    turnLabel.setText(currentPlayer + "'s turn");
                } else if (clickedButton == restartButton) {
                    currentPlayer = "X";
                    turnLabel.setText(currentPlayer + "'s turn");
                    setUpBoard();
                }
            }
        }
    }

    private void setUpBoard() {
        panel.removeAll(); // clear the panel before setting up the board
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
        restartButton.setVisible(false); // restart button is hidden when a new game starts
        turnLabel.setVisible(true);
        panel.revalidate(); // refresh the panel to show the new buttons
        panel.repaint(); // repaint the panel to ensure the buttons are displayed
        restartButton.addActionListener(this); 
    }

    private void disableButtons() {
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[0].length; col++) {
                boardButtons[row][col].setEnabled(false);
            }
        }
    }

    private boolean isBoardEmpty(int row, int col) {
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