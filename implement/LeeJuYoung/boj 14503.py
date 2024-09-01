# 방향 정의: 북, 동, 남, 서
DIRECTION = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def turn_left(d):
    return (d + 3) % 4  # 반시계 방향 회전

def robot_cleaning(N, M, r, c, d, room):
    count = 0
    while True:
        # 현재 위치 청소
        if room[r][c] == 0:
            room[r][c] = 2  # 청소 완료 표시
            count += 1
        
        cleaned = False
        for _ in range(4):
            d = turn_left(d)  # 왼쪽으로 회전
            nr, nc = r + DIRECTION[d][0], c + DIRECTION[d][1]
            if room[nr][nc] == 0:  # 청소되지 않은 빈 칸이 있으면 전진
                r, c = nr, nc
                cleaned = True
                break
        
        if not cleaned:  # 4방향 모두 청소가 되어 있는 경우
            nr, nc = r - DIRECTION[d][0], c - DIRECTION[d][1]
            if room[nr][nc] == 1:  # 후진할 곳이 벽이면 종료
                break
            else:
                r, c = nr, nc  # 후진
            
    return count

# 입력 처리
N, M = map(int, input().split())
r, c, d = map(int, input().split())

# 방의 외곽에 벽을 추가하여 크기 N+2, M+2의 배열로 변환
room = [[1] * (M + 2) for _ in range(N + 2)]

# 입력받은 방 상태를 배열 가운데에 삽입
for i in range(1, N + 1):
    row = list(map(int, input().split()))
    for j in range(1, M + 1):
        room[i][j] = row[j - 1]

# 로봇의 초기 위치도 1씩 증가시킴
r += 1
c += 1

# 청소한 칸의 수 출력
print(robot_cleaning(N, M, r, c, d, room))
