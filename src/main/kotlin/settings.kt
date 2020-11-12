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
    init {
        state = WelcomeState(props.shift, props.count)
    }

    override fun RBuilder.render() {
        div("row") {
            label("col-sm-4") {
                +"Сдвиг карт в руке"
                select("browser-default custom-select") {
                    option {
                        attrs {
                            selected = props.shift
                            value = "1"
                        }
                        + "⇛ Слева направо ⇛"
                    }
                    option {
                        attrs {
                            selected = !props.shift
                            value = "2"
                        }
                        + "⇚ Справа налево ⇚"
                    }
                }
            }
            label("col-sm-4") {
                +"Карт на руке"
                select("browser-default custom-select") {
                    option {
                        attrs {
                            selected = props.count == 5
                            value = "5"
                        }
                        + "5"
                    }
                    option {
                        attrs {
                            selected = props.count == 4
                            value = "4"
                        }
                        + "4"
                    }
                }
            }
        }
    }
}