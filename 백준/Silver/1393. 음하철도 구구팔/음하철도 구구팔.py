import math

station_x, station_y = map(int, input().split())
x, y, dx, dy = map(int, input().split())
distance = 1000000000
prev_x, prev_y = 0, 0
def gcd(a,b):
    while b > 0:
        a, b = b, a % b

    return a


gcd = gcd(dx, dy)
dx, dy = dx // gcd, dy // gcd


while True:

    current_distance = math.sqrt(math.pow((station_x - x), 2) + math.pow((station_y - y), 2))
    if distance > current_distance:
        distance = current_distance
        prev_x = x
        prev_y = y
        x += dx
        y += dy

    else:
        print(prev_x, end=' ')
        print(prev_y, end=' ')
        break