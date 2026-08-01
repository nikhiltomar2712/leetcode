int openLock(char** deadends, int deadendsSize, char* target) {
    // visited[10000], index = num representation of the 4-digit lock
    bool visited[10000] = {false};
    
    // Mark deadends as visited
    for (int i = 0; i < deadendsSize; i++) {
        int code = (deadends[i][0]-'0')*1000 + (deadends[i][1]-'0')*100 +
                   (deadends[i][2]-'0')*10  + (deadends[i][3]-'0');
        visited[code] = true;
    }
    
    // If start is deadend
    if (visited[0]) return -1;
    
    // Target as number
    int targetNum = (target[0]-'0')*1000 + (target[1]-'0')*100 +
                    (target[2]-'0')*10  + (target[3]-'0');
    if (targetNum == 0) return 0;
    
    // BFS queue
    int queue[10000];
    int front = 0, rear = 0;
    queue[rear++] = 0;
    visited[0] = true;
    
    int steps = 0;
    
    while (front < rear) {
        int size = rear - front;
        for (int s = 0; s < size; s++) {
            int cur = queue[front++];
            
            // Try turning each of the 4 wheels +1 / -1
            int digits[4] = {cur/1000, (cur/100)%10, (cur/10)%10, cur%10};
            
            for (int i = 0; i < 4; i++) {
                int original = digits[i];
                
                // +1
                digits[i] = (original + 1) % 10;
                int next = digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3];
                if (!visited[next]) {
                    if (next == targetNum) return steps + 1;
                    visited[next] = true;
                    queue[rear++] = next;
                }
                
                // -1
                digits[i] = (original + 9) % 10;   // -1 with wrap
                next = digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3];
                if (!visited[next]) {
                    if (next == targetNum) return steps + 1;
                    visited[next] = true;
                    queue[rear++] = next;
                }
                
                digits[i] = original;  // restore
            }
        }
        steps++;
    }
    
    return -1;
}