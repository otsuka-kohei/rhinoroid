# Rhinoroid
[![](https://jitpack.io/v/otsuka-kohei/rhinoroid.svg)](https://jitpack.io/#otsuka-kohei/rhinoroid)
  
Rhinoroid is a simple wrapper of Mozilla Rhino for Android.  
Rhinoroid can evaluate JavaScript on Android.

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
   implementation 'com.github.otsuka_kohei:rhinoroid:<version>'
}
```

### Add AAPT option
To avoid error due to compressing .js file, add this option to your module `build.gradle`.
```gradle
android {
    aaptOptions {
        noCompress "js"
    }
}
```

### Put .js files into asset folder.
```
...
└── assets
    ├── a.js
    ├── b.js
    └── c
        └── c.js
```
a.js
```javascript
function funcA() {
  return "a";
}
```
b.js
```javascript
function funcB() {
  return "b";
}
```
c.js
```javascript
function funcC() {
  return "c";
}
```

### Use Rhinoroid
```kotlin
// On Android Activity

val result = Rhinoroid.open(this).use {
  it.import("a.js")
  it.import("b.js")
  it.import("c/c.js")
  it.evaluate("funcA() + funcB() + funcC();") as String
}
// abc
```


## Dependencies
- [Mozilla Rhino](https://github.com/mozilla/rhino)
  - For JavaScript evaluation.
