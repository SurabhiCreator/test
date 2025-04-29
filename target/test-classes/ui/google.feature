Feature: Launch Google

* configure driver = { type: 'chromedriver', start: 'true' }


Scenario: Open Google homepage
    Given driver 'https://www.google.com'
    Then match driver.title contains 'Google'