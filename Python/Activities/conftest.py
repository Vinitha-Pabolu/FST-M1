import pytest
 
# Create fixture
@pytest.fixture
def num_list():
 
    # Create list
    int_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    
    # Alternatively
    # int_list = list(range(11))
 
    return int_list
@pytest.fixture
def wallet_amount():
   amount = 0
   return amount
 
