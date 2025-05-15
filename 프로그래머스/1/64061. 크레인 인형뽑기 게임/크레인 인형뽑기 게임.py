def solution(board, moves):
    stk = []
    answer = 0
    for m in moves:
        for i in range(len(board)):
            if board[i][m-1] != 0:
                if stk and stk[-1] == board[i][m-1]:
                    stk.pop()
                    answer += 2
                else :
                    stk.append(board[i][m-1])
                
                board[i][m-1] = 0
                break
                
    return answer