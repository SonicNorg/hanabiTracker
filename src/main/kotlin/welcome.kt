import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.button
import react.dom.div
import react.dom.i
import styled.css
import styled.styledDiv

external interface WelcomeProps : RProps {
    var shift: Boolean
    var count: Int
    var cbacks: CBHolder
}

data class WelcomeState(
    var shift: Boolean,
    var count: Int,
    var cards: MutableList<CardState> = defaultCards(count),
    var history: MutableList<MutableList<CardState>> = mutableListOf()
) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState(props.shift, props.count)
        props.cbacks.cardsCallback = settingsCardsCallback()
        props.cbacks.shiftCallback = settingsShiftCallback()
        props.cbacks.undoCallback = stepBack()
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
                classes = mutableListOf("row buttons-row")
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
        history.add(0, cards.map { card -> card.copy(chosen = false) }.toMutableList())
        cards.removeAt(i)
        cards.add(CardState())
    } }

    private fun colorButtonCallback(cardNums: Array<Int>) = { color: Color ->
        setState {
            history.add(0, cards.map { card -> card.copy(chosen = false) }.toMutableList())
            cards.forEachIndexed { index, i ->
                if (cardNums.contains(index)) cards[index] = i.copy(color = color, chosen = false)
            }
        }
    }
    private fun numberButtonCallback(cardNums: Array<Int>) = { value: Value ->
        console.log(cardNums)
        setState {
            history.add(0, cards.map { card -> card.copy(chosen = false) }.toMutableList())
            cards.forEachIndexed { index, i ->
                if (cardNums.contains(index)) cards[index] = i.copy(name = value, chosen = false)
            }
        }
    }

    private fun getChosen(): Array<Int> =
        state.cards.mapIndexed { index, card -> index to card }.filter { it.second.chosen }.map { it.first }.toTypedArray()

    private fun settingsCardsCallback() = { chosen: Int -> setState(WelcomeState(state.shift, chosen, defaultCards(chosen), state.history)) }

    private fun settingsShiftCallback() = { chosen: Boolean -> setState(WelcomeState(chosen, state.count, defaultCards(state.count), state.history)) }

    private fun stepBack(): (Event) -> Unit = { _ ->
        if (state.history.isNotEmpty()) {
            val last = state.history.removeFirst()
            setState(WelcomeState(state.shift, state.count, last, state.history))
        }
    }
}

private fun defaultCards(size: Int): MutableList<CardState> = generateSequence { CardState() }.take(size).toMutableList()