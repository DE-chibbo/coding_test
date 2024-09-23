# 프로그래머스 고득점 Kit - 완전탐색 - 최소 직사각형

def solution(sizes):
    w, h = 0, 0
    for i in sizes:
    	# i[0]이 항상 크거나 같도록 정렬
        if i[0] < i[1]:
            i[0], i[1] = i[1], i[0]  
        if w < i[0]: w = i[0]  # 최대 너비
        if h < i[1]: h = i[1]  # 최대 높이
    answer = w * h  # 지갑의 최소 크기
    return answer