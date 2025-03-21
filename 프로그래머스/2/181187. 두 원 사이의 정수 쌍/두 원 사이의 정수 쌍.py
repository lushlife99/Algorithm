import math

def solution(r1, r2):
    answer = 0
    r1_sq = r1 * r1
    r2_sq = r2 * r2

    for x in range(r2 + 1):
        y_max = math.isqrt(r2_sq - x * x)
        if r1_sq >= x * x: 
            y_min = math.ceil(math.sqrt(r1_sq - x * x)) 
        else:
            y_min = 0
        
        answer += (y_max - y_min + 1)

    return (answer - (r2 - r1 + 1)) * 4 
