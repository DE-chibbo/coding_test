'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

def csh_vowel_dictionary(word):
    alphabet = ['A', 'E', 'I', 'O', 'U']
    
    answer = 0
    while word != 'A':
        if word[-1] == alphabet[0]:
            word = word[:-1]
        elif len(word) != len(alphabet):
            word = word[:len(word) - 1] + alphabet[(alphabet.index(word[-1]) - 1)]
            while len(word) != len(alphabet):
                word = word + alphabet[-1]
        elif len(word) == len(alphabet):
            word = word[:len(word) - 1] + alphabet[(alphabet.index(word[-1]) - 1)]
        answer += 1
    
    return answer + 1