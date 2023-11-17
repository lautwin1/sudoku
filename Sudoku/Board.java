class Board {
  private int board[][];
  private int box[][];
  //private final int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  public Board() {
    this.board = new int[9][9];
    this.box = new int[3][3];
    createBoard();
    printBoard();
  }

  // Creates the complete Sudoku board
  private void createBoard() {
    int rowOff = 0;
    int colOff = 0;  
    for(int i = 0; i < 9; i++) {
      switch(i) {
        case 0: break;
        case 1: colOff = 3; break;
        case 2: colOff = 6; break;
        case 3: rowOff = 3; break;
        case 4: rowOff = 3; colOff = 3; break;
        case 5: rowOff = 3; colOff = 6; break;
        case 6: rowOff = 6; break;
        case 7: rowOff = 6; colOff = 3; break;
        case 8: rowOff = 6; colOff = 6; break;
      }
      createBox(rowOff, colOff);
      for(int row = 0; row < box.length; row++) {
        for(int col = 0; col < box[row].length; col++) {
          board[row + rowOff][col + colOff] = box[row][col];
        }
      }

    }
  }

  // Creates a 3x3 box of numbers from 1-9
  private void createBox(int rOff, int cOff) {
    box = new int[3][3];
    for(int r = 0; r < box.length; r++) {
      for(int c = 0; c < box[r].length; c++) {
        int num = randNum();
        while(checkValidity(num, r + rOff, c + cOff) == false) {
          num = randNum();
        }
        box[r][c] = num;
      }
    }
  }

  // Returns random number between 1 and 9
  private int randNum() {
    return (int)Math.floor((Math.random() * 9) + 1);
  }

  // Checks if a number can be put in a certain cell
  private boolean checkValidity(int num, int row, int col) {
    int[] usedNumbers = new int[9];
    int i = 0;
    // Gets all numbers currently in the box
    for(int r = 0; r < box.length; r++) {
      for(int c = 0; c < box[r].length; c++) {
        if(box[r][c] != 0) {
          usedNumbers[i] = box[r][c];
          i++;
        }
      }
    }

    // Checks if the given number is already in the box
    for(i = 0; i < usedNumbers.length; i++) {
      if(usedNumbers[i] == num) {
        return false;
      }
    }
    System.out.println("No duplicates in box");

    // Checks if the given number appears in the row
    for(i = 0; i < board.length; i++) {
      if(board[row][i] == num) {
        return false;
      }
    }

    System.out.println("No duplicates in row");
    // Checks if the given number appears in the column
    for(i = 0; i < board[row].length; i++) {
      if(board[i][row] == num) {
        return false;
      }
    }
    System.out.println("No duplicates in column. Adding number");
    return true;
  }

  public void printBoard() {
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        System.out.print(board[r][c] + "  ");
      }
      System.out.println();
      System.out.println();
    }
  }
}