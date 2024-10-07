
Implementation
====================

- SudokuGenerator: class used to randomly generate a new partially filled sudoku that follows the sudoku rule.
How partially depends on the difficulty percent. The next method will continue to fill until a valid puzzle is
generated. Everytime a false number is placed, it will retry filling the cell. Each cell is represented by an
inner class. It can be either a cell that already has a number which is not editable, or a cell that has 0 as
num and can be updated. The solve method will solve the puzzle using the backtracking algorithm that will try
to fill the cells while using the check method to check the validity of that entry. You are also able to get
the value, check if editable, and check if the puzzle is completed.
 - LargeGrid: class used to initialize the large 3x3 grids. Each of the grid consists small 3x3 grid made by the
SmallGrid class. It will be used by the Frame class to be displayed on the window.
 - SmallGrid: class used to initialize the small 3x3 grids. Similar to the LargeGrid class, it will be able to set
up a 3x3 grid, within that grid, each cell is depended on the generated sudoku puzzle board. If it is not 0,
then that cell will be represented by an object in the CellPresenter class. If it is 0, editable, then that
cell will be represented by an object in the CellEditor class.
 - CellPresenter: class used to simply create a new label on that specific cell in the small grid.
 - CellEditor: class used to modify the specific cell in the small grid. It overrides the insert, delete, and change
update in the DocumentListener interface. InsertUpdate is called when text is inserted into the CellEditor.
RemoveUpdate is called when text is removed from the CellEditor. ChangedUpdate is called when the text in the
CellEditor is changed in any way caused by the inert or delete. Everytime the game is actually completed by using
the method in the SudokuGenerator class, it will attempt to initialize a new window dialog asking the name of the
player. It will write the name and time to a txt file. Later it will ask if the player wants to play another one.
 - GameTimer: class used to track the elapsed time after the initialization of the game. It will be able to update
the time by one second.
 - GenerateTimer: class used to create a label that will be used to display the timer in the frame. It has the
constructor, the initialization method and the method to stop the timer.
 - ToolBar: class used to store all the buttons and timer so that they could be used by the Frame class to be
displayed on the window. The buttons include tutorial, next, answer, rank. Tutorial button will be able to
open up a new window dialog to provide the instructions of the game.
 - Frame: class used to combine both the objects from the LargeGrid class and from ToolBar class. It will set up
the size, position, and the title of the window.
 - GenerateGame: class used for all the functions that the buttons are pointed to. It serves to initialize the object
from the Frame class and create a new sudoku puzzle board. It also includes the functions for reading the txt file
and display that ranking to a new window dialog.
 - PlayerInfo: class used to help with adding both the name and time information to the txt file.
 - RunSudoku: class that takes the GenerateGame class to actually run the game with the designated Game class.
 - SudokuTest: class used to test the core-game model, specially the generated sudoku puzzle.
