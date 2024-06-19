@ignore
Feature: 前提ユーザーの削除
  Background:
    // Todo: 呼び元でURLを設定する
    * def port = karate.properties['port']
    * url 'http://localhost:' + port
  Scenario: ユーザーが削除されること
    Given path 'users', userId
    When method delete
    Then status 204
