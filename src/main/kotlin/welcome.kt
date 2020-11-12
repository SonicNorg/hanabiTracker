import kotlinx.css.*
import react.*
import react.dom.div
import styled.css
import styled.styledDiv

external interface WelcomeProps : RProps {
    var shift: Boolean
    var count: Int
}

data class WelcomeState(
    var shift: Boolean,
    var count: Int,
    var cards: MutableList<CardState> = defaultCards(count)
) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState(props.shift, props.count)
    }

    override fun RBuilder.render() {
        div("card-body") {
            styledDiv {
                css {
                    marginLeft = 15.px
                }
                for (i in if(!state.shift) 0 until state.count else (state.count - 1) downTo 0) {
                    child(Card::class) {
                        attrs {
                            chosen = state.cards[i].chosen
                            name = state.cards[i].name
                            color = state.cards[i].color
                            callback = cardCallback(i)
                            deleteCallback = cardDelCallback(i)
                        }
                    }
                }
            }
        }

        styledDiv {
            css {
                marginBottom = 20.px
                classes = mutableListOf("row")
            }
            div("col-sm-6") {
                Color.values().forEach {
                    if (it != Color.UNKNOWN) {
                        child(ColorButton::class) {
                            attrs {
                                callback = colorButtonCallback(getChosen())
                                color = it
                            }
                        }
                    }
                }
            }
            div("col-sm-6") {
                Value.values().forEach {
                    if (it != Value.UNKNOWN) {
                        child(NumberButton::class) {
                            attrs {
                                callback = numberButtonCallback(getChosen())
                                value = it
                            }
                        }
                    }
                }
            }
        }
    }

    private fun cardCallback(i: Int): (CardState) -> Unit = { st: CardState -> setState { cards[i] = st } }

    private fun cardDelCallback(i: Int): () -> Unit = { setState {
        cards.removeAt(i)
        cards.add(CardState())
    } }

    private fun colorButtonCallback(cardNums: Array<Int>) = { color: Color ->
        setState {
            cards.forEachIndexed { index, i ->
                if (cardNums.contains(index)) cards[index] = i.copy(color = color, chosen = false)
            }
        }
    }
    private fun numberButtonCallback(cardNums: Array<Int>) = { value: Value ->
        console.log(cardNums)
        setState {
            cards.forEachIndexed { index, i ->
                if (cardNums.contains(index)) cards[index] = i.copy(name = value, chosen = false)
            }
        }
    }

    private fun getChosen(): Array<Int> =
        state.cards.mapIndexed { index, card -> index to card }.filter { it.second.chosen }.map { it.first }.toTypedArray()

}

private fun defaultCards(size: Int): MutableList<CardState> = generateSequence { CardState() }.take(size).toMutableList()