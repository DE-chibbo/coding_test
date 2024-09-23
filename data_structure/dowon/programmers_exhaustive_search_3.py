# 프로그래머스 고득점 Kit - 완전탐색 - 소수 찾기

from itertools import permutations

def isprime(n):
    # 주어진 n이 소수인지 확인하는 함수
    if n < 2:
        return False  # 2보다 작은 수는 소수가 아님
    for i in range(2, n):  # 2부터 n-1까지 검사
        if n % i == 0:  # 나누어 떨어지면 소수가 아님
            return False
    return True  # 소수일 경우 True 반환

def solution(numbers):
    answer = 0  # 최종 소수의 개수를 저장할 변수
    answers = []  # 소수를 저장할 리스트
    per = []  # 순열을 저장할 변수
    
    # numbers의 길이에 따라 1부터 length까지 반복
    for i in range(1, len(numbers) + 1):
        # numbers에서 i개의 숫자로 이루어진 모든 순열 생성
        per = list(map(''.join, permutations(numbers, i)))
        for p in list(set(per)):  # 중복된 순열 제거
            if isprime(int(p)):  # 생성된 순열이 소수인지 검사
                answers.append(int(p))  # 소수일 경우 리스트에 추가

    answer = len(set(answers))  # 중복된 소수를 제거하고 개수 세기
    return answer  # 소수의 개수 반환