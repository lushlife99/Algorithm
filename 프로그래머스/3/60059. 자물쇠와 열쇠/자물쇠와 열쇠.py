def right_rotate(arr):
    row = len(arr)
    col = len(arr[0])
    
    new_arr = [[0 for _ in range(row)] for _ in range(col)]
    for i in range(row):
        for j in range(col):
            new_arr[j][row - 1 - i] = arr[i][j]

    return new_arr

def is_openable(key, lock):
    key_row = len(key)
    key_col = len(key[0])
    lock_row = len(lock)
    lock_col = len(lock[0])

    if key_row < lock_row or key_col < lock_col:
        return False
    
    # 키를 이동하면서 홈 부분과 맞는지 검사
    for i in range(key_row - lock_row + 1):
        for j in range(key_col - lock_col + 1):
            sig = True
            for k in range(lock_row):
                for l in range(lock_col):
                    if key[i+k][j+l] + lock[k][l] != 1:
                        sig = False
                        break
                if not sig:
                    break
            if sig:
                return True
                
    return False

def solution(key, lock):
    n = len(lock)
    r1 = c1 = n
    r2 = c2 = 0
    
    has_empty = False  

    for i in range(n):
        for j in range(n):
            if lock[i][j] == 0:  # 홈이 있는 부분
                has_empty = True
                r1 = min(r1, i)
                c1 = min(c1, j)
                r2 = max(r2, i)
                c2 = max(c2, j)

    if not has_empty:
        return True

    row = r2 - r1 + 1
    col = c2 - c1 + 1

    empty_places = [[0 for _ in range(col)] for _ in range(row)]
    for i in range(r1, r2+1):
        for j in range(c1, c2+1):
            empty_places[i - r1][j - c1] = lock[i][j]

    for _ in range(4):
        if is_openable(key, empty_places):
            return True
        key = right_rotate(key)

    return False
