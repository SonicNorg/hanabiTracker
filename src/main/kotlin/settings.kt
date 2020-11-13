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

@JsExport
class Settings(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>() {
    val shiftCallback = props.cbacks.shiftCallback
    val cardsCallback = props.cbacks.cardsCallback

    init {
        state = WelcomeState(props.shift, props.count)
    }

    override fun RBuilder.render() {
        div("row") {
            label("col-sm-5 col-md-4") {
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
                        value = if (state.shift) "1" else "2"
                        onChangeFunction = {
                            setState(WelcomeState(it.target.asDynamic()["value"] as String == "1", state.count))
                            shiftCallback(it.target.asDynamic()["value"] as String == "1")
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
                        value = state.count.toString()
                        onChangeFunction = {
                            setState(WelcomeState(state.shift, (it.target.asDynamic()["value"] as String).toInt()))
                            cardsCallback((it.target.asDynamic()["value"] as String).toInt())
                        }
                    }
                }
            }
        }
    }
}