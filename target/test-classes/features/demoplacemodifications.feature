Feature: Add/Update/Delete place modifications in map

@AddPlace
Scenario Outline: Verify if places can be added successfully using AddPlaceApi
Given Add place payload with "<name>" "<language>" "<latitude>" "<longitude>"
When User calls "AddPlaceAPI" using http "Post" request
Then response should be http status "200" 
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place created maps to "<name>" using "GetPlaceAPI"

Examples:

 | name 	| language  | latitude  | longitude |
 | AAHouse  | French-IN | -38.383494 | 33.427362 |
 | BBHouse  | Spanish-IN | -48.26683 | 48.427362 |
 
@DeletePlace
Scenario: Verify if delete place api is working 
Given Delete place paylod
When User calls "DeletePlaceAPI" using http "Post" request
Then response should be http status "200" 
And "status" in response body is "OK"