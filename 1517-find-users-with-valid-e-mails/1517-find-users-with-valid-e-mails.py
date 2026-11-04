import pandas as pd

def valid_emails(users: pd.DataFrame) -> pd.DataFrame:
    # Regex: starts with letter, then allowed chars, ends with @leetcode.com
    pattern = r"^[A-Za-z][A-Za-z0-9_.-]*@leetcode\.com$"
    mask = users["mail"].str.match(pattern, na=False)
    return users.loc[mask, ["user_id", "name", "mail"]]