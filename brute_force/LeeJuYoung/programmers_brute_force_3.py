from itertools import permutations

# 소수 판별 함수
def is_prime(n):
    # 소수는 2 이상인 숫자여야 하므로 2 미만이면 False 반환
    if n < 2:
        return False
    
    # 2부터 n의 제곱근까지 숫자로 나누어 나누어 떨어지는지 확인 (소수 판별법)
    # 소수는 1과 자기 자신 외에 다른 약수를 가지지 않기 때문에 그 여부를 확인하는 과정
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:  # 나누어 떨어지면 소수가 아님
            return False
    return True  # 나누어 떨어지지 않으면 소수임

def solution(numbers):
    number_set = set()  # 가능한 모든 숫자를 저장할 집합 (중복을 제거하기 위해 set 사용)
    
    # 1자리부터 len(numbers) 자리까지의 모든 순열을 생성
    # 예를 들어 "17"이 주어지면 1자리 숫자는 1, 7이고, 2자리 숫자는 17, 71
    for i in range(1, len(numbers) + 1):
        # permutations(numbers, i)는 numbers에서 i자리로 만들 수 있는 모든 순열을 반환
        for perm in permutations(numbers, i):
            # perm은 ('1', '7')과 같은 튜플이므로 ''.join()을 사용하여 문자열로 합치고, 이를 정수로 변환
            num = int(''.join(perm))
            # 변환된 숫자를 집합에 추가 (중복된 숫자는 자동으로 제거됨)
            number_set.add(num)
    
    # 집합에 저장된 숫자들 중에서 소수인 숫자의 개수를 셈
    # is_prime 함수로 소수를 확인하고, 그 개수를 sum을 통해 계산
    prime_count = sum(1 for num in number_set if is_prime(num))
    
    return prime_count  # 소수의 개수를 반환
