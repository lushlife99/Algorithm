def solution(n, results):
    answer = 0
    answer_table = [[0 for _ in range(n+1)] for _ in range(n+1)] # 1 이김, -1 = 짐
    
    for win_player, lose_player in results:
        answer_table[win_player][lose_player] = 1
        answer_table[lose_player][win_player] = -1
    
    for _ in range(n):
        for i in range(1, n+1):
            for versus in range(1, n+1):
                if i == versus:
                    continue

                if answer_table[i][versus] == 1: # 이긴 상대라면
                    for j in range(1, n+1):
                        if answer_table[versus][j] == 1:
                            answer_table[i][j] = 1
                elif answer_table[i][versus] == -1: # 졌던 상대라면
                    for j in range(1, n+1):
                        if answer_table[versus][j] == -1:
                            answer_table[i][j] = -1
    
    for i in range(1, n+1):
        cnt = 0
        for versus in range(1, n+1):
            if i == versus:
                continue
            
            if answer_table[i][versus] != 0:
                cnt += 1
        if cnt == n-1:
            answer += 1
    
    print(answer_table)
    
    return answer



# 내가 이겼던 상대 -> 한테 졌던 상대 -> 무조건 이김
# 내가 이겼던 상대 -> 한테 이겼던 상대 -> 모름

# 내가 졌던 상대 -> 한테 이겼던 상대 -> 무조건 짐