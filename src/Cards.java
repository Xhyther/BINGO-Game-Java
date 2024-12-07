import java.util.Random;

public class Cards {
    private int[][]  numbers = new int[5][5];
    RandomBingo rb = new RandomBingo();
    BINGO bingoLetter;

    public void Intialize() {
        int rowB = 0;
        int rowI = 0;
        int rowN = 0;
        int rowG = 0;
        int rowO = 0;

        do {
            bingoLetter = rb.getBingo();
            int randomNum = rb.getRandom(bingoLetter);

            // Check the column and ensure the number is unique
            if (bingoLetter == BINGO.B && rowB < 5 && !isDuplicate(randomNum, 0)) {
                numbers[rowB][0] = randomNum;
                rowB++;
            } else if (bingoLetter == BINGO.I && rowI < 5 && !isDuplicate(randomNum, 1)) {
                numbers[rowI][1] = randomNum;
                rowI++;
            } else if (bingoLetter == BINGO.N && rowN < 5 && !isDuplicate(randomNum, 2)) {
                if (rowN == 2) {
                    numbers[rowN][2] = 0; // Free space
                    rowN++;
                } else {
                    numbers[rowN][2] = randomNum;
                    rowN++;
                }
            } else if (bingoLetter == BINGO.G && rowG < 5 && !isDuplicate(randomNum, 3)) {
                numbers[rowG][3] = randomNum;
                rowG++;
            } else if (bingoLetter == BINGO.O && rowO < 5 && !isDuplicate(randomNum, 4)) {
                numbers[rowO][4] = randomNum;
                rowO++;
            }

        } while (rowB < 5 || rowI < 5 || rowN < 5 || rowG < 5 || rowO < 5);
    }

    // Helper method to check if a number already exists in a specific column
    private boolean isDuplicate(int num, int col) {
        for (int i = 0; i < 5; i++) {
            if (numbers[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    public void printCard() {
        System.out.println("    B      I      N      G      O  ");
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.printf(" [%3d ]",numbers[i][j]);
                //Must have a check condition that it suppose to have a unique number
            }
            System.out.println();
        }
    }

    public boolean markNumber(int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numbers[i][j] == number) {
                    numbers[i][j] = 0; // Marked numbers are set to 0
                    return true;
                }
            }
        }
        return false; // Number not found
    }



    public boolean checkBingo() {
        // Check rows
        for (int i = 0; i < 5; i++) {
            boolean rowBingo = true;
            for (int j = 0; j < 5; j++) {
                if (numbers[i][j] != 0) {
                    rowBingo = false;
                    break;
                }
            }
            if (rowBingo) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 5; j++) {
            boolean colBingo = true;
            for (int i = 0; i < 5; i++) {
                if (numbers[i][j] != 0) {
                    colBingo = false;
                    break;
                }
            }
            if (colBingo) {
                return true;
            }
        }

        // Check main diagonal
        boolean mainDiagonalBingo = true;
        for (int i = 0; i < 5; i++) {
            if (numbers[i][i] != 0) {
                mainDiagonalBingo = false;
                break;
            }
        }
        if (mainDiagonalBingo) {
            return true;
        }

        // Check anti-diagonal
        boolean antiDiagonalBingo = true;
        for (int i = 0; i < 5; i++) {
            if (numbers[i][4 - i] != 0) {
                antiDiagonalBingo = false;
                break;
            }
        }
        if (antiDiagonalBingo) {
            return true;
        }

        // No Bingo
        return false;
    }





}

