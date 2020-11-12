import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSelectFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.label
import react.dom.option
import react.dom.select
import styled.css
import styled.styledDiv

external interface SettingsProps : RProps {
    var shift: Boolean
    var cards: Int
    var cardsCallback: (Int) -> Unit
    var shiftCallback: (Boolean) -> Unit
}

data class SettingsState(
    val shift: Boolean,
    val cards: Int,
    val cardsCallback: (Int) -> Unit,
    val shiftCallback: (Boolean) -> Unit
) : RState

@JsExport
class Settings(props: SettingsProps) : RComponent<SettingsProps, SettingsState>() {
    init {
        state = SettingsState(props.shift, props.cards, props.cardsCallback, props.shiftCallback)
    }

    override fun RBuilder.render() {
        div("row") {
            label("col-sm-4") {
                +"Сдвиг карт в руке"
                select("browser-default custom-select") {
                    option {
                        attrs {
                            value = "1"
                        }
                        +"⇛ Слева направо ⇛"
                    }
                    option {
                        attrs {
                            value = "2"
                        }
                        +"⇚ Справа налево ⇚"
                    }
                    attrs {
                        value = if (props.shift) "1" else "2"
                        onChangeFunction = {
                            setState(SettingsState(it.target.asDynamic()["value"] as String == "1", state.cards, state.cardsCallback, state.shiftCallback))
//                            state.shiftCallback(it.target.asDynamic()["value"] as String == "1")
                        }
                    }
                }
            }
            label("col-sm-4") {
                +"Карт на руке"
                select("browser-default custom-select") {
                    option {
                        attrs {
                            value = "5"
                        }
                        +"5"
                    }
                    option {
                        attrs {
                            value = "4"
                        }
                        +"4"
                    }
                    attrs {
                        value = props.cards.toString()
                        onChangeFunction = {
                            setState(SettingsState(state.shift, (it.target.asDynamic()["value"] as String).toInt(), state.cardsCallback, state.shiftCallback))
//                            state.cardsCallback((it.target.asDynamic()["value"] as String).toInt())
                        }
                    }
                }
            }
        }
    }
}