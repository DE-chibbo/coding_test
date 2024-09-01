class Solution:
    def alertNames(self, keyname: list[str], keytime: list[str]) -> list[str]:
        # 각 시간을 분 단위로 변환하여 저장할 리스트
        keytime_mins = []
        
        # keytime 리스트에 있는 시간을 'HH:MM' 형식에서 분 단위로 변환
        for i in keytime:
            h, m = i.split(':')  # 시간을 ':'로 분리하여 시와 분을 얻음
            keytime_mins.append(int(h) * 60 + int(m))  # 시를 분으로 변환하고 분과 합산하여 저장

        # 각 사람의 이름을 키로 하고, 해당하는 시간(분 단위) 리스트를 값으로 하는 딕셔너리
        d = {}
        
        # keyname 리스트의 각 이름에 대해 처리
        for i in range(len(keyname)):
            if keyname[i] not in d.keys():  # 만약 이름이 딕셔너리에 없으면
                d[keyname[i]] = [keytime_mins[i]]  # 새로운 키로 추가하고 해당 시간(분)을 리스트로 저장
            else:
                d[keyname[i]].append(keytime_mins[i])  # 이미 있으면 시간(분)을 기존 리스트에 추가

        # 경고 알림을 받을 이름들을 저장할 리스트
        res = []
        
        # 딕셔너리의 각 이름(key)과 그에 해당하는 시간 리스트(value)에 대해 처리
        for i in d.keys():
            l = d[i]  # 이름에 해당하는 시간 리스트
            l.sort()  # 시간 리스트를 오름차순으로 정렬
            
            if len(l) < 3:  # 만약 시간 리스트에 시간이 3개 미만이면
                continue  # 경고 조건을 만족할 수 없으므로 다음 이름으로 넘어감

            # 정렬된 시간 리스트에서 연속된 3개의 시간 중 첫 번째와 세 번째의 차이가 60분 이하인 경우 확인
            for j in range(2, len(l)):
                if l[j] - l[j-2] <= 60:  # 세 번째 시간과 첫 번째 시간의 차이가 60분 이하인지 확인
                    res.append(i)  # 조건을 만족하면 해당 이름을 결과 리스트에 추가
                    break  # 이 이름에 대해 더 이상 검사할 필요가 없으므로 종료

        # 최종적으로 이름들을 알파벳 순으로 정렬하여 반환
        res.sort()
        return res
