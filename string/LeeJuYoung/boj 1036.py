N = int(input())
nums = [input() for _ in range(N)]
K = int(input())

num36 = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
num36_diff = []
num_sum = 0
num_sep = []

# 문자열을 수정하기 위한 전처리 & 현재값들의 합 구함
for num in nums:
    temp = []
    temp.extend(num)
    num_sep.append(temp)
    num_sum += int(num, 36)

# 각 문자를 Z로 변경했을 때 합의 변화량을 구함
for k in num36:
    num_sum_diff = 0
    for num in num_sep:
        temp_str = ''
        for n in num:
            if n == k:
                temp_str += 'Z'
            else:
                temp_str += n
        num_sum_diff += int(temp_str, 36)
    num36_diff.append(num_sum_diff - num_sum)

# 최대값 == 기존값 + 최대 변화량
max_sum = num_sum + sum(sorted(num36_diff, reverse=True)[:K])

# 36진수로 변환
result = ''
while max_sum:
    result = num36[max_sum % 36] + result
    max_sum //= 36

print(result if result else '0')
