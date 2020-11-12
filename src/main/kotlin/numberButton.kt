import kotlinx.browser.document
import kotlinx.css.backgroundColor
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.i
import react.dom.strong
import styled.css
import styled.styledButton
import styled.styledDiv

external interface NumberButtonProps : RProps {
    var callback: (Value) -> Unit
    var value: Value
}

data class NumberButtonState(val value: Value) : RState

@JsExport
class NumberButton(props: NumberButtonProps) : RComponent<NumberButtonProps, NumberButtonState>() {
    init {
        state = NumberButtonState(props.value)
    }

    override fun RBuilder.render() {
        styledButton {
            strong {
                +(state.value.ordinal+1).toString()
            }
            css {
                classes = mutableListOf("no-vert-padding", "btn", "btn-md", "px-3", "number-button")
            }

            attrs {
                onClickFunction = {
                    props.callback(state.value)
                }
            }
        }
    }
}