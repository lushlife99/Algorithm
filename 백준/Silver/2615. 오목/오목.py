arr = [list(map(int, input().split())) for _ in range(19)]

def answer(x, y):
    print(arr[x][y])
    print(x+1, y+1)
    exit()


def check(x, y):
    check_width(x,y)
    check_height(x,y)
    check_right_diag(x,y)
    check_left_diag(x+4, y)


def check_width(x, y):
    if arr[x][y] != 0 and arr[x][y] == arr[x][y+1] == arr[x][y+2] == arr[x][y+3] == arr[x][y+4]:
        if (y+5 == 19 or arr[x][y] != arr[x][y+5]) and (y-1 == -1 or arr[x][y] != arr[x][y-1]) :
            answer(x, y)


def check_height(x, y):
    if arr[x][y] != 0 and arr[x][y] == arr[x+1][y] == arr[x+2][y] == arr[x+3][y] == arr[x+4][y]:
        if (x+5 == 19 or arr[x][y] != arr[x+5][y]) and (x-1 == -1 or arr[x][y] != arr[x-1][y]) :
            answer(x, y)


def check_right_diag(x, y):
    if arr[x][y] != 0 and arr[x][y] == arr[x+1][y+1] == arr[x+2][y+2] == arr[x+3][y+3] == arr[x+4][y+4]:
        if (x+5 == 19 or y+5 == 19 or arr[x][y] != arr[x+5][y+5]) and (y-1 == -1 or x-1 == -1 or arr[x][y] != arr[x-1][y-1]) :
            answer(x, y)


def check_left_diag(x, y):
    if arr[x][y] != 0 and arr[x][y] == arr[x-1][y+1] == arr[x-2][y+2] == arr[x-3][y+3] == arr[x-4][y+4]:
        if (x-5 == -1 or y+5 == 19 or arr[x][y] != arr[x-5][y+5]) and (y-1 == -1 or x+1 == 19 or arr[x][y] != arr[x+1][y-1]) :
            answer(x, y)


for i in range(15):
    for j in range(15):
        check(i,j)

for i in range(15, 19):
    for j in range(15):
        check_width(i, j)
        check_height(j, i)

print(0)