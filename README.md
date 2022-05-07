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

## Trouble Shooting for Rhino issue
Some JavaScript feature (e.g. Block-scoped constants which is defined by  `const` keyword) is not supported by Rhino.  
Thus, Rhinoroid which uses Rhino can throw exceptions when import or evaluate such javaScript code doe to this reason.  
To avoid this issue, you can use Babel to convert JavaAcript code to Rhino supported code.  
One of example of method is as follows.  
  
(1) Install Node.js
```bash
sudo apt install nodejs
```

(2) Setup babel 
```bash
mkdir babel_for_rhino
cd babel_for_rhino
npm init -y
npm install --save-dev @babel/core @babel/cli @babel/preset-env
```

(3) Create a config file  
Create `babel.config.json` as follows.  
Please replace `1.7.14` to Rhino version which you use.
```json
{
    "presets": [
        [
            "@babel/preset-env",
            {
                "targets": {
                    "rhino": "1.7.14"
                }
            }
        ]
    ]
}
```

(4) Execute Babel
```bash
./node_modules/.bin/babel not_rhino_supported.js -o rhino_supported.js
```

(5) Load converted JavaScript code to Rhinoroid


## Dependencies
- [Mozilla Rhino](https://github.com/mozilla/rhino)
  - For JavaScript evaluation.
