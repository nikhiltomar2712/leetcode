import pandas as pd

def find_customers(customers: pd.DataFrame, orders: pd.DataFrame) -> pd.DataFrame:
    # Filter customers whose id is NOT in the orders' customerId column
    df = customers[~customers['id'].isin(orders['customerId'])]
    
    # Select only the 'name' column and rename it to 'Customers'
    df = df[['name']].rename(columns={'name': 'Customers'})
    
    return df