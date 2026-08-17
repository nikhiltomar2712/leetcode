class RLEIterator {
    // The encoded array
    private int[] encoding;
    // Current index in the encoding array (points to a count)
    private int index;
    // How many of the current count have been used
    private int used;
    
    public RLEIterator(int[] encoded) {
        this.encoding = encoded;
        this.index = 0;
        this.used = 0;
    }
    
    public int next(int n) {
        // Continue until we've exhausted n elements or reached the end
        while (index < encoding.length) {
            // Current count available at this position
            int available = encoding[index] - used;
            
            // If we need more than available, skip this entire segment
            if (n > available) {
                n -= available;          // Reduce n by the number of elements we're skipping
                used = 0;                // Reset used for the next segment
                index += 2;              // Move to the next count-value pair
            } 
            // If we need exactly the available amount or less
            else {
                used += n;               // Increase the used count
                // The last element exhausted is the current value
                int result = encoding[index + 1];
                
                // If we've used exactly all of this segment, move to the next
                if (used == encoding[index]) {
                    used = 0;
                    index += 2;
                }
                
                return result;
            }
        }
        
        // No more elements left
        return -1;
    }
}