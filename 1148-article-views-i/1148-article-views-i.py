import pandas as pd

def article_views(views: pd.DataFrame) -> pd.DataFrame:
    # Filter rows where author viewed their own article
    df = views[views['author_id'] == views['viewer_id']]
    
    # Remove duplicate authors and sort by author_id
    df = df.drop_duplicates(subset=['author_id'])
    df = df.sort_values(by='author_id')
    
    # Rename column and return only the id column
    return df[['author_id']].rename(columns={'author_id': 'id'})