class MovieRentingSystem {
    // movie -> set of (price, shop) for unrented copies
    unordered_map<int, set<pair<int,int>>> unrented;
    // global set of (price, shop, movie) for rented copies
    set<tuple<int,int,int>> rented;
    // (shop, movie) -> price
    map<pair<int,int>, int> price;

public:
    MovieRentingSystem(int n, vector<vector<int>>& entries) {
        for (auto &e : entries) {
            int shop = e[0], movie = e[1], p = e[2];
            price[{shop, movie}] = p;
            unrented[movie].insert({p, shop});
        }
    }

    vector<int> search(int movie) {
        vector<int> res;
        auto it = unrented.find(movie);
        if (it == unrented.end()) return res;
        int cnt = 0;
        for (auto &[p, shop] : it->second) {
            res.push_back(shop);  // Only push shop, not movie
            if (++cnt == 5) break;
        }
        return res;
    }

    void rent(int shop, int movie) {
        int p = price[{shop, movie}];
        unrented[movie].erase({p, shop});
        rented.insert({p, shop, movie});
    }

    void drop(int shop, int movie) {
        int p = price[{shop, movie}];
        rented.erase({p, shop, movie});
        unrented[movie].insert({p, shop});
    }

    vector<vector<int>> report() {
        vector<vector<int>> res;
        int cnt = 0;
        for (auto &[p, shop, movie] : rented) {
            res.push_back({shop, movie});  // Push pair for report
            if (++cnt == 5) break;
        }
        return res;
    }
};