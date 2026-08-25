class PeekingIterator(object):
    def __init__(self, iterator):
        """
        Initialize your data structure here.
        :type iterator: Iterator
        """
        self.iterator = iterator
        self.cached = None
        # If the iterator has a next element, cache it
        if self.iterator.hasNext():
            self.cached = self.iterator.next()

    def peek(self):
        """
        Returns the next element in the iteration without advancing the iterator.
        :rtype: int
        """
        return self.cached

    def next(self):
        """
        :rtype: int
        """
        result = self.cached
        # Update cache to next element or None
        if self.iterator.hasNext():
            self.cached = self.iterator.next()
        else:
            self.cached = None
        return result

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.cached is not None


# Your PeekingIterator object will be instantiated and called as such:
# iter = PeekingIterator(Iterator(nums))
# while iter.hasNext():
#     val = iter.peek()   # Get next element without advancing the iterator.
#     iter.next()         # Should return the same value as peek().