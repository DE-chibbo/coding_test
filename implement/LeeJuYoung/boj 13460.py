from collections import deque

# 방향 정의: 상, 하, 좌, 우
DIRECTIONS = [(-1, 0), (1, 0), (0, -1), (0, 1)]

def move(x, y, dx, dy, board):
    move_count = 0
    # 벽(#)이나 구멍(O)을 만날 때까지 계속 이동
    while board[x + dx][y + dy] != '#' and board[x][y] != 'O':
        x += dx
        y += dy
        move_count += 1
    return x, y, move_count

def bfs(board, red_start, blue_start):
    n, m = len(board), len(board[0])
    queue = deque([(red_start[0], red_start[1], blue_start[0], blue_start[1], 0)])
    visited = set([(red_start[0], red_start[1], blue_start[0], blue_start[1])])

    while queue:
        rx, ry, bx, by, depth = queue.popleft()

        if depth >= 10:  # 10번 이하로 움직여야 함
            return -1
        
        for dx, dy in DIRECTIONS:
            nrx, nry, r_move = move(rx, ry, dx, dy, board)
            nbx, nby, b_move = move(bx, by, dx, dy, board)
            
            # 파란 구슬이 구멍에 빠졌다면 무시
            if board[nbx][nby] == 'O':
                continue
            # 빨간 구슬이 구멍에 빠졌다면 성공
            if board[nrx][nry] == 'O':
                return depth + 1
            
            # 두 구슬이 같은 위치에 있을 수 없으므로, 많이 움직인 구슬을 한 칸 뒤로
            if nrx == nbx and nry == nby:
                if r_move > b_move:
                    nrx -= dx
                    nry -= dy
                else:
                    nbx -= dx
                    nby -= dy
            
            if (nrx, nry, nbx, nby) not in visited:
                visited.add((nrx, nry, nbx, nby))
                queue.append((nrx, nry, nbx, nby, depth + 1))

    return -1  # 10번 이하로 움직여서 빨간 구슬을 빼낼 수 없는 경우

# 입력 처리
N, M = map(int, input().split())
board = []
red_start = blue_start = None

for i in range(N):
    row = list(input().strip())
    board.append(row)
    for j in range(M):
        if row[j] == 'R':
            red_start = (i, j)
        elif row[j] == 'B':
            blue_start = (i, j)

# 결과 출력
print(bfs(board, red_start, blue_start))
