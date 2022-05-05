# Rhinoroid
[![](https://jitpack.io/v/otsuka-kohei/rhinoroid.svg)](https://jitpack.io/#otsuka-kohei/rhinoroid)
  
JavaScript evaluator for Android

## How to use
### Add dependency
#### Method 1: JitPack (Recommended)
Add this Maven repository to your root `build.gradle`.
```gradle
allprojects {
   repositories {
      maven { url 'https://jitpack.io' }
   }
}
```
Add this dependency to your module `build.gradle`.
```gradle
dependencies {
   implementation 'com.github.otsuka-kohei:rhinoroid:<version>'
}
```

#### Method 2: GitHub Packages
Add this Maven repository to your root `build.gradle`.
```gradle
allprojects {
   repositories {
      maven {
            name = "GitHubPackages-rhinoroid"
            url = uri("https://maven.pkg.github.com/otsuka-kohei/rhinoroid")
            credentials {
                username = "<Your GitHub user name>"
                password = "<Your GitHub personal access token>"
            }
        }
   }
}
```
Add this dependency to your module `build.gradle`.
```gradle
dependencies {
   implementation 'com.github.otsuka-kohei:rhinoroidd:<version>'
}
```
