Feature: Userのテスト
  Background:
    * url 'http://localhost:9080'
  Scenario: ユーザーを作成して削除できること
    * def now = function(){ return java.lang.System.currentTimeMillis() }
    * def name = 'Test-' + now()
    Given path 'users'
    And header Content-Type = 'application/json; charset=utf-8'
    And request {name : '#(name)'}
    When method post
    Then status 201
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {id: '#uuid', name: '#(name)'}

    * def userId = response.id
    Given path 'users', userId
    When method delete
    Then status 204