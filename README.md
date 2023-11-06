# Android MVVM Architecture Sample (ViewModel + LiveData + Kotlin + Retrofit) - Weather App

MVVM Architecture is one of the most popular and latest architecture to develop a maintainable and manageable codebase. We are developing a sample `Weater Forecast` Android App with `MVVM Architecture` using `Kotlin` language and `Retrofit` network calling library. **For simplification, I didn't use `Dagger` or `Rx` in this project.** After completion of this repository, if you want to learn about `Dagger` implementation; please check [this repository for MVVM and Dagger implementation](https://github.com/hasancse91/weather-app-android-mvvm-dagger).

### Prerequisites
Basic `Kotlin`, `Coroutines` and knowledge of `HTTP` request by `Retrofit` library are required for this project.

### Project Description
We will develop a weather forecast Android Application with MVVM architecture. The UI you can implement as per your imagination. There is a `Home Screen` with some weather information of your current location(USer Permission Required). There is a 'Search' option in the navigation drawer, where you can search for 'City' by its name and click on desired city to show its information. Then App will send request to Open Weather web API and show the weather information in the UI.

### Open Weather API
We will use [Open Weather Map API](https://openweathermap.org/api) for collecting weather information. To get the real weather information of a city, you need to sign up and get your own `APP ID`. Otherwise you can test the API with their sample `BASE URL` and sample `APP ID` without creating account.

### Project Setup
Clone the project and open it using Android Studio. Use your own API key as number of requests are limited. 

#### Use Sample API without creating account
Add below lines at the end of your `local.properties` file. Then run the project. You'll get dummy or static API response from Open Weather API.
```properties
#this is sample Base URL
base_url=https://samples.openweathermap.org/data/2.5/

#this is sample App ID of Open Weather API
app_id=b6907d289e10d714a6e88b30761fae22
```
#### Use Real APP ID after sign up and activation of your APP ID
After Sign up at the website collect your own `APP ID` from their [API Keys page](https://home.openweathermap.org/api_keys). Then add below lines with your APP ID at the end of `local.properties` file.
```properties
#this is real Base URL
base_url=http://api.openweathermap.org/data/2.5/

#this is real App ID of Open Weather API
app_id=YOUR_OWN_APP_ID
```
#### I've used following github repo as reference for building this project :
[Open_GIT_REPO](https://github.com/hasancse91/weather-app-android-mvvm/)
**Note:** The free version of Open Weather API allows maximum 60 API calls per minute.
### Run the project
Sync the `Gradle` and run the project. Install APK on your emulator or real device. Turn on the internet of your testing device. For better understanding, I'll try to add comments of every methods.
