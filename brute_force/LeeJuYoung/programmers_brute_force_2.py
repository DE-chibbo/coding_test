def solution(answers):
    # 수포자들의 찍기 패턴 정의
    person1 = [1, 2, 3, 4, 5]
    person2 = [2, 1, 2, 3, 2, 4, 2, 5]
    person3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    # 세 수포자의 점수를 저장할 리스트
    scores = [0, 0, 0]
    
    # 문제 정답과 수포자들의 찍기 패턴 비교
    for i, answer in enumerate(answers):
        if answer == person1[i % len(person1)]:
            scores[0] += 1
        if answer == person2[i % len(person2)]:
            scores[1] += 1
        if answer == person3[i % len(person3)]:
            scores[2] += 1
    
    # 가장 높은 점수 찾기
    max_score = max(scores)
    
    # 가장 높은 점수를 받은 수포자 번호 반환 (1번 수포자부터 시작하므로 index에 +1)
    return [i + 1 for i, score in enumerate(scores) if score == max_score]