### README
Stripa Survivour (SS) game for the course TDT4220

Contributors:
* Henrik Udnes
* Kristian Hansen
* Lars Sorensen
* Vegard Nyeng
* Erlend Haakegaard
* Christopher Løkken

### HOW TO RUN

1. git clone https://github.com/collinlokken/tdt4220.git
2. Open project folder in Android Studio (File -> Open...)
3. Run on android emulator. If you don't have one, create a new one (Tools -> AVD Manager -> Create Virtual Device)

### PROJECT STRUCTURE

tdt4240 -> core -> src -> com -> mygdx.game:
#### Controller
 * modal
 * Controller
 * ControllerManager
 * GameController
 * GameOverController
 * HelpController
 * LeaderboardController
 * LoginController
 * MainMenuController
 * RegisterController
#### model -> game:
* component
   * BoostComponent
   * Component
   * CooldownDurationComponent
   * CoronaVirusShieldComponent
   * CoronaVirusShieldRewardComponent
   * DamageComponent
   * GravityComponent
   * HealingComponent
   * HealthComoponent
   * HitbooxComponent
   * LinearlyTimeDependentAccelerationComponent
   * PositionComponent
   * ScoreComponent
   * ScoreRewardComponent
   * ShieldComponent
   * ShieldConsumerComponent
   * ShieldRewardComponent
   * StandShieldComponent
   * StandShieldRewardComponent
   * VelocityComponent
* entity
 * CoffeeCup
 * CoinItem
 * CoronaVirus
 * CoronaVirusShield
 * Entity
 * LifePointItem
 * Player
 * Stand
* System
 * AbstractSystem
 * HealthSystem
 * PhysicsSystem
 * ScoreSystem
 * ShieldSystem
 * SpawnSystem


* view
* CoreInterfaceClass
* FireBaseInterface
* StripaSurvivor
