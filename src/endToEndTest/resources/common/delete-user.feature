@ignore
Feature: 前提ユーザーの削除
  Background:
    * url 'http://localhost:9080'
  Scenario: ユーザーが削除されること
    Given path 'users', userId
    When method delete
    Then status 204
