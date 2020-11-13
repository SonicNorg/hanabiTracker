import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.i
import styled.css
import styled.styledButton

external interface UndoButtonProps : RProps {
    var cbacks: CBHolder
}

@JsExport
class UndoButton(props: UndoButtonProps) : RComponent<UndoButtonProps, RState>() {
    val callback = props.cbacks.undoCallback

    override fun RBuilder.render() {
        styledButton {
            i("fas fa-undo no-vert-padding") { }
            css {
                classes = mutableListOf("no-vert-padding", "btn", "btn-xs", "btn-success", "px-3")
            }
            attrs {
                onClickFunction = callback
            }
        }
    }
}