import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("collapseOne")) {
            child(Welcome::class) {
                attrs {
                    count = 4
                    shift = false
                }
            }
        }
        render(document.getElementById("settings")) {
            child(Settings::class) {
                attrs {
                    shift = true
                    cards = 4
                }
            }
        }
    }
}
