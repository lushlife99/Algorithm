# index, left, right, deleted

def solution(n, k, cmd):
    answer = ''
    table = []
    deleted = []
    for i in range(n):
        table.append([i, i-1, i+1, 'O'])
    
    table[-1][2] = -1
    
    for op in cmd:
        if len(op) > 1:
            op1, op2 = op.split()
            if op1 == 'U':
                for i in range(int(op2)):
                    k = table[k][1]
            else:
                for i in range(int(op2)):
                    k = table[k][2]
        elif op == 'C':
            if table[k][2] != -1: # 다음 행이 있을 때
                next_k = table[k][2] # 다음 커서
                
                left = table[k][1] 
                right = table[k][2]
                if left != -1:
                    table[left][2] = right
                table[right][1] = left
                deleted.append(table[k])
                table[k] = [k, -1, -1, 'X']
                k = next_k
            else:
                left = table[k][1] # 다음 커서
                table[left][2] = -1
                deleted.append(table[k])
                table[k] = [k, -1, -1, 'X']
                k = left
        else: # Z일 때
            deleted_tuple = deleted.pop()
            table[deleted_tuple[1]][2] = deleted_tuple[0]
            table[deleted_tuple[2]][1] = deleted_tuple[0]
            table[deleted_tuple[0]] = [deleted_tuple[0], deleted_tuple[1], deleted_tuple[2], 'O']
    for i in range(n):
        answer += table[i][3]
    return answer