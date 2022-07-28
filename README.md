# Train Times Android App

## My Approach
- I created an app that pulls train times from one station to another. Inspired from the time boards in train stations, I wanted to display data that shows one location to another, like a train from London Victoria to Brighton for example.
- Note that this is NOT a journey planner, it doesn't create a journey but more so mimics what times would be displayed from trains available from a station.
- The app consists of two activities, first activity which is the form to input which train times the user would like to see, and the second activity displaying said information.
- Utilises the Realtimes Trains API that returns a JSON response of times using the station CRS (Computer Reservation System) code as input, so I created a function that converts the station name string in the first activity to the respective CRS code, which is sent to the API and the second activity displays the result.

## Wireframe
![Imgur](https://i.imgur.com/oVhPaGhl.png)

## Screenshots
- First Activity
![Imgur](https://i.imgur.com/Isx8A2vl.png)

- Second Activity
![Imgur](https://i.imgur.com/tuyuchpl.png)

## Technologies used
- Retrofit
- GSON Builder
- Excel
- Kotlin
- Realtimes Trains API
- XML

## Many thanks to (in no particular order):
- Ben Shapiro
- Aneta Kowalczyk
- William Da Silva
- Peter Murphy
- Jose Fernandez
- Marcin Lament
- Bryen Louis Viera Commins
- Alex Pallister