# App Introduction
* This app will find you random_trip_mate if you wanna go for a impulsive trip with random people!

# Watch out!
* Recommend you do enough chat before you meet them if you can't trust them yet.

# EndPoint that will be used and overall flow
* At the first view, you can choose CategoryActivity or CityActivity.
* Main Activity separates with two parts. 1. CategoryActivity & 2. CityActivity. When the category is clicked, it executes the "/search" query.

1. (CategoryActivity) "/search" is used. (there are many options, but in this app with category id input, show the restaurants list related with the category)
2. (CityActivity) "/collections" is used. (with city id input, show several collections in the city)

* (CategoryActivity/CityActivity) --> When restaurant is clicked --> (RestaurantActivity) "/restaurant" is used. (with restaurant id input, show restaurant detailed information)


# Features which are gonna be added later
* GoogleMap Api function for checking each other's coordinate synchronously.
