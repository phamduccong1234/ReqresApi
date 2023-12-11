#Author: Pham Cong
@Breeds
Feature: Breeds Api Test

  @MainCase
  Scenario Outline: Check response when send request successfully
    Given I have url and Method of breeds api
      | URL                                |
      | https://api.thecatapi.com/v1/breeds |
    When I send breeds request
    Then I check <StatusCode> of cat api correctly

    Examples: 
      | StatusCode | NumberOfCats |
      |        200 | 67						|
