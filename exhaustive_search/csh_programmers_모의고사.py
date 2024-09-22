'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

people_answers = {
    '1' : [1, 2, 3, 4, 5],
    '2' : [2, 1, 2, 3, 2, 4, 2, 5],
    '3' : [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
}

def csh_mock_exam(answers):
    result = []
    
    # 수포자들 점수 초기화 : [0, 0, 0]
    points = [0 for _ in range(len(people_answers))]
    
    # 정답 채점
    for i, answer in enumerate(answers): # i 번째 정답
        for p, person_answer in enumerate(people_answers.values()): # 각 사람의 i 번째 답
            re_idx = i % len(person_answer) # 반복 인덱스 계산
            if person_answer[re_idx] == answer: # 맞췄을 때
                points[p] += 1
    
    # 최대 점수 수포자 찾기
    max_point = max(points)
    for p, point in enumerate(points):
        if point == max_point:
            result.append(p + 1)
        
    return sorted(result)
    