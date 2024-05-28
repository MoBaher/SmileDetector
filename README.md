This is an SDK used to detect the smile ... 
-------------------------------------------
To use this sdk :
_________________
  1- add this in your build.gradle.kts file in dependancies section :
            implementation ("com.github.MoBaher:SmileDetector:1.0.6")

  2- Extend the class SdkCallback from the SDK in your activity.
            eg. 
                class StartActivity : AppCompatActivity() , SdkCallback {
                            ......

  3- add these in the activity you want to start the sdk :
                SmileDetectorSDK.Builder(this)
                .setSdkCallback(this)
                .build()
      Note that : You can add 
                  .setRectangleColorBlue(blue.isChecked)
                  .setRectangleInvisible(invisable.isChecked)
                in the builder as options  to remove the rectangle or change its color from red to blue ...  invisable and blue are checkboxes that you can create or just replace them by TRUE or FALSE directly.
  4- Implement onSuccess and onFailure methods and handle the image in the result response.   
