import kotlinx.css.paddingBottom
import kotlinx.css.paddingTop
import kotlinx.css.px
import react.*
import styled.css
import styled.styledDiv

external interface WelcomeProps : RProps {
    var shift: Boolean
    var count: Int
}

data class WelcomeState(var shift: Boolean, var count: Int) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState(props.shift, props.count)
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                paddingTop = 50.px
                paddingBottom = 50.px
            }
            for(i in 1..state.count) {
                child(Card::class) {
                    attrs {
                        color = Color.UNKNOWN
                        name = Value.UNKNOWN
                    }
                }
            }
        }
    }
}
