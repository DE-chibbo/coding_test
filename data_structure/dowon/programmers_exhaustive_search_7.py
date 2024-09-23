# 프로그래머스 고득점 Kit - 완전탐색 - 모음사전

def solution(word):
    answer = 0
    for index, value in enumerate(word):
        if index == 0:  # 781
            if value == 'A': answer += 1
            if value == 'E': answer += 782
            if value == 'I': answer += 1563
            if value == 'O': answer += 2344
            if value == 'U': answer += 3125
        if index == 1:  # 156
            if value == 'A': answer += 1
            if value == 'E': answer += 157
            if value == 'I': answer += 313
            if value == 'O': answer += 469
            if value == 'U': answer += 625
        if index == 2:  # 31
            if value == 'A': answer += 1
            if value == 'E': answer += 32
            if value == 'I': answer += 63
            if value == 'O': answer += 94
            if value == 'U': answer += 125
        if index == 3:  # 6
            if value == 'A': answer += 1
            if value == 'E': answer += 7
            if value == 'I': answer += 13
            if value == 'O': answer += 19
            if value == 'U': answer += 25
        if index == 4:  # 1
            if value == 'A': answer += 1
            if value == 'E': answer += 2
            if value == 'I': answer += 3
            if value == 'O': answer += 4
            if value == 'U': answer += 5
    return answer