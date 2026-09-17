Feature: Validatee Add Place API


@AddPlace
Scenario Outline: Validate Add Place API with post request
Given Add Place Payload with "<name>" "<address>" "<language>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And verify that place_id is created for "<name>" using "GetPlaceAPI"


Examples:
|name     | address | language|
|Sahil House |	London	| English |



@DeletePlace
Scenario: Validate Delete Place API with Post request
Given Delete Place Payload with "place_id"
When delete api is hit with "DeletePlaceAPI" with "Post" http request
Then status in response is "OK"
