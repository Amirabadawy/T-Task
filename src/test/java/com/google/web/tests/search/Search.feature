Feature: Search functionality

  Scenario Outline: Search on Python Jobs in Egypt on Google
    Given website is opened
    When type search text "<searchText>" in search input in google page
    Then wuzzuf link should be displayed in result set
    When click on wuzzuf link in google page
    Then number of jobs should be greater than zero

    Examples:
      | searchText |
      | Python Jobs in Egypt    |