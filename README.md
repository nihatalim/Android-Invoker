# Android-Invoker
Invoke a method independent from context

Step 1: Installation

Click this button
[![](https://jitpack.io/v/nihatalim/Android-Invoker.svg)](https://jitpack.io/#nihatalim/Android-Invoker)

In the opening page, click the GET button which latest version.
Then follow the instruction.

Step 2: Usage

a) Define a channel and unique id.

```
private static final String CHANNEL_ID = "HOME_SCREEN_STUFFS";
private static final String UNIQUE_ID_1 = "UNIQUE_1";
```

b) Put into Invoker your codes for execute when called

```
Invoker.put(UNIQUE_ID_1, CHANNEL_ID, new InvokerCallback() {
    @Override
    public void invoke(Object response) {
        // Do something here. Note that if you run on ui thread like update recycler dataset 
        // or other views you need to run this codes into runOnUiThread() function.
    }
});
```

c) Invoke registered invoke method.

You can invoke an unique into channel 

```
Invoker.invoke(UNIQUE_ID_1, CHANNEL_ID, message);
```

or you can invoke all uniques into channel

```
Invoker.invoke(CHANNEL_ID, message);
```
