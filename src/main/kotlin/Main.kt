import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.File
import java.io.PrintStream
import java.nio.file.Files
import java.util.*

// exiftool -all= *.jpg
// mogrify -resize 1024 *.jpg
// mogrify -resize 400 *.jpg

const val isRelease = true

fun main() {
    val folder = File("html")
    folder.mkdir()

    createIndexHtmlFile()
    minifyCss()
}

private fun createIndexHtmlFile() {
    val file = File("html/index.html")
    file.delete()
    val stream = PrintStream(file)

    stream.appendLine("<!DOCTYPE html>")
    stream.appendHTML().html {
        lang = "en"
        head {
            title("Simon Schubert")
            meta(name = "viewport", content = "width=device-width, initial-scale=1")
            link(rel = "icon", type = "image/png", href="images/icon.png")
            styleLink("stylesheet.css")
        }
        body {
            div {
                classes = setOf("header")
                div {
                    classes = setOf("background")
                }
                h1 {
                    text("Simon Schubert".uppercase())
                }
                div {
                    classes = setOf("profile-cover-wrapper")
                    div {
                        classes = setOf("profile-cover")
                    }
                }
                div {
                    classes = setOf("logos")
                    logo("https://github.com/SimonSchubert", "Github", "logo-github")
                    // logo("https://apps.apple.com/us/developer/simon-schubert/id1219649975", "Apple App Store", "logo-appstore")
                    logo("mailto:sschubert89@gmail.com", "E-Mail", "logo-email")
                }
            }
            div {
                classes = setOf("content")
                span {
                    text("A wise man whispered to me that personal blogs and websites became cool again. To be in total control of your content instead of relying on the algorithm of Facebook and instagram. Just like we had been before the facebook era.".uppercase())
                }
                p {
                    text("A little bit more about me: I live in Germany, Berlin - love bouldering, biking and traveling - develop mobile apps since ~2010 - CSS and HTML are not my super powers but lately I started enjoying building static websites - also I truly believe that the world would be a better place if we would invest more into Open Source software.".uppercase())
                }
                headline("Projects")
                div {
                    classes = setOf("projects")
                    project("project-coindodo.png", "coindodo.io", "https://coindodo.io")
                    project("project-linux.png", "Linux Command Library", "https://linuxcommandlibrary.com/")
                    project("project-quiz.jpg", "2 Player Quiz", "https://play.google.com/store/apps/details?id=com.inspiredandroid.twoplayerquizultimate&hl=en_US")
                    project("project-genocide.png", "Orc Genocide", "https://play.google.com/store/apps/details?id=com.inspiredandroid.orcgenocide&hl=en_US")
                }
                headline("Places")
                div {
                    div {
                        classes = setOf("map-legend")
                        div{
                            classes = setOf("map-hadbeen")
                            text("had been")
                        }
                        div {
                            text("want to be")
                        }
                    }
                    img {
                        classes = setOf("map")
                        src = "images/world.svg"
                        alt = "World map"
                    }
                }
                headline("Photos")
                div {
                    classes = setOf("photos")
                    photo("trip-joshua-tree", "USA - Joshua Tree", "")
                    photo("trip-boerse", "Germany - Goerlitz", "")
                    photo("trip-sicilia", "Italy - Sicilia", "")
                    photo("trip-south-france", "France - south", "photo of me in front of the sea")
                    photo("trip-antwerp", "Belgium - Antwerp", "photo of me eating fries")
                    photo("trip-morocco", "Morocco - Desert", "photo of me next to a donkey")
                    photo("trip-greece-santorini", "Greece - Santorini", "photo of me with white houses in the background")
                    photo("trip-greece-corfu", "Greece - Corfu", "photo of me diving")
                    photo("trip-portugal-lisbon", "Portugal - Lisbon", "photo of me eating ice cream in a crowded street")
                    photo("trip-italy-florence", "Italy - Florence", "photo of me and nadira with the skyline of florence in the background")
                    photo("trip-bali", "Bali", "photo of my legs with the beach and sea in the background")
                    photo("trip-usa-sanfrancisco", "USA - San Francisco", "photo of me and the golden gate bridge in the background")
                    photo("trip-iceland", "Iceland", "photo of my forehead and a geyser in the background")
                    photo("trip-turkey-cappadocia", "Turkey - Cappadocia", "photo of me and rock houses in the background")
                    photo("trip-turkey-istanbul", "Turkey - Istanbul", "photo of me and the skyline with seagulls in the background")
                    photo("trip-montenegro", "Montenegro", "photo of me with mountains, city and lake/sea in the background")
                    photo("trip-belgium-brussels", "Belgium - Brussels", "photo of me eating ice cream in front of Manneken Pis")
                    photo("trip-germany-potsdam", "Germany - Potsdam", "photo of me with a wind mill in the background")
                    photo("trip-germany-wegberg", "Germany - Wegberg", "photo of me and nadira sitting on a couch")
                    photo("trip-germany-havel", "Germany - Havel", "photo from the inside of my tent with a view to the lake")
                    photo("trip-croatia-dubrovnik", "Croatia - Dubrovnik", "photo of me in front of kings landing")
                    photo("trip-poland-warsaw", "Poland - Warsaw", "photo of me drinking water on top a tower")
                    photo("trip-finland-helsinki", "Finland - Helsinki", "photo of me eating ice cream in front of a port")
                    photo("trip-spain-calp", "Spain - Calp", "photo of me lying on the beach")
                }

                footer {
                    p {
                        text(
                            "Simon Schubert - sschubert89@gmail.com"
                        )
                    }
                }
            }
        }
    }
    stream.close()
}

fun FlowContent.photo(imageId: String, title: String, altText: String) {
    div {
        classes = setOf("photo")
        a("images/trips/$imageId.jpg") {
            target = ATarget.blank
            img {
                src = "images/trips/$imageId.jpg"
                alt = altText
                attributes["loading"] = "lazy"
            }
        }
        br
        span {
            text(title.uppercase(Locale.US))
        }
    }
}

fun FlowContent.headline(title: String) {
    val key = title.uppercase(Locale.US).replace(" ", "-")
    a("#$key") {
        id = key
        h2 {
            text(title.uppercase(Locale.US))
        }
    }
}

fun FlowContent.project(imgSrc: String, t: String, href: String) {
    div {
        classes = setOf("project")
        a(href) {
            target = ATarget.blank
            img {
                src = "images/$imgSrc"
                title = t
            }
        }
    }
}

fun FlowContent.logo(href: String, t: String, imgSrc: String) {
    a(href) {
        target = ATarget.blank
        title = t
        div {
            classes = setOf(imgSrc)
        }
    }
}

fun String.minifyCSS(): String {
    return replaceWhiteSpacesBeforeAndAfter(";")
        .replaceWhiteSpacesBeforeAndAfter("}")
        .replaceWhiteSpacesBeforeAndAfter("\\{")
        .replaceWhiteSpacesBeforeAndAfter(":")
        .replaceWhiteSpacesBeforeAndAfter(",")
}

fun String.replaceWhiteSpacesBeforeAndAfter(value: String): String {
    return replace("\\s*$value\\s*".toRegex(), value)
}

fun minifyCss() {
    val stylesheets = File("src/main/resources/stylesheets")
    stylesheets.listFiles()?.forEach {
        if (it.isFile) {
            val file = File("html", it.name)
            file.delete()
            if (isRelease) {
                val minified = it.readText().minifyCSS()
                file.writeText(minified)
            } else {
                Files.createLink(file.toPath(), it.toPath())
            }
        }
    }
}