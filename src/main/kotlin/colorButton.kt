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
import styled.css
import styled.styledButton
import styled.styledDiv

external interface ColorButtonProps : RProps {
    var callback: (Color) -> Unit
    var color: Color
}

data class ColorButtonState(val color: Color) : RState

@JsExport
class ColorButton(props: ColorButtonProps) : RComponent<ColorButtonProps, ColorButtonState>() {
    init {
        state = ColorButtonState(props.color)
    }

    override fun RBuilder.render() {
        styledButton {
            +" "
            css {
                backgroundColor = state.color.color
                classes = mutableListOf("no-vert-padding", "btn", "btn-md", "px-3", "number-button")
            }
            attrs {
                onClickFunction = {
                    props.callback(state.color)
                }
            }
        }
    }
}