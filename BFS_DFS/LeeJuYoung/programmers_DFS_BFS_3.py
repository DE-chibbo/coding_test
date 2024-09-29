from collections import deque

def solution(maps):
    # 상, 하, 좌, 우로 이동하기 위한 좌표 변화값
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 맵의 크기
    n = len(maps)
    m = len(maps[0])

    # BFS를 위한 큐
    queue = deque([(0, 0)])  # 시작점 (0, 0)
    
    # BFS 탐색 시작
    while queue:
        x, y = queue.popleft()

        # 현재 위치에서 4방향 탐색
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            # 맵을 벗어나는 경우 무시
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            # 벽이 있는 경우 무시
            if maps[nx][ny] == 0:
                continue

            # 처음 방문하는 칸이라면 거리를 기록하고 큐에 추가
            if maps[nx][ny] == 1:
                maps[nx][ny] = maps[x][y] + 1
                queue.append((nx, ny))

    # 상대 팀 진영에 도착할 수 있으면 거리를 반환, 도착할 수 없으면 -1
    return maps[n-1][m-1] if maps[n-1][m-1] != 1 else -1
