package pl.maciek;

import java.util.InputMismatchException;
import java.util.Scanner;


public class TicTacToe {
    static char playerX = 'X';
    static char playerO = 'O';
    static char[][] gameBoard = new char[3][3];
    static int corX;
    static int corY;
    static int moveCounter = 0; //Variable that store the number of chars that are currently on the game board

    public static void main(String[] args) {

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        printGameBoard();
        courseOfTheGame();
    }
    public static boolean conditionsForXWin(char[][] gameBoard) {
        return gameBoard[0][0] == 'X' && gameBoard[0][1] == 'X' && gameBoard[0][2] == 'X' ||
                gameBoard[1][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[1][2] == 'X' ||
                gameBoard[2][0] == 'X' && gameBoard[2][1] == 'X' && gameBoard[2][2] == 'X' ||

                gameBoard[0][0] == 'X' && gameBoard[1][0] == 'X' && gameBoard[2][0] == 'X' ||
                gameBoard[0][1] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][1] == 'X' ||
                gameBoard[0][2] == 'X' && gameBoard[1][2] == 'X' && gameBoard[2][2] == 'X' ||

                gameBoard[0][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][2] == 'X' ||
                gameBoard[2][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[0][2] == 'X';
    } //Conditions that must be met for player X to win the game

    public static boolean conditionsForOWin(char[][] gameBoard) {
        return gameBoard[0][0] == 'O' && gameBoard[0][1] == 'O' && gameBoard[0][2] == 'O' ||
                gameBoard[1][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[1][2] == 'O' ||
                gameBoard[2][0] == 'O' && gameBoard[2][1] == 'O' && gameBoard[2][2] == 'O' ||

                gameBoard[0][0] == 'O' && gameBoard[1][0] == 'O' && gameBoard[2][0] == 'O' ||
                gameBoard[0][1] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][1] == 'O' ||
                gameBoard[0][2] == 'O' && gameBoard[1][2] == 'O' && gameBoard[2][2] == 'O' ||

                gameBoard[0][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][2] == 'O' ||
                gameBoard[2][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[0][2] == 'O';
    } //Conditions that must be met for player O to win the game

    public static void printGameBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    } //Printing game borad

    public static void courseOfTheGame() {
        Scanner sc = new Scanner(System.in);
        boolean isGameFinished = true;
        boolean isInputCorrect = true;

        while (isInputCorrect) {
            System.out.println("Enter the coordinates: ");
            try {
                corX = sc.nextInt();
                corY = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                continue;
            }

            if (corY < 1 || corY > 3 || corX < 1 || corX > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (gameBoard[corX - 1][corY - 1] == playerX || gameBoard[corX - 1][corY - 1] == playerO) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                isInputCorrect = false;
            }
        }
        moveCounter++;
        if (moveCounter % 2 != 0) {
            gameBoard[corX - 1][corY - 1] = playerX;
        } else {
            gameBoard[corX - 1][corY - 1] = playerO;
        }
        printGameBoard();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == ' ') {
                    isGameFinished = false;
                }
            }
        }

        if (!conditionsForXWin(gameBoard) && !conditionsForOWin(gameBoard) && !isGameFinished) {
            courseOfTheGame();
        } else if (conditionsForXWin(gameBoard) || conditionsForXWin(gameBoard) && !isGameFinished) {
            System.out.println("X wins");
        } else if (conditionsForOWin(gameBoard) || conditionsForOWin(gameBoard) && !isGameFinished) {
            System.out.println("O wins");
        } else if (!conditionsForOWin(gameBoard) && !conditionsForOWin(gameBoard) && isGameFinished) {
            System.out.println("Draw");
        }
    }
}