# Compose-Rolling-Number

A rotating number component inspired by [Uacaps](https://github.com/uacaps/NumberTicker) written in Jetpack Compose.

<img src="https://github.com/esatgozcu/AndroidPushManagerSDK/assets/35576161/ba1fdf2f-6c44-4540-b8e9-0ad647c6d797" width="300"/>

## Installation

1- Add it in your settings.gradle - dependencyResolutionManagement

```Gradle
repositories {
      ...
      maven { url 'https://jitpack.io' }
}
```

2- Add the dependency

```Gradle
dependencies {
      implementation 'com.github.esatgozcu:Compose-Rolling-Number:1.0.1'
}
```

## Example Usage

```Kotlin

@Composable
fun Greeting() {
    val number = remember { mutableStateOf(100) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RollingNumberView(vm = RollingNumberVM(number.value.toString(), suffix = "$"))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Increase",Modifier.clickable {
            number.value = number.value + 123
        })
    }
}

```