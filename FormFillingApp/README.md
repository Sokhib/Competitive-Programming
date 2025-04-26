# Form Filling App with MVI Architecture

This is a simple form filling app built with Jetpack Compose and MVI architecture. The app has two screens:

1. Login screen - where users enter their username and password
2. Welcome screen - which displays a welcome message with the username

## MVI Architecture Explained (ELI5)

MVI stands for Model-View-Intent, and it's a way to organize your app's code to make it more predictable and easier to
test.

### Intent

**What is an Intent?**

An Intent is like telling someone what you want to do. In our app, Intents are actions that users take or events that
happen.

**What should be included in Intent?**

- **User actions**: Things the user does, like clicking a button, typing text, or swiping
- **System events**: Things that happen automatically, like a timer finishing or data loading
- **Navigation requests**: When the user wants to go to a different screen

**Examples from our app:**

- `LoginIntent.UpdateUsername`: When the user types in the username field
- `LoginIntent.SubmitLogin`: When the user clicks the login button
- `WelcomeIntent.NavigateBack`: When the user wants to go back to the login screen

### State

**What is a State?**

State is like a snapshot of what the app looks like at a specific moment. It contains all the information needed to
display the UI.

**What should be included in State?**

- **UI data**: Text to display, whether something is visible or hidden
- **Form values**: What the user has typed in fields
- **Validation status**: Whether inputs are valid or have errors
- **Loading indicators**: Whether something is loading or ready

**Examples from our app:**

- `LoginState.username`: The text in the username field
- `LoginState.isUsernameError`: Whether there's an error with the username
- `LoginState.isLoading`: Whether the login process is happening
- `WelcomeState.username`: The username to display on the welcome screen

### Effect

**What is an Effect?**

An Effect is something that happens once and doesn't change how the app looks. It's like a side effect of an action.

**What should be included in Effect?**

- **Navigation**: Going to a different screen
- **Showing messages**: Displaying a toast or snackbar
- **One-time events**: Playing a sound, vibrating the phone
- **External actions**: Saving to a file, sending an email

**Examples from our app:**

- `LoginEffect.NavigateToWelcome`: Go to the welcome screen
- `LoginEffect.ShowError`: Show an error message
- `WelcomeEffect.NavigateBack`: Go back to the login screen

## How MVI Works Together

1. The **View** (UI) displays the current **State** and sends **Intents** when the user does something
2. The **ViewModel** receives **Intents**, updates the **State**, and emits **Effects**
3. The **View** observes changes to the **State** and updates the UI
4. The **View** also observes **Effects** and performs one-time actions

This cycle creates a unidirectional data flow that makes the app more predictable and easier to debug.

## Validation

The app includes a flexible validation system that allows for different types of validation with custom error messages:

- `NotBlankValidator`: Ensures a field is not empty
- `MinLengthValidator`: Ensures a field has a minimum length
- `EmailValidator`: Ensures a field contains a valid email address
- `PatternValidator`: Ensures a field matches a specific pattern

These validators can be combined using the `ValidationManager` to create complex validation rules.

## Navigation

The app uses Jetpack Compose Navigation with custom animations between screens:

- Sliding in from the right when navigating forward
- Sliding in from the left when navigating backward

## Testing

The app includes both unit tests and UI tests:

- Unit tests for the ViewModels and validation logic
- UI tests for the screens and navigation