# 튜플을 집합으로 변경
# 집합을 길이순으로 변경
# 답 작성

def solution(s):
    answer = []
    tuples = []
    numbers = []
    number = ""
    for c in s[1:len(s)-1]:
        if c.isdigit():
            number += c
        else:
            if c == "{":
                numbers = []
                number = ""
            elif c == "}":
                numbers.append(int(number))
                answer.append(numbers)
                number = ""
            elif c == "," and number != "":
                numbers.append(int(number))
                number = ""
    
    answer.sort(key=len)
    for i in range(len(answer)):
        for num in answer[i]:
            if num not in tuples:
                tuples.append(num)
    return tuples