Feature: Todoのテスト
  Background:
    * url 'http://localhost:9080'
  Scenario: Todoを作成して変更、削除までできること
    Todo dataを作成してその内容を変更後に削除を行う

    * def createdUser = call read('common/create-user.feature')

    * def userId = createdUser.userId
    * def description = 'dummy description'
    Given path 'todos'
    And header Content-Type = 'application/json; charset=utf-8'
    And request {userId : '#(userId)', description: '#(description)'}
    When method post
    Then status 201
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {id: '#uuid', userId: '#(userId)', status: 'TODO', description: '#(description)'}

    * def todoId = response.id
    Given path 'todos', todoId
    And header Content-Type = 'application/json; charset=utf-8'
    And request {status : 'DONE'}
    When method patch
    Then status 200
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {id: '#uuid', userId: '#(userId)', status: 'DONE', description: '#(description)'}

    Given path 'todos', todoId
    When method delete
    Then status 204

    Given path 'users', userId
    When method delete
    Then status 204

    Then call read('common/delete-user.feature') {userId: '#(userId)'}

  Scenario Outline: ステータスを<status>に変更できること
    * def createdUser = call read('common/create-user.feature')

    * def userId = createdUser.userId
    * def description = 'dummy description'
    Given path 'todos'
    And header Content-Type = 'application/json; charset=utf-8'
    And request {userId : '#(userId)', description: '#(description)'}
    When method post
    Then status 201
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {id: '#uuid', userId: '#(userId)', status: 'TODO', description: '#(description)'}

    * def todoId = response.id
    Given path 'todos', todoId
    And header Content-Type = 'application/json; charset=utf-8'
    And request {status : <status>}
    When method patch
    Then status 200
    And match responseHeaders['Content-Type'][0] == "application/json"
    And match response == {id: '#uuid', userId: '#(userId)', status: <status>, description: '#(description)'}

    Given path 'todos', todoId
    When method delete
    Then status 204

    Given path 'users', userId
    When method delete
    Then status 204

    Then call read('common/delete-user.feature') {userId: '#(userId)'}

    Examples:
      |status|
      |'TODO'|
      |'IN_PROGRESS'|
      |'DONE'|