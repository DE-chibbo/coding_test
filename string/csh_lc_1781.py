'''
leetcode test
- case 1 : passed
- case 2 : failed

time complexity : O(2n)
'''

class Solution:
    def beautySum(self, s: str) -> int:
        mapped_char_count = {}
        mapped_minmax_char = {
            'max': set(),
            'min': set()
        }
        sum_beauty = 0

        for i, c in enumerate(s):
            mapped_char_count[c] = mapped_char_count.get(c, 0)
            mapped_char_count[c] += 1

            current_max_char = next(iter(mapped_minmax_char['max']), c)
            current_min_char = next(iter(mapped_minmax_char['min']), c)

            if mapped_char_count[c] == mapped_char_count[current_max_char]:
                mapped_minmax_char['max'].add(c)
            elif mapped_char_count[c] > mapped_char_count[current_max_char]:
                mapped_minmax_char['max'] = set({c})
            elif mapped_char_count[c] == mapped_char_count[current_min_char]:
                mapped_minmax_char['min'].add(c)
            elif mapped_char_count[c] < mapped_char_count[current_min_char]:
                mapped_minmax_char['min'] = set({c})

            if i >= 2:
                beauty = mapped_char_count[current_max_char] - mapped_char_count[current_min_char]
                sum_beauty += beauty

        for i, c in enumerate(s):
            mapped_char_count[c] -= 1

            current_min_char = next(iter(mapped_minmax_char['min']), c)

            if mapped_char_count[c] == 0:
                del mapped_char_count[c]
                mapped_minmax_char['min'].discard(c)
            elif mapped_char_count[c] == mapped_char_count[current_min_char]:
                mapped_minmax_char['min'].add(c)
            elif mapped_char_count[c] < mapped_char_count[current_min_char]:
                mapped_minmax_char['min'] = set({c})

            mapped_minmax_char['max'].discard(c)
            if len(mapped_minmax_char['max']) == 0:
                mapped_minmax_char['max'].add(c)

            if i < len(s) - 3:
                current_max_char = next(iter(mapped_minmax_char['max']), c)
                current_min_char = next(iter(mapped_minmax_char['min']), c)

                beauty = mapped_char_count[current_max_char] - mapped_char_count[current_min_char]
                sum_beauty += beauty

        return sum_beauty