# Yayang Setiyawan - 18090045 - 5D

project Membuat Crud Android Menggunakan Room Database

Team = Solo

Menambahkan dependencies di build.gradle(:app) && plugins

     plugins {
          id 'com.android.application'
          id 'kotlin-android'
          id 'kotlin-kapt'}
     
    dependencies {

      implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
      implementation 'androidx.core:core-ktx:1.3.2'
      implementation 'androidx.appcompat:appcompat:1.2.0'
      implementation 'com.google.android.material:material:1.2.1'
      implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
      implementation 'androidx.navigation:navigation-fragment-ktx:2.3.1'
      implementation 'androidx.navigation:navigation-ui-ktx:2.3.1'
      testImplementation 'junit:junit:4.+'
      androidTestImplementation 'androidx.test.ext:junit:1.1.2'
      androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
      implementation 'org.koin:koin-androidx-viewmodel:2.0.1'
      def room_version = "2.2.5"
      implementation "androidx.room:room-runtime:$room_version"
      kapt "androidx.room:room-compiler:$room_version"
      implementation "com.airbnb.android:lottie:3.4.4"
    }
