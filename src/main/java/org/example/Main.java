package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        playGame();
    }

    private static void displayBoard(char[] board) {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void playGame() {
        Scanner scan = new Scanner(System.in);
        char[] board = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        boolean isEmpty = true;
        byte winner = 0;

        System.out.println("Enter box number to select. Enjoy!\n");
        while (true) {
            displayBoard(board);

            if (!isEmpty) {
                initializeBoard(board);
                isEmpty = true;
            }

            if (winner == 1 || winner == 2 || winner == 3) {
                printResult(winner);
                break;
            }

            userMove(scan, board);

            if (checkWinner(board, 'X')) {
                winner = 1;
                continue;
            }

            if (isBoardFull(board)) {
                winner = 3;
                continue;
            }

            computerMove(board);

            if (checkWinner(board, 'O')) {
                winner = 2;
            }
        }
    }

    private static void initializeBoard(char[] board) {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    private static void printResult(byte winner) {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }

    private static void userMove(Scanner scan, char[] board) {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10 && board[input - 1] != 'X' && board[input - 1] != 'O') {
                board[input - 1] = 'X';
                break;
            } else {
                System.out.println("Invalid input or box already in use. Enter another.");
            }
        }
    }

    private static void computerMove(char[] board) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (board[rand - 1] != 'X' && board[rand - 1] != 'O') {
                board[rand - 1] = 'O';
                break;
            }
        }
    }

    private static boolean checkWinner(char[] board, char symbol) {
        return ((board[0] == symbol && board[1] == symbol && board[2] == symbol) ||
                (board[3] == symbol && board[4] == symbol && board[5] == symbol) ||
                (board[6] == symbol && board[7] == symbol && board[8] == symbol) ||
                (board[0] == symbol && board[3] == symbol && board[6] == symbol) ||
                (board[1] == symbol && board[4] == symbol && board[7] == symbol) ||
                (board[2] == symbol && board[5] == symbol && board[8] == symbol) ||
                (board[0] == symbol && board[4] == symbol && board[8] == symbol) ||
                (board[2] == symbol && board[4] == symbol && board[6] == symbol));
    }

    private static boolean isBoardFull(char[] board) {
        for (char c : board) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        return true;
    }
}