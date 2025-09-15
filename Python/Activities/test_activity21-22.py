import pytest
# Additon test
@pytest.mark.activity
def test_addition():	
	num1 = 10
	num2 = 15  
	# Add them
	sum = num1 + num2
	# Assertion
	assert sum == 25
# Subtraction test
@pytest.mark.activity
def test_subtraction():  
	num1 = 50
	num2 = 35    
	# Subtract 
	diff = num1 - num2
	# Assertion
	assert diff == 15
# Multiplication test
@pytest.mark.activity
def test_multiplication():
	num1 = 5
	num2 = 20
	# Multiply 
	prod = num1 * num2
	# Assertion
	assert prod == 100
# Division test
@pytest.mark.activity
def test_division():  
	num1 = 100
	num2 = 5  
	# Divide them
	quot = num1 / num2
	# Assertion
	assert quot == 20