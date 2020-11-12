import kotlinx.css.padding
import kotlinx.css.paddingBottom
import kotlinx.css.paddingTop
import kotlinx.css.px
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.img
import styled.css
import styled.styledDiv
import styled.styledInput

external interface WelcomeProps : RProps {
    var name: String
}

data class WelcomeState(val name: String) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState(props.name)
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                paddingTop = 50.px
                paddingBottom = 50.px
            }
            for (i in 1..5) {
                styledDiv {
                    css {
                        +WelcomeStyles.unknownCard
                    }
                }
            }
        }
    }
}
