class Solution {
private:
    double radius;
    double x_center;
    double y_center;
    random_device rd;
    mt19937 gen;
    uniform_real_distribution<double> dist;
    
public:
    Solution(double radius, double x_center, double y_center) 
        : radius(radius), x_center(x_center), y_center(y_center),
          gen(rd()), dist(0.0, 1.0) {}
    
    vector<double> randPoint() {
        // Generate random angle between 0 and 2*PI
        double theta = 2 * M_PI * dist(gen);
        
        // Generate random radius using inverse transform sampling
        // PDF: f(r) = 2r/R² for uniform distribution in circle
        double r = radius * sqrt(dist(gen));
        
        // Convert polar coordinates to Cartesian
        double x = x_center + r * cos(theta);
        double y = y_center + r * sin(theta);
        
        return {x, y};
    }
};