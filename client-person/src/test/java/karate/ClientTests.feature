Feature: Client API Tests

	Scenario: Register customer without account
		Given url 'http://localhost:8080/clients/register'
		And request
		"""
		{
		"cardId": "1357987437",
		"name": "Jose Lema",
		"email": "jose@example.com",
		"gender": "Male",
		"age": 30,
		"address": "Main St",
		"phone": "098254785",
		"password": "1234",
		"state": "Active"
		}
		"""
		When method post
		Then status 201
		And print response