import sys

input_str = sys.stdin.readline().strip()

ans = ""
stk = []

for c in input_str:
    if 'A' <= c <= 'Z':
        ans += c
    elif c == '/' or c == '*':
        while stk and (stk[-1] == '/' or stk[-1] == '*'):
            ans += stk.pop()
        stk.append(c)
    elif c == '-' or c == '+':
        while stk and stk[-1] != '(':
            ans += stk.pop()
        stk.append(c)
    elif c == '(':
        stk.append(c)
    elif c == ')':
        while stk and stk[-1] != '(':
            ans += stk.pop()
        stk.pop()

while stk:
    ans += stk.pop()

print(ans)
