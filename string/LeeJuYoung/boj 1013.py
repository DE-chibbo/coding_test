"""
def star_vega(num: str) -> str:
  num = simplify(num)
  i = 0
  while i < len(num):
    if i+2 <= len(num) and num[i:i+2] == ['0', '1']:
      del num[i:i+2]
      continue
    elif i+4 <= len(num) and num[i:i+4] == ['1', '0', '0', '1']:
      del num[i:i+4]
      continue
    i += 1
  if len(num) == 0:
    print('YES')
  else:
    print('NO')

     
    
def simplify(num: str) -> list:
    num = list(num)
    i = 2
    while i < len(num):
      if num[i] == '0' and num[i-1] == '0' and num[i-2] == '0':
        num.pop(i)
        continue
      elif num[i] == '1' and num[i-1] == '1' and num[i-2] == '1':
        num.pop(i)
        continue
      i += 1
    
    i = 1
    while i < len(num):
      if i+2 < len(num) and num[i-1:i+3] == ['1', '1', '0', '1']:
        num.pop(i)
        continue
      i += 1
    if len(num) > 1 and num[-1] == '1' and num[-2] == '1':
      num.pop(-1)
    return num


n = int(input())
for i in range(n):
  num = input()
  star_vega(num)
"""

    

import re
t = int(input())
for i in range(t):
    a = input()
    p = re.compile('(100+1+|01)+')
    if p.fullmatch(a):
        print("YES")
    else:
        print("NO")