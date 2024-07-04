import sys
input = sys.stdin.readline

s, p = map(int, input().split())
str = list(map(str, input()))
a,c,g,t = map(int, input().split())
a_count = 0
c_count = 0
g_count = 0
t_count = 0
count = 0
start = 0
end = p - 1

for i in range(start,p):
    if str[i] == 'A':
        a_count += 1
    elif str[i] == 'C':
        c_count += 1
    elif str[i] == 'G':
        g_count += 1
    else: t_count += 1

if a_count >= a and c_count >= c and g_count >= g and t_count >= t:
    count += 1

end += 1

while end < s:

    if str[start] == 'A':
        a_count -= 1
    elif str[start] == 'C':
        c_count -= 1
    elif str[start] == 'G':
        g_count -= 1
    else: t_count -= 1

    if str[end] == 'A':
        a_count += 1
    elif str[end] == 'C':
        c_count += 1
    elif str[end] == 'G':
        g_count += 1
    else: t_count += 1

    start += 1
    end += 1

    if a_count >= a and c_count >= c and g_count >= g and t_count >= t:
        count += 1

print(count)