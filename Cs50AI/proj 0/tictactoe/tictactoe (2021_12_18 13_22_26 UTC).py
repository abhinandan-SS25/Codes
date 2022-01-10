"""
Tic Tac Toe Player
"""
import math
import copy

X = "X"
O = "O"
EMPTY = None


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
    if initial_state()==board:
        return X
    elif terminal(board):
        return O
    else:
        x,y=0,0
        for i in board:
            x=x+i.count(X)
            y=y+i.count(O)
        if (x+y)%2==0:
            return X
        else:
            return O
            
        


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    
    if terminal(board):
        return O
    
    ac=set()
    
    for i in range(3):
        for j in range(3):
            if board[i][j]==EMPTY:
                ac.add((i,j))
    return ac                
    


def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """
    if action not in actions(board):
        raise ValueError("Invalid Move")
    
    b1=copy.deepcopy(board)
    
    b1[action[0]][action[1]]=player(board)
    return b1
    
        


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
        


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """
    x=winner(board)
    if x==X or x==O:
        return True
    else:
        return None


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """
    if terminal(board) and winner(board)==X:
        return 1
    elif terminal(board) and winner(board)==O:
        return -1
    elif terminal(board) and winner(board)==None:
        return 0

def minimax(board):
    current_player = player(board)

    if current_player == X:
        v = -math.inf
        for action in actions(board):
            k = min_value(result(board, action))    
            if k > v:
                v = k
                best_move = action
    else:
        v = math.inf
        for action in actions(board):
            k = max_value(result(board, action))    
            if k < v:
                v = k
                best_move = action
    return best_move

def max_value(board):
    if terminal(board):
        return utility(board)
    v = -math.inf
    for action in actions(board):
        v = max(v, min_value(result(board, action)))
    return v    

def min_value(board):
    if terminal(board):
        return utility(board)
    v = math.inf
    for action in actions(board):
        v = min(v, max_value(result(board, action)))
    return v
            
            
        
