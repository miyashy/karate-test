Feature: Karateの検証
  
  Background: 
    * url 'http://localhost:8080'
    
  Scenario: サンプルインスタンスを作成して作成したデータを参照する
    Given path 'sample'
    And request '{}'
    When method post
    Then status 201
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {"id":"hogehoge","body":"body"}

    * def id = response.id
    Given path 'sample', id
    When method get
    Then status 200
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {"id":"hogehoge","body":"body"}