class AuthenticationManager {
    private int ttl;
    private Map<String, Integer> tokens;

    public AuthenticationManager(int timeToLive) {
        this.ttl = timeToLive;
        this.tokens = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        tokens.put(tokenId, currentTime + ttl);
    }

    public void renew(String tokenId, int currentTime) {
        if (tokens.containsKey(tokenId) && tokens.get(tokenId) > currentTime) {
            tokens.put(tokenId, currentTime + ttl);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for (int expiry : tokens.values()) {
            if (expiry > currentTime) count++;
        }
        return count;
    }
}