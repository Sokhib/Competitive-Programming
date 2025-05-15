# TradingView Bot

A Telegram bot that allows you to subscribe to stocks, crypto coins, and forex pairs from TradingView. The bot will
notify you when the EMA 8 crosses the price.

## Features

- Subscribe to multiple assets (stocks, crypto, forex)
- Receive notifications when EMA 8 crosses the price
- Simple command interface
- Configurable polling interval

## Prerequisites

- JDK 11 or higher
- Gradle
- Telegram account
- Telegram bot token (from BotFather)

## Setup

### 1. Create a Telegram Bot

1. Open Telegram and search for `@BotFather`
2. Start a chat with BotFather and send `/newbot`
3. Follow the instructions to create a new bot
4. Once created, BotFather will provide you with a token. Save this token for later use.

### 2. Configure the Bot

1. Copy the sample properties file:
   ```
   cp bot.properties.sample bot.properties
   ```

2. Edit `bot.properties` and fill in your bot token and username:
   ```
   bot.token=YOUR_BOT_TOKEN_HERE
   bot.username=YOUR_BOT_USERNAME_HERE
   polling.interval.minutes=5
   ```

### 3. Build the Bot

```bash
./gradlew build
```

### 4. Run the Bot

```bash
./gradlew run
```

## Usage

Once the bot is running, you can interact with it using the following commands:

- `/start` - Start the bot and get a welcome message
- `/help` - Show available commands
- `/subscribe <type> <symbol>` - Subscribe to a symbol
    - Types: stock, crypto, forex
    - Examples:
        - `/subscribe stock AAPL`
        - `/subscribe crypto BTC`
        - `/subscribe forex EURUSD`
- `/unsubscribe <symbol>` - Unsubscribe from a symbol
    - Example: `/unsubscribe AAPL`
- `/list` - List all your subscriptions

## How It Works

1. The bot periodically fetches price data for all subscribed assets
2. It calculates the 8-period Exponential Moving Average (EMA 8)
3. It checks if the EMA 8 has crossed the price since the last check
4. If a crossing is detected, it sends a notification to the subscribed users

## Technical Details

- Built with Kotlin
- Uses the Telegram Bot API for messaging
- Uses OkHttp for API requests
- Implements EMA calculation algorithm
- Caches price data to reduce API calls

## Limitations

- The current implementation uses simulated data for stocks and forex
- For crypto, it attempts to use the CoinGecko API but falls back to simulated data if the API call fails
- In a production environment, you would want to use a reliable market data API

## Future Improvements

- Add persistence for subscriptions (database)
- Implement more technical indicators
- Add customizable alert conditions
- Add support for more asset types
- Improve error handling and retry logic
- Add user authentication/authorization

## License

This project is licensed under the MIT License - see the LICENSE file for details.