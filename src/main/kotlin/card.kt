import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

external interface CardProps : RProps {
    var name: String
}

data class CardState(val name: String) : RState

@JsExport
class Card(props: CardProps): RComponent<CardProps, CardState>() {
    init {
        state = CardState(props.name)
    }
    override fun RBuilder.render() {

    }
}