#Author: Pham Cong
@Cat
Feature: Cat Api Test

  @MainCase
  Scenario Outline: Check response when send request successfully
    Given I have url and Method and request body of cat api
      | URL                                | RequestBodyName                  |
      | https://api.thecatapi.com/v1/votes | \\CatApi\\CatApiRequestBody.json |
    When I send cat request
    Then I check <StatusCode> of cat api correctly

    Examples: 
      | URL                                | StatusCode | RequestBodyName                  |
      | https://api.thecatapi.com/v1/votes |        201 | \\CatApi\\CatApiRequestBody.json |
