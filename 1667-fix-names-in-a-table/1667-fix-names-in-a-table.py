import pandas as pd

def fix_names(users: pd.DataFrame) -> pd.DataFrame:
    # Capitalize first letter, lowercase the rest
    users['name'] = users['name'].str.capitalize()
    
    # Sort by user_id ascending
    return users.sort_values(by='user_id')