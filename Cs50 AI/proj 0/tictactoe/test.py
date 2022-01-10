X = "X"
O = "O"
EMPTY = None

import copy

def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    #check horizontal
    for i in board:
        if i==[X,X,X]:
            return X
        elif i==[O,O,O]:
            return O
        
    #check vertical X
    for i in range(0,3):
        x=board[0][i]
        if board[1][i]==x and board[2][i]==x:
            return x
    
    #check diagonals
    x=board[0][0]
    if board[1][1]==x and board[2][2]==x:
        return x
    
    y=board[2][0]
    if board[1][1]==y and board[0][2]==y:
        return y
    
    return None

def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    
    
    ac=set()
    
    for i in range(3):
        for j in range(3):
            if board[i][j]==EMPTY:
                ac.add((i,j))
    return ac   


def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    x,y=0,0
    for i in board:
        x=x+i.count(X)
        y=y+i.count(O)
    if ((x+y)%2)==0:
        return X
    else:
        return O
    
def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """
    if action not in actions(board):
        raise ValueError("Invalid Move")
    
    b1=copy.deepcopy(board)
    
    b1[action[0]][action[1]]=player(board)
    return b1
    

board=eval(input("Enter the board:"))
action=eval(input("Enter action:"))
print(result(board,action))