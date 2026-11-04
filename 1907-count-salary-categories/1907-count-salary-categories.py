import pandas as pd

def count_salary_categories(accounts: pd.DataFrame) -> pd.DataFrame:
    low = accounts[accounts['income'] < 20000].shape[0]
    
    average = accounts[
        (accounts['income'] >= 20000) & (accounts['income'] <= 50000)
    ].shape[0]
    
    high = accounts[accounts['income'] > 50000].shape[0]
    
    return pd.DataFrame({
        'category': ['Low Salary', 'Average Salary', 'High Salary'],
        'accounts_count': [low, average, high]
    })