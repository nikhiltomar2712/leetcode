class Solution {
private:
    unordered_map<string, string> codeToUrl;
    unordered_map<string, string> urlToCode;
    const string chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const int CODE_LENGTH = 6;
    
    string generateRandomCode() {
        string code;
        for (int i = 0; i < CODE_LENGTH; i++) {
            code += chars[rand() % 62];
        }
        return code;
    }
    
public:
    string encode(string longUrl) {
        if (urlToCode.count(longUrl)) {
            return "http://tinyurl.com/" + urlToCode[longUrl];
        }
        
        string code;
        do {
            code = generateRandomCode();
        } while (codeToUrl.count(code));  // Handle collision
        
        codeToUrl[code] = longUrl;
        urlToCode[longUrl] = code;
        
        return "http://tinyurl.com/" + code;
    }

    string decode(string shortUrl) {
        string code = shortUrl.substr(shortUrl.find_last_of('/') + 1);
        return codeToUrl[code];
    }
};