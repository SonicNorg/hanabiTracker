import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.i
import styled.css
import styled.styledDiv

external interface CardProps : RProps {
    var name: Value
    var color: Color
    var chosen: Boolean
    var callback: (CardState) -> Unit
    var deleteCallback: () -> Unit
}

data class CardState(val name: Value = Value.UNKNOWN, val color: Color = Color.UNKNOWN, val chosen: Boolean = false) :
    RState

@JsExport
class Card(props: CardProps) : RComponent<CardProps, RState>() {

    override fun RBuilder.render() {
        styledDiv {
            css {
                display = Display.inlineBlock
            }
            styledDiv {
                css {
                    display = Display.block
                    textAlign = TextAlign.center
                }
                i("fas fa-trash-alt") {
                    attrs {
                        onClickFunction = {
                            props.deleteCallback()
                        }
                    }
                }
            }
            styledDiv {
                styledDiv {
                    css {
                        +WelcomeStyles.cardText
                    }
                    if (props.name == Value.UNKNOWN) {
                        +"?"
                    } else {
                        +(props.name.ordinal + 1).toString()
                    }
                }
                css {
                    +WelcomeStyles.card
                    when {
                        props.chosen -> +WelcomeStyles.chosenCard
                        props.color != Color.UNKNOWN -> backgroundColor = props.color.color
                        else -> +WelcomeStyles.unknownCard
                    }
                }
                attrs {
                    onClickFunction = {
                        props.callback(CardState(props.name, props.color, !props.chosen))
                    }
                }
            }
        }
    }
}

enum class Color(val color: kotlinx.css.Color) {
    RED(kotlinx.css.Color.crimson),
    WHITE(kotlinx.css.Color.whiteSmoke),
    BLUE(kotlinx.css.Color.dodgerBlue),
    GREEN(kotlinx.css.Color.limeGreen),
    YELLOW(kotlinx.css.Color.gold),
    UNKNOWN(kotlinx.css.Color.black);
}

enum class Value {
    ONE, TWO, THREE, FOUR, FIVE, UNKNOWN
}