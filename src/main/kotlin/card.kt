import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

external interface CardProps : RProps {
    var name: Value
    var color: Color
}

data class CardState(val name: Value = Value.UNKNOWN, val color: Color = Color.UNKNOWN) : RState

@JsExport
class Card(props: CardProps) : RComponent<CardProps, CardState>() {
    init {
        state = CardState(props.name, props.color)
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +WelcomeStyles.unknownCard
            }
        }
    }
}

enum class Color {
    RED, WHITE, BLUE, GREEN, YELLOW, UNKNOWN
}

enum class Value {
    ONE, TWO, THREE, FOUR, FIVE, UNKNOWN
}