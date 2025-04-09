# 스택에 가격이 떨어지기 전까지 저장
# 가격이 떨어졌다면 갱신


def solution(prices):
    answer = [0] * len(prices)
    stack = []
    
    for i in range(len(prices)):
        price = prices[i]
        while stack and stack[-1][0] > price:
            p, index = stack.pop()
            answer[index] = i - index
        stack.append((price, i))
    
    while stack:
        price, index = stack.pop()
        answer[index] = len(prices) - index - 1
            
    return answer