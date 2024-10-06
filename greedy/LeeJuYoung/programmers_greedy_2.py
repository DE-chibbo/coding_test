def solution(name):
    def min_move(char):
        return min(ord(char) - ord('A'), ord('Z') - ord(char) + 1)

    n = len(name)
    answer = 0

    for char in name:
        answer += min_move(char)

    move = n - 1

    for i in range(n):
        next_idx = i + 1

        while next_idx < n and name[next_idx] == 'A':
            next_idx += 1
        distance_right = i + i + n - next_idx
        distance_left = (n - next_idx) + i + min(i, n - next_idx)

        move = min(move, distance_right, distance_left)

    return answer + move