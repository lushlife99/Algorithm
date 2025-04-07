def convert_base_k(n, k):
    if n == 0:
        return 0
    result = ''
    while n > 0:
        result += str(n%k)
        n //= k
    return result[::-1]

def is_prime(num):
    
    if num == 0 or num == 1:
        return False
    
    if num == 2 or num == 3:
        return True
    
    if num % 2 == 0 or num % 3 == 0:
        return False
    
    i = 5
    while i*i <= num:
        if num % i == 0 or num % (i+2) == 0:
            return False
        i += 6
    
    return True
    
def solution(n, k):
    answer = 0
    base_k_str = convert_base_k(n, k)
    splited_str = base_k_str.split("0")
    for s in splited_str:
        if s != "" and is_prime(int(s)):
            answer += 1
    return answer