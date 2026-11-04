import pandas as pd

def calculate_special_bonus(employees: pd.DataFrame) -> pd.DataFrame:
    # Initialize bonus column to 0
    employees['bonus'] = 0
    
    # Set bonus = salary where employee_id is odd AND name does not start with 'M'
    employees.loc[
        (employees['employee_id'] % 2 != 0) & (~employees['name'].str.startswith('M')),
        'bonus'
    ] = employees['salary']
    
    # Select required columns and sort
    return employees[['employee_id', 'bonus']].sort_values('employee_id')