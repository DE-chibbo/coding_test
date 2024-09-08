def solution(s):
    answer = True
    list = []
    for i in s:
        if i =='(' : list.append('(')
        elif i == ')' : 
            if len(list) < 1 : return False
            else : del list[-1]
    if len(list) != 0 : return False
    return True