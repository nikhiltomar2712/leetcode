#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <list>
#include <queue>
#include <algorithm>

class Twitter {
private:
    struct Tweet {
        int id;
        int time;
        Tweet(int id, int time) : id(id), time(time) {}
    };
    
    struct User {
        std::unordered_set<int> following;
        std::list<Tweet> tweets;
        
        User() {
            following.insert(0); // Placeholder
        }
    };
    
    std::unordered_map<int, User> users;
    int timestamp;
    
    User& getUser(int userId) {
        if (users.find(userId) == users.end()) {
            users[userId] = User();
            users[userId].following.insert(userId);
        }
        return users[userId];
    }
    
public:
    Twitter() : timestamp(0) {}
    
    void postTweet(int userId, int tweetId) {
        User& user = getUser(userId);
        user.tweets.push_front(Tweet(tweetId, timestamp++));
    }
    
    std::vector<int> getNewsFeed(int userId) {
        std::vector<int> result;
        
        if (users.find(userId) == users.end()) {
            return result;
        }
        
        User& user = getUser(userId);
        
        // Min-heap to keep only the 10 most recent tweets
        auto cmp = [](const std::pair<int, int>& a, const std::pair<int, int>& b) {
            return a.first > b.first;
        };
        std::priority_queue<std::pair<int, int>, 
                           std::vector<std::pair<int, int>>, 
                           decltype(cmp)> minHeap(cmp);
        
        // Helper lambda to add tweets from a user's list
        auto addTweets = [&](const std::list<Tweet>& tweetList) {
            for (const Tweet& tweet : tweetList) {
                minHeap.push({tweet.time, tweet.id});
                if (minHeap.size() > 10) {
                    minHeap.pop();
                }
            }
        };
        
        // Add user's own tweets
        addTweets(user.tweets);
        
        // Add tweets from followed users (EXCLUDING the user themselves)
        for (int followeeId : user.following) {
            // Skip the user themselves to avoid duplicates
            if (followeeId == userId) continue;
            
            if (users.find(followeeId) != users.end()) {
                addTweets(users[followeeId].tweets);
            }
        }
        
        // Extract tweets from heap (most recent first)
        while (!minHeap.empty()) {
            result.push_back(minHeap.top().second);
            minHeap.pop();
        }
        
        // Reverse to get most recent first
        std::reverse(result.begin(), result.end());
        return result;
    }
    
    void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        
        User& follower = getUser(followerId);
        getUser(followeeId);
        
        follower.following.insert(followeeId);
    }
    
    void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        
        if (users.find(followerId) == users.end()) return;
        
        User& follower = users[followerId];
        follower.following.erase(followeeId);
    }
};