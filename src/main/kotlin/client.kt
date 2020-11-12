import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    val holder = CBHolder()
    window.onload = {
        render(document.getElementById("collapseOne")) {
            child(Welcome::class) {
                attrs {
                    count = 5
                    shift = true
                    cbacks = holder
                }
            }
        }

        render(document.getElementById("settings")) {
            child(Settings::class) {
                attrs {
                    shift = true
                    count = 5
                    cbacks = holder
                }
            }
        }
    }
}

class CBHolder {
    lateinit var cardsCallback: (Int) -> Unit
    lateinit var shiftCallback: (Boolean) -> Unit
}