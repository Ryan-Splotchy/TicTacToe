package tictactoe.logic;


import tictactoe.ui.Board;

import java.util.*;

public class GameLogic {
    private static Board gameBoard = new Board();

    private static final List<Integer> row1 = Arrays.asList(1, 2, 3);
    private static final List<Integer> row2 = Arrays.asList(4, 5, 6);
    private static final List<Integer> row3 = Arrays.asList(7, 8, 9);
    private static final List<Integer> col1 = Arrays.asList(1, 4, 7);
    private static final List<Integer> col2 = Arrays.asList(2, 5, 8);
    private static final List<Integer> col3 = Arrays.asList(3, 6, 9);
    private static final List<Integer> diagonal1 = Arrays.asList(1, 5, 9);
    private static final List<Integer> diagonal2 = Arrays.asList(3, 5, 7);

    private static final List<List> winningConditionList = new ArrayList<>();

    private static final List<Integer> playerPosistions = new ArrayList<>();
    private static final List<Integer> CPUPosistions = new ArrayList<>();

    public static void main(String[] args) {
        gameBoard.printBoard();
        System.out.println("Please enter a number between 1 and 9 inclusive representing a corresponding place on the board: ");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            int playerPos;
            if(scanner.hasNextInt()) {
                playerPos = scanner.nextInt();
                if(playerPos <= 0) {
                    System.out.println("Please enter an integer between 1 and 9 inclusive: ");
                    continue;
                }
                while(playerPosistions.contains(playerPos) || CPUPosistions.contains(playerPos)) {
                    System.out.println("Position Unavailable, choose again!");
                    playerPos = scanner.nextInt();
                }
                placePiece("Player", playerPos, gameBoard.getBoard());
                if((checkBoardForWinner() != null) && (checkBoardForWinner().equals("Player wins!!!"))) {
                    System.out.println(checkBoardForWinner());
                    gameBoard.printBoard();
                    break;
                }
                gameBoard.printBoard();
            }else {
                System.out.println("Please enter an integer between 1 and 9 inclusive: ");
            }

            System.out.println("---------------------------------------------------------------------");


            int cpuPos = new Random().nextInt(10); // cpuAI();
            while(playerPosistions.contains(cpuPos) || CPUPosistions.contains(cpuPos)) {
                cpuPos = new Random().nextInt(10); // cpuAI();
            }
            placePiece("CPU", cpuPos, gameBoard.getBoard());
            if((checkBoardForWinner() != null) && (checkBoardForWinner().equals("CPU wins!!!"))) {
                System.out.println("CPU wins!!!");
                gameBoard.printBoard();
                break;
            }

            if(playerPosistions.size() + CPUPosistions.size() == 9) {
                System.out.println("Tie!!!");
                gameBoard.printBoard();
                break;
            }
            gameBoard.printBoard();



        }
    }

    // Note: Unimplemented method whose purpose is to intelligently determine the next/winning move
    private static int cpuAI() {
        winningConditionList.add(row1);
        winningConditionList.add(row2);
        winningConditionList.add(row3);
        winningConditionList.add(col1);
        winningConditionList.add(col2);
        winningConditionList.add(col3);
        winningConditionList.add(diagonal1);
        winningConditionList.add(diagonal2);

        int numberMatched = 0;

        // TODO: Add a method call to a new method marked with an asterisk, where the next move is determined by the current set of CPU places stored in CPUPositions
        for(List<Integer> l : winningConditionList) {
            for(int i : l) {
                if(CPUPosistions.contains(i)) {
                    numberMatched++;

                    // I was thinking of using Arrays.mismatch() for each list in winningConditionsList with a list of each of the CPU moves, selecting an index from one of the resulting lists as the next move. Once numbersMatched got
                    //  to 2, return the final index of any matched list in winningConditionsList.
                    if(numberMatched == 1) {
//                        int pos = i + 1;
//                        System.out.println(pos); These lines are for testing...
                        if(i + 1 > l.size()) {
                            // *
                            int newPos = (int) winningConditionList.get(winningConditionList.indexOf(l) + 1).get(i);
                            return newPos;
                        }
                        return l.get(i + 1);
                    }
                }else {
                    break;
                }
            }
        }

        return new Random().nextInt(10);
    }

    // Method called after each move
    public static String checkBoardForWinner() {
        winningConditionList.add(row1);
        winningConditionList.add(row2);
        winningConditionList.add(row3);
        winningConditionList.add(col1);
        winningConditionList.add(col2);
        winningConditionList.add(col3);
        winningConditionList.add(diagonal1);
        winningConditionList.add(diagonal2);

       for(List l : winningConditionList) {
           if(playerPosistions.containsAll(l)) {
               return "Player wins!!!";
           }else if(CPUPosistions.containsAll(l)) {
               return "CPU wins!!!";
           }
       }

       return null;
    }

    // Places the piece at selected position
    private static void placePiece(String user, int pos, char[][] board) {
        char symbol = ' ';
        if(user.equals("Player")) {
            symbol = 'X';
            playerPosistions.add(pos);
        }else if(user.equals("CPU")) {
            symbol = 'O';
            CPUPosistions.add(pos);
        }


        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
        }
    }
}
