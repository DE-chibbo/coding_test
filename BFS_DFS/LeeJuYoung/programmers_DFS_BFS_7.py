def solution(game_board, table):
    from collections import deque

    n = len(game_board)

    # 상하좌우 방향
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 보드에서 빈 공간(0으로 연결된 영역) 추출
    def get_spaces(board, target):
        visited = [[False]*n for _ in range(n)]
        spaces = []
        for i in range(n):
            for j in range(n):
                if board[i][j] == target and not visited[i][j]:
                    queue = deque()
                    queue.append((i, j))
                    visited[i][j] = True
                    space = []
                    min_x, min_y = i, j
                    max_x, max_y = i, j
                    while queue:
                        x, y = queue.popleft()
                        space.append((x, y))
                        min_x = min(min_x, x)
                        min_y = min(min_y, y)
                        max_x = max(max_x, x)
                        max_y = max(max_y, y)
                        for k in range(4):
                            nx = x + dx[k]
                            ny = y + dy[k]
                            if 0 <= nx < n and 0 <= ny < n:
                                if board[nx][ny] == target and not visited[nx][ny]:
                                    visited[nx][ny] = True
                                    queue.append((nx, ny))
                    # 모양을 상대 좌표로 저장
                    shape = []
                    for x, y in space:
                        shape.append((x - min_x, y - min_y))
                    spaces.append(shape)
        return spaces

    # 퍼즐 조각 회전 (0°, 90°, 180°, 270°)
    def rotate(shape):
        rotations = []
        for _ in range(4):
            max_x = max(s[0] for s in shape)
            max_y = max(s[1] for s in shape)
            new_shape = [(y, max_x - x) for x, y in shape]
            min_x = min(s[0] for s in new_shape)
            min_y = min(s[1] for s in new_shape)
            normalized_shape = sorted([(x - min_x, y - min_y) for x, y in new_shape])
            rotations.append(normalized_shape)
            shape = new_shape
        return rotations

    # 게임 보드의 빈 공간과 테이블의 퍼즐 조각 추출
    empty_spaces = get_spaces(game_board, 0)
    puzzle_pieces = get_spaces(table, 1)

    # 퍼즐 조각의 회전 형태 미리 계산
    pieces_rotations = []
    for piece in puzzle_pieces:
        rotations = rotate(piece)
        pieces_rotations.append(rotations)

    used = [False] * len(pieces_rotations)
    result = 0

    # 빈 공간에 퍼즐 조각을 맞춰봄
    for space in empty_spaces:
        matched = False
        space_sorted = sorted(space)
        for i, rotations in enumerate(pieces_rotations):
            if used[i]:
                continue
            for rotated_piece in rotations:
                if space_sorted == rotated_piece:
                    used[i] = True
                    result += len(space)
                    matched = True
                    break
            if matched:
                break

    return result
