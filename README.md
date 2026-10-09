# Ultimate Blackjack

A single-player blackjack table written in Java Swing. You start with 100
chips, place bets with 1/5/25/100/500 chips, and play against the dealer with
hit, stay, double and insurance. A chat panel at the bottom logs every hand.

This project accompanies a YouTube tutorial on building a blackjack game in
Java.

> **Following the tutorial?** The code shown in the videos (the original 2019
> IntelliJ project layout, before the Gradle build and rule fixes) is preserved
> at the git tag **`tutorial-2019`**:
>
> ```bash
> git clone https://github.com/mpgray/Ultimate-Blackjack.git
> cd Ultimate-Blackjack
> git checkout tutorial-2019
> ```

## Requirements

- JDK 21 (the Gradle wrapper downloads Gradle itself; Gradle's toolchain
  support will use an installed JDK 21).

## Build, run, test

```bash
./gradlew run      # start the game
./gradlew test     # run the rules tests (headless, no window opens)
./gradlew build    # compile, test and build the jar
```

The runnable jar is written to `build/libs/` and works from any directory,
since the card and chip images are packaged inside it:

```bash
java -jar build/libs/ultimate-blackjack-0.4.0.jar
```

`./gradlew installDist` produces a launcher script under
`build/install/ultimate-blackjack/bin/`.

There is one entry point, `blackjack.Main`. The game is local only: the chat
panel echoes what you type into the game log; there is no network client or
server.

## House rules

- Blackjack pays 3:2; a blackjack against a dealer blackjack pushes.
- The dealer hits soft 17 and stands on hard 17 or more.
- A dealer blackjack beats every other hand, including a three-card 21.
- Double down on your first two cards (one more card, bet doubled).
- Insurance is offered when the dealer shows an ace; it costs half your bet
  and pays 2:1 if the dealer has blackjack.
- Splitting is not implemented (the button is shown but disabled).

## Layout

```
src/main/java/blackjack/          game model and rules (Rules.java holds the pure scoring logic)
src/main/java/blackjack/views/    Swing UI
src/main/resources/cards, chips   images loaded from the classpath
src/test/java/blackjack/          JUnit 5 tests
art/chips/                        source artwork not used at runtime
```

## License

MIT, see [LICENSE](LICENSE).
