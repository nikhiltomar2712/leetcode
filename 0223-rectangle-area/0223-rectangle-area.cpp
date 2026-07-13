class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, 
                    int bx1, int by1, int bx2, int by2) {
        // Calculate individual areas
        long long areaA = (long long)(ax2 - ax1) * (ay2 - ay1);
        long long areaB = (long long)(bx2 - bx1) * (by2 - by1);
        
        // Calculate overlap area
        int overlapX = max(0, min(ax2, bx2) - max(ax1, bx1));
        int overlapY = max(0, min(ay2, by2) - max(ay1, by1));
        long long overlapArea = (long long)overlapX * overlapY;
        
        // Total area = areaA + areaB - overlapArea
        return (int)(areaA + areaB - overlapArea);
    }
};