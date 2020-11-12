import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(Welcome::class) {
                attrs {
                    count = 5
                    shift = true
                }
            }
        }
        render(document.getElementById("settings")) {
            child(Settings::class) {
                attrs {
                    count = 5
                    shift = true
                }
            }
        }
    }
}
