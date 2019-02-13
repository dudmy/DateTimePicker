# DateTimePicker

[![License](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/dudmy/DateTimePicker/blob/master/LICENSE)
[![Download](https://img.shields.io/bintray/v/dudmy/maven/datetimepicker.svg?colorB=yellow&style=flat)](https://bintray.com/dudmy/maven/datetimepicker)

An android library to select dates and times from a single screen.

<img src="https://github.com/dudmy/DateTimePicker/blob/master/screenshot-1.gif" width="270" height="480"> <img src="https://github.com/dudmy/DateTimePicker/blob/master/screenshot-2.gif" width="270" height="480">

## How to use

Add a dependency to `build.gradle`.

```groovy
dependiencies {
    implementation 'net.dudmy:datetimepicker:1.0.0'
}
```

Use as shown below, or refer to the sample code provided [here](https://github.com/dudmy/DateTimePicker/blob/master/app/src/main/java/net/dudmy/datetimepickersample/MainActivity.kt).

```xml
<net.dudmy.datetimepicker.DateTimePicker
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

```kotlin
val dateTimePicker = DateTimePicker(it.context)
dateTimePicker.setMinDate(minDate)
dateTimePicker.setMaxDate(maxDate)
dateTimePicker.setOnDateTimeChangeListener(object : OnDateTimeChangeListener {
    override fun onDateTimeChanged(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int) {
        // do something.
    }
})
```

## License

```
Copyright 2019 Yujin Jung

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
