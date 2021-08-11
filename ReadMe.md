# Adding Client ID & Client Secret
1. Open app layer build.gradle file
2. Search for *Enter_Your_Client_ID*, replace with your Client Id
3. Search for *Enter_Your_Clinet_Secret*, replace with your Client Secret
4. Sync project with Gradle file
5. Build and run the Application

# Approach taken.
I try to follow the **Clean Architecture** approach.
The project is this divided into different layers(modules).

- Presentation: layer that interacts with the Build
- Use cases: the actions that the use can trigger
- Domain: this are the business rules
- Data: this defines the different dat sources

# Suggested Improvements With Time and Scope
