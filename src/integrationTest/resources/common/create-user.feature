@ignore
Feature: 前提ユーザーの作成
  Background:
    // Todo: 呼び元でURLを設定する
    * def port = karate.properties['port']
    * url 'http://localhost:' + port
  Scenario: ユーザーが作成されること
    * def now = function(){ return java.lang.System.currentTimeMillis() }
    * def name = 'Test-' + now()
    Given path 'users'
    And header Content-Type = 'application/json; charset=utf-8'
    And request {name : '#(name)'}
    When method post
    Then status 201
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {id: '#uuid', name: '#(name)'}
    And def userId = response.id
