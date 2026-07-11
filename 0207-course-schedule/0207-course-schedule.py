class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        # Build adjacency list and in-degree array
        graph = [[] for _ in range(numCourses)]
        in_degree = [0] * numCourses
        
        for a, b in prerequisites:
            graph[b].append(a)  # b -> a
            in_degree[a] += 1
        
        # Initialize queue with all courses having in-degree 0
        queue = deque([i for i in range(numCourses) if in_degree[i] == 0])
        
        count = 0  # number of courses taken so far
        
        while queue:
            course = queue.popleft()
            count += 1
            
            for neighbor in graph[course]:
                in_degree[neighbor] -= 1
                if in_degree[neighbor] == 0:
                    queue.append(neighbor)
        
        # If we took all courses, no cycle was found
        return count == numCourses
        