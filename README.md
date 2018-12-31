# Android Technical Assessment

For the assignment, we are building 2 screens of an android app that ask autocomplete questions. One screen will use the Google Maps address API and the other will use a local array.

## Screen 1 - Address Autocomplete

#### Empty Input
<img src="https://user-images.githubusercontent.com/1063696/48273411-d9d39f80-e40e-11e8-9931-2b8e08be2d90.jpg" width="250" />

#### Autocomplete Results
<img src="https://user-images.githubusercontent.com/1063696/48273510-1dc6a480-e40f-11e8-9174-5cf206ac9dd0.jpg" width="250" />

#### No Selection Error*
<img src="https://user-images.githubusercontent.com/1063696/48273583-4353ae00-e40f-11e8-977d-f05ab87417c6.jpg" width="250" />

Here's a sample API request to be used to gather the address predictions: `https://maps.googleapis.com/maps/api/place/autocomplete/json?input=137 Noe Street&types=address&key=AIzaSyBnMJjJXi3cyIVxzhdlYyaCG3PPQ4huF78`

Here's a sample response:

```
{
   "predictions" : [
      {
         "description" : "137 Noe Street, San Francisco, CA, USA",
         "id" : "43bfe0be3d72ecd106478160c884f2575d395ea9",
         "matched_substrings" : [
            {
               "length" : 3,
               "offset" : 0
            },
            {
               "length" : 10,
               "offset" : 4
            }
         ],
         "place_id" : "ChIJB7S4cx1-j4ARmL5Ru4d7HdU",
         "reference" : "ChIJB7S4cx1-j4ARmL5Ru4d7HdU",
         "structured_formatting" : {
            "main_text" : "137 Noe Street",
            "main_text_matched_substrings" : [
               {
                  "length" : 3,
                  "offset" : 0
               },
               {
                  "length" : 10,
                  "offset" : 4
               }
            ],
            "secondary_text" : "San Francisco, CA, USA"
         },
         "terms" : [
            {
               "offset" : 0,
               "value" : "137"
            },
            {
               "offset" : 4,
               "value" : "Noe Street"
            },
            {
               "offset" : 16,
               "value" : "San Francisco"
            },
            {
               "offset" : 31,
               "value" : "CA"
            },
            {
               "offset" : 35,
               "value" : "USA"
            }
         ],
         "types" : [ "street_address", "geocode" ]
      },
      {
         "description" : "137 Noe Street, Honolulu, HI, USA",
         "id" : "7ee86e77816e10aeeee3c9c02126792c9434d250",
         "matched_substrings" : [
            {
               "length" : 10,
               "offset" : 4
            }
         ],
         "place_id" : "EiExMzcgTm9lIFN0cmVldCwgSG9ub2x1bHUsIEhJLCBVU0E",
         "reference" : "EiExMzcgTm9lIFN0cmVldCwgSG9ub2x1bHUsIEhJLCBVU0E",
         "structured_formatting" : {
            "main_text" : "137 Noe Street",
            "main_text_matched_substrings" : [
               {
                  "length" : 10,
                  "offset" : 4
               }
            ],
            "secondary_text" : "Honolulu, HI, USA"
         },
         "terms" : [
            {
               "offset" : 0,
               "value" : "137"
            },
            {
               "offset" : 4,
               "value" : "Noe Street"
            },
            {
               "offset" : 16,
               "value" : "Honolulu"
            },
            {
               "offset" : 26,
               "value" : "HI"
            },
            {
               "offset" : 30,
               "value" : "USA"
            }
         ],
         "types" : [ "route", "geocode" ]
      },
      {
         "description" : "137 Noe Street, Pierre Part, LA, USA",
         "id" : "9e53c3d9f113bd8475490956be302871b4e52d81",
         "matched_substrings" : [
            {
               "length" : 14,
               "offset" : 0
            }
         ],
         "place_id" : "EiQxMzcgTm9lIFN0cmVldCwgUGllcnJlIFBhcnQsIExBLCBVU0EiMRIvChQKEglr8U3puFchhhFtKH7I9PkuYxCJASoUChIJa_FN6bhXIYYRZNLuQvE7lI4",
         "reference" : "EiQxMzcgTm9lIFN0cmVldCwgUGllcnJlIFBhcnQsIExBLCBVU0EiMRIvChQKEglr8U3puFchhhFtKH7I9PkuYxCJASoUChIJa_FN6bhXIYYRZNLuQvE7lI4",
         "structured_formatting" : {
            "main_text" : "137 Noe Street",
            "main_text_matched_substrings" : [
               {
                  "length" : 14,
                  "offset" : 0
               }
            ],
            "secondary_text" : "Pierre Part, LA, USA"
         },
         "terms" : [
            {
               "offset" : 0,
               "value" : "137 Noe Street"
            },
            {
               "offset" : 16,
               "value" : "Pierre Part"
            },
            {
               "offset" : 29,
               "value" : "LA"
            },
            {
               "offset" : 33,
               "value" : "USA"
            }
         ],
         "types" : [ "route", "geocode" ]
      }
   ],
   "status" : "OK"
}
```

When the user clicks next with a valid input, they will be taken to the second question.

## Screen 2 - Insurance Carriers

#### Empty Input
<img src="https://user-images.githubusercontent.com/1063696/48273624-62ead680-e40f-11e8-8229-bdf409a1ab60.jpg" width="250" />

#### Autocomplete Results
<img src="https://user-images.githubusercontent.com/1063696/48273673-7c8c1e00-e40f-11e8-9c21-0dfc48ecc729.jpg" width="250" />

#### No Selection Error*
<img src="https://user-images.githubusercontent.com/1063696/48273583-4353ae00-e40f-11e8-977d-f05ab87417c6.jpg" width="250" />

The list of possible insurance carriers are listed in the `carriers.json` file.

After clicking next, the user should be taken back to the main activity.

## Basic Workflow
<img src="https://user-images.githubusercontent.com/1063696/48276312-b102d880-e415-11e8-8111-c50ff256ff6f.png" width="250" />

## Implementation Notes
- Implementing the progress bar in the mock up images is not necessary
- Use Java or Kotlin to implement the sample app
- Please thoroughly unit test your application
- Use any architecture that you think is appropriate (MVC, MVP, VIP, MVVM etc) that will make your testing easier
- Feel free to add third party libraries to help with your implementation e.g. Retrofit, RxJava etc.
- To avoid unnecessary API calls, it would be a good idea to debounce the user input
- Push the completed assignment to a github repository and email the link to anand@cover.com and pablo@cover.com

## What we're looking for
- Can you convert written requirements into working code?
- Strong understanding of Android programming fundamentals
- How, if at all, do you leverage third party libraries?
- Is your code easy to understand and co-worker friendly?
- Is your code sufficiently tested?
- How do you separate business logic from UI