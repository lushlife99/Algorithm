def solution(storey):
    cnt = 0
    while storey :
        storey, r = storey//10, storey%10

        if r < 5 or (r==5 and storey%10 < 5):
            cnt += r
        else :
            cnt += (10-r); storey += 1

    return cnt