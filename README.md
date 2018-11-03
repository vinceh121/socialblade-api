# socialblade-api
Unofficial Java API to communicate to SocialBlade.

For now, only Youtube and Twitter are implemented.

## How to use
The library can be used like any other Maven artefact, just add the Jitpack.io repository:

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

And then add the dependency:

```xml
<dependency>
    <groupId>com.github.vinceh121</groupId>
    <artifactId>socialblade-api</artifactId>
    <version>0.2.0</version>
</dependency>
```

## Example

The following example prints out the number of subscribers of pewdiepie's Youtube channel

```java
SocialBlade sb = new SocialBlade();
sb.login("Your SocialBlade username", "Your SocialBlade password");

YTStats stats = sb.statsYoutube(JOptionPane.showInputDialog("Youtuber to lookup"));
System.out.println(stats.getSubs());
```

## License

The library is released under the GNU General Public License V3.
I have no affiliation with SocialBlade.

