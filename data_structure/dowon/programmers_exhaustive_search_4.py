# 프로그래머스 고득점 Kit - 완전탐색 - 카펫
def solution(brown, yellow):
    total = brown + yellow
    # 세로 길이 기준 완탐
    for i in range(3, int(total / 2) + 1):
    	# 면적이 자연수로 나눠지고, 그때 노란색 카펫의 갯수가 일치하는 경우 찾음
        if (total % i) == 0 and (i - 2) * (total / i - 2) == yellow:
            break
    return [total / i, i]