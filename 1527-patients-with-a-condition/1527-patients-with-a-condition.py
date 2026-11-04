import pandas as pd

def find_patients(patients: pd.DataFrame) -> pd.DataFrame:
    # Match DIAB1 at start of string OR after a space
    mask = patients["conditions"].str.contains(r'(^| )DIAB1', regex=True, na=False)
    return patients[mask]