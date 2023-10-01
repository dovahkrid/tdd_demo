Feature: Login
  Perform login on phone number is inputted

  @login-feature
  Scenario Outline: Input phone number
    Given I have a Main Activity
    When I click phone number field
    And I enter valid phone number <phone_number>
    And I close the keyboard
    And I press submit button
    Then I should see a success toast

    Examples:
      | phone_number |
      | 868218516 |