class Solution(object):
    def numOfStrings(self, patterns, word):
        count = 0
        for p in patterns:
            # Manually check if p is in word
            found = False
            for i in range(len(word) - len(p) + 1):
                if word[i:i+len(p)] == p:
                    found = True
                    break
            if found:
                count += 1
        return count