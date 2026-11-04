import pandas as pd

def invalid_tweets(tweets: pd.DataFrame) -> pd.DataFrame:
    # Filter rows where content length is strictly greater than 15
    invalid = tweets[tweets['content'].str.len() > 15]
    # Return only the tweet_id column
    return invalid[['tweet_id']]