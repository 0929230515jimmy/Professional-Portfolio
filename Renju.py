import random


BOARD_SIZE = int(input("Enter the size of the board"))

board = []
def initBoard() :
    for i in range(BOARD_SIZE) :
        row = ["+"] * BOARD_SIZE
        board.append(row)

def printBoard() :
    for i in range(BOARD_SIZE) :
        for j in range(BOARD_SIZE) :
            print(board[i][j], end="")
        print()

initBoard()
printBoard()

inputStr = input("The cordinate you want (Enter as x, y format): \n")
while inputStr != None :
    x_str, y_str = inputStr.split(sep = ",")
    if int(x_str)==99 and int(y_str)==99:
        print("forfeit")
        break
    if int(x_str)>BOARD_SIZE or int(y_str)>BOARD_SIZE:
        print("cordinate not inside the board")
        printBoard()
        inputStr = input("The cordinate you want (Enter as x, y format): \n")
        continue
    if board[int(y_str)-1][int(x_str)-1]=="O" or board[int(y_str)-1][int(x_str)-1]=="X":
        print("This spot has already been occupied")
        printBoard()
        inputStr = input("The cordinate you want (Enter as x, y format): \n")
        continue
    board[int(y_str)-1][int(x_str)-1] = "O"
    
    while True:
        y_index=random.randrange(BOARD_SIZE)+1
        x_index=random.randrange(BOARD_SIZE)+1

        if board[int(y_index)-1][int(x_index)-1]=="X" or board[int(y_index)-1][int(x_index)-1]=="O":
            continue
        board[int(y_index)-1][int(x_index)-1]="X"
        break
    winflag = 0
    for i in range(BOARD_SIZE):
        for j in range(BOARD_SIZE):
            if board[i][j]=="O" and board[i+1][j]=="O" and board[i+2][j]=="O" and board[i+3][j]=="O" and board[i+4][j]=="O":
                print("O wins")
                winflag=1
            if board[i][j]=="O" and board[i][j+1]=="O" and board[i][j+2]=="O" and board[i][j+3]=="O" and board[i][j+4]=="O":
                print("O wins")
                winflag=1
            if board[i][j]=="O" and board[i+1][j+1]=="O" and board[i+2][j+2]=="O" and board[i+3][j+3]=="O" and board[i+4][j+4]=="O":
                print("O wins")
                winflag=1
            if board[i][j]=="O" and board[i-1][j+1]=="O" and board[i-2][j+2]=="O" and board[i-3][j+3]=="O" and board[i-4][j+4]=="O":
                print("O wins")
                winflag=1
        
            if winflag==0:
                if board[i][j]=="X" and board[i+1][j]=="X" and board[i+2][j]=="X" and board[i+3][j]=="X" and board[i+4][j]=="X":
                    print("X wins")
                    winflag=1
                if board[i][j]=="X" and board[i][j+1]=="X" and board[i][j+2]=="X" and board[i][j+3]=="X" and board[i][j+4]=="X":
                    print("X wins")
                    winflag=1
                if board[i][j]=="X" and board[i+1][j+1]=="X" and board[i+2][j+2]=="X" and board[i+3][j+3]=="X" and board[i+4][j+4]=="X":
                    print("X wins")
                    winflag=1
                if board[i][j]=="X" and board[i-1][j+1]=="X" and board[i-2][j+2]=="X" and board[i-3][j+3]=="X" and board[i-4][j+4]=="X":
                    print("X wins")
                    winflag=1
    printBoard()
    
    if winflag==1:
        break
    inputStr = input("The cordinate you want (Enter as x, y format): \n")
