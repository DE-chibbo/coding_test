from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 좌표계를 2배 확장하여 정밀하게 처리
    grid = [[-1] * 102 for _ in range(102)]  # -1로 초기화하여 외부 영역 표시
    
    for rec in rectangle:
        x1, y1, x2, y2 = map(lambda x: x * 2, rec)
        # 내부 영역 표시
        for x in range(x1 + 1, x2):
            for y in range(y1 + 1, y2):
                grid[x][y] = 0  # 내부 영역은 0으로 표시

    for rec in rectangle:
        x1, y1, x2, y2 = map(lambda x: x * 2, rec)
        # 경계선 표시
        for x in range(x1, x2 + 1):
            if grid[x][y1] != 0:
                grid[x][y1] = 1  # 아래 경계선
            if grid[x][y2] != 0:
                grid[x][y2] = 1  # 위 경계선
        for y in range(y1, y2 + 1):
            if grid[x1][y] != 0:
                grid[x1][y] = 1  # 왼쪽 경계선
            if grid[x2][y] != 0:
                grid[x2][y] = 1  # 오른쪽 경계선

    # BFS 탐색
    start_x, start_y = characterX * 2, characterY * 2
    end_x, end_y = itemX * 2, itemY * 2

    visited = [[False] * 102 for _ in range(102)]
    queue = deque()
    queue.append((start_x, start_y, 0))
    visited[start_x][start_y] = True

    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    while queue:
        x, y, dist = queue.popleft()
        if x == end_x and y == end_y:
            return dist // 2  # 좌표 확장에 따른 실제 거리 반환
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            # 이동 가능하고, 경계선이며, 방문하지 않은 경우
            if 0 <= nx < 102 and 0 <= ny < 102 and not visited[nx][ny]:
                if grid[nx][ny] == 1:
                    visited[nx][ny] = True
                    queue.append((nx, ny, dist + 1))
    return -1
