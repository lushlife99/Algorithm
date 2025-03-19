def solution(board, skill):
    row, col = len(board), len(board[0])
    damages = [[0 for _ in range(col+1)] for _ in range(row+1)]
    
    for type_, r1, c1, r2, c2, degree in skill:
        damage = type_
        if type_ == 2:
            damage = -1
        
        damages[r1][c1] -= damage * degree
        damages[r1][c2+1] += damage * degree
        damages[r2+1][c1] += damage * degree
        damages[r2+1][c2+1] -= damage * degree

    
    answer = 0
    
    
    for i in range(row):
        for j in range(1, col):
            damages[i][j] += damages[i][j-1]
    
    for i in range(col):
        for j in range(1, row):
            damages[j][i] += damages[j-1][i]
    
    for i in range(row):
        for j in range(col):
            board[i][j] += damages[i][j]
            if board[i][j] > 0:
                answer += 1
    
    return answer