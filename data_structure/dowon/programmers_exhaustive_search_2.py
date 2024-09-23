# 프로그래머스 고득점 Kit - 완전탐색 - 모의고사

def solution(answers):
    # 수포자의 패턴
    a = [1, 2, 3, 4, 5]
    b = [2, 1, 2, 3, 2, 4, 2, 5]
    c = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]

    # 수포자가 맞춘 문제 수를 저장할 리스트
    scores = [0, 0, 0]  # a, b, c의 점수

    # 답안 확인
    for i in range(len(answers)):
        if answers[i] == a[i % len(a)]:
            scores[0] += 1
        if answers[i] == b[i % len(b)]:
            scores[1] += 1
        if answers[i] == c[i % len(c)]:
            scores[2] += 1

    # 최대 점수 찾기
    max_score = max(scores)

    # 최고 점수를 받은 수포자 찾기
    answer = [i + 1 for i in range(3) if scores[i] == max_score]

    return answer