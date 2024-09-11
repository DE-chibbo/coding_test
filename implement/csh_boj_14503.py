'''
boj test : 테스트 미진행

time complexity : O(n)
- n : 전체 칸 수
'''

def csh_boj_14503(room_size: list[int], robot_state: list[int],  room: list[list[int]]):
    # input = sys.stdin.read().splitlines()
    # N = int(input[0])
    # words = input[1:N+1]
    # K = int(input[N+1])

    '''
    현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
    현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
        바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
        바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
    현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
        반시계 방향으로 $90^\circ$ 회전한다.
        바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
        1번으로 돌아간다.
    '''

    result = 0

    move_vector = [
        [-1, 0],
        [0, 1],
        [1, 0],
        [0, -1]
    ]

    while True:
        if room[robot_state[0]][robot_state[1]] == 0:
            result += 1
        arround_state = [
            room[robot_state[0] - 1][robot_state[1]] if 0 <= robot_state[0] - 1 else True,
            room[robot_state[0]][robot_state[1] + 1] if room_size[1] > robot_state[1] + 1 else True,
            room[robot_state[0] + 1][robot_state[1]] if room_size[0] > robot_state[0] + 1 else True,
            room[robot_state[0]][robot_state[1] - 1] if 0 <= robot_state[1] - 1 else True,
        ]
        if sum(arround_state) == 4:
            if type(arround_state[(2 + robot_state[2]) % 4]) is bool:
                break
            robot_state[0] -= move_vector[robot_state[2]][0]
            robot_state[1] -= move_vector[robot_state[2]][1]
        elif sum(arround_state) < 4:
            robot_state[2] -= 1
            if arround_state[robot_state[2]] == 0:
                robot_state[0] += move_vector[robot_state[2]][0]
                robot_state[1] += move_vector[robot_state[2]][1]

   
    print(result)
    return result