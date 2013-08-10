# LibGDX Server

This project is for running a libGDX instance that has Files and Net implemented, and Graphics/Audio/Input disabled.

This allows for the sharing of code and resources across server and client.

## Usage:
```java
import com.badlogic.gdx.ApplicationListener;
import net.pevnostgames.carpethorn.core.asset.MapAssetLoader;
import net.pevnostgames.carpethorn.core.game.Game;
import net.pevnostgames.carpethorn.core.tile.TileMap;
import net.pevnostgames.lwjglserver.ServerApplication;

public class TestServerApp implements ApplicationListener {

  /**
   * The frequency to call the update loop at in seconds.
   * AKA 60 times per second.
   */
  public static float frequency = 1.0F / 60;

  /**
   * Standard main method, showing how to create the server application.
   */
  public static void main(String[] args) {
		new ServerApplication(new TestServerApp(), frequency);
  }

  /**
   * This is called once upon creation
   */
  @Override
  public void create() {
  }

  /**
   * This is called at the specified frequency
   */
  @Override
  public void render() {
  }

  /**
   * Unused
   */
  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void dispose() {
  }
}

```

## Maven:
```
  <repository>
    <id>pevnostgames</id>
  	<url>http://repo.pevnostgames.net</url>
  </repository>

  <dependency>
    <groupId>net.pevnostgames</groupId>
    <artifactId>libgdxserver</artifactId>
    <version>1.0.0</version>
  </dependency>
```
## Pull Request Guidelines:
* Please follow my formatting as best you can
* Add Apache header to new files
* Sign-off commits using -s on the commit.
