def solution(s):
    answer = s[0].upper()
    for i in range(1, len(s)):
        if s[i-1] == " ":
            answer += s[i].upper()
        else:
            answer += s[i].lower()
    return answer