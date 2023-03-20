Create an application which will retrieve users in order that they signed up on GitHub. Our app should contain two screens:
  ● Users screen
  ● Detail user information screen
Users screen: It should display a list of users retrieved from url of public GitHub API (https://api.github.com/users). Every user item should contain such information: avatar preview and user login. By tapping on any user item should lead to Detail user information screen (It would be cool to use shared element transition animation). List should support pull-to-refresh feature, dynamic loading of new elements (https://developer.github.com/v3/users/ param “since” at the bottom of the page). Handling configuration changes such as screen rotation is required.
Detail user information screen: It should display some user information such as: avatar preview, name, url, public repositories, public gists and followers. This information you can fetch with request (https://api.github.com/users/:username). Toolbar header should display the user's name and have up navigation. By pressing url leads to detailed user information on the web.
Handling internet connection and errors are preferable for great user experience.
Note:
  ● Feel free to use third party libraries
  ● Use Retrofit for network requests
  ● Use up-to-date architectural patterns (for example Android Architecture Components)
  ● You can use Kotlin language
  ● Please, push your source code to github.com
  ● Here links for clarity:
https://drive.google.com/file/d/1Lae3WeENcLB8QfLAkwKgkRHZL_Fxik2R/view?usp= sharing 
https://drive.google.com/file/d/1ms2Qnst1wbPdjT4G8eo5ndveeAOH-K1s/view?usp=s haring
